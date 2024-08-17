package com.tamscrap.tamscrap;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite

@SelectClasses({ TestClientes.class, TestProductos.class,TestPedidos.class })
public class AllTests {

}
