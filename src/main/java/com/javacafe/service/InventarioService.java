
package com.javacafe.service;

import com.javacafe.model.InventarioProducto;
import java.util.LinkedHashMap;
import java.util.Map;

public class InventarioService {

    private final Map<String, InventarioProducto> inventario =
            new LinkedHashMap<>();

    public InventarioService() {
        inventario.put("cafe", new InventarioProducto("Cafe", 50));
        inventario.put("capuchino", new InventarioProducto("Capuchino", 30));
        inventario.put("latte", new InventarioProducto("Latte", 30));
        inventario.put("mocaccino", new InventarioProducto("Mocaccino", 20));
        inventario.put("te", new InventarioProducto("Te", 40));
        inventario.put("chocolate", new InventarioProducto("Chocolate", 25));
    }

    public boolean disponible(String producto) {
        InventarioProducto item = inventario.get(
                producto.toLowerCase());

        return item != null && item.hayExistencias();
    }

    public void vender(String producto, int cantidad) {

        InventarioProducto item = inventario.get(
                producto.toLowerCase());

        if (item == null) {
            throw new IllegalArgumentException(
                    "Producto inexistente.");
        }

        item.descontar(cantidad);
    }

    public void reabastecer(String producto, int cantidad) {

        InventarioProducto item = inventario.get(
                producto.toLowerCase());

        if (item == null) {
            throw new IllegalArgumentException(
                    "Producto inexistente.");
        }

        item.reabastecer(cantidad);
    }

    public void mostrarInventario() {

        System.out.println("\n===== INVENTARIO =====");

        for (InventarioProducto item : inventario.values()) {
            System.out.println(item);
        }

        System.out.println("=====================");
    }
}
