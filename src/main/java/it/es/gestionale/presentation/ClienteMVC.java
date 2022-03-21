package it.es.gestionale.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.es.gestionale.dto.CreazioneClienteDto;
import it.es.gestionale.dto.ModificaClienteAsClienteDto;
import it.es.gestionale.dto.ModificaClienteDto;
import it.es.gestionale.exception.CreationDtoException;
import it.es.gestionale.factory.ClienteDtoFactory;
import it.es.gestionale.model.ClienteEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.service.ClienteService;
import it.es.gestionale.service.UtenteService;
import static it.es.gestionale.utility.Errors.*;

@Controller
@RequestMapping("/cliente")
@SessionAttributes("utente")
public class ClienteMVC {

    @Autowired
    ClienteService srv;
	@Autowired
	UtenteService usrv;

    @GetMapping
    public String findAll(@SessionAttribute("utente") UtenteEntity user, Model model){
    	
        if( user.getRuolo()==Role.cliente ) {
        	//TODO CLI-4 nel caso di cliente ritorna una pagina modifica per il cliente
        	return "";
        }
        
    	model.addAttribute("isSuper",user.getRuolo()==Role.supervisore);
        model.addAttribute("clienti", srv.findAll());
        model.addAttribute("filters", new String[] {"Nome","Cognome","Email"});
        return "lista-clienti";
    }
	
	@GetMapping("/edit")
	public String toEditPageFromCliente(
			@SessionAttribute("utente") UtenteEntity user,
			Model model
			) { 

		//eseguiamo il comando solo se è un cliente a richiederlo
		if(user.getRuolo()==Role.cliente)
		{	
			var cliente = srv.findByUtenteId(user.getId());
			if(cliente.isPresent()) {
				model.addAttribute("cliente", ClienteDtoFactory.createAsCliente(cliente.get())); 
				return "modifica-cliente";
			}				
			
			return accessDeniedMVC(model);
		}
		return genericErrorMVC(model,"no /edit use for "+user.getRuolo());
	}
    
	@GetMapping("/{id}")
	public String toEditPage(
			@SessionAttribute("utente") UtenteEntity user,
			@PathVariable("id") int id, Model model
			) { 

		//eseguiamo il comando solo se è un impiegato o un supervisore a richiederlo
		if(user.getRuolo()==Role.impiegato || user.getRuolo()==Role.supervisore)
		{	
			ClienteEntity cliente = srv.getById(id);
			if(cliente!=null) {
				model.addAttribute("cliente", ClienteDtoFactory.create(cliente)); 
				return "modifica-cliente";
			}				
			
			//se non è trovato torniamo alla lista
			return "redirect:/cliente";
		}
		
		return accessDeniedMVC(model);
	}

	@PostMapping("/add") 
	public String addCliente(
			@SessionAttribute(name = "utente") UtenteEntity utente,
			Model model,
			CreazioneClienteDto clienteDto) {
		
		if (utente.getRuolo() != Role.supervisore && utente.getRuolo() != Role.impiegato)
			return accessDeniedMVC(model);
		
		try {
			this.srv.create(clienteDto);
			return this.findAll(utente, model);
		} catch (CreationDtoException e) {
			return genericErrorMVC(model, e.getMessage()); 
		}
	}

	@GetMapping("/add-form") 
	public String addForm(
			@SessionAttribute(name = "utente") UtenteEntity utente,
			Model model) {
        
		//controlla nel caso sia il cliente a richiedere la rotta torna un errore
		if(utente.getRuolo()!=Role.supervisore  &&	utente.getRuolo()!=Role.impiegato )
			return accessDeniedMVC(model);
		
		model.addAttribute("cliente", new UtenteEntity());	
		return "crea-cliente";
	}
	
	// Update
	@PutMapping
	public String updateFromClient(
			@SessionAttribute(name = "utente") UtenteEntity utente,
			Model model,
			ModificaClienteAsClienteDto clienteDto) {
	
		var optClientToUpd = this.srv.findById(clienteDto.getId());
		if(optClientToUpd.isPresent()) {
			
			var email = clienteDto.getEmail();
			var clientToUpd = optClientToUpd.get();
			
			if (clientToUpd.getUtente() != utente)
				return accessDeniedMVC(model);
			
			if(!clientToUpd.getEmail().equals(email)) {
				//email change, so has to be unique!
				if(email.isBlank() || this.usrv.findByEmail(email).isPresent()) {
					//email already used by someone else
					//rimane nella pag di modifica
					return "#";
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
			this.usrv.save(utente);
			//TODO client page
			return "";
		}
		return accessDeniedMVC(model);
	}
	
	// Update
	@PutMapping("/super")
	public String updateFromSuper(
			@SessionAttribute(name = "utente") UtenteEntity utente,
			Model model,
			ModificaClienteDto clienteDto) {

		var userRole = utente.getRuolo();
		if (userRole != Role.supervisore && userRole != Role.impiegato)
			return accessDeniedMVC(model);

		var optClientToUpd = this.srv.findById(clienteDto.getId());
		if(optClientToUpd.isPresent()) {
			var email = clienteDto.getEmail();
			var clientToUpd = optClientToUpd.get();
			
			if(!clientToUpd.getEmail().equals(email)) {
				//email change, so has to be unique!
				if(email.isBlank() || this.usrv.findByEmail(email).isPresent()) {
					//email already used by someone else
					//TODO error
					return "";
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
			this.usrv.save(clientToUpd.getUtente());
			return this.findAll(utente, model);
		}
		return accessDeniedMVC(model); 
	}
}
