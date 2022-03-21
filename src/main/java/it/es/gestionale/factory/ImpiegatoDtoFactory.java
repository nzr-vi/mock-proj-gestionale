package it.es.gestionale.factory;

import it.es.gestionale.dto.ImpiegatoItemDto;
import it.es.gestionale.dto.ImpiegatoModificaDto;
import it.es.gestionale.dto.ModificaClienteAsClienteDto;
import it.es.gestionale.dto.ModificaClienteDto;
import it.es.gestionale.model.ClienteEntity;
import it.es.gestionale.model.ImpiegatoEntity;
import it.es.gestionale.model.UtenteEntity.Role;

public class ImpiegatoDtoFactory {
	
	public static ImpiegatoModificaDto create(ImpiegatoEntity impiegato) {
		var dto = new ImpiegatoModificaDto();
		dto.setId(impiegato.getId());
		dto.setCognome(impiegato.getUtente().getCognome());
		dto.setEmail(impiegato.getUtente().getEmail());
		dto.setNome(impiegato.getUtente().getNome());
		dto.setPassword(impiegato.getUtente().getPassword());
		var rif = impiegato.getRiferimento();
		if(rif!=null)
			dto.setRiferimento_id(rif.getId());
		var uff = impiegato.getUfficio();
		if(uff!=null)
			dto.setUfficio_id(uff.getId());
		dto.setStipendio(impiegato.getStipendio());
		dto.setRuolo(impiegato.getUtente().getRuolo());
		return dto;
	}
	
	public static ImpiegatoItemDto createListItem(ImpiegatoEntity impiegato) {
		var dto = new ImpiegatoItemDto();
		dto.setId(impiegato.getId());
		dto.setCognome(impiegato.getUtente().getCognome());
		dto.setNome(impiegato.getUtente().getNome());
		dto.setRuolo(impiegato.getUtente().getRuolo());
		var rif = impiegato.getRiferimento();
		if(rif!=null)
			dto.setRiferimento_id(rif.getId());
		var uff = impiegato.getUfficio();
		if(uff!=null)
			dto.setUfficio_id(uff.getId());
		dto.setStipendio(impiegato.getStipendio());
		return dto;
	}
}
