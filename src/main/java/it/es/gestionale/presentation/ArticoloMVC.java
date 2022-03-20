package it.es.gestionale.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.service.ArticoloService;

@Controller
@RequestMapping("/articolo")
@SessionAttributes("utente")
public class ArticoloMVC {

    @Autowired
    ArticoloService srv;

    @GetMapping
    public String findAll(@SessionAttribute("utente") UtenteEntity user, Model model){
        
    	model.addAttribute("isSuper",user.getRuolo()==Role.SUPERVISORE);
        model.addAttribute("articoli", srv.findAll());
        model.addAttribute("filters", new String[] {"Categoria","Descrizione","Prezzo"});
        return "articolo";
    }

	@PostMapping("/save") 
	public String saveStudente(@SessionAttribute(name = "utente") UtenteEntity utente,
			ArticoloEntity a) {
		
		if(utente.getRuolo()==Role.SUPERVISORE)
		{
			a = srv.save(a);
		}
		
		return "redirect:"; 
	}
	
	@GetMapping("/add") 
	public String addForm(@SessionAttribute("utente") UtenteEntity user, Model model) {
        
		if(user.getRuolo()==Role.SUPERVISORE)
		{
			model.addAttribute("articolo", new ArticoloEntity());
			return "addArticolo";			
		}
		
		return this.findAll(user, model);
	}
    
	@GetMapping("/{id}")
	public String modifica(@SessionAttribute("utente") UtenteEntity user,
			@PathVariable("id") int id, Model model) { 

		if(user.getRuolo()==Role.SUPERVISORE)
		{
			ArticoloEntity articolo = srv.getById(id);
			
			if(articolo==null)
				return "redirect:/home";
					
			model.addAttribute("articolo", articolo); 
			
			return "addArticolo";				
		}
		return this.findAll(user, model);
	}
	
}
