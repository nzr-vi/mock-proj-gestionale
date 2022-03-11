package it.es.gestionale.presentation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.es.gestionale.model.EsempioModel;
import it.es.gestionale.service.EsempioService;

@Controller
@RequestMapping("/lista-editori")
@SessionAttributes("editore")
public class EsempioCtrl {

	@Autowired
	EsempioService srv; // Autowired consente a Spring di capire se trattare un oggetto come un transient o un singleton

	@GetMapping
	public String getEditori(Model model, HttpSession session) {

		String titolo = "Elenco degli editori";
		
		model.addAttribute("titolo", titolo);
		//model.addAttribute("editori", srv.getListaEditori());
		// Passiamo la lista mediante il "passacarte" del service
		
		if(session.getAttribute("esito") != null) {
			model.addAttribute("esito", session.getAttribute("esito"));
			session.setAttribute("esito", null);
		}

		return "editori";
	}
	
	@PostMapping("/add-editore")
	public String addEditore(Model model, String nome, String contatto, HttpSession session) {
		
		try {
			//session.setAttribute("esito", "Editore numero " + srv.saveEditore(new EsempioModel(nome, contatto)).getId() + " inserito correttamente.");
			
		} catch(Exception e) {
			session.setAttribute("esito", "Qualcosa è andato storto: " + e.getMessage() + ".");
		}
		
		return "redirect:/lista-editori";
	}
	
	@PostMapping("/modify-editore")
	public String modifyEditore(EsempioModel editore, Model model, HttpSession session) {
	
		try {
			//session.setAttribute("esito", "Editore numero " + srv.saveEditore(editore).getId() + " modificato correttamente.");
		} catch(Exception e) {
			session.setAttribute("esito", "Qualcosa è andato storto: " + e.getMessage() + ".");
		}
		
		return "redirect:/lista-editori";
	}
	
}
