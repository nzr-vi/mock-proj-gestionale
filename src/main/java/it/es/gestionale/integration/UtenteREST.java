package it.es.gestionale.integration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.es.gestionale.model.EsempioModel;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.service.UtenteService;

@RestController
@RequestMapping("/api/utente")
@SessionAttributes("utente")
public class UtenteREST {

	@Autowired
	UtenteService srv;

	@PostMapping("/login")
	public UtenteEntity login(@RequestParam("mail") String mail, 
			@RequestParam("password") String password,
			HttpSession session) {

		var user = srv.getByEmail(mail);
		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("utente", user);
			return user;
		}
		
		return new UtenteEntity();
	}

	@GetMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@DeleteMapping("/{id}")
	public String deleteEditore(@PathVariable("id") int id, HttpSession session) {

		try {
			// srv.deleteEditore(id);
			session.setAttribute("esito", "Cancellazione avvenuta correttamente.");
			return "Cancellazione avvenuta correttamente.";
		} catch (Exception e) {
			session.setAttribute("esito", "Qualcosa è andato storto: " + e.getMessage() + ".");
			return "Qualcosa è andato storto: " + e.getMessage() + ".";
		}
	}

//	@PostMapping("/saveCanzone")
//	public String salvaModifica (@RequestParam(value = "isUpdate", required = true) boolean isUpdate, 
//								 @RequestBody Utente newCanzone
//			){
//		
//		
//		if(srv.salvaModifica(newCanzone, isUpdate)) return "Salvataggio effettuato";
//		else return "Errore nel passaggio della richiesta";
//
//		
//	}
//	
//	@DeleteMapping("/deleteCanzone")
//	public String deleteCanzone (@RequestParam(value = "id", required = true) int id) {
//		
//		if(srv.deleteCanzone(id)) return "Cancellazione effettuata";
//		else return "Errore durante la cancellazione";
//		
//	}
//	
//	public Utente getCanzone (@PathVariable(value = "id") int id) {
//		
//	return srv.getOne(id);
//	}
//	

}
