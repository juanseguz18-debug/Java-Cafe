package com.javacafe;

import com.javacafe.factory.ProductoFactory;
import com.javacafe.model.Producto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoFactoryTest {

    @Test
    public void testCrearProducto() {
        Producto producto = ProductoFactory.crearProducto("CAFE");
        assertNotNull(producto);
    }
}