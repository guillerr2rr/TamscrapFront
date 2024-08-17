package com.Tamscrap.Tamscrap.service;

import com.Tamscrap.Tamscrap.Model.Cliente;

import java.util.List;

public interface ClienteService {

    public Cliente obtenerPorId(Long id);

    public Cliente obtenerPorUsername(String username);

//	public Cliente InsertarClienteNombre(String nombre);

    public Cliente insertarCliente(Cliente cliente);


    public void eliminarCliente(Long id);

    public List<Cliente> obtenerTodos();
}
