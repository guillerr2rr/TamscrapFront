package com.Tamscrap.Tamscrap.controller;

import com.Tamscrap.Tamscrap.Model.Producto;
import com.Tamscrap.Tamscrap.serviceImpl.ProductoServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoServiceImpl productoService;

    public ProductoController(ProductoServiceImpl productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/api/productos")
    public Producto guardarProducto(@RequestBody Producto producto) {
        System.out.println(producto);
        productoService.insertarProducto(producto);
        return producto;
    }

    @GetMapping({"", "/"})
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.obtenerTodos();
        model.addAttribute("producto", productos);
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
    public String editarProducto(@PathVariable Long id, @ModelAttribute("productoEditar") Producto producto, BindingResult bindingResult) {
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
