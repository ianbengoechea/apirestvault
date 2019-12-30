package com.apirestvault.apirestvault.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestvault.apirestvault.entities.Categoria;
import com.apirestvault.apirestvault.repository.CategoriaRepository;


@RestController
@RequestMapping({"/categorias"})
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;


	@GetMapping
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
}