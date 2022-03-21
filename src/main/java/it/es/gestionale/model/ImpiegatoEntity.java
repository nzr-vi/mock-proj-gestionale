package it.es.gestionale.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "impiegato")
public class ImpiegatoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private UtenteEntity utente;
	
	@ManyToOne
	@JoinColumn(name="rif_to")
	@JsonIgnore
	private ImpiegatoEntity riferimento;
	
	private double stipendio;
	
	@ManyToOne
	@JoinColumn(name="ufficio_id")
	@JsonIgnore
	private UfficioEntity ufficio;
	
	@OneToMany(mappedBy ="impiegato")
	@JsonIgnore
	private List<OrdineEntity> ordini;
	
	public List<OrdineEntity> getOrdini() {
		return ordini;
	}

	public void setUfficio(UfficioEntity ufficio) {
		this.ufficio = ufficio;
	}

	public UfficioEntity getUfficio() {
		return ufficio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ImpiegatoEntity getRiferimento() {
		return riferimento;
	}

	public void setRiferimento(ImpiegatoEntity riferimento) {
		this.riferimento = riferimento;
	}

	public void setStipendio(double stipendio) {
		this.stipendio = stipendio;
	}

		
	public UtenteEntity getUtente() {
		return utente;
	}

	public void setUtente(UtenteEntity utente) {
		this.utente = utente;
	}

	public double getStipendio() {
		return stipendio;
	}

	public void ufficio(UfficioEntity ufficio) {
		this.ufficio = ufficio;
	}

	@Override
	public String toString() {
		return "ImpiegatoEntity [ id=" + id + ", riferimento=" + riferimento
				+ ", stipendio=" + stipendio + "]";
	}

}
