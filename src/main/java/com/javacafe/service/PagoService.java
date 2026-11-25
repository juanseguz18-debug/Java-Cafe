
package com.javacafe.service;

import com.javacafe.model.MetodoPago;

public class PagoService {

    public boolean procesarPago(double total, MetodoPago metodo) {

        if (total <= 0) {
            System.out.println("Monto invalido.");
            return false;
        }

        switch (metodo) {

            case EFECTIVO:
                System.out.println("Pago recibido en efectivo.");
                return true;

            case TARJETA:
                System.out.println("Tarjeta aprobada (simulacion).");
                return true;

            case TRANSFERENCIA:
                System.out.println("Transferencia confirmada (simulacion).");
                return true;

            default:
                System.out.println("Metodo de pago no reconocido.");
                return false;
        }
    }
}
