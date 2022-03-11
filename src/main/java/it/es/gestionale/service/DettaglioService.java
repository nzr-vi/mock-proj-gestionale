package it.es.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.es.gestionale.model.DettaglioEntity;
import it.es.gestionale.model.DettaglioKey;
import it.es.gestionale.repository.DettaglioDB;



@Service
public class DettaglioService {
	
	@Autowired
	DettaglioDB db;
	
	public List<DettaglioEntity> getLista() {
	return db.findAll();	//Passacarte
	}
	
	public DettaglioEntity save(DettaglioEntity e){
	return db.save(e);
	}

	public void delete(DettaglioKey id) {
	db.delete(db.getById(id));
	}
	
	// public DettaglioEntity getByid(int id) {
	// return dbEdi.findById(id).orElse(new DettaglioEntity());
	// }

}
