package com.Tamscrap.Tamscrap.Model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "productos_pedidos")
public class ProductosPedidos {

    @EmbeddedId
    private ProductoPedidoId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("pedidoId")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productoId")
    private Producto producto;

    @Column(name = "cantidad")
    private int cantidad=0;

    @Column(name = "nombre")
    private String nombre;

    public ProductosPedidos() {}

    public ProductosPedidos(Producto producto, Pedido pedido, int cantidad) {
        this.producto = producto;
        this.pedido = pedido;
        this.cantidad = cantidad;
        this.nombre = producto.getNombre();
        this.id = new ProductoPedidoId(pedido.getId(), producto.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductosPedidos that = (ProductosPedidos) o;
        return Objects.equals(producto, that.producto) &&
                Objects.equals(pedido, that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, producto);
    }

    @Override
    public String toString() {
        return "ProductosPedidos{" +
                "id=" + id +
                ", pedido=" + pedido +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public ProductoPedidoId getId() {
        return id;
    }

    public void setId(ProductoPedidoId id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
