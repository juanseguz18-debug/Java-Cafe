
package com.javacafe.observer;

import java.util.ArrayList;
import java.util.List;

public class NotificadorPedido {

    private final List<ObservadorPedido> observadores = new ArrayList<>();

    public void agregarObservador(ObservadorPedido observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(ObservadorPedido observador) {
        observadores.remove(observador);
    }

    public void notificar(String mensaje) {
        for (ObservadorPedido observador : observadores) {
            observador.actualizar(mensaje);
        }
    }
}
