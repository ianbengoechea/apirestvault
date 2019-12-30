package com.apirestvault.apirestvault.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirestvault.apirestvault.entities.Cliente;
import com.apirestvault.apirestvault.exception.ResourceNotFoundException;
import com.apirestvault.apirestvault.repository.ClienteRepository;

@RestController
@RequestMapping({"/clientes"})
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	//devolver todos los registros
	
	@GetMapping
	public List<Cliente> findAll(){
		return repository.findAll();
	}
	
	// crea un cliente
    
	@PostMapping("/new")
    public Cliente create(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }
    
//	// modifica un cliente
	
	@PutMapping("/edit")
	public Cliente show(@RequestParam int id_cliente,
            @Valid @RequestBody Cliente clienteRequest) {
		
		if(!repository.existsById(id_cliente)) {
			throw new ResourceNotFoundException("Cliente ","id",id_cliente);
		}
		
		return repository.findById(id_cliente).map(cliente -> {
			cliente.setEmpresa(clienteRequest.getEmpresa());
			cliente.setCountry(clienteRequest.getCountry());
			cliente.setEmail(clienteRequest.getEmail());
			cliente.setFirstname(clienteRequest.getFirstname());
			cliente.setLastname(clienteRequest.getLastname());
			cliente.setTelephone(clienteRequest.getTelephone());
		
			return repository.save(cliente);
		})
		.orElseThrow(() -> new ResourceNotFoundException("Cliente ", "id", id_cliente));
	}
    
	// elimina un cliente
	
	@DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCliente(@RequestParam int id_cliente) {
        return repository.findById(id_cliente)
                .map(cliente -> {
                    repository.delete(cliente);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Articulo no encontrado:  ", null, id_cliente));
    }

}