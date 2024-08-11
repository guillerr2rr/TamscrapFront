package com.Tamscrap.Tamscrap.Model;


import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
@NaturalIdCache
@Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private double precio;


    @OneToMany(
            mappedBy = "producto",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ProductosPedidos> pedidos = new HashSet<ProductosPedidos>();


    public Producto() {

    }

    public Producto(String n, double p) {
        nombre = n;
        pedidos = new HashSet<ProductosPedidos>();

        precio = p;

    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Set<ProductosPedidos> getPedidos() {
        return pedidos;
    }


    public void setPedidos(Set<ProductosPedidos> pedidos) {
        this.pedidos = pedidos;
    }


    public String toString(Integer cantidad) {
        // Formato de la cadena de salida
        String resultado = nombre + "--->>\n";
        resultado += "Cantidad: " + cantidad + "\n";
        resultado += "Precio: " + precio + " €\n";

        return resultado;
    }

    public double getPrecio() {
        return precio;
    }


    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
