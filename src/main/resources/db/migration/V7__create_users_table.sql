CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int AUTO_INCREMENT NOT NULL UNIQUE,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `profile_id` int NOT NULL,
  `birthdate` DATE NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `address` VARCHAR(250) NOT NULL,
  `type_document` ENUM('DNI','LC','P') NOT NULL,
  `state` TINYINT UNSIGNED NOT NULL DEFAULT 1,
  `number_phone` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_users_email` (`email`)
);
