
package com.javacafe.observer;

public class ObservadorCaja implements ObservadorPedido {

    @Override
    public void actualizar(String mensaje) {
        System.out.println("[CAJA] " + mensaje);
    }
}
