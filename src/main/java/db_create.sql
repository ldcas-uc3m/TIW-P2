-- MySQL Script generated by MySQL Workbench
-- Tue Oct 18 20:37:25 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tiwp2
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tiwp2` ;

-- -----------------------------------------------------
-- Schema tiwp2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tiwp2` DEFAULT CHARACTER SET utf8 ;
USE `tiwp2` ;


-- -----------------------------------------------------
-- Table `tiwp2`.`posiciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tiwp2`.`posiciones` ;

CREATE TABLE IF NOT EXISTS `tiwp2`.`posiciones` (
  `nombre` VARCHAR(15) NOT NULL,
  `max_jugadores` INT NOT NULL,
  `num_jugadores` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`nombre`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tiwp2`.`jugadores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tiwp2`.`jugadores` ;

CREATE TABLE IF NOT EXISTS `tiwp2`.`jugadores` (
  `dni` VARCHAR(9) NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `alias` VARCHAR(20) NULL,
  `posicion` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`dni`),
  CONSTRAINT `fk_jugadores_posicion`
    FOREIGN KEY (`posicion`)
    REFERENCES `tiwp2`.`posiciones` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Data for table `tiwp2`.`posiciones`
-- -----------------------------------------------------
START TRANSACTION;
USE `tiwp2`;
INSERT INTO `tiwp2`.`posiciones` (`nombre`, `max_jugadores`)
  VALUES ('Portero', 3);
INSERT INTO `tiwp2`.`posiciones` (`nombre`, `max_jugadores`)
  VALUES ('Defensa', 8);
INSERT INTO `tiwp2`.`posiciones` (`nombre`, `max_jugadores`)
  VALUES ('Medio', 8);
INSERT INTO `tiwp2`.`posiciones` (`nombre`, `max_jugadores`)
  VALUES ('Delantero', 6);
COMMIT;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
