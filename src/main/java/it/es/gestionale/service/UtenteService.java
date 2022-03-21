package it.es.gestionale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.repository.UtenteDB;


@Service
public class UtenteService {

	@Autowired
	UtenteDB db;

	public UtenteEntity createNew(
			String nome, String cognome, String email, Role ruolo) {
		UtenteEntity user = new UtenteEntity();
		user.setNome(nome);
		user.setCognome(cognome);
		user.setEmail(email);
		user.setPassword(email);
		user.setRuolo(ruolo);
		return this.save(user);
		
	}
	
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
	
	public Optional<UtenteEntity> findByEmail(String email) {
		return db.findByEmailIgnoreCase(email);
	}
	
	public UtenteEntity getByEmail(String email) {
		return db.findByEmailIgnoreCase(email).orElse(null);
	}
}
