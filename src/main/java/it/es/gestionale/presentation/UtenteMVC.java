package it.es.gestionale.presentation;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.service.UtenteService;

@Controller
@RequestMapping("")
@SessionAttributes("utente")
public class UtenteMVC {

	@Autowired
	UtenteService srv; 

	@GetMapping("")
	public String getIndex(Model model, @ModelAttribute UtenteEntity utente) {
		if(utente == null) {
			
		}
		return "login";
	}
	
	@GetMapping("/index")
	public String login(@SessionAttribute(name = "utente") UtenteEntity utente, Model model, HttpSession session) {
		if (utente != null) {
			model.addAttribute("utente",utente);
			switch(utente.getRuolo()) {
				case SUPERVISORE:
					return "supervisore";
				case IMPIEGATO:
					return "home-impiegato";
				case CLIENTE:
					return "cliente";
				default:
					break;
			}
		}
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		

		return "login";
	}

//	@PostMapping("/add-editore")
//	public String addEditore(Model model, String nome, String contatto) {
//		
//		try {
//			//session.setAttribute("esito", "Editore numero " + srv.saveEditore(new EsempioModel(nome, contatto)).getId() + " inserito correttamente.");
//			
//		} catch(Exception e) {
//			session.setAttribute("esito", "Qualcosa è andato storto: " + e.getMessage() + ".");
//		}
//		
//		return "redirect:/lista-editori";
//	}
//	
//	@PostMapping("/modify-editore")
//	public String modifyEditore(EsempioModel editore, Model model, HttpSession session) {
//	
//		try {
//			//session.setAttribute("esito", "Editore numero " + srv.saveEditore(editore).getId() + " modificato correttamente.");
//		} catch(Exception e) {
//			session.setAttribute("esito", "Qualcosa è andato storto: " + e.getMessage() + ".");
//		}
//		
//		return "redirect:/lista-editori";
//	}
	
}
