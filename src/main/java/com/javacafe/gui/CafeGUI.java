
package com.javacafe.gui;

import com.javacafe.builder.Pedido;
import com.javacafe.decorator.ExtraDecorador;
import com.javacafe.factory.ProductoFactory;
import com.javacafe.model.Producto;
import com.javacafe.service.CafeService;
import com.javacafe.strategy.DescuentoEstudiante;
import com.javacafe.strategy.DescuentoHappyHour;
import com.javacafe.strategy.DescuentoNormal;
import com.javacafe.strategy.EstrategiaDescuento;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CafeGUI extends JFrame {

    private final JTextField clienteField = new JTextField("Cliente general", 15);

    private final JComboBox<String> productos = new JComboBox<>(
            new String[]{"cafe", "capuchino", "latte",
                "mocaccino", "te", "chocolate"});

    private final JComboBox<String> extras = new JComboBox<>(
            new String[]{"Sin extra", "Leche extra",
                "Shot de espresso", "Jarabe de vainilla"});

    private final JComboBox<String> descuentos = new JComboBox<>(
            new String[]{"Normal", "Estudiante 10%", "Happy Hour 20%"});

    private final JTextArea resumen = new JTextArea(15, 30);

    private final List<Producto> productosPedido = new ArrayList<>();

    private final CafeService cafeService = new CafeService();

    public CafeGUI() {

        setTitle("JavaCafe - Sistema de pedidos");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formulario = new JPanel(new GridLayout(0, 2, 8, 8));

        formulario.add(new JLabel("Cliente:"));
        formulario.add(clienteField);

        formulario.add(new JLabel("Producto:"));
        formulario.add(productos);

        formulario.add(new JLabel("Extra:"));
        formulario.add(extras);

        formulario.add(new JLabel("Descuento:"));
        formulario.add(descuentos);

        JButton agregar = new JButton("Agregar producto");
        JButton finalizar = new JButton("Finalizar pedido");
        JButton limpiar = new JButton("Nuevo pedido");

        formulario.add(agregar);
        formulario.add(finalizar);

        formulario.add(limpiar);

        resumen.setEditable(false);
        resumen.setFont(new Font("Monospaced", Font.PLAIN, 13));

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(resumen), BorderLayout.CENTER);

        agregar(agregar, finalizar, limpiar);

        add(panel);

        setVisible(true);
    }

    private void agregar(JButton agregar, JButton finalizar, JButton limpiar) {

        agregar.addActionListener(e -> agregarProducto());

        finalizar.addActionListener(e -> finalizarPedido());

        limpiar.addActionListener(e -> limpiarPedido());
    }

    private void agregarProducto() {

        String tipo = (String) productos.getSelectedItem();

        Producto producto = ProductoFactory.crearProducto(tipo);

        int extra = extras.getSelectedIndex();

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

            default:
                break;
        }

        productosPedido.add(producto);

        resumen.append(producto.toString() + "\n");

        resumen.append("Producto agregado al pedido.\n\n");
    }

    private EstrategiaDescuento obtenerDescuento() {

        switch (descuentos.getSelectedIndex()) {

            case 1:
                return new DescuentoEstudiante();

            case 2:
                return new DescuentoHappyHour();

            default:
                return new DescuentoNormal();
        }
    }

    private void finalizarPedido() {

        if (productosPedido.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Agrega al menos un producto.");
            return;
        }

        Pedido.Builder builder = new Pedido.Builder();

        String cliente = clienteField.getText().trim();

        builder.cliente(cliente.isEmpty()
                ? "Cliente general" : cliente);

        for (Producto producto : productosPedido) {
            builder.agregarProducto(producto);
        }

        builder.descuento(obtenerDescuento());

        Pedido pedido = builder.build();

        resumen.append("\n========= TOTAL =========\n");

        resumen.append("Subtotal: $"
                + String.format("%.2f", pedido.calcularSubtotal()) + "\n");

        resumen.append("Descuento: "
                + pedido.getNombreDescuento() + "\n");

        resumen.append("Total: $"
                + String.format("%.2f", pedido.calcularTotal()) + "\n");

        resumen.append("=========================\n");

        cafeService.registrarPedido(pedido);

        JOptionPane.showMessageDialog(this,
                "Pedido finalizado.\nTotal: $"
                        + String.format("%.2f", pedido.calcularTotal()));
    }

    private void limpiarPedido() {

        productosPedido.clear();

        resumen.setText("");

        clienteField.setText("Cliente general");

        JOptionPane.showMessageDialog(this,
                "Se ha iniciado un nuevo pedido.");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new CafeGUI());
    }
}
