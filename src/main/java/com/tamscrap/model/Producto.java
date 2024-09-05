package com.tamscrap.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTOS")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre", unique = true, nullable = true)
	private String nombre;

	@Column(name = "precio")
	private double precio;

	@Column(name = "imagen")
	private String imagen;
	@Column(name = "lettering")
	private boolean lettering;
	@Column(name = "scrapbooking")
	private boolean scrapbooking;
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductosPedidos> pedidos = new HashSet<>();

	public Producto() {
		this.pedidos = new HashSet<>();
	}

	public Producto(String nombre, double precio, String imagen) {
		this.nombre = nombre;
		this.precio = precio;
		this.imagen = imagen;
	}

	public Producto(Long id, String nombre, double precio, String imagen, boolean lettering, boolean scrapbooking,
			Set<ProductosPedidos> pedidos) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.imagen = imagen;
		this.lettering = lettering;
		this.scrapbooking = scrapbooking;
		this.pedidos = new HashSet<>();
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", imagen=" + imagen
				+ ", lettering=" + lettering + ", scrapbooking=" + scrapbooking + ", pedidos=" + pedidos + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isLettering() {
		return lettering;
	}

	public void setLettering(boolean lettering) {
		this.lettering = lettering;
	}

	public boolean isScrapbooking() {
		return scrapbooking;
	}

	public void setScrapbooking(boolean scrapbooking) {
		this.scrapbooking = scrapbooking;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Set<ProductosPedidos> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<ProductosPedidos> pedidos) {
		this.pedidos = pedidos;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, imagen, lettering, nombre, pedidos, precio, scrapbooking);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(id, other.id) && Objects.equals(imagen, other.imagen) && lettering == other.lettering
				&& Objects.equals(nombre, other.nombre) && Objects.equals(pedidos, other.pedidos)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& scrapbooking == other.scrapbooking;
	}
}
