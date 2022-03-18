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

import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.model.ImpiegatoEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.service.ArticoloService;
import it.es.gestionale.service.ImpiegatoService;

@Controller
@RequestMapping("/impiegato")
@SessionAttributes("utente")
public class ImpiegatoMVC {

    @Autowired
    ImpiegatoService srv;


    @GetMapping
    public String findAll(@SessionAttribute(name = "utente") UtenteEntity user,  Model model){  
    	if(user.getRuolo() != Role.supervisore) {
    		model.addAttribute("message","utente non autorizzato");
    		return "errore";
    	}

        model.addAttribute("impiegati", srv.findAll());
        model.addAttribute("filters", new String[] {"Nome", "Cognome", "Ruolo", "Stipendio"});
        return "impiegato";
    }

   
	@PostMapping("/save") 
	public String save(@SessionAttribute(name = "utente") UtenteEntity user, ImpiegatoEntity a, Model model ) {
		
		if(user.getRuolo() != Role.supervisore) {
    		model.addAttribute("message","utente non autorizzato");
    		return "errore";
    	}
		
		a = srv.save(a);
		
		return "redirect:"; 
	}
	

	@GetMapping("/add") 
	public String addForm(@SessionAttribute(name = "utente") UtenteEntity user, Model model) {
		
		if(user.getRuolo() != Role.supervisore) {
    		model.addAttribute("message","utente non autorizzato");
    		return "errore";
    	}
		
		model.addAttribute("impiegato", new ImpiegatoEntity());
		
		return "addImpiegato";
	}
    
	@GetMapping("/{id}")
	public String modifica(@SessionAttribute(name = "utente") UtenteEntity user, @PathVariable("id") int id, Model model) { 

		if(user.getRuolo() != Role.supervisore) {
    		model.addAttribute("message","utente non autorizzato");
    		return "errore";
    	}
		
		ImpiegatoEntity impiegato = srv.getByid(id);
		
		if(impiegato==null)
			return "redirect:/impiegato";
		
		model.addAttribute("impiegato", impiegato); 
		
		return "addImpiegato";	
	}
	
}
