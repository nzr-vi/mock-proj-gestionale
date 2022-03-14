package it.es.gestionale.integration;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.model.EsempioModel;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.service.ArticoloService;
import it.es.gestionale.service.UtenteService;

@RestController
@RequestMapping("/api/articoli")
public class ArticoloREST {

	@Autowired
	ArticoloService srv;

	@GetMapping
	public List<ArticoloEntity> getLista(){
		return srv.getList();
		 
	}
	
	@CrossOrigin
	@GetMapping("desc/{descrizione}")
	public List<ArticoloEntity> getByDesc(@PathVariable("descrizione") String descrizione){
		return srv.getArticoliByDescrizione(descrizione);
	}
	
	@CrossOrigin
	@GetMapping("descrizione")
	public List<String> getDescrizione(){
		return this.srv.getDescrizione();
	}
	@CrossOrigin
	@GetMapping("cat/{categoria}")
	public List<ArticoloEntity> getByCat(@PathVariable("categoria") String categoria){
		return srv.getArticoliByCategoria(categoria);
	}
	
	@CrossOrigin
	@GetMapping("categoria")
	public List<String> getCategoria(){
		return this.srv.getCategoria();
	}
}

