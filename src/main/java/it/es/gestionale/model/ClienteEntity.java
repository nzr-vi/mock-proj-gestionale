package it.es.gestionale.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="cliente")
public class ClienteEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private UtenteEntity utente;

    private String telefono;
    private String indirizzo;
    private String citta;
    private String provincia;
    private String regione;
    private Integer credito;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<OrdineEntity> ordini;
    

	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return this.utente.getNome();
    }
    
    public void setNome(String nome) {
        this.utente.setNome(nome);
    }
    
    public String getCognome() {
    	return this.utente.getCognome();
    }
    
    public void setCognome(String cognome) {
        this.utente.setCognome(cognome);
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return this.utente.getEmail();
    }
    public void setEmail(String email) {
        this.utente.setEmail(email);
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public String getCitta() {
        return citta;
    }
    public void setCitta(String citta) {
        this.citta = citta;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getRegione() {
        return regione;
    }
    public void setRegione(String regione) {
        this.regione = regione;
    }
    public Integer getCredito() {
        return credito;
    }
    public void setCredito(Integer credito) {
        this.credito = credito;
    }

    public List<OrdineEntity> getOrdini() {
        return ordini;
    }
 
    public UtenteEntity getUtente() {
		return utente;
	}
    
    public void setUtente(UtenteEntity utente) {
		this.utente = utente;
	}
    
    @Override
    public String toString() {
        return "ClienteEntity [citta=" + citta + ", cognome=" + this.getCognome() + ", credito=" + credito + ", email=" + this.getEmail()
                + ", id=" + id + ", indirizzo=" + indirizzo + ", nome=" + this.getNome() + ", provincia=" + provincia
                + ", regione=" + regione + ", telefono=" + telefono + "]";
    }
}
