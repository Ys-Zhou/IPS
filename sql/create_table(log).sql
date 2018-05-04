CREATE TABLE `ips`.`log` (
  `logid` int(11) NOT NULL AUTO_INCREMENT,
  `androidid` varchar(100) NOT NULL,
  `time` datetime NOT NULL,
  `flag` varchar(10) NOT NULL,
  `mac` varchar(17) NOT NULL,
  `rssis` varchar(200) NOT NULL,
  PRIMARY KEY (`logid`));
