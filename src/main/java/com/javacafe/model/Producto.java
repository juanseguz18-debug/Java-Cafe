
package com.javacafe.model;

public class Producto {

    private String nombre;
    private double precio;
    private String descripcion;

    public Producto(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        }
    }

    @Override
    public String toString() {
        return nombre + " - $" + String.format("%.2f", precio)
                + " | " + descripcion;
    }
}

