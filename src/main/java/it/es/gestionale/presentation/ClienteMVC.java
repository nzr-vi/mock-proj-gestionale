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

@Controller
@RequestMapping("/lista-clienti")
@SessionAttributes("utente")
public class ClienteMVC {

    @Autowired
    ClienteService srv;
	@Autowired
	UtenteService usrv;


    @GetMapping
    public String findAll(@SessionAttribute("utente") UtenteEntity user, Model model){
        if(user==null || user.getRuolo()==Role.cliente)
		return "";
    	model.addAttribute("isSuper",user.getRuolo()==Role.supervisore);
        model.addAttribute("clienti", srv.findAll());
        model.addAttribute("filters", new String[] {"Nome","Cognome","Email"});
        return "lista-clienti";
    }
	
	@GetMapping("/{id}")
	public String modifica(@PathVariable("id") int id, Model model) { 

		ClienteEntity cliente = srv.getById(id);
		
		if(cliente==null)
			return "redirect:/lista-clienti";
		
		
		model.addAttribute("cliente", cliente); 
		
		return "modifica-cliente";	
	}

	@PostMapping("/save") 
	public String saveUtente(@SessionAttribute(name = "utente") UtenteEntity utente,
	UtenteEntity u) {
		
		if(utente.getRuolo()==Role.supervisore)
		{
			u = usrv.save(u);
		}
		
		return "redirect:"; 
	}

	@PostMapping("/modifica") 
	public String modificaUtente(@SessionAttribute(name = "utente") UtenteEntity utente,
	ClienteEntity c) {
		
		if(utente.getRuolo()==Role.supervisore)
		{
			c = srv.save(c);
		}
		
		return "redirect:"; 
	}

	@GetMapping("/crea-cliente/add") 
	public String addForm(Model model) {
        
		model.addAttribute("cliente", new UtenteEntity());
		
		return "crea-cliente";
	}
	
}
