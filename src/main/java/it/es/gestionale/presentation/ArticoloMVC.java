package it.es.gestionale.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.service.ArticoloService;

@Controller
@RequestMapping("/home")
public class ArticoloMVC {

    @Autowired
    ArticoloService srv;


    @GetMapping
    public String findAll(Model model){
        
        model.addAttribute("articoli", srv.findAll());

        return "home";
    }

   
	@PostMapping("/save") 
	public String saveStudente(ArticoloEntity a) {
		// salva studente 
		srv.save(a);
		
		
		return "redirect:/home"; 
	}
	

	@GetMapping("/add") 
	public String addForm(Model model) {
	
		model.addAttribute("articolo", new ArticoloEntity());
		
		return "addArticolo";
	}
    
}
