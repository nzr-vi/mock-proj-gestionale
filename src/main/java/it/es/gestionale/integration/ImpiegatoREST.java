package it.es.gestionale.integration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import it.es.gestionale.model.ImpiegatoEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.service.ImpiegatoService;

@RestController
@RequestMapping("/api/impiegato")
@SessionAttributes("utente")
public class ImpiegatoREST {

	@Autowired
	ImpiegatoService srv;

	@GetMapping
	public List<ImpiegatoEntity> getLista(){
		return srv.findAll();
		 
	}
	
	@CrossOrigin
	@GetMapping("price_range")
	public List<ImpiegatoEntity> getByStipendio(@SessionAttribute(name = "utente") UtenteEntity user,
			@RequestParam("min") double min, @RequestParam("max") double max){
		if(user.getRuolo() != Role.supervisore) {
    		return new ArrayList<>();
    	}
		return srv.getByStipendio(min, max);
	}
	
	@CrossOrigin
	@GetMapping("ruo/{ruolo}")
	public List<ImpiegatoEntity> getByRuolo(@SessionAttribute(name = "utente") UtenteEntity user,
			@PathVariable("ruolo") String ruolo){
		if(user.getRuolo() != Role.supervisore) {
    		return new ArrayList<>();
    	}
		return srv.getImpiegatoByRuolo(ruolo);
	}
	
	@CrossOrigin
	@GetMapping("ruolo")
	public List<String> getRuolo(@SessionAttribute(name = "utente") UtenteEntity user){
		if(user.getRuolo() != Role.supervisore) {
    		return new ArrayList<>();
    	}
		return this.srv.getRuolo();
	}
	
	@CrossOrigin
	@GetMapping("nom/{nome}")
	public List<ImpiegatoEntity> getByCat(@SessionAttribute(name = "utente") UtenteEntity user,
			@PathVariable("nome") String nome){
		if(user.getRuolo() != Role.supervisore) {
    		return new ArrayList<>();
    	}
		return srv.getImpiegatoByNome(nome);
	}
	
	@CrossOrigin
	@GetMapping("nome")
	public List<String> getNome(@SessionAttribute(name = "utente") UtenteEntity user){
		if(user.getRuolo() != Role.supervisore) {
    		return new ArrayList<>();
    	}
		return this.srv.getNome();
	}
	
	@CrossOrigin
	@GetMapping("cog/{cognome}")
	public List<ImpiegatoEntity> getByCognome(@SessionAttribute(name = "utente") UtenteEntity user,
			@PathVariable("cognome") String cognome){
		if(user.getRuolo() != Role.supervisore) {
    		return new ArrayList<>();
    	}
		return srv.getImpiegatoByCognome(cognome);
	}
	
	@CrossOrigin
	@GetMapping("cognome")
	public List<String> getCognome(@SessionAttribute(name = "utente") UtenteEntity user){
		if(user.getRuolo() != Role.supervisore) {
    		return new ArrayList<>();
    	}
		return this.srv.getCognome();
	}
//	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
//	public ResponseEntity<ArticoloEntity> save(@SessionAttribute(name = "utente") UtenteEntity utente,
//			@RequestBody ArticoloEntity a) {
//
//		if(utente.getRuolo()==Role.supervisore)
//		{
//			a = srv.save(a);
//			return new ResponseEntity<ArticoloEntity>(a, HttpStatus.OK);	
//		}
//		return ResponseEntity.badRequest().build();
//	}
//
////Update
//    @PutMapping
//	public ResponseEntity<ArticoloEntity> putOne(@SessionAttribute(name = "utente") UtenteEntity utente,
//            @RequestBody ArticoloEntity a) { 
//
//        if(utente.getRuolo()!=Role.supervisore || srv.getById(a.getId()) == null) {
//			return new ResponseEntity<ArticoloEntity>(a, HttpStatus.BAD_REQUEST);	
//		}else {
//			// salvo, e restituisco lo studente con i campi aggiornati
//			a = srv.save(a);
//			return new ResponseEntity<ArticoloEntity>(a, HttpStatus.OK);			
//		}
//		
//	}
}
