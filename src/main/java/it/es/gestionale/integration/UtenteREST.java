package it.es.gestionale.integration;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.es.gestionale.dto.LoginInputDto;
import it.es.gestionale.dto.LoginOutputDto;
import it.es.gestionale.model.EsempioModel;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.service.UtenteService;

@RestController
@RequestMapping("/api/utente")
public class UtenteREST {

	@Autowired
	UtenteService srv;
	
	@PostMapping("/login")
	public LoginOutputDto login(@RequestBody LoginInputDto loginData, HttpSession session) {

		LoginOutputDto response = new LoginOutputDto();
		
		UtenteEntity utente = srv.getByEmail(loginData.getMail());
		if (utente != null && utente.getPassword().equals(loginData.getPassword())) {
			session.setAttribute("utente", utente);
			response.setCode(LoginOutputDto.LOGIN_SUCCESS);
			response.setMessage("Login successful!\n"
					+ "Welcome "+utente.getNome()+" "+utente.getCognome());
		}
		
		return response;
	}

	@GetMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
//	@DeleteMapping("/{id}")
//	public String deleteEditore(@PathVariable("id") int id, HttpSession session) {
//
//		try {
//			// srv.deleteEditore(id);
//			session.setAttribute("esito", "Cancellazione avvenuta correttamente.");
//			return "Cancellazione avvenuta correttamente.";
//		} catch (Exception e) {
//			session.setAttribute("esito", "Qualcosa è andato storto: " + e.getMessage() + ".");
//			return "Qualcosa è andato storto: " + e.getMessage() + ".";
//		}
//	}
//	
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
