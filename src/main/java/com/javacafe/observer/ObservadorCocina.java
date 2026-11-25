
package com.javacafe.observer;

public class ObservadorCocina implements ObservadorPedido {

    @Override
    public void actualizar(String mensaje) {
        System.out.println("[COCINA] " + mensaje);
    }
}
