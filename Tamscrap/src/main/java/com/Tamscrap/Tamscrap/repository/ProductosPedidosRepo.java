package com.Tamscrap.Tamscrap.repository;


import com.Tamscrap.Tamscrap.Model.ProductoPedidoId;
import com.Tamscrap.Tamscrap.Model.ProductosPedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosPedidosRepo extends JpaRepository<ProductosPedidos, ProductoPedidoId> {

}