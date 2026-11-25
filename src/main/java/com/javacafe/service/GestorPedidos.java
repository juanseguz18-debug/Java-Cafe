
package com.javacafe.service;

import com.javacafe.builder.Pedido;
import com.javacafe.model.EstadoPedido;
import java.util.ArrayList;
import java.util.List;

public class GestorPedidos {

    private final List<Pedido> pedidos = new ArrayList<>();
    private final List<EstadoPedido> estados = new ArrayList<>();

    public void registrarPedido(Pedido pedido) {
        pedidos.add(pedido);
        estados.add(EstadoPedido.PENDIENTE);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public EstadoPedido getEstado(int indice) {
        return estados.get(indice);
    }

    public void cambiarEstado(int indice, EstadoPedido nuevoEstado) {

        EstadoPedido actual = estados.get(indice);

        if (actual == EstadoPedido.ENTREGADO
                || actual == EstadoPedido.CANCELADO) {
            throw new IllegalStateException(
                    "El pedido ya finalizó.");
        }

        if (!transicionValida(actual, nuevoEstado)) {
            throw new IllegalArgumentException(
                    "Cambio de estado no permitido: "
                    + actual + " -> " + nuevoEstado);
        }

        estados.set(indice, nuevoEstado);

        System.out.println("Pedido #" + (indice + 1)
                + " cambió a " + nuevoEstado);
    }

    private boolean transicionValida(
            EstadoPedido actual, EstadoPedido nuevo) {

        switch (actual) {

            case PENDIENTE:
                return nuevo == EstadoPedido.EN_PREPARACION
                        || nuevo == EstadoPedido.CANCELADO;

            case EN_PREPARACION:
                return nuevo == EstadoPedido.LISTO
                        || nuevo == EstadoPedido.CANCELADO;

            case LISTO:
                return nuevo == EstadoPedido.ENTREGADO;

            default:
                return false;
        }
    }

    public void mostrarPedidos() {

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        for (int i = 0; i < pedidos.size(); i++) {

            Pedido pedido = pedidos.get(i);

            System.out.println("--------------------------");
            System.out.println("Pedido #" + (i + 1));
            System.out.println("Cliente: " + pedido.getCliente());
            System.out.println("Estado: " + estados.get(i));
            System.out.println("Total: $"
                    + String.format("%.2f", pedido.calcularTotal()));
        }

        System.out.println("--------------------------");
    }
}
