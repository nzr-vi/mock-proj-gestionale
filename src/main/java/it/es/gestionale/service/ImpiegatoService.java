package it.es.gestionale.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.es.gestionale.model.ImpiegatoEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.repository.ImpiegatoDB;

@Service
public class ImpiegatoService {
	
	@Autowired
	ImpiegatoDB db;
	
	public List<ImpiegatoEntity> findAll() {
	return db.findAll();	//Passacarte
	}
	
	public ImpiegatoEntity save(ImpiegatoEntity e){
	return db.save(e);
	}

	public void delete(int id) {
	db.delete(db.getById(id));
	}
	
	public ImpiegatoEntity getByid(int id) {
	return db.findById(id).orElse(new ImpiegatoEntity());
	}

	public List<ImpiegatoEntity> getByStipendio(double min, double max) {
		return db.findByStipendioBetween(min, max);
	}

	public List<ImpiegatoEntity> getImpiegatoByRuolo(String ruolo) {
		return db.findByRuolo(ruolo);
	}

	public List<String> getRuolo() {
		return Arrays.asList(Role.impiegato.toString(), Role.supervisore.toString());
	}

	public List<ImpiegatoEntity> getImpiegatoByNome(String nome) {
		return db.findByNomeStartsWith(nome);
		}

	public List<ImpiegatoEntity> getImpiegatoByCognome(String cognome) {
		return db.findByCognomeStartsWith(cognome);	
	}

}
