package com.Tamscrap.Tamscrap.repository;


import com.Tamscrap.Tamscrap.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Long > {
     Optional<Cliente> findByUsername(String username);


}