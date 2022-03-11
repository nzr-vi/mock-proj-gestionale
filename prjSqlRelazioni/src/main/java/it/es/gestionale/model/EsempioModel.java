package it.es.gestionale.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "editore")
public class EsempioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome", length = 50, nullable = true)
	private String nome;
	
	@Column(name="contatto", length = 100, nullable = true)
	private String contatto;
	
	public EsempioModel() {}
	
	public EsempioModel(String nome, String contatto) {
		this.nome = nome;
		this.contatto = contatto;
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

	public String getContatto() {
		return contatto;
	}

	public void setContatto(String contatto) {
		this.contatto = contatto;
	}

	@Override
	public String toString() {
		return "Editore [id=" + id + ", nome=" + nome + ", contatto=" + contatto + "]";
	}
	
}
