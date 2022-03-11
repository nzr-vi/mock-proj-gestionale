CREATE TABLE `gestionale_spring`.`utente` (
  `id` INT NOT NULL,
  `nome` VARCHAR(50) NULL,
  `cognome` VARCHAR(50) NULL,
  `mail` VARCHAR(100) NOT NULL,
  `ruolo` ENUM('supervisore', 'impiegato', 'cliente') NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `mail_UNIQUE` (`mail` ASC) VISIBLE);