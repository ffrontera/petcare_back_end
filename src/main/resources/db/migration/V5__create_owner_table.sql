CREATE TABLE IF NOT EXISTS `owner` (
  `owner_id` int AUTO_INCREMENT NOT NULL UNIQUE,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `birthdate` DATE NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `address` VARCHAR(250) NOT NULL,
  `type_document` VARCHAR(20) NOT NULL,
  `state` TINYINT UNSIGNED NOT NULL,
  `number_phone` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`owner_id`)
);
