package com.Tamscrap.Tamscrap.service;


import com.Tamscrap.Tamscrap.Model.Producto;

import java.util.List;

public interface ProductoService {

	public Producto obtenerPorId(Long id);

	public Producto obtenerPorNombre(String username);

	public Producto insertarProducto(Producto Producto);

	public void eliminarProducto(Long id);

	public List<Producto> obtenerTodos();
	
}
