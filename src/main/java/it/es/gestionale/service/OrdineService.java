package it.es.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.es.gestionale.model.OrdineEntity;
import it.es.gestionale.repository.OrdineDB;


@Service
public class OrdineService {

	@Autowired
	OrdineDB db;

	public List<OrdineEntity> getList() {
		return db.findAll(); 
	}

	public OrdineEntity save(OrdineEntity e) {
		return db.save(e);
	}

	public void delete(int id) {
		db.delete(db.getById(id));
	}

	public OrdineEntity getByid(int id) {
		return db.findById(id).orElse(new OrdineEntity());
	}
}
