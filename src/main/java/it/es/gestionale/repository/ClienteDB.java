package it.es.gestionale.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.es.gestionale.model.ClienteEntity;


@Repository
public interface ClienteDB extends JpaRepository<ClienteEntity, Integer>{

	Optional<ClienteEntity> findByUtenteId(int id);
	Optional<ClienteEntity> findById(int id);
	@Query(nativeQuery = true, 
			value = "select * from cliente c, utente u  "
					+ "where c.utente_id = u.id && u.nome like ?1%")
    List<ClienteEntity> findByNome(String nome);
	
	@Query(nativeQuery = true, 
			value = "select * from cliente c, utente u  "
					+ "where c.utente_id = u.id && u.cognome like ?1%")
	List<ClienteEntity> findByCognome(String cognome);
	
	@Query(nativeQuery = true, 
			value = "select * from cliente c, utente u  "
					+ "where c.utente_id = u.id && u.email like ?1%")
	List<ClienteEntity> findByEmail(String email);
	
}