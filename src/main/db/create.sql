CREATE DATABASE IF NOT EXISTS template;
USE template;
DROP TABLE IF EXISTS `article`;

CREATE TABLE IF NOT EXISTS `article` (
	`title` VARCHAR(255),
	`content` TEXT,
	`published` BOOLEAN DEFAULT 0,
    PRIMARY KEY (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT
INTO `article` 
	(`title`,
	`content`,
	`published`)
VALUES ("git",
		"꼬리표(tag) 달기\n\n 소프트웨어의 새 버전을 발표할 때마다 ",
		1);
		
		