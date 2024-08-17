package com.tamscrap.tamscrap.repository;


import com.tamscrap.tamscrap.model.ProductoPedidoId;
import com.tamscrap.tamscrap.model.ProductosPedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosPedidosRepo extends JpaRepository<ProductosPedidos, ProductoPedidoId> {

}