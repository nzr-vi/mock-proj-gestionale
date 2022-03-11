package it.es.gestionale.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DettaglioKey implements Serializable{

	@Column(name="articolo_id")
	int articoloId;
	
	@Column(name="ordine_id")
	int ordineId;
}
