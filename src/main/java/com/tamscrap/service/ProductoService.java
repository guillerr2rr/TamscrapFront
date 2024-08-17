package com.tamscrap.service;


import com.tamscrap.model.Producto;

import java.util.List;

public interface ProductoService {

	public Producto obtenerPorId(Long id);

	public Producto obtenerPorNombre(String username);

	public Producto insertarProducto(Producto Producto);

	public void eliminarProducto(Long id);

	public List<Producto> obtenerTodos();
	
}
