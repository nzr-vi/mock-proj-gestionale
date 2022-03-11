package it.es.gestionale;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.model.DettaglioEntity;
import it.es.gestionale.repository.ArticoloDB;
import it.es.gestionale.repository.DettaglioDB;
import it.es.gestionale.repository.OrdineDB;

@SpringBootTest
class EsTestArticolo {
	
	@Autowired
	ArticoloDB dbArt;

	@Autowired
	DettaglioDB dbDet;
	
	@Autowired
	OrdineDB dbOr;

	/*
	 Elenca gli utenti che hanno ordinato l'articolo 4
	*/
	@Test
	@Transactional
	void esOrdiniImp() {
		
		List<DettaglioEntity> dettagli = dbDet.findAll();
		
		for(DettaglioEntity dettaglio : dettagli) {
			if(dettaglio.getArticolo().getId() == 4) {
				//System.out.println(dettaglio.getOrdine().getCliente().getId());
				switch(dettaglio.getOrdine().getCliente().getId()) {
				case 5: break;
				case 6: break;
				default: fail(); //assertTrue(false);
				break;
				}
			}
		}
	}

	/*
	 Conta e mostra il numero di ordini a sistema di ogni articolo
	*/
	@Test
	@Transactional
	void esCountOrdiniImp() {
		
		List<ArticoloEntity> articoli = dbArt.findAll();
		
		for(ArticoloEntity articolo : articoli) {
			switch(articolo.getId()) {
			case 8:
			case 9:
				if(articolo.getDettagli().size() != 0) fail();
				//assertEquals(articolo.getDettagli().size(), 0);
				break;
			case 6: 
				if(articolo.getDettagli().size() != 1) fail();
				//assertEquals(articolo.getDettagli().size(), 1);
				break;
			case 1:
			case 4:
			case 5:
				if(articolo.getDettagli().size() != 2) fail();
				//assertEquals(articolo.getDettagli().size(), 2);
				break;
			case 2:
			case 3:
				if(articolo.getDettagli().size() != 3) fail();
				//assertEquals(articolo.getDettagli().size(), 3);
				break;
			case 7:
				if(articolo.getDettagli().size() != 4) fail();
				//assertEquals(articolo.getDettagli().size(), 4);
				break;
			default: fail(); //assertTrue(false);
				break;
			}
			//System.out.println("ID: " + articolo.getId() + " count(ordine_id): " + articolo.getDettagli().size());
		}	
	}

	/*
	 Mostra la somma del totale venduto di un articolo
	*/
	@Test
	@Transactional
	void esSumVenduto() {

		//int idImpiegato=6; ?
		
		List<DettaglioEntity> dettagli = dbDet.findAll();
		double[] somme = new double[7];
		double[] risultati = {4550, 616, 1800, 1600, 1400, 1752.50, 408};
		
		for(DettaglioEntity dettaglio : dettagli) {
			somme[dettaglio.getArticolo().getId()-1] = somme[dettaglio.getArticolo().getId()-1] + (dettaglio.getQuantita()*dettaglio.getArticolo().getPrezzo());
		}
		
		for(int i = 0; i<risultati.length; i++) {
			//System.out.println("ID: " + (i+1) + " count(ordine_id): " + somme[i]);
			if(somme[i] != risultati[i]) fail();
			//assertEquals(somme[i], risultati[i]);
		}
	}
		

	/*
	 Mostra il cliente che ha ordinato il numero maggiore di articolo 2
	*/
	@Test
	@Transactional
	void esCountOrdiniAllImp() {
		
		int[] quantita = new int[7];
		int maxCID = 0, maxQ = 0;
		
		List<DettaglioEntity> dettagli = dbDet.findAll();
		
		for(DettaglioEntity dettaglio : dettagli) {
			if(dettaglio.getArticolo().getId() == 2) {
				quantita[dettaglio.getOrdine().getCliente().getId()-1] = quantita[dettaglio.getOrdine().getCliente().getId()-1] + dettaglio.getQuantita();
			}
		}
		
		for(int i=0; i<quantita.length; i++) {
			//System.out.println("ID: " + (i+1) + " Quantita 2: " + quantita[i]);
			if(quantita[i]>maxQ) {
				maxCID = i+1;
				maxQ = quantita[i];
			}
		}
		
		if(maxCID != 5 || maxQ != 10) fail();
		
		//assertEquals(5, maxCID);
		//assertEquals(10, maxQ);
		
	}
}
