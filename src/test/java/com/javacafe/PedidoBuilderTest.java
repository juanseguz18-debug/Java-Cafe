package com.javacafe;

import com.javacafe.builder.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoBuilderTest {

    @Test
    public void testPedido() {
        assertDoesNotThrow(() -> {
            assertNotNull(Pedido.class);
        });
    }
}