package it.es.gestionale.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.es.gestionale.dto.CreazioneClienteDto;
import it.es.gestionale.dto.ImpiegatoCreazioneDto;
import it.es.gestionale.dto.ImpiegatoItemDto;
import it.es.gestionale.dto.ImpiegatoModificaDto;
import it.es.gestionale.exception.CreationDtoException;
import it.es.gestionale.factory.ImpiegatoDtoFactory;
import it.es.gestionale.model.ClienteEntity;
import it.es.gestionale.model.ImpiegatoEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.repository.ImpiegatoDB;

@Service
public class ImpiegatoService {

	@Autowired
	ImpiegatoDB db;

	@Autowired
	UfficioService uffSrv;

	@Autowired
	UtenteService usrv;

	public ImpiegatoEntity save(ImpiegatoModificaDto impiegatoDto) throws CreationDtoException {

		var impOpt = this.db.findById(impiegatoDto.getId());
		if (impOpt.isEmpty())
			throw new CreationDtoException("impiegato not found");

		var imp = impOpt.get();

		var email = impiegatoDto.getEmail();

		var user = imp.getUtente();

		if (email != null) {

			if (!email.equals(user.getEmail()) && (email.isBlank() || this.usrv.findByEmail(email).isPresent()))
				throw new CreationDtoException("email is already registered");

			user.setEmail(impiegatoDto.getEmail());
		}
		user.setNome(impiegatoDto.getNome());
		user.setCognome(impiegatoDto.getCognome());
		user.setPassword(impiegatoDto.getPassword());
		user.setRuolo(impiegatoDto.getRuolo());
		
		
		imp.setStipendio(impiegatoDto.getStipendio());
		var rifTo = impiegatoDto.getRiferimento_id();
		var ufficioId = impiegatoDto.getUfficio_id();
		if (rifTo != null) {
			var superior = this.db.findById(rifTo);
			if (superior.isPresent())
				imp.setRiferimento(superior.get());
		}
		if (ufficioId != null) {
			var ufficio = this.uffSrv.findByid(ufficioId);
			if (ufficio.isPresent())
				imp.setUfficio(ufficio.get());
		}

		return this.db.save(imp);

	}

	public ImpiegatoEntity create(ImpiegatoCreazioneDto impiegatoDto) throws CreationDtoException {

		var email = impiegatoDto.getEmail();
		if (email.isBlank() || this.usrv.findByEmail(email).isPresent())
			throw new CreationDtoException("email is already registered");

		var user = this.usrv.createNew(impiegatoDto.getNome(), impiegatoDto.getCognome(), email, impiegatoDto.getRuolo());

		ImpiegatoEntity imp = new ImpiegatoEntity();
		imp.setUtente(user);
		var rifTo = impiegatoDto.getRiferimento_id();
		var ufficioId = impiegatoDto.getUfficio_id();
		if (rifTo != null) {
			var superior = this.db.findById(rifTo);
			if (superior.isPresent())
				imp.setRiferimento(superior.get());
		}
		if (ufficioId != null) {
			var ufficio = this.uffSrv.findByid(ufficioId);
			if (ufficio.isPresent())
				imp.setUfficio(ufficio.get());
		}

		return this.db.save(imp);
	}

	public List<ImpiegatoEntity> findAll() {
		return db.findAll(); // Passacarte
	}

	public ImpiegatoEntity save(ImpiegatoEntity e) {
		return db.save(e);
	}

	public void delete(int id) {
		db.delete(db.getById(id));
	}

	public ImpiegatoEntity getByid(int id) {
		return db.findById(id).orElse(new ImpiegatoEntity());
	}

	private List<ImpiegatoItemDto> convert(List<ImpiegatoEntity> list) {
		return list.stream().map(i -> ImpiegatoDtoFactory.createListItem(i)).collect(Collectors.toList());
	}

	public List<ImpiegatoItemDto> getListByStipendio(double min, double max) {
		return this.convert(this.getByStipendio(min, max));
	}

	public List<ImpiegatoEntity> getByStipendio(double min, double max) {
		return db.findByStipendioBetween(min, max);
	}

	public List<ImpiegatoItemDto> getListImpiegatoByRuolo(String ruolo) {
		return this.convert(this.getImpiegatoByRuolo(ruolo));
	}

	public List<ImpiegatoEntity> getImpiegatoByRuolo(String ruolo) {
		return db.findByRuolo(ruolo);
	}

	public List<String> getRuolo() {
		return Arrays.asList(Role.impiegato.toString(), Role.supervisore.toString());
	}

	public List<ImpiegatoItemDto> getListImpiegatoByNome(String ruolo) {
		return this.convert(this.getImpiegatoByNome(ruolo));
	}

	public List<ImpiegatoEntity> getImpiegatoByNome(String nome) {
		return db.findByNomeStartsWith(nome);
	}

	public List<ImpiegatoItemDto> getListImpiegatoByCognome(String ruolo) {
		return this.convert(this.getImpiegatoByCognome(ruolo));
	}

	public List<ImpiegatoEntity> getImpiegatoByCognome(String cognome) {
		return db.findByCognomeStartsWith(cognome);
	}

}
