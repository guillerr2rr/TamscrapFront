package com.tamscrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tamscrap.model.Cliente;
import com.tamscrap.service.impl.ClienteServiceImpl;
import com.tamscrap.service.impl.PedidoServiceImpl;
import com.tamscrap.service.impl.ProductoServiceImpl;

@Controller
public class WebApplication {


    private final ClienteServiceImpl clienteService;

    private final ProductoServiceImpl productoService;

    private final PedidoServiceImpl pedidoService;

    public WebApplication(ClienteServiceImpl clienteService, ProductoServiceImpl productoService, PedidoServiceImpl pedidoService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
    }

    @GetMapping("/")
    String home() {
        return "index";
    }

    @GetMapping("/iniciarTablas")
    String llenarTablas() {
        llenarTablasDeDatos();
        return "index";
    }

    private final void llenarTablasDeDatos() {


        // Crear un cliente y guardar en la base de datos
        Cliente cliente = new Cliente();
        clienteService.insertarCliente(cliente);
        Cliente cliente2 = new Cliente();
        clienteService.insertarCliente(cliente2);
    }

}
