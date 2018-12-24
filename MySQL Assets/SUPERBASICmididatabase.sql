-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema superbasicmididatabase
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema superbasicmididatabase
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `superbasicmididatabase` DEFAULT CHARACTER SET utf8 ;
USE `superbasicmididatabase` ;

-- -----------------------------------------------------
-- Table `superbasicmididatabase`.`PNotes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `superbasicmididatabase`.`PNotes` (
  `id_NOTE` INT NOT NULL AUTO_INCREMENT,
  `NOTE_ON` FLOAT NULL,
  `NOTE_OFF` FLOAT NULL,
  `KEY` INT NULL,
  `VELOCITY` INT NULL,
  `TRACK_NUMBER` INT NULL,
  PRIMARY KEY (`id_NOTE`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
