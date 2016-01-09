create table `ClientAuthConfig` (
	`id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`creationDate` DATETIME ,
	`lastUpdatedDate` DATETIME ,
	`creationBy` VARCHAR(128) ,
	`lastUpdatedBy` VARCHAR(128) ,
	`appId` VARCHAR(128) ,
	`appSecret` VARCHAR(128) ,
	`nonce` VARCHAR(128) );
