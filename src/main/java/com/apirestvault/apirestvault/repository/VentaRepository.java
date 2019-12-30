package com.apirestvault.apirestvault.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apirestvault.apirestvault.entities.Venta;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {


	
	@Query("SELECT v FROM Venta v WHERE v.fechaVenta >= :from AND v.fechaVenta <= :to")
	List<Venta> findOperations(
			@Param("from") java.util.Date start,
			@Param("to") java.util.Date end);
}