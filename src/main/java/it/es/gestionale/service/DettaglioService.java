package it.es.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.es.gestionale.model.DettaglioEntity;
import it.es.gestionale.repository.DettaglioDB;

@Service
public class DettaglioService {

	@Autowired
	DettaglioDB db;

	public List<DettaglioEntity> getList() {
		return db.findAll();
	}

	public DettaglioEntity save(DettaglioEntity e) {
		return db.save(e);
	}

	// public void delete(int id) {
	// 	db.delete(db.getById(id));
	// }

	// public DettaglioEntity getByid(int id) {
	// 	return db.findById(id).orElse(new DettaglioEntity());
	// }
}
