package it.es.gestionale.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="utente")
@Scope("session")
public class UtenteEntity {
    
	public enum Role{
		cliente, 
		impiegato, 
		supervisore
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@Column(name="nome", length = 50, nullable = true)
    private String nome;
    
	@Column(name="cognome", length = 100, nullable = true)
    private String cognome;
    
    private String email;
    
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role ruolo;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRuolo() {
		return ruolo;
	}
	public void setRuolo(Role ruolo) {
		this.ruolo = ruolo;
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
}
