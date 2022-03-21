package it.es.gestionale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.es.gestionale.dto.OrderItemDto;
import it.es.gestionale.model.ArticoloEntity;
import it.es.gestionale.model.OrdineEntity;
import it.es.gestionale.repository.OrdineDB;


@Service
public class OrdineService {

	@Autowired
	OrdineDB db;

	public List<OrdineEntity> getList() {
		return db.findAll(); 
	}

	public OrdineEntity save(OrdineEntity e) {
		return db.save(e);
	}

	public void delete(int id) {
		db.delete(db.getById(id));
	}

	public OrdineEntity getByid(int id) {
		return db.findById(id).orElse(new OrdineEntity());
	}

	public List<OrdineEntity> findAll() {
		return db.findAll(); // Passacarte
	}
	
	public List<OrderItemDto> getAll(){
		return this.db.findAll().stream().map(o ->{
			var order = new OrderItemDto();
			order.setId_order(o.getId());
			order.setId_impiegato(o.getImpiegato().getId());
			order.setId_cliente(o.getCliente().getId());
			order.setTotale(calcTotale(o));
			return order;
			}).collect(Collectors.toList());
	}
	
	public double calcTotale(OrdineEntity ordine) {
		return ordine.getDettagli().stream()
		.mapToDouble(d->d.getQuantita()*d.getArticolo().getPrezzo())
		.sum();
		
	}
}
