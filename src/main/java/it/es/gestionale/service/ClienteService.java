package it.es.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.es.gestionale.repository.UtenteDB;
import it.es.gestionale.model.ClienteEntity;
import it.es.gestionale.repository.ClienteDB;



@Service
public class ClienteService {

	@Autowired
	ClienteDB db;

	public List<ClienteEntity> getList() {
		return db.findAll(); // Passacarte
	}

	public ClienteEntity save(ClienteEntity e) {
		return db.save(e);
	}

	public void delete(int id) {
		db.delete(db.getById(id));
	}

	public ClienteEntity getByid(int id) {
		return db.findById(id).orElse(new ClienteEntity());
	}
}
