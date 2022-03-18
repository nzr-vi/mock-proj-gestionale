package it.es.gestionale.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.repository.ArticoloDB;


@Service
public class ArticoloService {

	@Autowired
	ArticoloDB db;

	
	public List<ArticoloEntity> getByPrice(double min, double max) {
		return db.findByPrezzoBetween(min,max); // Passacarte
	}
	
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
	public void importCsv(MultipartFile file) {
		try {
			
			BufferedReader buffer = new BufferedReader(
					new InputStreamReader(file.getInputStream(), "UTF-8"));
			
			CsvToBean<ArticoloEntity> csv = new CsvToBeanBuilder<ArticoloEntity>(buffer)
					.withSeparator(';')
					.withIgnoreLeadingWhiteSpace(true)
					.withType(ArticoloEntity.class).build();

			var listaCsv = csv.parse();
			db.saveAll(listaCsv);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
