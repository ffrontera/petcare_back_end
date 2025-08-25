CREATE TABLE IF NOT EXISTS `pet` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`name` text NOT NULL,
	`species` text NOT NULL,
	`breed` text NOT NULL,
	`age` int NOT NULL,
	`weight` decimal(10,2) NOT NULL,
	`comments` text NOT NULL,
	`owner_id` int NOT NULL,
	PRIMARY KEY (`id`)
);