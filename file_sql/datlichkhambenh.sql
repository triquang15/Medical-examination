-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: datlichkhambenh
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `bacsi`
--

DROP TABLE IF EXISTS `bacsi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bacsi` (
  `mabs` int NOT NULL AUTO_INCREMENT,
  `tenbs` varchar(45) NOT NULL,
  `ngaysinh` date NOT NULL,
  `makhoa` int NOT NULL,
  PRIMARY KEY (`mabs`),
  UNIQUE KEY `mabs_UNIQUE` (`mabs`),
  KEY `fk_khoa_idx` (`makhoa`),
  CONSTRAINT `fk_khoa` FOREIGN KEY (`makhoa`) REFERENCES `khoa` (`makhoa`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bacsi`
--

LOCK TABLES `bacsi` WRITE;
/*!40000 ALTER TABLE `bacsi` DISABLE KEYS */;
INSERT INTO `bacsi` VALUES (1,'Dr.Quang','1995-09-12',2),(2,'Rolnadol','1996-09-17',8);
/*!40000 ALTER TABLE `bacsi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `benhnhan`
--

DROP TABLE IF EXISTS `benhnhan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `benhnhan` (
  `mabn` int NOT NULL AUTO_INCREMENT,
  `tenbn` varchar(45) NOT NULL,
  `ngaysinh` date NOT NULL,
  `gioitinh` varchar(20) NOT NULL,
  PRIMARY KEY (`mabn`),
  UNIQUE KEY `mabn_UNIQUE` (`mabn`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `benhnhan`
--

LOCK TABLES `benhnhan` WRITE;
/*!40000 ALTER TABLE `benhnhan` DISABLE KEYS */;
INSERT INTO `benhnhan` VALUES (1,'Nguyen Tri Quang','2022-01-12','Male'),(3,'Leo Messi','2022-06-16','Male'),(4,'Linda','2022-06-13','Male');
/*!40000 ALTER TABLE `benhnhan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khoa`
--

DROP TABLE IF EXISTS `khoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khoa` (
  `makhoa` int NOT NULL AUTO_INCREMENT,
  `tenkhoa` varchar(45) NOT NULL,
  PRIMARY KEY (`makhoa`),
  UNIQUE KEY `makhoa_UNIQUE` (`makhoa`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khoa`
--

LOCK TABLES `khoa` WRITE;
/*!40000 ALTER TABLE `khoa` DISABLE KEYS */;
INSERT INTO `khoa` VALUES (1,'Odontology'),(2,'Admission Office'),(8,'Admission');
/*!40000 ALTER TABLE `khoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lichkham`
--

DROP TABLE IF EXISTS `lichkham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lichkham` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mabs` int NOT NULL,
  `mabn` int NOT NULL,
  `ngaykham` date NOT NULL,
  `noidung` text NOT NULL,
  `title` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`),
  KEY `fk_bacsi_idx` (`mabs`),
  KEY `fk_benhnhan_idx` (`mabn`),
  CONSTRAINT `fk_bacsi` FOREIGN KEY (`mabs`) REFERENCES `bacsi` (`mabs`),
  CONSTRAINT `fk_benhnhan` FOREIGN KEY (`mabn`) REFERENCES `benhnhan` (`mabn`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lichkham`
--

LOCK TABLES `lichkham` WRITE;
/*!40000 ALTER TABLE `lichkham` DISABLE KEYS */;
INSERT INTO `lichkham` VALUES (1,2,4,'2022-06-16','<span style=\"box-sizing: border-box; border: 0px; margin: 0px; padding: 0px; font-weight: 700; color: rgb(58, 58, 58); font-family: sans-serif; font-size: 19px;\">You must be hospitalized right now</span>','Haematology'),(3,1,3,'2022-06-12','<div>346</div>','afig'),(4,2,4,'2022-06-19','<div>qfqfq</div>','1213'),(5,2,4,'2022-06-21','AWF','qwafD');
/*!40000 ALTER TABLE `lichkham` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-16 21:21:46
