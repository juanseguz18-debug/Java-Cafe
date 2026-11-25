
package com.javacafe.service;

public class CajaService {

    private double totalVentas;
    private int cantidadVentas;
    private boolean cajaAbierta;

    public void abrirCaja() {

        if (cajaAbierta) {
            System.out.println("La caja ya esta abierta.");
            return;
        }

        cajaAbierta = true;
        System.out.println("Caja abierta.");
    }

    public void registrarVenta(double total) {

        if (!cajaAbierta) {
            throw new IllegalStateException(
                    "Debe abrir la caja antes de vender.");
        }

        if (total <= 0) {
            throw new IllegalArgumentException(
                    "El total debe ser mayor a cero.");
        }

        totalVentas += total;
        cantidadVentas++;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void cerrarCaja() {

        if (!cajaAbierta) {
            System.out.println("La caja ya esta cerrada.");
            return;
        }

        System.out.println("\n===== CIERRE DE CAJA =====");
        System.out.println("Ventas realizadas: " + cantidadVentas);
        System.out.println("Total vendido: $"
                + String.format("%.2f", totalVentas));
        System.out.println("==========================");

        cajaAbierta = false;
    }

    public boolean isCajaAbierta() {
        return cajaAbierta;
    }
}
