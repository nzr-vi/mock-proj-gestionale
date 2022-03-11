connettersi a mysql come root

CREATE DATABASE 'gestionale_spring';
CREATE USER 'utente_spring'@'localhost' IDENTIFIED BY 'laPassword';
GRANT ALL on gestionale_spring.* to 'utente_spring'@'localhost';

mysql -u utente_spring -p gestionale_spring < seed.sql