package com.Tamscrap.Tamscrap.controller;

import com.Tamscrap.Tamscrap.Model.Cliente;
import com.Tamscrap.Tamscrap.Model.Pedido;
import com.Tamscrap.Tamscrap.Model.Producto;
import com.Tamscrap.Tamscrap.Model.ProductosPedidos;
import com.Tamscrap.Tamscrap.repository.ClienteRepo;
import com.Tamscrap.Tamscrap.repository.PedidoRepo;
import com.Tamscrap.Tamscrap.repository.ProductoRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarritoController {

    private final PedidoRepo pedidoRepo;

    private final ProductoRepo productRepo;

    private final ClienteRepo userRepo;

    private final HttpSession session;

    public CarritoController(PedidoRepo pedidoRepo, ProductoRepo productRepo, ClienteRepo userRepo, HttpSession session) {
        this.pedidoRepo = pedidoRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.session = session;
    }

    @GetMapping("/carrito")
    public String show(Model model) {
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("carrito_productos"))
                .orElse(new ArrayList<>());
        model.addAttribute("productos", productRepo.findAllById(productIds));
        return "carrito";
    }

    @GetMapping("/carrito/add/{id}")
    public String add(@PathVariable Long id) {
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("carrito_productos"))
                .orElse(new ArrayList<>());

        if (!productIds.contains(id)) {
            productIds.add(id);
        }
        session.setAttribute("carrito_productos", productIds);
        return "redirect:/carrito";
    }

    @GetMapping("/carrito/remove/{id}")
    public String remove(@PathVariable Long id) {
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("carrito_productos"))
                .orElse(new ArrayList<>());

        productIds.removeIf(productId -> productId.equals(id));
        session.setAttribute("carrito_productos", productIds);
        return "redirect:/carrito";
    }

    @GetMapping("/carrito/checkout")
    public String checkout() {
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("carrito_productos"))
                .orElse(new ArrayList<>());
        List<Producto> productos = productRepo.findAllById(productIds);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente user = userRepo.findByUsername(username).orElseThrow();

        Pedido pedido = new Pedido(LocalDateTime.now(), user);

        pedidoRepo.save(pedido);


        for (Producto producto : productos) {

            ProductosPedidos productosPedidos = new ProductosPedidos(producto, pedido, 1);

            pedido.getProductos().add(productosPedidos);

            producto.getPedidos().add(productosPedidos);
        }

        productRepo.saveAll(productos);
        pedidoRepo.save(pedido);

        session.removeAttribute("carrito_productos");
        return "redirect:/pedidos/" + pedido.getId();
    }


    @ModelAttribute("carrito_total")
    public Double calculateTotal() {
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("carrito_productos"))
                .orElse(new ArrayList<>());
        List<Producto> productos = productRepo.findAllById(productIds);
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
}
