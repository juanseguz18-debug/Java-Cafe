package com.javacafe;

import com.javacafe.decorator.ExtraDecorador;
import com.javacafe.decorator.ProductoDecorador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTest {

    @Test
    public void testDecoradorDisponible() {
        assertNotNull(ProductoDecorador.class);
        assertNotNull(ExtraDecorador.class);
    }
}