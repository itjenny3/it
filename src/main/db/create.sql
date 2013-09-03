CREATE DATABASE IF NOT EXISTS itjenny;
USE itjenny;
DROP TABLE IF EXISTS `article`;
DROP TABLE IF EXISTS `bookmark`;
DROP TABLE IF EXISTS `social_user`;

CREATE TABLE IF NOT EXISTS `article` (
	`title` VARCHAR(255),
	`content` TEXT,
	`published` BOOLEAN DEFAULT 0,
    PRIMARY KEY (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `bookmark` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(255),
    `provider_user_id` VARCHAR(255),
    `chapter` INTEGER,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `social_user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `access_token` VARCHAR(255) NOT NULL,
    `create_date` DATETIME,
    `display_name` VARCHAR(255),
    `expire_time` BIGINT,
    `image_url` VARCHAR(255),
    `profile_url` VARCHAR(255),
    `provider_id` VARCHAR(255) not null,
    `provider_user_id` VARCHAR(255),
    `rank` INTEGER NOT NULL,
    `refresh_token` VARCHAR(255),
    `secret` VARCHAR(255),
    `user_id` VARCHAR(255),
    `email` VARCHAR(255),
    `password` VARCHAR(255),
    PRIMARY KEY (`id`),
    UNIQUE (`user_id`, `provider_id`, `rank`),
    UNIQUE (`user_id`, `provider_id`, `provider_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT
INTO `article` 
	(`title`,
	`content`,
	`published`)
VALUES ("QA",
		"Skill Set0\n---------\n - Spring : 자바 Framework\n\nSkill Set\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer\n\nSkill Set2\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer2\n\nSkill Set3\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer3\n\n",
		1);