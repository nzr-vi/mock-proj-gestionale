package it.es.gestionale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.es.gestionale.model.UfficioEntity;

@Repository
public interface UfficioDB extends JpaRepository<UfficioEntity, Integer>{
	
}

