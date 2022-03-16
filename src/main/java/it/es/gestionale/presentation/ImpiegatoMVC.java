package it.es.gestionale.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//@SessionAttributes("utente")
public class ImpiegatoMVC {

    @Autowired
    ImpiegatoService srv;


    @GetMapping
    public String findAll( Model model){
        
//    	model.addAttribute("isSuper",user.getRuolo()==Role.supervisore);
        model.addAttribute("impiegati", srv.findAll());
        model.addAttribute("filters", new String[] {"Nome", "Cognome", "Ruolo","Stipendio"});
        return "impiegato";
    }

   
	@PostMapping("/save") 
	public String save(ImpiegatoEntity a) {
		
			a = srv.save(a);
		
		return "redirect:"; 
	}
	

	@GetMapping("/add") 
	public String addForm(Model model) {
        
	
		model.addAttribute("impiegato", new ImpiegatoEntity());
		
		return "addImpiegato";
	}
    
	@GetMapping("/{id}")
	public String modifica(@PathVariable("id") int id, Model model) { 

		ImpiegatoEntity impiegato = srv.getByid(id);
		
		if(impiegato==null)
			return "redirect:/impiegato";
		
		
		model.addAttribute("impiegato", impiegato); 
		
		return "addImpiegato";	
	}
	
}
