package com.tamscrap;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.tamscrap.service.ClienteServiceImplTest;
import com.tamscrap.service.PedidoServiceImplTest;
import com.tamscrap.service.ProductoServiceImplTest;

@Suite

@SelectClasses({ ClienteServiceImplTest.class, ProductoServiceImplTest.class,PedidoServiceImplTest.class })
public class AllServiceImplTest {

}
