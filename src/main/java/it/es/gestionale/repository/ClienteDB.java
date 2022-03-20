package it.es.gestionale.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.es.gestionale.model.ClienteEntity;


@Repository
public interface ClienteDB extends JpaRepository<ClienteEntity, Integer>{

	Optional<ClienteEntity> findById(int id);
    List<ClienteEntity> findByNome(String nome);
	List<ClienteEntity> findByCognome(String cognome);
	List<ClienteEntity> findByEmail(String email);
	
}