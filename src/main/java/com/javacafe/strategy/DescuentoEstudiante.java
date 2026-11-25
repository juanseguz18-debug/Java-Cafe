
package com.javacafe.strategy;

public class DescuentoEstudiante implements EstrategiaDescuento {

    @Override
    public double aplicarDescuento(double precio) {
        return precio * 0.90;
    }

    @Override
    public String getNombre() {
        return "Descuento de estudiante (10%)";
    }
}
