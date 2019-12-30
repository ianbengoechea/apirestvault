package com.apirestvault.apirestvault.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirestvault.apirestvault.entities.Venta;
import com.apirestvault.apirestvault.entities.VentasItem;
import com.apirestvault.apirestvault.exception.ResourceNotFoundException;
import com.apirestvault.apirestvault.repository.ArticuloRepository;
import com.apirestvault.apirestvault.repository.ClienteRepository;
import com.apirestvault.apirestvault.repository.VentaRepository;
import com.apirestvault.apirestvault.request.VentaDTO;

@RestController
@RequestMapping({"/ventas"})
public class VentaController {

	@Autowired
	private VentaRepository repository;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private ArticuloRepository articuloRepo;

	//devolver todos los registros
	
	@GetMapping
	public List<Venta> findAll(){
		return repository.findAll();
	}
	

	@GetMapping("/filtro")
	public List<Venta> findOperators(@RequestParam String from,@RequestParam String to) throws ParseException{
		java.util.Date start = new SimpleDateFormat("yyyy-MM-dd").parse(from);
		java.util.Date end = new SimpleDateFormat("yyyy-MM-dd").parse(to);
		return repository.findOperations(start, end);
	}
	
	@PostMapping("/new")
  public Venta create(@RequestBody VentaDTO venta){
		
		Venta v = new Venta();
		
		v.setFechaVenta(new Date());
		v.setCliente(clienteRepo.findById(venta.getIdCliente())
				.orElseThrow(() -> new ResourceNotFoundException("Cliente ", null, venta.getIdCliente())));
		
		VentasItem vi = new VentasItem();
		
		vi.setArticulo(articuloRepo.findById(venta.getIdArticulo())
				.orElseThrow(() -> new ResourceNotFoundException("Articulo ", null, venta.getIdArticulo())));
		vi.setCantidad(venta.getCantidad());
		vi.setVenta(v);
		v.setVentasItems(Arrays.asList(vi));
		
		return repository.save(v);
  }

}
