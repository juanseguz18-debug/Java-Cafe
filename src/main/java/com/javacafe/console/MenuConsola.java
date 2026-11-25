
package com.javacafe.console;

import com.javacafe.builder.Pedido;
import com.javacafe.decorator.ExtraDecorador;
import com.javacafe.factory.ProductoFactory;
import com.javacafe.model.Producto;
import com.javacafe.service.CafeService;
import com.javacafe.strategy.DescuentoEstudiante;
import com.javacafe.strategy.DescuentoHappyHour;
import com.javacafe.strategy.DescuentoNormal;
import com.javacafe.strategy.EstrategiaDescuento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConsola {

    private final Scanner scanner = new Scanner(System.in);
    private final CafeService cafeService = new CafeService();
    private final List<Pedido> pedidos = new ArrayList<>();

    public void iniciar() {

        int opcion;

        do {
            System.out.println("\n=================================");
            System.out.println("          JAVA CAFE");
            System.out.println("=================================");
            System.out.println("1. Crear pedido");
            System.out.println("2. Ver ventas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    crearPedido();
                    break;

                case 2:
                    mostrarVentas();
                    break;

                case 3:
                    System.out.println("Gracias por visitar JavaCafe.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 3);
    }

    private void crearPedido() {

        Pedido.Builder builder = new Pedido.Builder();
        System.out.print("Nombre del cliente: ");
        String cliente = scanner.nextLine();

        builder.cliente(cliente.isBlank() ? "Cliente general" : cliente);

        boolean continuar = true;

        while (continuar) {

            mostrarMenuProductos();

            System.out.print("Seleccione un producto: ");
            String tipo = scanner.nextLine().toLowerCase();

            Producto producto;

            try {
                producto = ProductoFactory.crearProducto(tipo);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println("\n¿Desea agregar extras?");
            System.out.println("1. Leche extra ($1500)");
            System.out.println("2. Shot de espresso ($2000)");
            System.out.println("3. Jarabe de vainilla ($1000)");
            System.out.println("4. Sin extras");

            System.out.print("Opción: ");
            int extra = leerEntero();

            switch (extra) {
                case 1:
                    producto = new ExtraDecorador(producto,
                            "Leche extra", 1500);
                    break;

                case 2:
                    producto = new ExtraDecorador(producto,
                            "Shot de espresso", 2000);
                    break;

                case 3:
                    producto = new ExtraDecorador(producto,
                            "Jarabe de vainilla", 1000);
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Opción inválida. Sin extras.");
            }

            builder.agregarProducto(producto);

            System.out.println("Producto agregado: " + producto);

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            continuar = scanner.nextLine().equalsIgnoreCase("s");
        }

        System.out.println("\nSeleccione el descuento:");
        System.out.println("1. Normal");
        System.out.println("2. Estudiante (10%)");
        System.out.println("3. Happy Hour (20%)");
        System.out.print("Opción: ");

        EstrategiaDescuento descuento;

        switch (leerEntero()) {
            case 2:
                descuento = new DescuentoEstudiante();
                break;

            case 3:
                descuento = new DescuentoHappyHour();
                break;

            default:
                descuento = new DescuentoNormal();
        }

        Pedido pedido = builder.descuento(descuento).build();

        pedidos.add(pedido);

        System.out.println("\n========== RESUMEN ==========");
        System.out.println("Cliente: " + pedido.getCliente());

        for (Producto p : pedido.getProductos()) {
            System.out.println("- " + p.getDescripcion()
                    + " | $" + String.format("%.2f", p.getPrecio()));
        }

        System.out.println("Subtotal: $"
                + String.format("%.2f", pedido.calcularSubtotal()));

        System.out.println("Descuento: " + pedido.getNombreDescuento());

        System.out.println("TOTAL: $"
                + String.format("%.2f", pedido.calcularTotal()));

        System.out.println("===========================");

        cafeService.registrarPedido(pedido);
    }

    private void mostrarMenuProductos() {

        System.out.println("\n--------- MENU ---------");
        System.out.println("Escriba una de estas opciones:");
        System.out.println("cafe       - Café americano ($5000)");
        System.out.println("capuchino  - Capuchino ($8000)");
        System.out.println("latte      - Latte ($9000)");
        System.out.println("mocaccino  - Mocaccino ($10000)");
        System.out.println("te         - Té ($4000)");
        System.out.println("chocolate  - Chocolate caliente ($7000)");
        System.out.println("-----------------------");
    }

    private void mostrarVentas() {

        System.out.println("\n======= VENTAS =======");

        if (pedidos.isEmpty()) {
            System.out.println("Todavía no hay pedidos.");
            return;
        }

        double totalVentas = 0;

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);

            System.out.println("Pedido #" + (i + 1)
                    + " | Cliente: " + pedido.getCliente()
                    + " | Total: $" + String.format("%.2f",
                            pedido.calcularTotal()));

            totalVentas += pedido.calcularTotal();
        }

        System.out.println("----------------------");
        System.out.println("Cantidad de pedidos: " + pedidos.size());
        System.out.println("Total vendido: $" + String.format("%.2f", totalVentas));
        System.out.println("======================");
    }

    private int leerEntero() {

        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}
