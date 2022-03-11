package it.es.ordine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.es.gestionale.model.OrdineEntity;
import it.es.gestionale.repository.OrdineDB;


@Service
public class OrdineService {

	@Autowired
	OrdineDB ordineDb;

	public List<OrdineEntity> getList() {
		return ordineDb.findAll(); 
	}

	public OrdineEntity save(OrdineEntity e) {
		return ordineDb.save(e);
	}

	public void delete(int id) {
		ordineDb.delete(ordineDb.getById(id));
	}

	public OrdineEntity getByid(int id) {
		return ordineDb.findById(id).orElse(new OrdineEntity());
	}
}
