package it.es.gestionale.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ufficio")
public class UfficioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome", length = 50, nullable = true)
	private String nome;
	
	@Column(name="luogo_id", length = 100, nullable = true)
	private String luogo;

	@OneToMany(mappedBy ="ufficio")
	private List<ImpiegatoEntity> impiegati;

	public List<ImpiegatoEntity> getImpiegati(){
		return impiegati;
	}
	
	public UfficioEntity() {}
	
	public UfficioEntity(String nome, String luogo) {
		this.nome = nome;
		this.luogo = luogo;
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

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	@Override
	public String toString() {
		return "UfficioEntity [id=" + id + ", impiegati=" + impiegati + ", luogo=" + luogo + ", nome=" + nome + "]";
	}


	
	
}
