package it.es.gestionale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.es.gestionale.model.ImpiegatoEntity;
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
		return findAll()
				.stream()
				.map(a -> a.getRuolo())
				.distinct()
				.sorted()
				.collect(Collectors.toList())
				;
	}

	public List<ImpiegatoEntity> getImpiegatoByNome(String nome) {
		return db.findByNome(nome);
		}

	public List<String> getNome() {
		return findAll()
				.stream()
				.map(a -> a.getNome())
				.distinct()
				.sorted()
				.collect(Collectors.toList())
				;
	}

	public List<ImpiegatoEntity> getImpiegatoByCognome(String cognome) {
		return db.findByCognome(cognome);	
	}

	public List<String> getCognome() {
		return findAll()
				.stream()
				.map(a -> a.getCognome())
				.distinct()
				.sorted()
				.collect(Collectors.toList())
				;
	}
}
