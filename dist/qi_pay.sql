-- MySQL dump 10.11
--
-- Host: localhost    Database: qi_pay
-- ------------------------------------------------------
-- Server version	5.5.22.t5-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pay_ta`
--

DROP TABLE IF EXISTS `pay_ta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pay_ta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `sn` varchar(4) NOT NULL,
  `forward_url` varchar(64) DEFAULT NULL,
  `notify_url` varchar(64) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_ta`
--

LOCK TABLES `pay_ta` WRITE;
/*!40000 ALTER TABLE `pay_ta` DISABLE KEYS */;
INSERT INTO `pay_ta` VALUES (1,'指点三国','1A2B',NULL,'http://qid.zdsanguo.com/api/v1/pay/notify','2012-11-19 03:04:50','自营指点三国 Android版');
/*!40000 ALTER TABLE `pay_ta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay_ta_pay`
--

DROP TABLE IF EXISTS `pay_ta_pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pay_ta_pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `index` varchar(15) NOT NULL,
  `logo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_ta_pay`
--

LOCK TABLES `pay_ta_pay` WRITE;
/*!40000 ALTER TABLE `pay_ta_pay` DISABLE KEYS */;
INSERT INTO `pay_ta_pay` VALUES (1,'支付宝','0','daily_reward_ball.png'),(2,'财付通','1','daily_reward_ball.png'),(3,'Q币','2','icon-default.png');
/*!40000 ALTER TABLE `pay_ta_pay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay_transaction`
--

DROP TABLE IF EXISTS `pay_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pay_transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_sn` varchar(32) NOT NULL,
  `order_sn` varchar(255) DEFAULT NULL,
  `ta_transaction_sn` varchar(64) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `t_timestamp` timestamp NULL DEFAULT NULL,
  `ta` varchar(32) NOT NULL,
  `tr` varchar(64) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `status` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pay_transaction_log`
--

DROP TABLE IF EXISTS `pay_transaction_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pay_transaction_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment` text NOT NULL,
  `level` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=422 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pay_transaction_notify_endpoint`
--

DROP TABLE IF EXISTS `pay_transaction_notify_endpoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pay_transaction_notify_endpoint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_sn` varchar(32) NOT NULL,
  `notify_endpoint` varchar(255) DEFAULT NULL,
  `creation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-19 11:08:19
