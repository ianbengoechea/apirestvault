package com.apirestvault.apirestvault.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the articulos database table.
 * 
 */
@Entity
@Table(name = "articulos")
@NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "ventas_items" })
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_articulos")
	private int idArticulos;

	private int cantidad;

	private String descripcion;

	private String nombre;

	@Column(name = "precio_unitario")
	private int precioUnitario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	//bi-directional many-to-one association to VentasItem
	@OneToMany(mappedBy="articulo")
	private List<VentasItem> ventasItems;

	
	public Articulo() {
	}

	public int getIdArticulos() {
		return this.idArticulos;
	}

	public void setIdArticulos(int idArticulos) {
		this.idArticulos = idArticulos;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<VentasItem> getVentasItems() {
		return this.ventasItems;
	}

	public void setVentasItems(List<VentasItem> ventasItems) {
		this.ventasItems = ventasItems;
	}

	public VentasItem addVentasItem(VentasItem ventasItem) {
		getVentasItems().add(ventasItem);
		ventasItem.setArticulo(this);

		return ventasItem;
	}

	public VentasItem removeVentasItem(VentasItem ventasItem) {
		getVentasItems().remove(ventasItem);
		ventasItem.setArticulo(null);

		return ventasItem;
	}


}