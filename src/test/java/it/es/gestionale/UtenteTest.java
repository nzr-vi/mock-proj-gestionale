package it.es.gestionale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.Transient;

import it.es.gestionale.model.ClienteEntity;
import it.es.gestionale.model.OrdineEntity;
import it.es.gestionale.model.UtenteEntity;
import it.es.gestionale.model.UtenteEntity.Role;
import it.es.gestionale.repository.ClienteDB;
import it.es.gestionale.repository.OrdineDB;
import it.es.gestionale.repository.UtenteDB;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class UtenteTest {
	@Autowired
	UtenteDB db;


	@Test
	@Transactional
	void saveNewUserTest() {

		UtenteEntity attempted = new UtenteEntity();
		attempted.setEmail("a@gmail.com");
		attempted.setCognome("mario");
		attempted.setNome("monti");
		attempted.setPassword(attempted.getEmail());
		attempted.setRuolo(Role.cliente);
		
		var newUser = this.db.save(attempted);
		
		System.out.println("---created:\n---"+newUser);
		
		assertNotEquals(null, newUser);
	}

}
