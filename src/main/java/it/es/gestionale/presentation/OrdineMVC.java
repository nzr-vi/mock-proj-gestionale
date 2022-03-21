package it.es.gestionale.presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import it.es.gestionale.service.OrdineService;

@Controller
@RequestMapping("/ordine")
@SessionAttributes("utente")
public class OrdineMVC {

    @Autowired
    OrdineService srv;

    @GetMapping
    public String findAll(@SessionAttribute("utente") UtenteEntity user, Model model){
        
    	if(user.getRuolo()==Role.supervisore) {
    		
    	  	model.addAttribute("isSuper",user.getRuolo()==Role.supervisore);
            model.addAttribute("ordini", srv.getAll());
         
            return "ordine";
    	}
    		return "errore";
    }
    
    @GetMapping("/export")
	public ResponseEntity<InputStreamResource> download(String param) throws IOException {

		String outFile = this.srv.exportCsv();

		if (outFile != null) {
			File download = new File(outFile);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(download));

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=exportArticolo.csv")
					.contentLength(download.length())
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(resource);
		}

		return ResponseEntity.notFound().build();
	}
//	@PostMapping("/save") 
//	public String saveStudente(@SessionAttribute(name = "utente") UtenteEntity utente,
//			ArticoloEntity a) {
//		
//		if(utente.getRuolo()==Role.supervisore)
//		{
//			a = srv.save(a);
//		}
//		
//		return "redirect:"; 
//	}
//	
//	@GetMapping("/add") 
//	public String addForm(@SessionAttribute("utente") UtenteEntity user, Model model) {
//        
//		if(user.getRuolo()==Role.supervisore)
//		{
//			model.addAttribute("articolo", new ArticoloEntity());
//			return "addArticolo";			
//		}
//		
//		return this.findAll(user, model);
//	}
//    
//	@GetMapping("/{id}")
//	public String modifica(@SessionAttribute("utente") UtenteEntity user,
//			@PathVariable("id") int id, Model model) { 
//
//		if(user.getRuolo()==Role.supervisore)
//		{
//			ArticoloEntity articolo = srv.getById(id);
//			
//			if(articolo==null)
//				return "redirect:/home";
//					
//			model.addAttribute("articolo", articolo); 
//			
//			return "addArticolo";				
//		}
//		return this.findAll(user, model);
//	}
//	
}
