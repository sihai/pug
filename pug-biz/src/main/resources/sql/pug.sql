
--
-- Current Database: `pug`
--
DROP DATABASE IF EXISTS `pug`;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `pug` /*!40100 DEFAULT CHARACTER SET utf8 */;

GRANT ALL PRIVILEGES ON pug.* TO pug@"%" IDENTIFIED BY '378206';

USE `pug`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` bigint(22) NOT NULL auto_increment,
	`name` varchar(128) collate utf8_bin NOT NULL,
	`password` varchar(64) collate utf8_bin default NULL,
  	`email` varchar(128) collate utf8_bin default NULL,
 	`mobile` varchar(32) collate utf8_bin default NULL,
  	`birthday` date default NULL,
  	`sex` tinyint(4) default NULL,
  	`logo` varchar(128) collate utf8_bin default NULL,
  	`grade` bigint(22) NOT NULL,
  	`type` bigint(22) NOT NULL,
  	`status` bigint(22) NOT NULL,
  	`from_where` bigint(22) NOT NULL,
  	`is_deleted` tinyint(1) NOT NULL,
 	`gmt_create` datetime NOT NULL,
  	`gmt_modified` datetime NOT NULL,
  	PRIMARY KEY  (`id`),
  	UNIQUE KEY `unique_name` (`name`),
  	UNIQUE KEY `unique_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Table structure for table `pug`
--

DROP TABLE IF EXISTS `pug`;
CREATE TABLE `pug` (
	`id` bigint(22) NOT NULL auto_increment,
	`name` varchar(128) collate utf8_bin NOT NULL,
	`description` varchar(512) collate utf8_bin DEFAULT NULL,
	`owner` bigint(22) NOT NULL,
	`status` bigint(22) NOT NULL,
	`type` bigint(22) NOT NULL,
  	`is_deleted` tinyint(1) NOT NULL,
 	`gmt_create` datetime NOT NULL,
  	`gmt_modified` datetime NOT NULL,
  	PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


--
-- Table structure for table `point`
--

DROP TABLE IF EXISTS `point`;
CREATE TABLE `point` (
	`id` bigint(22) NOT NULL auto_increment,
	`name` varchar(128) collate utf8_bin NOT NULL,
	`description` varchar(512) collate utf8_bin DEFAULT NULL,
	`longitude` double NOT NULL,
	`latitude` double NOT NULL,
	`pug` bigint(22) NOT NULL,
  	`is_deleted` tinyint(1) NOT NULL,
 	`gmt_create` datetime NOT NULL,
  	`gmt_modified` datetime NOT NULL,
  	PRIMARY KEY  (`id`),
  	CONSTRAINT `point_pug` FOREIGN KEY (`pug`) REFERENCES `pug` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;