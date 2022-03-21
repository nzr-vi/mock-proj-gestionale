package it.es.gestionale.factory;

import it.es.gestionale.dto.ModificaClienteAsClienteDto;
import it.es.gestionale.dto.ModificaClienteDto;
import it.es.gestionale.model.ClienteEntity;

public class ClienteDtoFactory {
	
	public static ModificaClienteDto create(ClienteEntity cliente) {
		var dto = new ModificaClienteDto();
		dto.setId(cliente.getId());
		dto.setCitta(cliente.getCitta());
		dto.setCognome(cliente.getCognome());
		dto.setCredito(cliente.getCredito());
		dto.setEmail(cliente.getEmail());
		dto.setIndirizzo(cliente.getIndirizzo());
		dto.setNome(cliente.getNome());
		dto.setProvincia(cliente.getProvincia());
		dto.setRegione(cliente.getRegione());
		dto.setTelefono(cliente.getTelefono());
		return dto;
	}
	
	public static ModificaClienteAsClienteDto createAsCliente(ClienteEntity cliente) {
		var dto = new ModificaClienteAsClienteDto();
		dto.setId(cliente.getId());
		dto.setCitta(cliente.getCitta());
		dto.setCognome(cliente.getCognome());
		dto.setPassword(cliente.getUtente().getPassword());
		dto.setEmail(cliente.getEmail());
		dto.setIndirizzo(cliente.getIndirizzo());
		dto.setNome(cliente.getNome());
		dto.setProvincia(cliente.getProvincia());
		dto.setRegione(cliente.getRegione());
		dto.setTelefono(cliente.getTelefono());
		return dto;
	}
}
