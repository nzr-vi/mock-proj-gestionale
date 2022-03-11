package it.es.gestionale.presentation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.es.gestionale.dto.LoginInputDto;
import it.es.gestionale.model.EsempioModel;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.service.UtenteService;

@Controller
@RequestMapping("")
@SessionAttributes("utente")
public class UtenteMVC {

	@Autowired
	UtenteService srv; 

	@GetMapping("")
	public String getIndex(Model model, HttpSession session) {
		if(session.getAttribute("utente")!=null) {
			
		}
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginInputDto loginData,
			Model model, HttpSession session) {

		var user = srv.getByEmail(loginData.getMail());
		if (user != null && user.getPassword().equals(loginData.getPassword())) {
			session.setAttribute("utente", user);
			model.addAttribute("utente",user);
			switch(user.getRuolo()) {
				case "supervisore":
					return "supervisore/index";
				case "impiegato":
					return "impiegato/index";
				case "cliente":
					return "cliente/index";
				default:
					break;
			}
		}
		
		model.addAttribute("error", "login failed");
		return "login";
	}

	@GetMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
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
