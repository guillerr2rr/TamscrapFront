package com.Tamscrap.Tamscrap.repository;


import com.Tamscrap.Tamscrap.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {
	Producto findByNombre(String nombre);

}
