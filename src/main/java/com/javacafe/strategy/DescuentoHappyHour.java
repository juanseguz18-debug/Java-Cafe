
package com.javacafe.strategy;

public class DescuentoHappyHour implements EstrategiaDescuento {

    @Override
    public double aplicarDescuento(double precio) {
        return precio * 0.80;
    }

    @Override
    public String getNombre() {
        return "Happy Hour (20%)";
    }
}
