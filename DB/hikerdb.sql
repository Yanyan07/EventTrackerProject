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
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zipcode` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hiker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hiker` ;

CREATE TABLE IF NOT EXISTS `hiker` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(10) NULL,
  `location_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hiker_locaton1_idx` (`location_id` ASC),
  CONSTRAINT `fk_hiker_locaton1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trail` ;

CREATE TABLE IF NOT EXISTS `trail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `length` DECIMAL NULL,
  `location_id` INT NOT NULL,
  `image_url` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trail_locaton1_idx` (`location_id` ASC),
  CONSTRAINT `fk_trail_locaton1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `single_hiking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `single_hiking` ;

CREATE TABLE IF NOT EXISTS `single_hiking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hiker_id` INT NOT NULL,
  `trail_id` INT NOT NULL,
  `distance` DECIMAL(5,2) NULL,
  `hiking_date` DATE NULL,
  INDEX `fk_hiker_trail_hiker1_idx` (`hiker_id` ASC),
  INDEX `fk_hiker_trail_trail1_idx` (`trail_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_hiker_trail_hiker1`
    FOREIGN KEY (`hiker_id`)
    REFERENCES `hiker` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hiker_trail_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikerdb`;
INSERT INTO `location` (`id`, `street`, `city`, `state`, `zipcode`) VALUES (1, '2200 15th st', 'denver', 'co', '80202');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `zipcode`) VALUES (2, '1000 us hwy 36', 'estes park', 'co', '80517');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `zipcode`) VALUES (3, 's brook forest rd', 'conifer', 'co', '80433');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `zipcode`) VALUES (4, '12343 guanella pass rd', 'ladho springs', 'co', '80452');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `zipcode`) VALUES (5, '1001 lookout mountain rd', 'golden', 'co', '80401');

COMMIT;


-- -----------------------------------------------------
-- Data for table `hiker`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikerdb`;
INSERT INTO `hiker` (`id`, `name`, `gender`, `location_id`) VALUES (1, 'hanna', 'female', NULL);
INSERT INTO `hiker` (`id`, `name`, `gender`, `location_id`) VALUES (2, 'oscar', 'male', NULL);
INSERT INTO `hiker` (`id`, `name`, `gender`, `location_id`) VALUES (3, 'jenny', 'female', NULL);
INSERT INTO `hiker` (`id`, `name`, `gender`, `location_id`) VALUES (4, 'stan', 'male', NULL);
INSERT INTO `hiker` (`id`, `name`, `gender`, `location_id`) VALUES (5, 'eric', 'male', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `trail`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikerdb`;
INSERT INTO `trail` (`id`, `name`, `length`, `location_id`, `image_url`) VALUES (1, 'Emerald Lake Trail', 3.6, 2, 'https://cdn-assets.alltrails.com/uploads/photo/image/23830180/large_40ce3619a520c7ec4212ab595c25dd98.jpg');
INSERT INTO `trail` (`id`, `name`, `length`, `location_id`, `image_url`) VALUES (2, 'Mount Bierstadt Trail', 7.8, 4, 'https://cdn-assets.alltrails.com/uploads/photo/image/11251912/large_cf8e45ccd1adb1e4ce75457e5529fb00.jpg');
INSERT INTO `trail` (`id`, `name`, `length`, `location_id`, `image_url`) VALUES (3, 'Chavez and Beaver Brook Trail Loop', 5.0, 5, 'https://cdn-assets.alltrails.com/uploads/photo/image/29605732/large_3d6ce3c1064141503013c5b41d9a1e88.jpg');
INSERT INTO `trail` (`id`, `name`, `length`, `location_id`, `image_url`) VALUES (4, 'Maxwell Falls Lower Trail', 4.4, 3, 'https://cdn-assets.alltrails.com/uploads/photo/image/30231336/large_450ed72d5a37010b9031a837c054112e.jpg');
INSERT INTO `trail` (`id`, `name`, `length`, `location_id`, `image_url`) VALUES (5, 'Cherry Creek Trail', 40, 1, 'https://cdn-assets.alltrails.com/uploads/photo/image/40500178/large_61f91e6aa72329bf14871734025fc5b6.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `single_hiking`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikerdb`;
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (1, 1, 1, 1.5, '2021-10-1');
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (2, 1, 4, 0.9, '2021-10-5');
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (3, 2, 3, 3.0, '2021-10-1');
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (4, 2, 5, 4.5, '2021-10-3');
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (5, 2, 5, 2.5, '2021-10-5');
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (6, 2, 5, 2.5, '2021-10-7');
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (7, 3, 2, 1.6, '2021-10-2');
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (8, 3, 1, 1.0, '2021-10-5');
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (9, 4, 5, 1.8, '2021-10-1');
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (10, 4, 2, 2.0, '2021-10-5');
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (11, 5, 3, 1.0, '2021-10-2');
INSERT INTO `single_hiking` (`id`, `hiker_id`, `trail_id`, `distance`, `hiking_date`) VALUES (12, 5, 5, 2.0, '2021-10-6');

COMMIT;

