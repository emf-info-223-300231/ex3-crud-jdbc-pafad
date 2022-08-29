create schema 223_personne_1table;
use 223_personne_1table;

-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: 223_personne
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `t_personne`
--

DROP TABLE IF EXISTS `t_personne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_personne` (
  `PK_PERS` int(11) AUTO_INCREMENT NOT NULL,
  `Prenom` varchar(100) DEFAULT NULL,
  `Nom` varchar(100) DEFAULT NULL,
  `Date_naissance` date DEFAULT NULL,
  `No_rue` int(11) DEFAULT NULL,
  `Rue` varchar(100) DEFAULT NULL,
  `NPA` int(11) DEFAULT NULL,
  `Ville` varchar(100) DEFAULT NULL,
  `Actif` tinyint(1) DEFAULT NULL,
  `Salaire` decimal(12,2) DEFAULT NULL,
  `date_modif` datetime DEFAULT NULL,
  `no_modif` int(11) DEFAULT '0',
  PRIMARY KEY (`PK_PERS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_personne`
--

LOCK TABLES `t_personne` WRITE;
/*!40000 ALTER TABLE `t_personne` DISABLE KEYS */;
INSERT INTO `t_personne` VALUES (1,'Max','Dupond','1968-02-29',442,'Pérolles',1701,'Fribourg',0,25000.00,'2015-09-11 12:58:23',0),(2,'Bernard','Haller','1943-05-13',14,'Rte du Centre',1724,'Marly',1,100000.00,'2015-09-11 12:49:28',0),(3,'Alexia','Baurer','1990-01-01',12,'Ch. de la Gare',1753,'Matran',1,75000.00,'2015-09-11 12:49:28',0),(4,'Louis','Rossier','1932-07-31',25,'Rte des Murailles',1754,'Avry',1,45000.00,'2015-09-11 12:49:28',0),(5,'André','Magnin','1988-08-08',65,'Les Dailles',1752,'Villars-sur-Glâne',0,47500.00,'2015-09-11 12:49:28',0),(6,'Edouard','Gumy','1972-12-01',23,'Gorges du Gotéron',1700,'Fribourg',0,52134.00,'2015-09-11 12:49:28',0),(9,'Jacques-André','de la Patelière','1958-10-15',1,'Rue du château',1700,'Fribourg',1,125110.00,'2015-09-11 12:49:28',0),(11,'Chen','Talow','1972-02-29',1,'School Road',87363,'New Dehli',1,12000.00,'2015-09-11 12:49:28',0),(12,'Mike','Gandi','1966-06-06',236,'Bourbon Street',87456,'New Nehli',1,14500.00,'2015-09-11 12:49:28',0),(13,'Robert','Moret','1966-03-03',5,'La Jorettaz',1663,'Gruyère',1,39000.00,'2015-09-11 12:49:28',0),(14,'Gabrielle','Vuarnoz','1955-05-05',12,'Gai-Logis',1745,'Lentigny',0,63000.00,'2015-09-11 12:49:28',0),(15,'Francis','Fragnière','1969-02-12',1,'Le Buth',1669,'Lessoc',0,41156.00,'2015-09-11 12:49:28',0),(16,'Laurence','Toffel','1980-03-21',35,'Wilerweg',3280,'Morat',1,57300.00,'2015-09-11 12:49:28',0),(17,'Henri','Marmier','1976-10-13',8,'Rue du château',1530,'Payerne',0,43050.00,'2015-09-11 12:49:28',0),(18,'Christel','Charrière','2019-03-14',2,'Pl. de la Perraisa',1680,'Romont',1,23000.00,'2015-09-11 12:49:28',0),(19,'Thomas','Weibel','1996-06-30',6,'Ober Tasberg',1717,'St. Ursen',0,NULL,'2015-09-11 12:49:28',0),(20,'Frédéric','Bielmann','2000-01-01',35,'Le Chêne',1733,'Treyvaux',0,NULL,'2015-09-11 12:49:28',0),(21,'Urs','Lehmann','2008-08-08',3,'Bernertor',3200,'Murten',0,NULL,'2015-09-11 12:49:28',0);
/*!40000 ALTER TABLE `t_personne` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-04 12:59:32
