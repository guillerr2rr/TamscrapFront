package com.Tamscrap.Tamscrap.controller;

import com.Tamscrap.Tamscrap.Model.Cliente;
import com.Tamscrap.Tamscrap.serviceImpl.ClienteServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServiceImpl clienteService;

    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }
    @PostMapping("api/clientes")
    public Cliente guardarClientes(@RequestBody Cliente clientes) {
        System.out.println(clientes);
        clienteService.insertarCliente(clientes);
        return clientes;
    }

    @GetMapping(value = {"", "/"})
    String clientes(Model model) {
        List<Cliente> lista = clienteService.obtenerTodos();
        model.addAttribute("clientes", lista);
        model.addAttribute("nuevoCliente", new Cliente());
        model.addAttribute("clienteaEditar", new Cliente());
        model.addAttribute("nombreNuevo", "");
        return "clientes/listarClientes";
    }

    @GetMapping("/add")
    public String crearCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("nuevoCliente", cliente);
        return "clientes/crearCliente";
    }

    @PostMapping("/add")
    public String addCliente(@ModelAttribute("nuevoCliente") Cliente cliente, BindingResult bindingResult) {
        clienteService.insertarCliente(cliente);
        System.out.println("Insertar cliente nuevo: " + cliente.getUsername());

        return "redirect:/clientes";

    }

    @GetMapping("/edit/{id}")
    public String mostrarFormularioEdicionCliente(@PathVariable Long id, Model model) {

        Cliente cliente = clienteService.obtenerPorId(id);

        model.addAttribute("clienteEditar", cliente);

        return "clientes/editarClientes";
    }

    @PostMapping("/edit/{id}")
    public String editarCliente(@PathVariable Long id, @ModelAttribute("clienteEditar") Cliente cliente,
                                BindingResult bindingResult) {

        Cliente clienteExistente = clienteService.obtenerPorId(id);

        clienteExistente.setUsername(cliente.getUsername());
        clienteService.insertarCliente(clienteExistente);
        return "redirect:/clientes";
    }

    @GetMapping({"/delete/{id}"})
    public String eliminarCliente(@PathVariable Long id) {

        clienteService.eliminarCliente(id);

        return "redirect:/clientes";
    }

    @GetMapping("/{id}")
    public String mostrarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerPorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes/cliente";
    }
}
