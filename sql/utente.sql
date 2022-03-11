CREATE TABLE `gestionale_spring`.`utente` (
  `id` INT NOT NULL,
  `nome` VARCHAR(50) NULL,
  `cognome` VARCHAR(50) NULL,
  `mail` VARCHAR(100) NOT NULL,
  `ruolo` ENUM('supervisore', 'impiegato', 'cliente') NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `mail_UNIQUE` (`mail` ASC) VISIBLE);

ALTER TABLE `gestionale_spring`.`utente` 
    ADD COLUMN `password` VARCHAR(30) NOT NULL AFTER `mail`;

INSERT INTO utente (id, nome, cognome, mail, password, ruolo) VALUES
(1, 'Pippo', 'Rossi','cliente@gmail.com', 'c', 'cliente'),
(2, 'Pluto', 'Verdi','impiegato@gmail.com', 'i', 'impiegato'),
(3, 'Paperino', 'Gialli','supervisore@gmail.com', 's', 'supervisore');