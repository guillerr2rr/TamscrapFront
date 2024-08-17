package com.tamscrap.repository;


import com.tamscrap.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {
	Producto findByNombre(String nombre);

}
