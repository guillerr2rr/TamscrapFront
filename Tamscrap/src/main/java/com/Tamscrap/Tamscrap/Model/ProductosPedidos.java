package com.Tamscrap.Tamscrap.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "ProductosPedidos")
@Table(name = "productos_pedidos")
public class ProductosPedidos {
	
	@Override
	public String toString() {
		return "ProductosPedidos [id=" + id + ", pedido=" + pedido + ", producto=" + producto + ", cantidad="
				+ cantidad + ", nombre=" + nombre + "]";
	}

	@EmbeddedId
    private ProductoPedidoId id;
	
    @ManyToOne(/*cascade = CascadeType.MERGE,*/ fetch = FetchType.EAGER)
    @MapsId("pedidoId")
    private Pedido pedido;
	
    @ManyToOne(/*cascade = CascadeType.MERGE,*/ fetch = FetchType.EAGER)
    @MapsId("productoId")
    private Producto producto;
 
    @Column(name = "cantidad")
    private Integer cantidad =1;
    
//    @Column(name = "fecha")
//    private String fecha ="31-12-2023";
    
    
    private String nombre;
 
    
    private ProductosPedidos() {}
 
    public ProductosPedidos(Producto b, Pedido p, int c) {
        this.producto = b;
        this.pedido = p;
        this.cantidad = c;
        this.nombre = b.getNombre();
        this.id = new ProductoPedidoId(p.getId(), b.getId());
    }
	
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ProductosPedidos that = (ProductosPedidos) o;
        return Objects.equals(producto, that.producto) &&
               Objects.equals(pedido, that.pedido);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(pedido, producto);
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return getProducto().getNombre();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
}
