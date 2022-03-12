package it.es.gestionale.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.service.ArticoloService;

@RestController
@RequestMapping("/api/articolo")
public class ArticoloREST {


    @Autowired
    ArticoloService srv;

    @GetMapping
    public List<ArticoloEntity> allArticoli(){
        return srv.findAll();
    }


	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<ArticoloEntity> save(@RequestBody ArticoloEntity a) { 
            

                a = srv.save(a);

                return new ResponseEntity<ArticoloEntity>(a, HttpStatus.OK);
					
		}
		
	}
    

