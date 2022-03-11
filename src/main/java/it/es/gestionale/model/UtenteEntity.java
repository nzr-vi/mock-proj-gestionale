package it.es.gestionale.model;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name ="utente")
public class UtenteEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String ruolo;

    @OneToMany(mappedBy = "cliente")
    private List<OrdineEntity> ordini;

    public UtenteEntity(){}
    
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
    public String getNumeroTel() {
        return telefono;
    }
    public void setNumeroTel(String numeroTel) {
        this.telefono = numeroTel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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


    @Override
    public String toString() {
        return "ClienteEntity [citta=" + citta + ", cognome=" + cognome + ", credito=" + credito + ", email=" + email
                + ", id=" + id + ", indirizzo=" + indirizzo + ", nome=" + nome + ", provincia=" + provincia
                + ", regione=" + regione + ", telefono=" + telefono + "]";
    }

    


}
