package it.es.gestionale.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;


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
    	
        if( user.getRuolo()==Role.CLIENTE ) {
        	//TODO CLI-4 nel caso di cliente ritorna una pagina modifica per il cliente
        	return "";
        }
        
    	model.addAttribute("isSuper",user.getRuolo()==Role.SUPERVISORE);
        model.addAttribute("clienti", srv.findAll());
        model.addAttribute("filters", new String[] {"Nome","Cognome","Email"});
        return "lista-clienti";
    }
	
	@GetMapping("/{id}")
	public String modifica(
			@SessionAttribute("utente") UtenteEntity user,
			@PathVariable("id") int id, Model model
			) { 

		//eseguiamo il comando solo se è un impiegato o un supervisore a richiederlo
		if(user.getRuolo()==Role.IMPIEGATO || user.getRuolo()==Role.SUPERVISORE)
		{	
			ClienteEntity cliente = srv.getById(id);
			if(cliente!=null) {
				model.addAttribute("cliente", cliente); 
				return "modifica-cliente";
			}				
			
			//se non è trovato torniamo alla lista
			return "redirect:/lista-clienti";
		}
		
		return accessDeniedMVC(model);
	}

	@PostMapping("/save") 
	public String saveUtente(
			@SessionAttribute(name = "utente") UtenteEntity utente,
			Model model,
			UtenteEntity u) {
		
		//TODO
		
		//sia l'impiegato che il sup possono aggiungere utenti
		if(utente.getRuolo()==Role.SUPERVISORE 
				||	utente.getRuolo()==Role.IMPIEGATO )
		{
			u = usrv.save(u);
			return "redirect:"; 
		}
		return accessDeniedMVC(model);
	}

	@PostMapping("/modifica") 
	public String modificaUtente(
			@SessionAttribute(name = "utente") UtenteEntity utente,
			Model model,
			ClienteEntity c) {
		
		if(utente.getRuolo()==Role.SUPERVISORE 
				||	utente.getRuolo()==Role.IMPIEGATO 
				||	( utente.getRuolo()==Role.CLIENTE && utente.getId()==c.getUtente().getId()))
		{
			srv.update(c);
			//TODO aggiungere redirect per modifica per il cliente
			return utente.getRuolo()==Role.CLIENTE?"":this.findAll(utente, model);
		}
		
		return "redirect:"; 
	}

	@GetMapping("/add-form") 
	public String addForm(
			@SessionAttribute(name = "utente") UtenteEntity utente,
			Model model) {
        
		//controlla nel caso sia il cliente a richiedere la rotta torna un errore
		if(utente.getRuolo()!=Role.SUPERVISORE  &&	utente.getRuolo()!=Role.IMPIEGATO )
			return accessDeniedMVC(model);
		
		model.addAttribute("cliente", new UtenteEntity());	
		return "crea-cliente";
	}
	
}
