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
	DettaglioDB dbEdi;
	
	public List<DettaglioEntity> getLista() {
	return dbEdi.findAll();	//Passacarte
	}
	
	public DettaglioEntity save(DettaglioEntity e){
	return dbEdi.save(e);
	}

	public void delete(DettaglioKey id) {
	dbEdi.delete(dbEdi.getById(id));
	}
	
	// public DettaglioEntity getByid(int id) {
	// return dbEdi.findById(id).orElse(new DettaglioEntity());
	// }

}
