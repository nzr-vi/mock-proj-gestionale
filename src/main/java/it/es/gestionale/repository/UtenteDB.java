package it.es.gestionale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.es.gestionale.model.UtenteEntity;

@Repository
public interface UtenteDB extends JpaRepository<UtenteEntity, Integer>{
	
	Optional<UtenteEntity> findByEmailIgnoreCase(String email);
}

