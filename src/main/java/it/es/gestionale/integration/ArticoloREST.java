package it.es.gestionale.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.service.ArticoloService;

@RestController
@RequestMapping("/api/articolo")
@SessionAttributes("utente")
public class ArticoloREST {

	@Autowired
	ArticoloService srv;

	@GetMapping
	public List<ArticoloEntity> allArticoli() {
		return srv.findAll();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<ArticoloEntity> save(@SessionAttribute(name = "utente") UtenteEntity utente,
			@RequestBody ArticoloEntity a) {

		if(utente.getRuolo()==Role.supervisore)
		{
			a = srv.save(a);
			return new ResponseEntity<ArticoloEntity>(a, HttpStatus.OK);	
		}
		return ResponseEntity.badRequest().build();
	}

//Update
    @PutMapping
	public ResponseEntity<ArticoloEntity> putOne(@SessionAttribute(name = "utente") UtenteEntity utente,
            @RequestBody ArticoloEntity a) { 

        if(utente.getRuolo()!=Role.supervisore || srv.getById(a.getId()) == null) {
			return new ResponseEntity<ArticoloEntity>(a, HttpStatus.BAD_REQUEST);	
		}else {
			// salvo, e restituisco lo studente con i campi aggiornati
			a = srv.save(a);
			return new ResponseEntity<ArticoloEntity>(a, HttpStatus.OK);			
		}
		
	}
}
