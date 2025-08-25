ALTER TABLE `pet` ADD CONSTRAINT `pet_fk7` FOREIGN KEY (`owner_id`) REFERENCES `owner`(`owner_id`);
ALTER TABLE `availability` ADD CONSTRAINT `availability_fk1` FOREIGN KEY (`sitter_id`) REFERENCES `sitter`(`sitter_id`);
ALTER TABLE `booking` ADD CONSTRAINT `booking_fk1` FOREIGN KEY (`owner_id`) REFERENCES `owner`(`owner_id`);

ALTER TABLE `booking` ADD CONSTRAINT `booking_fk2` FOREIGN KEY (`sitter_id`) REFERENCES `sitter`(`sitter_id`);
ALTER TABLE `review` ADD CONSTRAINT `review_fk1` FOREIGN KEY (`owner_id`) REFERENCES `owner`(`owner_id`);

ALTER TABLE `review` ADD CONSTRAINT `review_fk2` FOREIGN KEY (`sitter_id`) REFERENCES `sitter`(`sitter_id`);

ALTER TABLE `review` ADD CONSTRAINT `review_fk3` FOREIGN KEY (`booking_id`) REFERENCES `booking`(`id`);


ALTER TABLE `users` ADD CONSTRAINT `users_fk3` FOREIGN KEY (`profile_id`) REFERENCES `profiles`(`profile_id`);

ALTER TABLE `item_services` ADD CONSTRAINT `item_services_fk0` FOREIGN KEY (`booking_id`) REFERENCES `booking`(`id`);

ALTER TABLE `item_services` ADD CONSTRAINT `item_services_fk1` FOREIGN KEY (`item_code`) REFERENCES `service`(`service_id`);
ALTER TABLE `pet_booking` ADD CONSTRAINT `pet_booking_fk0` FOREIGN KEY (`booking_id`) REFERENCES `booking`(`id`);

ALTER TABLE `pet_booking` ADD CONSTRAINT `pet_booking_fk1` FOREIGN KEY (`pet_id`) REFERENCES `pet`(`id`);