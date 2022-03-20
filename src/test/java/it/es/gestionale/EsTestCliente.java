package it.es.gestionale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.Transient;

import it.es.gestionale.model.ClienteEntity;
import it.es.gestionale.model.OrdineEntity;
import it.es.gestionale.repository.ClienteDB;
import it.es.gestionale.repository.OrdineDB;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class EsTestCliente {
	@Autowired
	ClienteDB db;
	OrdineDB dbOrdini;

	/*
	 Elenca (mediante Sysout) gli ordini del cliente 4
	*/
	@Test
	@Transactional
	void esOrdiniImp() {

		List<ClienteEntity> clienti =  db.findAll();
		System.out.println("--------------");
		for( ClienteEntity cliente :clienti){
			
			if(cliente.getId()==6){
				
				System.out.println(cliente.getOrdini());
			}
		}
		System.out.println("--------------");
	}

	/*
	 Conta e mostra il numero di ordini a sistema di ogni cliente
	*/
	@Test
	@Transactional
	void esCountOrdiniImp() {

		List<ClienteEntity> clienti =  db.findAll();
		
		System.out.println("--------------");
		for(ClienteEntity cliente : clienti){
			System.out.println("Numero ordini Cliente " + cliente.getNome() + ": " + cliente.getOrdini().size());
		}
		System.out.println("--------------");

		
	}

	/*
	 Mostra il numero di articoli in ogni ordine del cliente 3
	*/
	@Test
	void esSumVenduto() {

		int idImpiegato=6;
		assertTrue(false);
		
	}
	
	/*
	 Mostra il l'utente con il più alto numero di ordini
	*/
	@Test
	void esCountOrdiniAllImp() {
		assertTrue(false);
		
	}
	
	@Test
	@Transactional
	void testUtente() {
		var c = db.getById(1);
		var u = c.getUtente();
		assertNotEquals(null,u);
	}
}
