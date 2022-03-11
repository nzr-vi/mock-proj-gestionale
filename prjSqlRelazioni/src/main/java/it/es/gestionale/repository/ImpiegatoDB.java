package it.es.gestionale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.es.gestionale.model.ImpiegatoEntity;

@Repository
public interface ImpiegatoDB extends JpaRepository<ImpiegatoEntity, Integer>{
	
}

