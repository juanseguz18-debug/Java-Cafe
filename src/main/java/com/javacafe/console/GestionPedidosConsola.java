
package com.javacafe.console;

import com.javacafe.service.GestorPedidos;
import com.javacafe.model.EstadoPedido;
import java.util.Scanner;

public class GestionPedidosConsola {

    private final Scanner scanner = new Scanner(System.in);
    private final GestorPedidos gestor = new GestorPedidos();

    public void iniciar() {

        int opcion;

        do {
            System.out.println("\n===== GESTION DE PEDIDOS =====");
            System.out.println("1. Ver pedidos");
            System.out.println("2. Cambiar estado de pedido");
            System.out.println("3. Ver estados disponibles");
            System.out.println("4. Salir");
            System.out.print("Opcion: ");

            opcion = leerNumero();

            switch (opcion) {
                case 1:
                    gestor.mostrarPedidos();
                    break;

                case 2:
                    cambiarEstado();
                    break;

                case 3:
                    mostrarEstados();
                    break;

                case 4:
                    System.out.println("Saliendo de gestion.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 4);
    }

    private void cambiarEstado() {

        gestor.mostrarPedidos();

        if (gestor.getPedidos().isEmpty()) {
            return;
        }

        System.out.print("Numero del pedido: ");
        int numero = leerNumero();

        int indice = numero - 1;

        if (indice < 0 || indice >= gestor.getPedidos().size()) {
            System.out.println("Pedido inexistente.");
            return;
        }

        System.out.println("Estado actual: " + gestor.getEstado(indice));

        mostrarEstados();

        System.out.print("Seleccione el nuevo estado: ");
        int estado = leerNumero();

        EstadoPedido nuevoEstado;

        switch (estado) {
            case 1:
                nuevoEstado = EstadoPedido.PENDIENTE;
                break;

            case 2:
                nuevoEstado = EstadoPedido.EN_PREPARACION;
                break;

            case 3:
                nuevoEstado = EstadoPedido.LISTO;
                break;

            case 4:
                nuevoEstado = EstadoPedido.ENTREGADO;
                break;

            case 5:
                nuevoEstado = EstadoPedido.CANCELADO;
                break;

            default:
                System.out.println("Estado invalido.");
                return;
        }

        try {
            gestor.cambiarEstado(indice, nuevoEstado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void mostrarEstados() {
        System.out.println("\n===== ESTADOS =====");
        System.out.println("1. PENDIENTE");
        System.out.println("2. EN_PREPARACION");
        System.out.println("3. LISTO");
        System.out.println("4. ENTREGADO");
        System.out.println("5. CANCELADO");
    }

    private int leerNumero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }
}
