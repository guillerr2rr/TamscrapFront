package com.tamscrap.controller;

import java.util.List;
import java.util.Set;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tamscrap.model.Cliente;
import com.tamscrap.model.Pedido;
import com.tamscrap.model.Producto;
import com.tamscrap.model.ProductosPedidos;
import com.tamscrap.service.impl.ClienteServiceImpl;
import com.tamscrap.service.impl.PedidoServiceImpl;
import com.tamscrap.service.impl.ProductoServiceImpl;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private final ProductoServiceImpl productoService;

	private final PedidoServiceImpl pedidoService;

	private final ClienteServiceImpl clienteService;

	public PedidoController(ProductoServiceImpl productoService, PedidoServiceImpl pedidoService,
			ClienteServiceImpl clienteService) {
		this.productoService = productoService;
		this.pedidoService = pedidoService;
		this.clienteService = clienteService;
	}

	@PostMapping("/api/pedidos")
	public Pedido guardarPedidos(@RequestBody Pedido pedidos) {
		pedidoService.insertarPedido(pedidos);
		return pedidos;
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
		return "pedidos/listarPedidos";
	}

	@GetMapping("/add")
	public String crearPedido(Model model) {
		List<Producto> productos = productoService.obtenerTodos();
		List<Cliente> clientes = clienteService.obtenerTodos();

		Pedido pedido = new Pedido();
		model.addAttribute("listaproductos", productos);
		model.addAttribute("clientes", clientes);
		model.addAttribute("pedidoNuevo", pedido);
		return "pedidos/crearPedido";
	}

	@PostMapping("/add")
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

	@GetMapping(value = { "/edit/{id}", "{id}" })
	public String editarPedido(@PathVariable Long id, Model model) {

		Pedido pedido = pedidoService.obtenerPorId(id);
		List<Cliente> clientes = clienteService.obtenerTodos();
		List<Producto> listaproductos = productoService.obtenerTodos();
		ProductosPedidos productosPedidosNew = new ProductosPedidos(new Producto(), new Pedido(), 1);

		model.addAttribute("pedidoMostrar", pedido);
		model.addAttribute("clientes", clientes);
		model.addAttribute("listaproductos", listaproductos);
		model.addAttribute("pedidoNuevo", productosPedidosNew);

		return "pedidos/editarPedido";
	}

	@PostMapping("/edit/{id}")
	public String actualizarPedido(@PathVariable Long id, @ModelAttribute Pedido pedido,
			@RequestParam("cantidad") Integer[] cantidades, Model model, BindingResult bindingResult) {

		Pedido pedidoExistente = pedidoService.obtenerPorId(id);
		Cliente cliente = clienteService.obtenerPorId(pedido.getCliente().getId());
		pedidoExistente.setCliente(cliente);

		Set<ProductosPedidos> productosPedidos = pedidoExistente.getProductos();
		ProductosPedidos[] productosArray = productosPedidos.toArray(new ProductosPedidos[0]);

		// Actualizar las cantidades y eliminar los ProductosPedidos con cantidad <= 0
		for (int i = 0; i < Math.min(productosArray.length, cantidades.length); i++) {
			ProductosPedidos productoPedido = productosArray[i];
			if (cantidades[i] > 0) {
				productoPedido.setCantidad(cantidades[i]);
			} else {
				pedidoExistente.removeProducto(productoPedido.getProducto());
			}
		}
		pedidoService.insertarPedido(pedidoExistente);

		model.addAttribute("clientes", clienteService.obtenerTodos());
		model.addAttribute("listaproductos", productoService.obtenerTodos());
		model.addAttribute("pedidos", pedidoService.obtenerTodos());
		model.addAttribute("pedidoNuevo", new Pedido());

		return "redirect:/pedidos";
	}

	@PostMapping("/addProducto/{id}")
	public String agregarProducto(@PathVariable Long id, @RequestParam("producto.id") Long idProducto,
			@RequestParam("cantidad") int cantidad) {

		Pedido pedidoExistente = pedidoService.obtenerPorId(id);
		Producto producto = productoService.obtenerPorId(idProducto);

		ProductosPedidos productoPedidoExistente = null;
		for (ProductosPedidos bp : pedidoExistente.getProductos()) {
			if (bp.getProducto().getId().equals(idProducto)) {
				productoPedidoExistente = bp;
				break;
			}
		}

		int nuevaCantidad = cantidad;

		if (productoPedidoExistente != null) {
			nuevaCantidad += productoPedidoExistente.getCantidad();
			productoPedidoExistente.setCantidad(nuevaCantidad);
			pedidoExistente.addProducto(producto, nuevaCantidad);
		} else {

			pedidoExistente.addProducto2(producto, nuevaCantidad);

		}

		pedidoExistente.calcularPrecio();
		pedidoService.insertarPedido(pedidoExistente);
		return "redirect:/pedidos";
		// return "redirect:/pedidos/edit/" + id;
	}

	@GetMapping("/removeProducto/{pedidoId}/{productoId}")
	public String removeProducto(@PathVariable Long pedidoId, @PathVariable Long productoId, Model model) {
		Pedido pedido = pedidoService.obtenerPorId(pedidoId);
		Producto producto = productoService.obtenerPorId(productoId);

		pedido.removeProducto(producto);
		pedidoService.insertarPedido(pedido);
		return "redirect:/pedidos";
	}
//
//	@GetMapping("/{id}")
//	public String mostrarPedido(@PathVariable Long id, Model model) {
//		Pedido pedido = pedidoService.obtenerPorId(id);
//		Set<ProductosPedidos> listaProductos = pedido.getProductos();
//		Cliente cliente = pedido.getCliente();
//
//		model.addAttribute("pedidoMostrar", pedido);
//		model.addAttribute("clientes", cliente);
//		model.addAttribute("listaproductosPedidos", listaProductos);
//
//		List<Producto> productos = productoService.obtenerTodos();
//		model.addAttribute("listaproductos", productos);
//
//		ProductosPedidos productosPedidosNew = new ProductosPedidos(new Producto(), new Pedido(), 1);
//		model.addAttribute("pedidoNuevo", productosPedidosNew);
//
//		return "pedidos/pedido";
//	}

	@GetMapping("/delete/{id}")
	public String eliminarPedido(@PathVariable Long id) {
		pedidoService.eliminarPedido(id);
		return "redirect:/pedidos";
	}

}
