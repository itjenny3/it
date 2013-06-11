CREATE DATABASE IF NOT EXISTS template;
USE template;
DROP TABLE IF EXISTS `like`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `document`;
DROP TABLE IF EXISTS `missionOnMember`;
DROP TABLE IF EXISTS `itemOnMember`;
DROP TABLE IF EXISTS `docOnMember`;
DROP TABLE IF EXISTS `mission`;
DROP TABLE IF EXISTS `item`;
DROP TABLE IF EXISTS `myDoc`;

CREATE TABLE IF NOT EXISTS `UserConnection` (
	`userId` CHAR(255) NOT NULL,
    `providerId` CHAR(255) NOT NULL,
    `providerUserId` CHAR(255),
    `rank` INT NOT NULL,
    `displayName` CHAR(255),
    `profileUrl` VARCHAR(512),
    `imageUrl` VARCHAR(512),
    `accessToken` CHAR(255) NOT NULL,					
    `secret` CHAR(255),
    `refreshToken` CHAR(255),
    `expireTime` BIGINT,
    PRIMARY KEY (`userId`, `providerId`, `providerUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `member` (
	`providerUserId` CHAR(16),
	`name` CHAR(20),
	`gender` BOOLEAN,
	`email` CHAR(40),
	`level` INT DEFAULT 0,
	`point` INT DEFAULT 0,
	`doc` INT DEFAULT 0,
	`comment` INT DEFAULT 0,
	`commentOnMyDoc` INT DEFAULT 0,
	`like` INT DEFAULT 0,
	`likeOnMyDoc` INT DEFAULT 0,
	`dislike` INT DEFAULT 0,
	`dislikeOnMyDoc` INT DEFAULT 0,
	`sharing` INT DEFAULT 0,
	`sharingOfMyDoc` INT DEFAULT 0,
	`chanceToDoc` INT DEFAULT 2,
	`chanceToComment` INT DEFAULT 2,
	`chanceToLike` INT DEFAULT 2,
	`chanceToDislike` INT DEFAULT 2,
	`locale` CHAR(4),
	`regDate` DATETIME NOT NULL,
	`lastLoginDate` DATETIME NOT NULL,
	PRIMARY KEY (`providerUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `mission` (
	`missionId` INT,
	`condition` INT,
	`desc` TEXT,
	PRIMARY KEY (`missionId`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `item` (
	`itemId` INT,
	`name` CHAR(10),
	`condition` INT,
	`desc` TEXT,
	`price` INT,
	PRIMARY KEY (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `docOnMember` (
	`id` INT AUTO_INCREMENT,
	`content` TEXT NOT NULL,
	`providerUserId` CHAR(16) NOT NULL,
	`regDate` DATETIME NOT NULL,
	PRIMARY KEY (`id`),
	INDEX (`providerUserId`),
	FOREIGN KEY (`providerUserId`) REFERENCES `member`(`providerUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `missionOnMember` (
	`providerUserId` CHAR(16),
	`missionId` INT,
	PRIMARY KEY (`providerUserId`, `missionId`),
	FOREIGN KEY (`providerUserId`) REFERENCES `member`(`providerUserId`),
	FOREIGN KEY (`missionId`) REFERENCES `mission`(`missionId`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `itemOnMember` (
	`providerUserId` CHAR(16),
	`itemId` INT,
	PRIMARY KEY (`providerUserId`, `itemId`),
	FOREIGN KEY (`itemId`) REFERENCES `item`(`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `document` (
	`documentId` INT AUTO_INCREMENT,
	`content` TEXT NOT NULL,
	`point` INT DEFAULT 0,
	`like` INT DEFAULT 0,
	`dislike` INT DEFAULT 0,
	`comment` INT DEFAULT 0,
	`sharing` INT DEFAULT 0,
	`providerUserId` CHAR(16) NOT NULL,
	`name` CHAR(20),
	`regDate` DATETIME NOT NULL,
	PRIMARY KEY (`documentId`),
	INDEX (`providerUserId`, `regDate`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `comment` (
	`commentId` INT AUTO_INCREMENT,
	`documentId` INT NOT NULL,
	`content` TEXT NOT NULL,
	`providerUserId` CHAR(16) NOT NULL,
	`name` CHAR(20),
	`isMyDoc` BOOLEAN NOT NULL,
	`regDate` DATETIME NOT NULL,
	PRIMARY KEY (`commentId`),
	INDEX (`providerUserId`, `regDate`, `isMyDoc`),
	FOREIGN KEY (`documentId`) REFERENCES document(`documentId`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `like` (
	`providerUserId` CHAR(16) NOT NULL,
	`documentId` INT NOT NULL,
	`isLike` BOOLEAN NOT NULL,
	`regDate` DATETIME NOT NULL,
	INDEX (`providerUserId`, `regDate`, `isLike`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `myDoc` (
	`myDocId` INT AUTO_INCREMENT,
	`title` TEXT NOT NULL,
	`content` TEXT NOT NULL,
	`providerUserId` CHAR(16) NOT NULL,
	`tag` TEXT,
	`isGoal` BOOLEAN NOT NULL,
	`regDate` DATETIME NOT NULL,
	PRIMARY KEY (`myDocId`),
	INDEX (`providerUserId`, `regDate`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT
INTO `item` 
	(`itemId`,
	`name`,
	`condition`,
	`price`,
	`desc`)
VALUES (1,
		"item1",
		1,
		20,
		"what's this?");
	
INSERT
INTO `item` 
	(`itemId`,
	`name`,
	`condition`,
	`price`,
	`desc`)
VALUES (2,
		"item2",
		2,
		50,
		"what's this?");
	
INSERT
INTO `item` 
	(`itemId`,
	`name`,
	`condition`,
	`price`,
	`desc`)
VALUES (3,
		"item3",
		3,
		100,
		"what's this?");