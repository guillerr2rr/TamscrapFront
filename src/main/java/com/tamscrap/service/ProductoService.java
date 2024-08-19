package com.tamscrap.service;

import com.tamscrap.model.Producto;

import java.util.List;

public interface ProductoService {

	Producto obtenerPorId(Long id);

	Producto obtenerPorNombre(String username);

	Producto insertarProducto(Producto Producto);

	void eliminarProducto(Long id);

	List<Producto> obtenerTodos();

}
