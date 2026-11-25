
package com.javacafe.model;

public class InventarioProducto {

    private String nombre;
    private int cantidad;

    public InventarioProducto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean hayExistencias() {
        return cantidad > 0;
    }

    public void descontar(int unidades) {

        if (unidades <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser positiva.");
        }

        if (unidades > cantidad) {
            throw new IllegalStateException(
                    "Inventario insuficiente de " + nombre);
        }

        cantidad -= unidades;
    }

    public void reabastecer(int unidades) {

        if (unidades <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser positiva.");
        }

        cantidad += unidades;
    }

    @Override
    public String toString() {
        return nombre + " | Existencias: " + cantidad;
    }
}
