
package com.javacafe.decorator;

import com.javacafe.model.Producto;

public class ExtraDecorador extends ProductoDecorador {

    private String extra;
    private double precioExtra;

    public ExtraDecorador(Producto producto, String extra, double precioExtra) {
        super(producto);
        this.extra = extra;
        this.precioExtra = precioExtra;
    }

    @Override
    public double getPrecio() {
        return productoDecorado.getPrecio() + precioExtra;
    }

    @Override
    public String getNombre() {
        return productoDecorado.getNombre();
    }

    @Override
    public String getDescripcion() {
        return productoDecorado.getDescripcion() + " + " + extra;
    }

    @Override
    public String toString() {
        return getNombre() + " | " + getDescripcion()
                + " | Total: $" + String.format("%.2f", getPrecio());
    }
}

