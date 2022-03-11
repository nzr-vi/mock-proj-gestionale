package it.es.gestionale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.es.gestionale.model.ClienteEntity;


@Repository
public interface ClienteDB extends JpaRepository<ClienteEntity, Integer>{
	
}