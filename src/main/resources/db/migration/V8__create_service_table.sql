CREATE TABLE IF NOT EXISTS `service` (
	`service_id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`name_service` text NOT NULL,
	`description_service` text NOT NULL,
	`price` decimal(10,2) NOT NULL,
	`state` boolean NOT NULL,
	PRIMARY KEY (`service_id`)
);