
package com.javacafe.factory;

import com.javacafe.model.Bebida;
import com.javacafe.model.Producto;

public class ProductoFactory {

    public static Producto crearProducto(String tipo) {

        switch (tipo.toLowerCase()) {

            case "cafe":
                return new Bebida("Café americano", 5000,
                        "Café negro tradicional", "Mediano");

            case "capuchino":
                return new Bebida("Capuchino", 8000,
                        "Café con leche espumosa", "Mediano");

            case "latte":
                return new Bebida("Latte", 9000,
                        "Café espresso con leche", "Mediano");

            case "mocaccino":
                return new Bebida("Mocaccino", 10000,
                        "Café con chocolate y leche", "Mediano");

            case "te":
                return new Bebida("Té", 4000,
                        "Infusión caliente", "Mediano");

            case "chocolate":
                return new Bebida("Chocolate caliente", 7000,
                        "Chocolate preparado con leche", "Mediano");

            default:
                throw new IllegalArgumentException(
                        "Producto no disponible: " + tipo);
        }
    }
}
