package it.es.gestionale.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "impiegato")
public class ImpiegatoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome", length = 50, nullable = true)
	private String nome;
	
	@Column(name="cognome", length = 100, nullable = true)
	private String cognome;

	private String ruolo;

	@Column(name="rif_to")
	private Integer riferimento;
	
	private double stipendio;

	@ManyToOne
	@JoinColumn(name="ufficio_id")
	private UfficioEntity ufficio;
	
	@OneToMany(mappedBy ="impiegato")
	private List<OrdineEntity> ordini;
	
	public List<OrdineEntity> getOrdini() {
		return ordini;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public Integer getRiferimento() {
		return riferimento;
	}

	public void setRiferimento(Integer riferimento) {
		this.riferimento = riferimento;
	}

	public double getStipendio() {
		return stipendio;
	}

	public void setStipendio(double stipendio) {
		this.stipendio = stipendio;
	}


	@Override
	public String toString() {
		return "ImpiegatoEntity [cognome=" + cognome + ", id=" + id + ", nome=" + nome + ", riferimento=" + riferimento
				+ ", ruolo=" + ruolo + ", stipendio=" + stipendio + "]";
	}


	

	
}
