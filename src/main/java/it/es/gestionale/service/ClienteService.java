package it.es.gestionale.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

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

	public ClienteEntity create(ClienteEntity e) {
		e.setId(-1);
		return db.save(e);
	}
	
	public ClienteEntity update(ClienteEntity e) {
		if(db.findById(e.getId()).isPresent()){			
			return db.save(e);
		}
		throw new EntityNotFoundException("cliente with ID: "+e.getId()+" not found");
	}

	public void delete(int id) {
		var cliente = db.findById(id);
		
		if(cliente.isEmpty())
			throw new EntityNotFoundException("cliente with ID: "+id+" not found");

		db.delete(cliente.get());
	}

	public Optional<ClienteEntity> findById(int id) {
		return db.findById(id);
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
