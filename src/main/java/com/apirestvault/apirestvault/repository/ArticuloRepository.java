package com.apirestvault.apirestvault.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apirestvault.apirestvault.entities.Articulo;


@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {

	
	@Query("select a from Articulo a WHERE categoria.idCategoria = :idCategoria")
	public List<Articulo> findArticuloByCategory(Integer idCategoria);

	
	
	
}