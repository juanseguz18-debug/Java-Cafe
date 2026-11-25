
package com.javacafe.builder;

import com.javacafe.model.Producto;
import com.javacafe.strategy.EstrategiaDescuento;
import com.javacafe.strategy.DescuentoNormal;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private final List<Producto> productos;
    private EstrategiaDescuento descuento;
    private String cliente;

    private Pedido(Builder builder) {
        this.productos = new ArrayList<>(builder.productos);
        this.descuento = builder.descuento;
        this.cliente = builder.cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String getCliente() {
        return cliente;
    }

    public double calcularSubtotal() {
        double subtotal = 0;

        for (Producto producto : productos) {
            subtotal += producto.getPrecio();
        }

        return subtotal;
    }

    public double calcularTotal() {
        return descuento.aplicarDescuento(calcularSubtotal());
    }

    public String getNombreDescuento() {
        return descuento.getNombre();
    }

    public static class Builder {

        private final List<Producto> productos = new ArrayList<>();
        private EstrategiaDescuento descuento = new DescuentoNormal();
        private String cliente = "Cliente general";

        public Builder cliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder agregarProducto(Producto producto) {
            productos.add(producto);
            return this;
        }

        public Builder descuento(EstrategiaDescuento descuento) {
            this.descuento = descuento;
            return this;
        }

        public Pedido build() {
            if (productos.isEmpty()) {
                throw new IllegalStateException(
                        "El pedido debe tener al menos un producto.");
            }

            return new Pedido(this);
        }
    }
}
