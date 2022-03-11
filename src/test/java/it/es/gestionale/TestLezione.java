package it.es.gestionale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import javax.transaction.Transactional;

import it.es.gestionale.model.UfficioEntity;
import it.es.gestionale.repository.ImpiegatoDB;
import it.es.gestionale.repository.UfficioDB;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestLezione {
	@Autowired
	UfficioDB tabellaUfficio;

	@Autowired
	ImpiegatoDB tabellaImpiegato;


	@Test
	@Transactional 
	void contextLoads() {

		List<UfficioEntity> luf=tabellaUfficio.findAll();


		System.out.println("-------");
		for(UfficioEntity u:luf){
			switch(u.getId()){
				case 1:
				assertEquals("IT", u.getNome());
				break;
				case 2:
				assertEquals("Amministrazione", u.getNome());
				break;
				case 3:
				assertEquals("Commerciale", u.getNome());
				break;
				default:
				break;
			}
		}
		
		System.out.println("-------");

	}

	@Test
	@Transactional 
	void tuttiGliUfficiHannoAlmenoUnDipendente() {

		List<UfficioEntity> luf=tabellaUfficio.findAll();
		for(UfficioEntity u:luf){
			assertTrue(u.getImpiegati().size()>=1) ;
		}
		
		System.out.println("-------");

	}
	
	

	

}
