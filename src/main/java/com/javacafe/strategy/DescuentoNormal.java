
package com.javacafe.strategy;

public class DescuentoNormal implements EstrategiaDescuento {

    @Override
    public double aplicarDescuento(double precio) {
        return precio;
    }

    @Override
    public String getNombre() {
        return "Sin descuento";
    }
}
