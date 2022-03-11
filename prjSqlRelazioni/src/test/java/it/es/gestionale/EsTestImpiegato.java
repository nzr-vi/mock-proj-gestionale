package it.es.gestionale;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import it.es.gestionale.model.DettaglioEntity;
import it.es.gestionale.model.ImpiegatoEntity;
import it.es.gestionale.model.OrdineEntity;
import it.es.gestionale.repository.ImpiegatoDB;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EsTestImpiegato {
	@Autowired
	ImpiegatoDB db;

	/*
	 Elenca (mediante Sysout) gli ordini di un impiegato 
	*/
	@Test
	@Transactional
	void esOrdiniImp() {
		int idImpiegato=4;
		Set<Integer> idOrdiniAttesiRimasti = new HashSet<>(
				List.of(new Integer[] {1,2,4,5,12})
				);
		
		ImpiegatoEntity impiegato=this.db.getById(idImpiegato);
		for (OrdineEntity ordine:impiegato.getOrdini()) {
			System.out.println(ordine);
			idOrdiniAttesiRimasti.remove(ordine.getId());
		}
		
		assertEquals(0,idOrdiniAttesiRimasti.size());
	}

	/*
	 Conta e mostra il numero di ordini a sistema di ogni impiegato
	*/
	@Test
	@Transactional
	void esCountOrdiniImp() {
		var impiegati = this.db.findAll();
		var numOrdini = new HashMap<Integer,Integer>();
		
		int[][] arrayValoriAttesi = {
				{1,0},{2,0},{3,0},{4,5},{5,0},{6,0},{7,0},{8,2},{9,0}
				};
		var valoriAttesi = Arrays.stream(arrayValoriAttesi)
				.collect(Collectors.toMap(array->array[0], array->array[1]));
		
		for (ImpiegatoEntity impiegato : impiegati )	
			numOrdini.put(impiegato.getId(), impiegato.getOrdini().size());
		
		assertEquals(valoriAttesi.size(), numOrdini.size());
		
		for (var ordiniImpiegato : numOrdini.entrySet()) {
			System.out.println("numero oridini impiegato ["+ordiniImpiegato.getKey()+"] = "+ordiniImpiegato.getValue());
			assertEquals(ordiniImpiegato.getValue(),valoriAttesi.get(ordiniImpiegato.getKey()));			
		}
	}

	@SuppressWarnings("unused")
	private double calcoloVendutoStream(int idImpiegato){
		return this.db.getById(idImpiegato).getOrdini().stream()
				.flatMapToDouble(o->
					o.getDettagli().stream()
					.mapToDouble(de->
						de.getArticolo().getPrezzo()*de.getQuantita()
						)
					)
				.sum();
	}
	private double calcoloVenduto(int idImpiegato){
		ImpiegatoEntity impiegato=this.db.getById(idImpiegato);
		double count=0;
		for (OrdineEntity ord : impiegato.getOrdini()) {
			for (DettaglioEntity de : ord.getDettagli()) {
				count += de.getArticolo().getPrezzo()*de.getQuantita();
			}
		}
		return count;
	}

	/*
	 Mostra la somma del totale venduto di un impiegato
	*/
	@Test
	@Transactional
	void esSumVenduto() {

		int idImpiegato=6;
		double somma=this.calcoloVenduto(idImpiegato);
		System.out.println("Somma= "+somma);
		assertEquals(0,somma);
		
	}

	/*
	 Conta e mostra il numero di ordini a sistema di ogni impiegato
	*/
	@Test
	@Transactional
	void esCountOrdiniAllImp() {
		//Lo stesso rispetto al punto 2
		esCountOrdiniImp();
		
	}









}
