
package com.javacafe.model;

public class Bebida extends Producto {

    private String tamano;

    public Bebida(String nombre, double precio, String descripcion, String tamano) {
        super(nombre, precio, descripcion);
        this.tamano = tamano;
    }

    public String getTamano() {
        return tamano;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tamaño: " + tamano;
    }
}
