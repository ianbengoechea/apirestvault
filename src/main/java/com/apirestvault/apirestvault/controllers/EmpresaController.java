package com.apirestvault.apirestvault.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestvault.apirestvault.entities.Empresa;
import com.apirestvault.apirestvault.repository.EmpresaRepository;


@RestController
@RequestMapping({"/empresas"})
public class EmpresaController {

	@Autowired
	private EmpresaRepository repository;


	@GetMapping
	public List<Empresa> findAll(){
		return repository.findAll();
	}
	
}