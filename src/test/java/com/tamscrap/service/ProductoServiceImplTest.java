package com.tamscrap.service;

import com.tamscrap.model.Producto;
import com.tamscrap.service.impl.ProductoServiceImpl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@SpringBootTest
public class ProductoServiceImplTest {
    //Mock
    //no funciona sin autowired??

    @Autowired
    private ProductoServiceImpl ProductoService;


    @Test
    void test01_insertarProducto() {
        Producto producto = new Producto("TestProd1", 3.99, "https://dummyimage.com/200x200/fff/aaa");
        Producto producto2 = new Producto("TestProd2", 4.99, "https://dummyimage.com/200x200/fff/aaa");


        ProductoService.insertarProducto(producto);

        ProductoService.insertarProducto(producto2);

        assertNotNull(producto);
        assertEquals("TestProd1", producto.getNombre());


        Producto productoPrueba = ProductoService.obtenerPorId(producto.getId());
        assertNotNull(productoPrueba);
        assertEquals("TestProd1", productoPrueba.getNombre());
    }

    @Test
    void test02_eliminarProducto() {
        Producto productoEliminado = ProductoService.obtenerPorNombre("TestProd2");
        ProductoService.eliminarProducto(productoEliminado.getId());
        assertNull(ProductoService.obtenerPorNombre("TestProd2"));
    }

    @Test
    void test03_actualizarProducto() {
        Producto producto = ProductoService.obtenerPorNombre("TestProd1");
        assertNotNull(producto);

        producto.setNombre("TestProd1 Actualizado");
        Producto productoActualizado = ProductoService.insertarProducto(producto);
        assertNotNull(productoActualizado);

        assertEquals("TestProd1 Actualizado", productoActualizado.getNombre());
    }

    @Test
    void test04_obtenerTodos() {
        assertEquals(7, ProductoService.obtenerTodos().size());
    }
}