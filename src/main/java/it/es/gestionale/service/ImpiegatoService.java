package it.es.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.es.gestionale.model.EsempioModel;
import it.es.gestionale.model.ImpiegatoEntity;
import it.es.gestionale.repository.ImpiegatoDB;

@Service
public class ImpiegatoService {
	
	@Autowired
	ImpiegatoDB dbEdi;
	
	public List<ImpiegatoEntity> getLista() {
	return dbEdi.findAll();	//Passacarte
	}
	
	public ImpiegatoEntity save(ImpiegatoEntity e){
	return dbEdi.save(e);
	}

	public void delete(int id) {
	dbEdi.delete(dbEdi.getById(id));
	}
	
	public ImpiegatoEntity getByid(int id) {
	return dbEdi.findById(id).orElse(new ImpiegatoEntity());
	}

}
