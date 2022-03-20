package it.es.gestionale.integration;

import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	public List<ArticoloEntity> getLista(){
		return srv.findAll();
		 
	}
	
	@CrossOrigin
	@GetMapping("price_range")
	public List<ArticoloEntity> getByPrice(@RequestParam("min") double min, @RequestParam("max") double max){
		return srv.getByPrice(min, max);
	}
	
	@CrossOrigin
	@GetMapping("des/{descrizione}")
	public List<ArticoloEntity> getByDesc(@PathVariable("descrizione") String descrizione){
		return srv.getArticoliByDescrizione(descrizione);
	}
	
	@CrossOrigin
	@GetMapping("des_bw/{descrizione}")
	public List<ArticoloEntity> getByDescBeginWith(@PathVariable("descrizione") String descrizione){
		return srv.getArticoloDescStartWith(descrizione);
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

	@CrossOrigin
	@DeleteMapping()
	public ResponseEntity<ArticoloEntity> deleteById(
			@SessionAttribute(name = "utente") UtenteEntity utente, 
			@RequestParam int id
			)
	{
		if(utente.getRuolo()==Role.SUPERVISORE)
		{
			this.srv.delete(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@CrossOrigin
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<ArticoloEntity> save(@SessionAttribute(name = "utente") UtenteEntity utente,
			@RequestBody ArticoloEntity a) {

		if(utente.getRuolo()==Role.SUPERVISORE)
		{
			a = srv.save(a);
			return new ResponseEntity<ArticoloEntity>(a, HttpStatus.OK);	
		}
		return ResponseEntity.badRequest().build();
	}

	@CrossOrigin
    @PutMapping
	public ResponseEntity<ArticoloEntity> putOne(@SessionAttribute(name = "utente") UtenteEntity utente,
            @RequestBody ArticoloEntity a) { 

        if(utente.getRuolo()!=Role.SUPERVISORE || srv.getById(a.getId()) == null) {
			return new ResponseEntity<ArticoloEntity>(a, HttpStatus.BAD_REQUEST);	
		}else {
	
			a = srv.save(a);
			return new ResponseEntity<ArticoloEntity>(a, HttpStatus.OK);			
		}
		
	}
    
	@CrossOrigin
	@PostMapping("/import")
	public ResponseEntity<?> insertCSV(@SessionAttribute("utente") UtenteEntity user,
			@RequestParam("fileCSV") MultipartFile file) {
		if(user.getRuolo()==Role.SUPERVISORE)
		{
			this.srv.importCsv(file);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
}
