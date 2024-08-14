package com.Tamscrap.Tamscrap.repository;

import com.Tamscrap.Tamscrap.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepo extends JpaRepository<Pedido, Long> {


}
