package com.tamscrap;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.tamscrap.service.TestClientes;
import com.tamscrap.service.TestPedidos;
import com.tamscrap.service.TestProductos;

@Suite

@SelectClasses({ TestClientes.class, TestProductos.class,TestPedidos.class })
public class AllTests {

}
