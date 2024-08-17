package com.tamscrap;

import com.tamscrap.model.Cliente;
import com.tamscrap.model.Pedido;
import com.tamscrap.model.Producto;
import com.tamscrap.model.ProductosPedidos;
import com.tamscrap.service.impl.ClienteServiceImpl;
import com.tamscrap.service.impl.PedidoServiceImpl;
import com.tamscrap.service.impl.ProductoServiceImpl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@SpringBootTest
public class TestPedidos {

    @Autowired
    private PedidoServiceImpl pedidoService;
    @Autowired
    private ClienteServiceImpl clienteService;
    @Autowired
    private ProductoServiceImpl productoService;

    @Test
    @Transactional
    @Rollback(false)
    void test01_insertarPedido() {

        // Crear el primer pedido
        Cliente cliente1 = clienteService.obtenerPorUsername("Test Actualizado");
        Pedido pedido1 = new Pedido(cliente1);
        Producto producto1 = productoService.obtenerPorNombre("Prod1");
        ProductosPedidos productosPedidos1 = new ProductosPedidos(producto1, pedido1, 1);
        pedido1.getProductos().add(productosPedidos1);
        pedidoService.insertarPedido(pedido1);

        // Crear el segundo pedido
        Cliente cliente2 = clienteService.obtenerPorUsername("user1");
        Pedido pedido2 = new Pedido(cliente2);
        Producto producto2 = productoService.obtenerPorNombre("Prod2");
        ProductosPedidos productosPedidos2 = new ProductosPedidos(producto2, pedido2, 6);
        pedido2.getProductos().add(productosPedidos2);
        pedidoService.insertarPedido(pedido2);

        // Verificar que los pedidos se hayan insertado correctamente
        Pedido pedidoRecuperado1 = pedidoService.obtenerPorId(pedido1.getId());
        assertNotNull(pedidoRecuperado1, "El primer pedido no se insertó correctamente en la base de datos");

        Pedido pedidoRecuperado2 = pedidoService.obtenerPorId(pedido2.getId());
        assertNotNull(pedidoRecuperado2, "El segundo pedido no se insertó correctamente en la base de datos");
    }
//
//
//    @Test
//    void test02_eliminarPedido() {
//        Pedido pedido = pedidoService.obtenerPorId(1);
//        pedidoService.eliminarPedido(pedido.getId());
//        assertNull(pedidoService.obtenerPorId(1));
//    }
//
//    @Test
//    @Transactional
//    void test03_actualizarPedido() {
//        Pedido pedido = pedidoService.obtenerPorId(2);
//        assertNotNull(pedido);
//
//        Cliente clienteActualizado = new Cliente("Actualizar");
//
//        Producto producto = productoService.obtenerPorId(2);
//        assertNotNull(producto);
//
//        // Agregar el producto al pedido
//        pedido.addProducto(producto, 3);
//
//        // Actualizar el cliente del pedido
//        pedido.setCliente(clienteActualizado);
//
//        Pedido pedidoActualizado = pedidoService.insertarPedido(pedido);
//        assertNotNull(pedidoActualizado);
//
//        assertEquals(clienteActualizado, pedidoActualizado.getCliente());
//        // Verificar que el pedido tiene 1 producto asignado
//        assertNotNull(pedidoActualizado.getProductos());
//        assertEquals(1, pedidoActualizado.getProductos().size());
//    }
//
//    @Test
//    void test04_obtenerTodos() {
//        assertEquals(1, pedidoService.obtenerTodos().size());
//    }
}