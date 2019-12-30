package com.apirestvault.apirestvault.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ventas database table.
 * 
 */
@Entity
@Table(name="ventas")
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
//@SqlResultSetMapping(
//		  name="VentaReporte",
//		  entities={
//		    @EntityResult(
//		      entityClass = Venta.class,
//		        fields={
//		        		@FieldResult(name="id_ventas",column="idVentas"),
//		        		@FieldResult(name="cliente_id",column="clienteId"),
//		        		@FieldResult(name="fecha_venta", column="fechaVenta"),
//		        		@FieldResult(name="comentarios",column="comentarios"),
//		        		@FieldResult(name="company_id",column="companyId"),
//		        		@FieldResult(name="nombre_empresa",column="nombreEmpresa"),
//		        		@FieldResult(name="id_venta_detalle",column="idVentaDetalle"),
//		        		@FieldResult(name="articulos_id",column="articulosId"),
//		        		@FieldResult(name="cantidad",column="cantidad"),
//		        		@FieldResult(name="id_venta",column="idVenta"),
//		        		@FieldResult(name="nombre_articulo",column="nombreArticulo"),
//		        		@FieldResult(name="precio_unitario",column="precioUnitario"),
//		        		@FieldResult(name="total",column="total")})})
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ventas")
	private Integer idVentas;
	
	private String comentarios;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_venta")
	private Date fechaVenta;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	//bi-directional many-to-one association to VentasItem
	@OneToMany(mappedBy="venta",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<VentasItem> ventasItems;

	
	public Venta() {
	}

	public Integer getIdVentas() {
		return this.idVentas;
	}

	public void setIdVentas(Integer idVentas) {
		this.idVentas = idVentas;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFechaVenta() {
		return this.fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public List<VentasItem> getVentasItems() {
		return this.ventasItems;
	}

	public void setVentasItems(List<VentasItem> ventasItems) {
		this.ventasItems = ventasItems;
	}

	public VentasItem addVentasItem(VentasItem ventasItem) {
		getVentasItems().add(ventasItem);
		ventasItem.setVenta(this);

		return ventasItem;
	}

	public VentasItem removeVentasItem(VentasItem ventasItem) {
		getVentasItems().remove(ventasItem);
		ventasItem.setVenta(null);

		return ventasItem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}