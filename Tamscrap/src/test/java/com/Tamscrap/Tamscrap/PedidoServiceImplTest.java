package com.Tamscrap.Tamscrap;

import com.Tamscrap.Tamscrap.Model.Pedido;
import com.Tamscrap.Tamscrap.repository.PedidoRepo;
import com.Tamscrap.Tamscrap.serviceImpl.PedidoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PedidoServiceImplTest {

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Mock
    private PedidoRepo pedidoRepo;

    @Test
    public void testInsertarPedido() {
        Pedido pedido = new Pedido();

        when(pedidoRepo.save(any(Pedido.class))).thenReturn(pedido);

        Pedido resultado = pedidoService.insertarPedido(pedido);

        assertNotNull(resultado);

        verify(pedidoRepo, times(1)).save(pedido);
    }
}
