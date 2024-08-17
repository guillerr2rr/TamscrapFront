package com.tamscrap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamscrap.model.Cliente;
import com.tamscrap.model.Pedido;
import com.tamscrap.model.Producto;
import com.tamscrap.repository.ClienteRepo;
import com.tamscrap.repository.PedidoRepo;
import com.tamscrap.repository.ProductoRepo;
import com.tamscrap.service.impl.ClienteServiceImpl;
import com.tamscrap.service.impl.PedidoServiceImpl;
import com.tamscrap.service.impl.ProductoServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class CarritoController {

    private final PedidoRepo pedidoRepo;
    private final ProductoRepo productRepo;
    private final ClienteRepo userRepo;
    private final HttpSession session;

    private final ProductoServiceImpl productoService;

    private final PedidoServiceImpl pedidoService;

    private final ClienteServiceImpl clienteService;

    public CarritoController(PedidoRepo pedidoRepo, ProductoRepo productRepo, ClienteRepo userRepo, HttpSession session, ProductoServiceImpl productoService, PedidoServiceImpl pedidoService, ClienteServiceImpl clienteService) {
        this.pedidoRepo = pedidoRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.session = session;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
    }
    @GetMapping(value = { "", "/" })
    public String mostrarPedidos(Model model) {
        List<Producto> productos = productoService.obtenerTodos();
        List<Cliente> clientes = clienteService.obtenerTodos();
        List<Pedido> lista = pedidoService.obtenerTodos();

        model.addAttribute("listaproductos", productos);
        model.addAttribute("clientes", clientes);
        model.addAttribute("pedidos", lista);
        model.addAttribute("pedidoNuevo", new Pedido());
        model.addAttribute("pedidoMostrar", new Pedido());
        model.addAttribute("nombreNuevo", "");
        model.addAttribute("productos", productos);
        model.addAttribute("productoNuevo", new Producto());
        return "pedidos/listarPedidos";
    }





    @GetMapping("/carrito")
    public String showCarrito(Model model) {
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("carrito_productos"))
                .orElse(new ArrayList<>());
        model.addAttribute("productos", productRepo.findAllById(productIds));
        return "carrito";
    }

    @GetMapping("/carrito/add/{id}")
    public String addProductToCarrito(@PathVariable Long id) {
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("carrito_productos"))
                .orElse(new ArrayList<>());

        if (!productIds.contains(id)) {
            productIds.add(id);
        }
        session.setAttribute("carrito_productos", productIds);
        return "redirect:/carrito";
    }

    @GetMapping("/carrito/remove/{id}")
    public String removeProductFromCarrito(@PathVariable Long id) {
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("carrito_productos"))
                .orElse(new ArrayList<>());

        productIds.removeIf(productId -> productId.equals(id));
        session.setAttribute("carrito_productos", productIds);
        return "redirect:/carrito";
    }

//    @PostMapping("/carrito/checkout")
//    public String checkoutCarrito() {
//        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("carrito_productos"))
//                .orElse(new ArrayList<>());
//        List<Producto> productos = productRepo.findAllById(productIds);
//
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Cliente cliente = userRepo.findByUsername(username).orElseThrow();
//
//        Pedido pedido = new Pedido(LocalDateTime.now(), cliente);
//        pedidoRepo.save(pedido);
//
//        for (Producto producto : productos) {
//            pedido.addProducto(producto, 1); // Utiliza el método addProducto del modelo Pedido
//        }
//
//        productRepo.saveAll(productos); // Guarda los productos si ha habido cambios
//        pedidoRepo.save(pedido); // Guarda el pedido actualizado
//
//        session.removeAttribute("carrito_productos");
//        return "redirect:/pedidos/" + pedido.getId();
//    }

    @GetMapping("/carrito/checkout")
    public String crearPedido(Model model) {
        List<Producto> productos = productoService.obtenerTodos();
        List<Cliente> clientes = clienteService.obtenerTodos();

        Pedido pedido = new Pedido();
        model.addAttribute("listaproductos", productos);
        model.addAttribute("clientes", clientes);
        model.addAttribute("pedidoNuevo", pedido);
        return "pedidos/crearPedido";
    }

    @PostMapping("/carrito/checkout")
    public String agregarPedido(@ModelAttribute("pedidoNuevo") Pedido pedido, BindingResult bindingResult,
                                @RequestParam("productos") String[] productoIds) {
        if (productoIds != null) {
            for (String productoId : productoIds) {
                Producto producto = productoService.obtenerPorId(Long.parseLong(productoId));
                pedido.addProducto(producto, 1);
            }
        }
        pedido.calcularPrecio();

        pedidoService.insertarPedido(pedido);

        return "redirect:/pedidos";
    }

    @GetMapping("/carrito/crearPedido")
    public String crearPedidoDesdeCarrito(Model model) {
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("carrito_productos"))
                .orElse(new ArrayList<>());
        List<Producto> productos = productRepo.findAllById(productIds);
        List<Cliente> clientes = userRepo.findAll();

        Pedido pedido = new Pedido();
        model.addAttribute("listaproductos", productos);
        model.addAttribute("clientes", clientes);
        model.addAttribute("pedidoNuevo", pedido);
        return "pedidos/crearPedido";
    }

    @ModelAttribute("carrito_total")
    public Double calculateCarritoTotal() {
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("carrito_productos"))
                .orElse(new ArrayList<>());
        List<Producto> productos = productRepo.findAllById(productIds);
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
}
