package com.apirestvault.apirestvault.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirestvault.apirestvault.entities.Articulo;
import com.apirestvault.apirestvault.exception.ResourceNotFoundException;
import com.apirestvault.apirestvault.repository.ArticuloRepository;

@RestController
@RequestMapping({"/articulos"})
public class ArticuloController {

	@Autowired
	private ArticuloRepository repository;

	//devolver todos los registros
	
	@GetMapping
	public List<Articulo> findAll(){
		return repository.findAll();
	}
	
	// devuelve filtro por categoria
    
	@GetMapping("/filter")
    public List<Articulo> show(@RequestParam int id){
        return repository.findArticuloByCategory(id);
    }
    
	// crea un articulo
    
	@PostMapping("/new")
    public Articulo create(@RequestBody Articulo articulo){
        return repository.save(articulo);
    }
    
//	// modifica un articulo
	
	@PutMapping("/edit")
	public Articulo show(@RequestParam int id,
            @Valid @RequestBody Articulo articuloRequest) {
		
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Articulo ","id",id);
		}
		
		return repository.findById(id).map(articulo -> {
			articulo.setCantidad(articuloRequest.getCantidad());
			articulo.setNombre(articuloRequest.getNombre());
			articulo.setDescripcion(articuloRequest.getDescripcion());
			articulo.setPrecioUnitario(articuloRequest.getPrecioUnitario());
			articulo.setCategoria(articuloRequest.getCategoria());
		
			return repository.save(articulo);
		})
		.orElseThrow(() -> new ResourceNotFoundException("Articulo ", "id", id));
	}
    
	// elimina un articulo
	
	@DeleteMapping("/delete")
    public ResponseEntity<Object> deleteArticulo(@RequestParam int id) {
		try {
			Articulo a = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Articulo no encontrado:  ", null, id));
			repository.delete(a);
		}catch(DataIntegrityViolationException sqle) {
			throw new ResourceNotFoundException("No se puede eliminar el Articulo ya que existe en registrada una venta", null, id);
		}catch(Exception e) {
			
		}
		
		return ResponseEntity.ok().build();
    }
	

}
