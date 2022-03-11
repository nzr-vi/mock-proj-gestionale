package it.es.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.repository.UtenteDB;


@Service
public class UtenteService {

	@Autowired
	UtenteDB db;

	public List<UtenteEntity> getList() {
		return db.findAll(); 
	}

	public UtenteEntity save(UtenteEntity e) {
		return db.save(e);
	}

	public void delete(int id) {
		db.delete(db.getById(id));
	}

	public UtenteEntity getByid(int id) {
		return db.findById(id).orElse(new UtenteEntity());
	}
	
	public UtenteEntity getByEmail(String email) {
		return db.findByEmailIgnoreCase(email).orElse(null);
	}
}
