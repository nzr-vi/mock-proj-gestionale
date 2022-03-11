package it.es.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.es.gestionale.model.ImpiegatoEntity;
import it.es.gestionale.repository.ImpiegatoDB;

@Service
public class ImpiegatoService {
	
	@Autowired
	ImpiegatoDB db;
	
	public List<ImpiegatoEntity> getLista() {
	return db.findAll();	//Passacarte
	}
	
	public ImpiegatoEntity save(ImpiegatoEntity e){
	return db.save(e);
	}

	public void delete(int id) {
	db.delete(db.getById(id));
	}
	
	public ImpiegatoEntity getByid(int id) {
	return db.findById(id).orElse(new ImpiegatoEntity());
	}

}
