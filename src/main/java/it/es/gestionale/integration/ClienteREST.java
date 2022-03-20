package it.es.gestionale.integration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import it.es.gestionale.model.ClienteEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.service.ClienteService;

@RestController
@RequestMapping("/api/clienti")
@SessionAttributes("utente")
public class ClienteREST {

	@Autowired
	ClienteService srv;

	@GetMapping
	public List<ClienteEntity> getLista() {
		return srv.findAll();

	}

	@CrossOrigin
	@GetMapping("nom/{nome}")
	public List<ClienteEntity> getByNome(@SessionAttribute(name = "utente") UtenteEntity utente,
			@PathVariable("nome") String nome) {

		if (utente.getRuolo() != Role.SUPERVISORE && utente.getRuolo() != Role.IMPIEGATO)
			return new ArrayList<>();

		return srv.getClienteByNome(nome);
	}

	@CrossOrigin
	@GetMapping("nome")
	public List<String> getNome(@SessionAttribute(name = "utente") UtenteEntity utente) {

		if (utente.getRuolo() != Role.SUPERVISORE && utente.getRuolo() != Role.IMPIEGATO)
			return new ArrayList<>();

		return this.srv.getNome();
	}

	@CrossOrigin
	@GetMapping("cog/{cognome}")
	public List<ClienteEntity> getByCognome(@SessionAttribute(name = "utente") UtenteEntity utente,
			@PathVariable("cognome") String cognome) {

		if (utente.getRuolo() != Role.SUPERVISORE && utente.getRuolo() != Role.IMPIEGATO)
			return new ArrayList<>();

		return srv.getClienteByCognome(cognome);
	}

	@CrossOrigin
	@GetMapping("cognome")
	public List<String> getCognome(@SessionAttribute(name = "utente") UtenteEntity utente) {

		if (utente.getRuolo() != Role.SUPERVISORE && utente.getRuolo() != Role.IMPIEGATO)
			return new ArrayList<>();

		return this.srv.getCognome();
	}

	@CrossOrigin
	@GetMapping("ema/{email}")
	public List<ClienteEntity> getByEmail(@SessionAttribute(name = "utente") UtenteEntity utente,
			@PathVariable("email") String email) {

		if (utente.getRuolo() != Role.SUPERVISORE && utente.getRuolo() != Role.IMPIEGATO)
			return new ArrayList<>();

		return srv.getClienteByEmail(email);
	}

	@CrossOrigin
	@GetMapping("email")
	public List<String> getEmail(@SessionAttribute(name = "utente") UtenteEntity utente) {

		if (utente.getRuolo() != Role.SUPERVISORE && utente.getRuolo() != Role.IMPIEGATO)
			return new ArrayList<>();

		return this.srv.getEmail();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<ClienteEntity> save(@SessionAttribute(name = "utente") UtenteEntity utente,
			@RequestBody ClienteEntity c) {

		if (utente.getRuolo() != Role.SUPERVISORE && utente.getRuolo() != Role.IMPIEGATO)
			return ResponseEntity.badRequest().build();

		// TODO
		// c = srv.save(c);
		return new ResponseEntity<ClienteEntity>(c, HttpStatus.OK);
	}

	// Update
	@PutMapping
	public ResponseEntity<ClienteEntity> putOne(@SessionAttribute(name = "utente") UtenteEntity utente,
			@RequestBody ClienteEntity c) {

		if (utente.getRuolo() != Role.SUPERVISORE && utente.getRuolo() != Role.IMPIEGATO
				&& c.getUtente().getId() != utente.getId())
			return ResponseEntity.badRequest().build();

		//TODO
		//c = srv.save(c);
		return new ResponseEntity<ClienteEntity>(c, HttpStatus.OK);
	}
}
