package it.es.gestionale.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "articolo")
public class ArticoloEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String descrizione;
	private double prezzo;
	private String categoria;
	private int rimanenza;
	
	@OneToMany(mappedBy ="articolo")
	@JsonIgnore
	private List<DettaglioEntity> dettagli;
	
	public ArticoloEntity() {}

	public ArticoloEntity(String descrizione, double prezzo, String categoria, int rimanenza) {
		super();
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.categoria = categoria;
		this.rimanenza = rimanenza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getRimanenza() {
		return rimanenza;
	}

	public void setRimanenza(int rimanenza) {
		this.rimanenza = rimanenza;
	}

	public List<DettaglioEntity> getDettagli() {
		return dettagli;
	}

	public void setDettagli(List<DettaglioEntity> dettagli) {
		this.dettagli = dettagli;
	}

	
}
