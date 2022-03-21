package it.es.gestionale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.es.gestionale.model.ImpiegatoEntity;


@Repository
public interface ImpiegatoDB extends JpaRepository<ImpiegatoEntity, Integer>{

	List<ImpiegatoEntity> findByStipendioBetween(double min, double max);

	@Query(value = "Select * from impiegato i, utente u "
					+ "where i.utente_id = u.id and u.ruolo = ?1", nativeQuery = true)
	List<ImpiegatoEntity> findByRuolo(String ruolo);

	@Query(value = "Select * from impiegato i, utente u "
			+ "where i.utente_id = u.id and u.nome like ?1%", nativeQuery = true)
	List<ImpiegatoEntity> findByNomeStartsWith(String nome);

	@Query(value = "Select * from impiegato i, utente u "
			+ "where i.utente_id = u.id and u.cognome like ?1%", nativeQuery = true)
	List<ImpiegatoEntity> findByCognomeStartsWith(String cognome);
	
}

