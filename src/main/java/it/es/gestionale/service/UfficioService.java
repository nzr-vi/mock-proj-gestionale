package it.es.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.es.gestionale.model.UfficioEntity;
import it.es.gestionale.repository.UfficioDB;



@Service
public class UfficioService {

	@Autowired
	UfficioDB db;

	public List<UfficioEntity> getList() {
		return db.findAll();
	}

	public UfficioEntity save(UfficioEntity e) {
		return db.save(e);
	}

	public void delete(int id) {
		db.delete(db.getById(id));
	}

	public UfficioEntity getByid(int id) {
		return db.findById(id).orElse(new UfficioEntity());
	}
}
