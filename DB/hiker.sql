-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hikerdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `hikerdb` ;

-- -----------------------------------------------------
-- Schema hikerdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hikerdb` DEFAULT CHARACTER SET utf8 ;
USE `hikerdb` ;

-- -----------------------------------------------------
-- Table `hiker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hiker` ;

CREATE TABLE IF NOT EXISTS `hiker` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS hikeruser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'hikeruser'@'localhost' IDENTIFIED BY 'hikeruser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'hikeruser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `hiker`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikerdb`;
INSERT INTO `hiker` (`id`, `name`) VALUES (1, 'Hanna');

COMMIT;

