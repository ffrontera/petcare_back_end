CREATE TABLE IF NOT EXISTS `review` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`owner_id` int NOT NULL,
	`sitter_id` int NOT NULL,
	`booking_id` int NOT NULL,
	`score` int NOT NULL,
	`comment` varchar(255) NOT NULL,
	`create_at` datetime NOT NULL,
	PRIMARY KEY (`id`)
);
