package com.apirestvault.apirestvault.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestvault.apirestvault.entities.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}