package com.tamscrap.tamscrap.repository;

import com.tamscrap.tamscrap.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepo extends JpaRepository<Pedido, Long> {


}
