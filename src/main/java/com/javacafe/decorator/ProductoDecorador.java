
package com.javacafe.decorator;

import com.javacafe.model.Producto;

public abstract class ProductoDecorador extends Producto {

    protected Producto productoDecorado;

    public ProductoDecorador(Producto productoDecorado) {
        super(
            productoDecorado.getNombre(),
            productoDecorado.getPrecio(),
            productoDecorado.getDescripcion()
        );

        this.productoDecorado = productoDecorado;
    }

    @Override
    public double getPrecio() {
        return productoDecorado.getPrecio();
    }

    @Override
    public String getNombre() {
        return productoDecorado.getNombre();
    }

    @Override
    public String getDescripcion() {
        return productoDecorado.getDescripcion();
    }
}
