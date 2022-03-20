package it.es.gestionale.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.es.gestionale.model.ClienteEntity;
import it.es.gestionale.repository.ClienteDB;



@Service
public class ClienteService {

	@Autowired
	ClienteDB db;

	public List<ClienteEntity> findAll() {
		return db.findAll(); // Passacarte
	}

	public ClienteEntity save(ClienteEntity e) {
		return db.save(e);
	}

	public void delete(int id) {
		db.delete(db.getById(id));
	}

	public ClienteEntity getById(int id) {
		return db.findById(id).orElse(new ClienteEntity());
	}

	public List<ClienteEntity> getClienteByNome(String nome) {
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

	public List<ClienteEntity> getClienteByCognome(String cognome) {
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

	public List<ClienteEntity> getClienteByEmail(String email) {
		return db.findByEmail(email);
	}

	public List<String> getEmail() {
		return findAll()
				.stream()
				.map(a -> a.getEmail())
				.distinct()
				.sorted()
				.collect(Collectors.toList())
				;
	}

}
