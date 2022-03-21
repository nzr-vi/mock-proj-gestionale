package it.es.gestionale.dto;

import it.es.gestionale.model.UtenteEntity.Role;

public class ImpiegatoItemDto {

	private int id;
	private String nome;
	private String cognome;
	private double stipendio;
	private Integer ufficio_id;
	private Integer riferimento_id;
	private Role ruolo;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRuolo() {
		return ruolo;
	}

	public void setRuolo(Role ruolo) {
		this.ruolo = ruolo;
	}

	public Integer getRiferimento_id() {
		return riferimento_id;
	}

	public void setRiferimento_id(Integer riferimento_id) {
		this.riferimento_id = riferimento_id;
	}

	public Integer getUfficio_id() {
		return ufficio_id;
	}

	public void setUfficio_id(Integer ufficio_id) {
		this.ufficio_id = ufficio_id;
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

	public double getStipendio() {
		return stipendio;
	}

	public void setStipendio(double stipendio) {
		this.stipendio = stipendio;
	}
	
}
