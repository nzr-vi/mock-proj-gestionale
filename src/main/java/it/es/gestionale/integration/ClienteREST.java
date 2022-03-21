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

import it.es.gestionale.dto.CreazioneClienteDto;
import it.es.gestionale.dto.ModificaClienteAsClienteDto;
import it.es.gestionale.dto.ModificaClienteDto;
import it.es.gestionale.model.ClienteEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.service.ClienteService;
import it.es.gestionale.service.UtenteService;

@RestController
@RequestMapping("/api/clienti")
@SessionAttributes("utente")
public class ClienteREST {

	@Autowired
	ClienteService srv;
	
	@Autowired
	UtenteService userSrv;

	@GetMapping
	public List<ClienteEntity> getLista() {
		return srv.findAll();

	}

	@CrossOrigin
	@GetMapping("nom/{nome}")
	public List<ClienteEntity> getByNome(@SessionAttribute(name = "utente") UtenteEntity utente,
			@PathVariable("nome") String nome) {

		if (utente.getRuolo() != Role.supervisore && utente.getRuolo() != Role.impiegato)
			return new ArrayList<>();

		return srv.getClienteByNome(nome);
	}

	@CrossOrigin
	@GetMapping("nome")
	public List<String> getNome(@SessionAttribute(name = "utente") UtenteEntity utente) {

		if (utente.getRuolo() != Role.supervisore && utente.getRuolo() != Role.impiegato)
			return new ArrayList<>();

		return this.srv.getNome();
	}

	@CrossOrigin
	@GetMapping("cog/{cognome}")
	public List<ClienteEntity> getByCognome(@SessionAttribute(name = "utente") UtenteEntity utente,
			@PathVariable("cognome") String cognome) {

		if (utente.getRuolo() != Role.supervisore && utente.getRuolo() != Role.impiegato)
			return new ArrayList<>();

		return srv.getClienteByCognome(cognome);
	}

	@CrossOrigin
	@GetMapping("cognome")
	public List<String> getCognome(@SessionAttribute(name = "utente") UtenteEntity utente) {

		if (utente.getRuolo() != Role.supervisore && utente.getRuolo() != Role.impiegato)
			return new ArrayList<>();

		return this.srv.getCognome();
	}

	@CrossOrigin
	@GetMapping("ema/{email}")
	public List<ClienteEntity> getByEmail(@SessionAttribute(name = "utente") UtenteEntity utente,
			@PathVariable("email") String email) {

		if (utente.getRuolo() != Role.supervisore && utente.getRuolo() != Role.impiegato)
			return new ArrayList<>();

		return srv.getClienteByEmail(email);
	}

	@CrossOrigin
	@GetMapping("email")
	public List<String> getEmail(@SessionAttribute(name = "utente") UtenteEntity utente) {

		if (utente.getRuolo() != Role.supervisore && utente.getRuolo() != Role.impiegato)
			return new ArrayList<>();

		return this.srv.getEmail();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<ClienteEntity> save(
			@SessionAttribute(name = "utente") UtenteEntity utente,
			@RequestBody CreazioneClienteDto clienteDto) {

		if (utente.getRuolo() != Role.supervisore && utente.getRuolo() != Role.impiegato)
			return ResponseEntity.badRequest().build();

		var email = clienteDto.getEmail();
		if(email.isBlank() || this.userSrv.findByEmail(email).isPresent())
			return ResponseEntity.unprocessableEntity().build();
		
		UtenteEntity user = new UtenteEntity();
		user.setNome(clienteDto.getNome());
		user.setCognome(clienteDto.getCognome());
		user.setEmail(email);
		user.setPassword(email);
		user.setRuolo(Role.cliente);
		
		var newlyCreatedUser = this.userSrv.save(user);
		
		ClienteEntity cliente = new ClienteEntity();
		cliente.setUtente(newlyCreatedUser);
		cliente.setCitta(clienteDto.getCitta());
		cliente.setCredito(clienteDto.getCredito());
		cliente.setIndirizzo(clienteDto.getIndirizzo());
		cliente.setProvincia(clienteDto.getProvincia());
		cliente.setRegione(clienteDto.getRegione());
		cliente.setTelefono(clienteDto.getTelefono());
		
		var newlyCreatedClinte = this.srv.create(cliente);
		return new ResponseEntity<>(newlyCreatedClinte, HttpStatus.OK);
	}

	// Update
	@PutMapping
	public ResponseEntity<ClienteEntity> updateFromClient(
			@SessionAttribute(name = "utente") UtenteEntity utente,
			@RequestBody ModificaClienteAsClienteDto clienteDto) {

		var userRole = utente.getRuolo();		
		var optClientToUpd = this.srv.findById(clienteDto.getId());
		if(optClientToUpd.isPresent()) {
			
			var email = clienteDto.getEmail();
			var clientToUpd = optClientToUpd.get();
			
			if (clientToUpd.getUtente() != utente)
				return ResponseEntity.badRequest().build();
			
			if(!clientToUpd.getEmail().equals(email)) {
				//email change, so has to be unique!
				if(email.isBlank() || this.userSrv.findByEmail(email).isPresent()) {
					//email already used by someone else
					return ResponseEntity.unprocessableEntity().build();
				}
			}
			
			clientToUpd.setCitta(clienteDto.getCitta());
			clientToUpd.setIndirizzo(clienteDto.getIndirizzo());
			clientToUpd.setCognome(clienteDto.getCognome());
			utente.setPassword(clienteDto.getPassword());
			clientToUpd.setEmail(clienteDto.getEmail());
			clientToUpd.setNome(clienteDto.getNome());
			clientToUpd.setProvincia(clienteDto.getProvincia());
			clientToUpd.setRegione(clienteDto.getRegione());
			clientToUpd.setTelefono(clienteDto.getTelefono());
			
			this.srv.update(clientToUpd);
			this.userSrv.save(utente);
		}
		return ResponseEntity.badRequest().build();
	}
	
	// Update
	@PutMapping("/super")
	public ResponseEntity<ClienteEntity> updateFromSuper(
			@SessionAttribute(name = "utente") UtenteEntity utente,
			@RequestBody ModificaClienteDto clienteDto) {

		var userRole = utente.getRuolo();
		if (userRole != Role.supervisore && userRole != Role.impiegato)
			return ResponseEntity.badRequest().build();

		var optClientToUpd = this.srv.findById(clienteDto.getId());
		if(optClientToUpd.isPresent()) {
			var email = clienteDto.getEmail();
			var clientToUpd = optClientToUpd.get();
			
			if(!clientToUpd.getEmail().equals(email)) {
				//email change, so has to be unique!
				if(email.isBlank() || this.userSrv.findByEmail(email).isPresent()) {
					//email already used by someone else
					return ResponseEntity.unprocessableEntity().build();
				}
			}
			
			clientToUpd.setCitta(clienteDto.getCitta());
			clientToUpd.setIndirizzo(clienteDto.getIndirizzo());
			clientToUpd.setCognome(clienteDto.getCognome());
			clientToUpd.setCredito(clienteDto.getCredito());
			clientToUpd.setEmail(clienteDto.getEmail());
			clientToUpd.setNome(clienteDto.getNome());
			clientToUpd.setProvincia(clienteDto.getProvincia());
			clientToUpd.setRegione(clienteDto.getRegione());
			clientToUpd.setTelefono(clienteDto.getTelefono());
			
			this.srv.update(clientToUpd);
			this.userSrv.save(clientToUpd.getUtente());
		}
		return ResponseEntity.badRequest().build();
	}
}
