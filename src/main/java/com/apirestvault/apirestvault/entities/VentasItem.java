package com.apirestvault.apirestvault.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the ventas_items database table.
 * 
 */
@Entity
@Table(name="ventas_items")
@NamedQuery(name="VentasItem.findAll", query="SELECT v FROM VentasItem v")
@JsonIgnoreProperties({ "venta" })
public class VentasItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_venta_detalle")
	private Integer idVentaDetalle;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	@JoinColumn(name="articulos_id")
	private Articulo articulo;
	
	private int cantidad;

	//bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="id_venta")
	private Venta venta;
	
	@Transient
	private BigDecimal total;

	public VentasItem() {
	}

	public Integer getIdVentaDetalle() {
		return this.idVentaDetalle;
	}

	public void setIdVentaDetalle(Integer idVentaDetalle) {
		this.idVentaDetalle = idVentaDetalle;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public BigDecimal getTotal() {
		if(articulo != null) 
			total = new BigDecimal(articulo.getPrecioUnitario() * cantidad);
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}