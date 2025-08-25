CREATE TABLE IF NOT EXISTS `profiles` (
	`profile_id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`profile_name` text NOT NULL,
	PRIMARY KEY (`profile_id`)
);