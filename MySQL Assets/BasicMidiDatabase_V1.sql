-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema basicmididatabase
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema basicmididatabase
-- -----------------------------------------------------
/*CREATE SCHEMA IF NOT EXISTS `basicmididatabase` DEFAULT CHARACTER SET utf8 ;*/
drop database if exists basicmididatabase;
create database basicmididatabase;
USE `basicmididatabase` ;

-- -----------------------------------------------------
-- Table `basicmididatabase`.`sequence`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `basicmididatabase`.`sequence` ;

CREATE TABLE IF NOT EXISTS `basicmididatabase`.`sequence` (
  `id_sequence` INT NOT NULL AUTO_INCREMENT,
  `songname` VARCHAR(200) NULL,
  PRIMARY KEY (`id_sequence`),
  UNIQUE INDEX `id_sequence_UNIQUE` (`id_sequence` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `basicmididatabase`.`midi_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `basicmididatabase`.`midi_event` ;

CREATE TABLE IF NOT EXISTS `basicmididatabase`.`midi_event` (
  `id_midi_event` INT NOT NULL AUTO_INCREMENT,
  `id_sequence` INT NULL,
  `track_num` INT NULL,
  `event_start` BIGINT(20) NULL,
  `message_type` VARCHAR(45) NULL,
  `channel_num` INT NULL,
  `command` VARCHAR(45) NULL,
  `key` INT NULL,
  `octave` INT NULL,
  `note_num` INT NULL,
  `note_name` VARCHAR(45) NULL,
  `velocity` INT NULL,
  PRIMARY KEY (`id_midi_event`),
  UNIQUE INDEX `id_midi_event_UNIQUE` (`id_midi_event` ASC),
  CONSTRAINT `FKsequence`
    FOREIGN KEY (`id_sequence`)
    REFERENCES `basicmididatabase`.`sequence` (`id_sequence`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
