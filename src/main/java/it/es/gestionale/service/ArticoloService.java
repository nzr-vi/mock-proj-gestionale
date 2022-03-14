package it.es.gestionale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.repository.ArticoloDB;


@Service
public class ArticoloService {

	@Autowired
	ArticoloDB db;

	public List<ArticoloEntity> findAll() {
		return db.findAll(); // Passacarte
	}

	public ArticoloEntity save(ArticoloEntity a) {
		return db.save(a);
	}

	public void delete(int id) {
		db.delete(db.getById(id));
	}

	public ArticoloEntity getById(int id) {
		return db.findById(id).orElse(new ArticoloEntity());
	}

	public List<ArticoloEntity> getArticoliByDescrizione(String descrizione) {
		return db.findByDescrizione(descrizione);
	}

	public List<String> getDescrizione() {
		return findAll()
				.stream()
				.map(a -> a.getDescrizione())
				.distinct()
				.sorted()
				.collect(Collectors.toList())
				;
	}

	public List<ArticoloEntity> getArticoliByCategoria(String categoria) {
		return db.findByCategoria(categoria);
	}

	public List<String> getCategoria() {
		return findAll()
				.stream()
				.map(a -> a.getCategoria())
				.distinct()
				.sorted()
				.collect(Collectors.toList())
				;
	}
}
