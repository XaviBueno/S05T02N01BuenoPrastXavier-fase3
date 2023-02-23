DROP DATABASE `daus`; 
CREATE DATABASE daus;
USE `daus` ;

-- -----------------------------------------------------
-- Table `daus`.`jugadors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS  `jugadors`  (
  `jugador_id` INT NOT NULL AUTO_INCREMENT,
  `exits` DOUBLE NULL DEFAULT NULL,
  `nom` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`jugador_id`));




-- -----------------------------------------------------
-- Table `daus`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `id` BIGINT NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `daus`.`jugador_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS jugador_roles (
  `jugador_id` INT NOT NULL,
  `role_id` BIGINT NOT NULL,
  PRIMARY KEY (`jugador_id`, `role_id`),
    FOREIGN KEY (`role_id`)
    REFERENCES `daus`.`role` (`id`),
     FOREIGN KEY (`jugador_id`)
    REFERENCES `daus`.`jugadors` (`jugador_id`));

INSERT INTO jugadors
VALUES (1,0,"admin","$2a$12$mau4r917jzmMQoZKqLIsoOGfYqBmyESxC.Q2rfKTxSf.e/vFT8VXK");

INSERT INTO role VALUES (1,"administrador", "ADMIN");
INSERT INTO role VALUES (2,"jugador","PLAYER");

INSERT INTO jugador_roles VALUES (1,1);




