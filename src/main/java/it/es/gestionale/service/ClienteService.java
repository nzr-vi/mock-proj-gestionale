package it.es.gestionale.service;


import static it.es.gestionale.utility.Errors.accessDeniedMVC;
import static it.es.gestionale.utility.Errors.genericErrorMVC;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.es.gestionale.dto.CreazioneClienteDto;
import it.es.gestionale.exception.CreationDtoException;
import it.es.gestionale.model.ClienteEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.repository.ClienteDB;



@Service
public class ClienteService {

	@Autowired
	ClienteDB db;
	
	@Autowired
	UtenteService usrv;
	
	public ClienteEntity create(CreazioneClienteDto clienteDto) throws CreationDtoException {
		
		var email = clienteDto.getEmail();
		if(email.isBlank() || this.usrv.findByEmail(email).isPresent())
			throw new CreationDtoException("email is already registered");
			
		UtenteEntity user = new UtenteEntity();
		user.setNome(clienteDto.getNome());
		user.setCognome(clienteDto.getCognome());
		user.setEmail(email);
		user.setPassword(email);
		user.setRuolo(Role.cliente);
		
		var newlyCreatedUser = this.usrv.save(user);
		
		ClienteEntity cliente = new ClienteEntity();
		cliente.setUtente(newlyCreatedUser);
		cliente.setCitta(clienteDto.getCitta());
		cliente.setCredito(0);
		cliente.setIndirizzo(clienteDto.getIndirizzo());
		cliente.setProvincia(clienteDto.getProvincia());
		cliente.setRegione(clienteDto.getRegione());
		cliente.setTelefono(clienteDto.getTelefono());
		
		return this.create(cliente);
	}
	
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
	
	public Optional<ClienteEntity> findByUtenteId(int id) {
		return db.findByUtenteId(id);
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
