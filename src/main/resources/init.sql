CREATE TABLE `users` (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `username` VARCHAR(50) NOT NULL,
                         `password` VARCHAR(100) NOT NULL,
                         `enabled` TINYINT(1) NOT NULL,
                         PRIMARY KEY (`id`)
);
CREATE TABLE `authorities` (
                               `id` INT NOT NULL AUTO_INCREMENT,
                               `username` VARCHAR(50) NOT NULL,
                               `authority` VARCHAR(50) NOT NULL,
                               PRIMARY KEY (`id`)
);

INSERT INTO `users` (`id`, `username`, `password`, `enabled`) VALUES (null, 'junny', '1234', 1);
INSERT INTO `authorities` (`id`, `username`, `authority`) VALUES (null, 'junny', 'write');
