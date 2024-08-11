package com.Tamscrap.Tamscrap.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;



@Embeddable 
public class ProductoPedidoId implements Serializable {

		    

	private static final long serialVersionUID = 1L;

		@Column(name = "pedido_id")
	    private Integer pedidoId;
			
		@Column(name = "producto_id")
	    private Integer productoId;
	 
	    
	    
	    private ProductoPedidoId() {}
	 
	    public ProductoPedidoId(Integer IdP, Integer IdB ) {
	    	 this.pedidoId = IdP;
	    	 this.productoId = IdB;
	       
	    }
	
	
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (o == null || getClass() != o.getClass())
	            return false;
	 
	        ProductoPedidoId that = (ProductoPedidoId) o;
	        return Objects.equals(productoId, that.productoId) &&
	               Objects.equals(pedidoId, that.pedidoId);
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(productoId, pedidoId);
	    }

		public Integer getProductoAd() {
			return productoId;
		}

		public void setProductoAd(Integer idProducto) {
			productoId = idProducto;
		}

		public Integer getPedidoAd() {
			return pedidoId;
		}

		public void setPedidoAd(Integer idPedido) {
			pedidoId = idPedido;
		}
	
	
	
	
	    
	
	
}
