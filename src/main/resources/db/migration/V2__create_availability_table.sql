CREATE TABLE IF NOT EXISTS `availability` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`sitter_id` int NOT NULL,
	`start_time` datetime NOT NULL,
	`end_time` datetime NOT NULL,
	`number_pet` int NOT NULL,
	PRIMARY KEY (`id`)
);
