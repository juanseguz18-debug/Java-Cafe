
package com.javacafe.service;

import com.javacafe.builder.Pedido;
import com.javacafe.observer.NotificadorPedido;
import com.javacafe.observer.ObservadorCaja;
import com.javacafe.observer.ObservadorCocina;
import com.javacafe.strategy.EstrategiaDescuento;

public class CafeService {

    private final NotificadorPedido notificador;

    public CafeService() {
        notificador = new NotificadorPedido();

        notificador.agregarObservador(new ObservadorCocina());
        notificador.agregarObservador(new ObservadorCaja());
    }

    public void registrarPedido(Pedido pedido) {

        String mensaje = "Nuevo pedido para " + pedido.getCliente()
                + ". Productos: " + pedido.getProductos().size()
                + ". Total: $" + String.format("%.2f", pedido.calcularTotal());

        notificador.notificar(mensaje);
    }

    public double calcularTotal(
            Pedido pedido,
            EstrategiaDescuento descuento) {

        return descuento.aplicarDescuento(pedido.calcularSubtotal());
    }
}
