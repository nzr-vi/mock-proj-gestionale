package it.es.gestionale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import it.es.gestionale.model.ArticoloEntity;


@Repository
public interface ArticoloDB extends JpaRepository<ArticoloEntity, Integer>{

  
	
}

