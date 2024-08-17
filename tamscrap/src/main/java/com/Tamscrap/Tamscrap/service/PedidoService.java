package com.tamscrap.tamscrap.service;

import com.tamscrap.tamscrap.model.Pedido;

import java.util.List;

public interface PedidoService {

	public Pedido obtenerPorId(Long id);

	public Pedido insertarPedido(Pedido pedido);

	public void eliminarPedido(Long id);

	public List<Pedido> obtenerTodos();

}
