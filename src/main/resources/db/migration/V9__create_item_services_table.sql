CREATE TABLE IF NOT EXISTS `item_services` (
	`booking_id` int NOT NULL,
	`item_code` int NOT NULL,
	`item_quantity` int NOT NULL,
	`unit_value` decimal(20,2) NOT NULL,
	`total_amount` decimal(20,2) NOT NULL
);
