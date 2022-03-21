package it.es.gestionale.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.es.gestionale.dto.OrderItemDto;
import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.model.EsempioModel;
import it.es.gestionale.model.OrdineEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.service.OrdineService;
import it.es.gestionale.service.UtenteService;

@RestController
@RequestMapping("/api/ord")
@SessionAttributes("utente")
public class OrdineREST {

	@Autowired
	OrdineService srv;

	@GetMapping
//	public List<OrdineEntity> getList(){
//		return srv.findAll();
//		 
//	}
	
	public List<OrderItemDto> login(@SessionAttribute("utente") UtenteEntity use) {
		if(use.getRuolo().equals(Role.supervisore)) {
			return this.srv.getAll();
		}
		return new ArrayList<>();
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
