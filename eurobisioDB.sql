-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: Eurobisio
-- ------------------------------------------------------
-- Server version	8.0.23-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Abestia`
--

DROP TABLE IF EXISTS `Abestia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Abestia` (
  `id` int NOT NULL,
  `generoa` varchar(45) DEFAULT NULL,
  `izena` varchar(45) DEFAULT NULL,
  `ParteHartzaileID` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Abestia_ParteHartzaile_idx` (`ParteHartzaileID`),
  CONSTRAINT `fk_Abestia_ParteHartzaile` FOREIGN KEY (`ParteHartzaileID`) REFERENCES `ParteHartzaile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Abestia`
--

LOCK TABLES `Abestia` WRITE;
/*!40000 ALTER TABLE `Abestia` DISABLE KEYS */;
INSERT INTO `Abestia` VALUES (1,'cumbia','Propuesta Indecente',10),(2,'regetonazo','M.I.L.F',13),(4,'DJ','Vapo Vapo X Pocoto',22);
/*!40000 ALTER TABLE `Abestia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bidaiatu`
--

DROP TABLE IF EXISTS `Bidaiatu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bidaiatu` (
  `ParteHartzaileID` int NOT NULL,
  `HegazkinZBK` int NOT NULL,
  `HerriaIzenaNondik` varchar(50) NOT NULL,
  `HerriaIzenaNora` varchar(50) NOT NULL,
  PRIMARY KEY (`ParteHartzaileID`,`HegazkinZBK`,`HerriaIzenaNondik`,`HerriaIzenaNora`),
  KEY `fk_Bidaiatu_Hegazkin_idx` (`HegazkinZBK`),
  KEY `fk_Bidaiatu_Nondik_idx` (`HerriaIzenaNondik`),
  KEY `fk_Bidaiatu_Nora_idx` (`HerriaIzenaNora`),
  CONSTRAINT `fk_Bidaiatu_Abeslari` FOREIGN KEY (`ParteHartzaileID`) REFERENCES `ParteHartzaile` (`id`),
  CONSTRAINT `fk_Bidaiatu_Hegazkin` FOREIGN KEY (`HegazkinZBK`) REFERENCES `Hegazkin` (`zbk`),
  CONSTRAINT `fk_Bidaiatu_Nondik` FOREIGN KEY (`HerriaIzenaNondik`) REFERENCES `Herria` (`izena`),
  CONSTRAINT `fk_Bidaiatu_Nora` FOREIGN KEY (`HerriaIzenaNora`) REFERENCES `Herria` (`izena`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bidaiatu`
--

LOCK TABLES `Bidaiatu` WRITE;
/*!40000 ALTER TABLE `Bidaiatu` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bidaiatu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bozkaketa`
--

DROP TABLE IF EXISTS `Bozkaketa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bozkaketa` (
  `idErab` int NOT NULL,
  `idAbeslari` int NOT NULL,
  `dataPH` date NOT NULL,
  PRIMARY KEY (`idErab`,`idAbeslari`,`dataPH`),
  KEY `fk_Bozkaketa_abeslari_idx` (`idAbeslari`),
  KEY `fk_Bozkaketa_data_idx` (`dataPH`),
  CONSTRAINT `fk_Bozkaketa_abeslari` FOREIGN KEY (`idAbeslari`) REFERENCES `ParteHartzaile` (`id`),
  CONSTRAINT `fk_Bozkaketa_data` FOREIGN KEY (`dataPH`) REFERENCES `Erregistro` (`data`),
  CONSTRAINT `fk_Bozkaketa_erabiltzaile` FOREIGN KEY (`idErab`) REFERENCES `Erabiltzaileak` (`idErabiltzaileak`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bozkaketa`
--

LOCK TABLES `Bozkaketa` WRITE;
/*!40000 ALTER TABLE `Bozkaketa` DISABLE KEYS */;
INSERT INTO `Bozkaketa` VALUES (15,10,'2001-09-11');
/*!40000 ALTER TABLE `Bozkaketa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Erabiltzaileak`
--

DROP TABLE IF EXISTS `Erabiltzaileak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Erabiltzaileak` (
  `idErabiltzaileak` int NOT NULL AUTO_INCREMENT,
  `ErabiltzaileIzena` varchar(45) NOT NULL,
  `ErabiltzaileGako` varchar(45) NOT NULL,
  `ModoBorbon` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idErabiltzaileak`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Erabiltzaileak`
--

LOCK TABLES `Erabiltzaileak` WRITE;
/*!40000 ALTER TABLE `Erabiltzaileak` DISABLE KEYS */;
INSERT INTO `Erabiltzaileak` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','admin'),(2,'bisbol','5464223733454883432b709d3977829a','abeslari'),(3,'rita','0ecee6a0624d5c22a7d004b3878fceaf','erabiltzaile'),(12,'mesi','8a9c28a00bcf7a136178d16a3c55ecbf','abeslari'),(13,'CR7','b9ca466c42fcf4b8bc33e22e2e2b3618','abeslari'),(14,'songExpert13','bec7a744fd168c5d65959ea4b288cb5d','erabiltzaile'),(15,'Perea','dbfaddf043c2822e70abac9a27493015','erabiltzaile'),(16,'bakunin','31e10abc4f418ed16ed9e646f5c0bee7','erabiltzaile'),(17,'pikachu','edb6eb67ad923f497521c09cab18e82c','erabiltzaile');
/*!40000 ALTER TABLE `Erabiltzaileak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Erregistratu`
--

DROP TABLE IF EXISTS `Erregistratu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Erregistratu` (
  `ErregistroData` date NOT NULL,
  `ParteHartzaileID` int NOT NULL,
  `ordua` time DEFAULT NULL,
  `puntuazioa` int DEFAULT '0',
  PRIMARY KEY (`ErregistroData`,`ParteHartzaileID`),
  KEY `fk_Erregistratu_ParteHartzaile_idx` (`ParteHartzaileID`),
  CONSTRAINT `fk_Erregistratu_Erregistro` FOREIGN KEY (`ErregistroData`) REFERENCES `Erregistro` (`data`),
  CONSTRAINT `fk_Erregistratu_ParteHartzaile` FOREIGN KEY (`ParteHartzaileID`) REFERENCES `ParteHartzaile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Erregistratu`
--

LOCK TABLES `Erregistratu` WRITE;
/*!40000 ALTER TABLE `Erregistratu` DISABLE KEYS */;
INSERT INTO `Erregistratu` VALUES ('2021-05-07',10,'10:20:00',2),('2021-05-07',13,'10:30:00',6),('2021-05-07',22,'11:00:00',1);
/*!40000 ALTER TABLE `Erregistratu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Erregistro`
--

DROP TABLE IF EXISTS `Erregistro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Erregistro` (
  `data` date NOT NULL,
  PRIMARY KEY (`data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Erregistro`
--

LOCK TABLES `Erregistro` WRITE;
/*!40000 ALTER TABLE `Erregistro` DISABLE KEYS */;
INSERT INTO `Erregistro` VALUES ('2001-09-11'),('2021-04-16'),('2021-05-07'),('2021-09-08');
/*!40000 ALTER TABLE `Erregistro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hegazkin`
--

DROP TABLE IF EXISTS `Hegazkin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Hegazkin` (
  `zbk` int NOT NULL,
  `izena` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`zbk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hegazkin`
--

LOCK TABLES `Hegazkin` WRITE;
/*!40000 ALTER TABLE `Hegazkin` DISABLE KEYS */;
/*!40000 ALTER TABLE `Hegazkin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Herria`
--

DROP TABLE IF EXISTS `Herria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Herria` (
  `izena` varchar(50) NOT NULL,
  `probintzia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`izena`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Herria`
--

LOCK TABLES `Herria` WRITE;
/*!40000 ALTER TABLE `Herria` DISABLE KEYS */;
INSERT INTO `Herria` VALUES ('Durango','Bizkaia'),('Erandio','Bizkaia'),('Lemoa','Bizkaia'),('Sestao','Bizkaia');
/*!40000 ALTER TABLE `Herria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ostatze`
--

DROP TABLE IF EXISTS `Ostatze`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ostatze` (
  `izena` varchar(50) NOT NULL,
  `herria` varchar(50) NOT NULL,
  PRIMARY KEY (`izena`,`herria`),
  KEY `fk_Ostatze_Herria_idx` (`herria`),
  CONSTRAINT `fk_Ostatze_Herria` FOREIGN KEY (`herria`) REFERENCES `Herria` (`izena`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ostatze`
--

LOCK TABLES `Ostatze` WRITE;
/*!40000 ALTER TABLE `Ostatze` DISABLE KEYS */;
/*!40000 ALTER TABLE `Ostatze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ParteHartzaile`
--

DROP TABLE IF EXISTS `ParteHartzaile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ParteHartzaile` (
  `id` int NOT NULL,
  `NAN` varchar(45) NOT NULL,
  `Izena` varchar(45) NOT NULL,
  `adina` int DEFAULT NULL,
  `herria` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `NAN_UNIQUE` (`NAN`),
  KEY `fk_ParteHartzaile_Herria_idx` (`herria`),
  CONSTRAINT `fk_ParteHartzaile_Herria` FOREIGN KEY (`herria`) REFERENCES `Herria` (`izena`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ParteHartzaile`
--

LOCK TABLES `ParteHartzaile` WRITE;
/*!40000 ALTER TABLE `ParteHartzaile` DISABLE KEYS */;
INSERT INTO `ParteHartzaile` VALUES (10,'666','mesi',40,'Erandio'),(13,'1234','bisbol',29,'Sestao'),(22,'552','CR7',28,'Lemoa');
/*!40000 ALTER TABLE `ParteHartzaile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-08 18:08:01
