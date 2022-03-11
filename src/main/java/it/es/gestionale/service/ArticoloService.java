package it.es.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.repository.ArticoloDB;


@Service
public class ArticoloService {

	@Autowired
	ArticoloDB db;

	public List<ArticoloEntity> getList() {
		return db.findAll(); // Passacarte
	}

	public ArticoloEntity save(ArticoloEntity e) {
		return db.save(e);
	}

	public void delete(int id) {
		db.delete(db.getById(id));
	}

	public ArticoloEntity getByid(int id) {
		return db.findById(id).orElse(new ArticoloEntity());
	}
}