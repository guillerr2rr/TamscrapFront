package com.tamscrap.service;

import com.tamscrap.model.Cliente;

import java.util.List;

public interface ClienteService {

    public Cliente obtenerPorId(Long id);

    public Cliente obtenerPorUsername(String username);

//	public Cliente InsertarClienteNombre(String nombre);

    public Cliente insertarCliente(Cliente cliente);


    public void eliminarCliente(Long id);

    public List<Cliente> obtenerTodos();
}
