package com.tamscrap.service;

import com.tamscrap.model.Cliente;
import com.tamscrap.model.UserAuthority;
import com.tamscrap.service.impl.ClienteServiceImpl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@SpringBootTest
public class ClienteServiceImplTest {
    //Mock
    //no funciona sin autowired??

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    void test01_insertarCliente() {
        Cliente cliente = new Cliente(
                "Test",
                passwordEncoder.encode("1234"),
                "Test@test.com",
                new ArrayList<>(List.of(UserAuthority.USER))
        );

        clienteService.insertarCliente(cliente);

        Cliente cliente2 = new Cliente(
                "Test2",
                passwordEncoder.encode("1234"),
                "Test2@test.com",
                new ArrayList<>(List.of(UserAuthority.USER))
        );

        clienteService.insertarCliente(cliente2);

        assertNotNull(cliente);
        assertEquals("Test", cliente.getUsername());


        Cliente clientePrueba = clienteService.obtenerPorId(cliente.getId());
        assertNotNull(clientePrueba);
        assertEquals("Test", clientePrueba.getUsername());
    }

    @Test
    void test02_eliminarCliente() {
        Cliente clienteEliminado = clienteService.obtenerPorUsername("Test2");
        clienteService.eliminarCliente(clienteEliminado.getId());
        assertNull(clienteService.obtenerPorUsername("Test2"));
    }

    @Test
    void test03_actualizarCliente() {
        Cliente cliente = clienteService.obtenerPorUsername("Test");
        assertNotNull(cliente);

        cliente.setUsername("Test Actualizado");
        Cliente clienteActualizado = clienteService.insertarCliente(cliente);
        assertNotNull(clienteActualizado);

        assertEquals("Test Actualizado", clienteActualizado.getUsername());
    }

    @Test
    void test04_obtenerTodos() {
        assertEquals(3, clienteService.obtenerTodos().size());
    }
}