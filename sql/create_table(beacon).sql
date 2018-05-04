CREATE TABLE `ips`.`beacon` (
  `mac` varchar(17) NOT NULL,
  `uuid` varchar(5) DEFAULT NULL,
  `dscp` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`mac`));
