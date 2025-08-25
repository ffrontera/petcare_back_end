CREATE TABLE IF NOT EXISTS `booking` (
	`id`int AUTO_INCREMENT NOT NULL UNIQUE,
	`owner_id` int NOT NULL,
	`sitter_id` int NOT NULL,
	`booking_start` datetime NOT NULL,
	`booking_end` datetime NOT NULL,
	`cost_total_services` decimal(10,0) NOT NULL,
	`create_at` datetime NOT NULL,
	`state` varchar(10) NOT NULL,
	PRIMARY KEY (`id`)
);
