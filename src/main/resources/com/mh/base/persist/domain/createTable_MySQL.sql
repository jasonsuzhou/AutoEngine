create table `ClientAuthConfig` (
	`id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`creationDate` DATETIME ,
	`lastUpdatedDate` DATETIME ,
	`creationBy` VARCHAR(128) ,
	`lastUpdatedBy` VARCHAR(128) ,
	`appId` VARCHAR(128) ,
	`appSecret` VARCHAR(128) ,
	`nonce` VARCHAR(128) );

CREATE TABLE `SYS_FUNCTION` (
	`func_id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`creation_date` DATETIME ,
	`last_updated_date` DATETIME ,
	`created_by` VARCHAR(128) ,
	`last_updated_by` VARCHAR(128) ,
	`func_name` VARCHAR(128) ,
	`func_link` VARCHAR(128) ,
	`parent_func_id` INT(11) ,
	`func_level` INT(11)
);

create table `SYS_ADMIN` (
	`id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`creation_date` DATETIME ,
	`last_updated_date` DATETIME ,
	`created_by` VARCHAR(64) ,
	`last_updated_by` VARCHAR(64) ,
	`username` VARCHAR(64) ,
	`password` VARCHAR(32) ,
	`salt` VARCHAR(32)
);
