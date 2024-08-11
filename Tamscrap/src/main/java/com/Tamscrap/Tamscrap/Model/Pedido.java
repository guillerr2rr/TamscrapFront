package com.Tamscrap.Tamscrap.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

 
@Entity
@Table(name = "pedidos")
public class Pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "precio")
    private double precio;

    @ManyToOne  
    @JoinColumn(name = "id_cliente", nullable = true)
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ProductosPedidos> productos;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido() {
        productos = new HashSet<ProductosPedidos>();
    }

    public Pedido(Cliente a) {
        cliente = a;
        productos = new HashSet<ProductosPedidos>();
 
    }

    public void addProduct(Producto b, int cantidad) {
        ProductosPedidos b_p = new ProductosPedidos(b, this, cantidad);
        if (productos.contains(b_p)) {
            productos.remove(b_p);
        }
        if (cantidad != 0) {
            productos.add(b_p);
        }
        b.getPedidos().add(b_p);
    }

    public void addProduct2(Producto b, int cantidad) {
        ProductosPedidos b_p = new ProductosPedidos(b, this, cantidad);
        if (productos.contains(b_p)) {
            productos.remove(b_p);
        }
        if (cantidad != 0) {
            productos.add(b_p);
        }
        // b.getPedidos().add(b_p);
    }

    public void removeProducto(Producto b) {
        for (Iterator<ProductosPedidos> iterator = productos.iterator();
             iterator.hasNext(); ) {
            ProductosPedidos b_p = iterator.next();

            if (b_p.getPedido().equals(this) &&
                    b_p.getProducto().equals(b)) {
                iterator.remove();
                b_p.getProducto().getPedidos().remove(b_p);
                b_p.setPedido(null);
                b_p.setProducto(null);
                b_p.setCantidad(0);
            }
        }
    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @ManyToOne( fetch = FetchType.EAGER)
    public Set<ProductosPedidos> getProductos() {
        return productos;
    }

    public void setProductos(Set<ProductosPedidos> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return imprimirProductos();
    }

    public String imprimirProductos() {

        String resultado = "Productos del pedido " + id + "\n";
        if (productos.size() == 0) {

        } else {
            for (ProductosPedidos b : productos) {
                resultado = resultado + b.getProducto().toString(b.getCantidad());
                resultado = resultado + "\n";
            }
            resultado = resultado + "\n\n";
        }
        return resultado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void calcularPrecio() {
        double resultado = 0.0;

        for (ProductosPedidos b : productos) {
            resultado += b.getProducto().getPrecio() * b.getCantidad();
        }
        precio = resultado;
    }

}
