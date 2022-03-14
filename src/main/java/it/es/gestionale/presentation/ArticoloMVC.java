package it.es.gestionale.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.service.ArticoloService;

@Controller
@RequestMapping("/home")
@SessionAttributes("utente")
public class ArticoloMVC {

    @Autowired
    ArticoloService srv;


    @GetMapping
    public String findAll(Model model){
        
        model.addAttribute("articoli", srv.findAll());

        return "home";
    }

   
	@PostMapping("/save") 
	public String saveStudente(@SessionAttribute(name = "utente") UtenteEntity utente,
			ArticoloEntity a) {
		
		if(utente.getRuolo()==Role.supervisore)
		{
			a = srv.save(a);
		}
		
		return "redirect:''"; 
	}
	

	@GetMapping("/add") 
	public String addForm(Model model) {
	
		model.addAttribute("articolo", new ArticoloEntity());
		
		return "addArticolo";
	}
    
}