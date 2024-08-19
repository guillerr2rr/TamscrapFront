package com.tamscrap.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamscrap.model.Producto;
import com.tamscrap.service.impl.ProductoServiceImpl;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	private final ProductoServiceImpl productoService;
	private static final Logger logger = Logger.getLogger(ProductoController.class.getName());

	public ProductoController(ProductoServiceImpl productoService) {
		this.productoService = productoService;
	}

	@PostMapping("/api/productos")
	public Producto guardarProducto(@RequestBody Producto producto) {
		logger.log(Level.INFO, "Producto recibido: {0}", producto);
		productoService.insertarProducto(producto);
		return producto;
	}

	@GetMapping({ "", "/" })
	public String listarProductos(Model model) {
		List<Producto> productos = productoService.obtenerTodos();
		model.addAttribute("productos", productos);
		model.addAttribute("productoNuevo", new Producto());
		return "productos/listarProductos";
	}

	@GetMapping("/add")
	public String mostrarFormularioDeNuevoProducto(Model model) {
		model.addAttribute("productoNuevo", new Producto());
		return "productos/crearProducto";
	}

	@PostMapping("/add")
	public String addProducto(@ModelAttribute("productoNuevo") Producto producto, BindingResult bindingResult) {
		productoService.insertarProducto(producto);
		return "redirect:/productos";
	}

	@GetMapping("/edit/{id}")
	public String mostrarFormularioDeEdicionDeProducto(@PathVariable Long id, Model model) {
		Producto producto = productoService.obtenerPorId(id);
		model.addAttribute("productoEditar", producto);
		return "productos/editarProducto";
	}

	@PostMapping("/edit/{id}")
	public String editarProducto(@PathVariable Long id, @ModelAttribute("productoEditar") Producto producto,
			BindingResult bindingResult) {
		Producto productoExistente = productoService.obtenerPorId(id);

		// Actualiza los detalles del producto existente
		productoExistente.setNombre(producto.getNombre());
		productoExistente.setPrecio(producto.getPrecio());

		productoService.insertarProducto(productoExistente);
		return "redirect:/productos";
	}

	@GetMapping("/delete/{id}")
	public String eliminarProducto(@PathVariable Long id) {
		productoService.eliminarProducto(id);
		return "redirect:/productos";
	}

	@GetMapping("/{id}")
	public String mostrarProducto(@PathVariable Long id, Model model) {
		Producto producto = productoService.obtenerPorId(id);
		model.addAttribute("producto", producto);
		return "productos/producto";
	}

}
