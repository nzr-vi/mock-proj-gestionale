package it.es.gestionale.dto;

public class OrderItemDto {
	private int id_order;
	private int id_impiegato;
	private int id_cliente;
	private double totale;
	
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public int getId_impiegato() {
		return id_impiegato;
	}
	public void setId_impiegato(int id_impiegato) {
		this.id_impiegato = id_impiegato;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public double getTotale() {
		return totale;
	}
	public void setTotale(double totale) {
		this.totale = totale;
	}
	public String toCsv() {
		return String.join(";", new String[] {Integer.toString(this.id_impiegato),Integer.toString(this.id_cliente),Double.toString(this.totale)});
	}
}
