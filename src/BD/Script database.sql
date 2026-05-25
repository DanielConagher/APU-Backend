CREATE DATABASE IF NOT EXISTS apu_db;
USE apu_db;

-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: apu_db
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id_administrador` int NOT NULL AUTO_INCREMENT,
  `primer_nombre` varchar(50) DEFAULT NULL,
  `segundo_nombre` varchar(50) DEFAULT NULL,
  `primer_apellido` varchar(50) DEFAULT NULL,
  `segundo_apellido` varchar(50) DEFAULT NULL,
  `ultima_conexion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_administrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentario` (
  `id_comentario` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `descripcion` text,
  `id_contenido` int DEFAULT NULL,
  PRIMARY KEY (`id_comentario`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_contenido` (`id_contenido`),
  CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `estudiante` (`id_estudiante`),
  CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`id_contenido`) REFERENCES `contenido` (`id_contenido`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES (1,1,'2026-05-23 18:30:00','Fue muy util y claro, muchas gracias.',1);
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contenido`
--

DROP TABLE IF EXISTS `contenido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contenido` (
  `id_contenido` int NOT NULL AUTO_INCREMENT,
  `id_cuestionario` int DEFAULT NULL,
  `id_nivel` int DEFAULT NULL,
  `teoria` text,
  `imagenes` json DEFAULT NULL,
  `experiencia_ganada` int DEFAULT NULL,
  `es_cuestionario` tinyint(1) DEFAULT NULL,
  `num_desastres` int DEFAULT NULL,
  `videos` json DEFAULT NULL,
  PRIMARY KEY (`id_contenido`),
  KEY `id_nivel` (`id_nivel`),
  KEY `fk_contenido_cuestionario` (`id_cuestionario`),
  CONSTRAINT `contenido_ibfk_1` FOREIGN KEY (`id_nivel`) REFERENCES `nivel` (`id_nivel`),
  CONSTRAINT `fk_contenido_cuestionario` FOREIGN KEY (`id_cuestionario`) REFERENCES `cuestionario` (`id_cuestionario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contenido`
--

LOCK TABLES `contenido` WRITE;
/*!40000 ALTER TABLE `contenido` DISABLE KEYS */;
INSERT INTO `contenido` VALUES (1,NULL,1,'Sismos en el Perú.\n\nDebemos entender casos anteriores de sismos en el Perú.\n\nEl Perú es uno de los países con mayor actividad sísmica en América del Sur debido a su ubicación en el Cinturón de Fuego del Pacífico.\n\nLos movimientos tectónicos ocurren principalmente por el choque entre la placa de Nazca y la placa Sudamericana, generando frecuentes terremotos en distintas regiones del país.\n\nA lo largo de la historia, el Perú ha sufrido sismos de gran magnitud que causaron pérdidas humanas y daños materiales.\n\nUno de los más recordados fue el terremoto de Ancash de 1970, considerado uno de los desastres naturales más graves del país.\n\nTambién destacan los sismos ocurridos en Lima, Pisco y Arequipa, los cuales evidenciaron la importancia de la prevención y la preparación ante emergencias.','[\"/util/images/theory/Terremoto_Nivel_1.png\"]',50,0,1,'[\"https://www.youtube.com/embed/91b-v3LudGk\"]');
/*!40000 ALTER TABLE `contenido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credenciales`
--

DROP TABLE IF EXISTS `credenciales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credenciales` (
  `id_credencial` int NOT NULL AUTO_INCREMENT,
  `id_estudiante` int DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `id_administrador` int DEFAULT NULL,
  PRIMARY KEY (`id_credencial`),
  UNIQUE KEY `correo` (`correo`),
  UNIQUE KEY `id_administrador` (`id_administrador`),
  KEY `id_usuario` (`id_estudiante`),
  CONSTRAINT `credenciales_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`),
  CONSTRAINT `fk_credenciales_administrador` FOREIGN KEY (`id_administrador`) REFERENCES `administrador` (`id_administrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credenciales`
--

LOCK TABLES `credenciales` WRITE;
/*!40000 ALTER TABLE `credenciales` DISABLE KEYS */;
/*!40000 ALTER TABLE `credenciales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionario`
--

DROP TABLE IF EXISTS `cuestionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuestionario` (
  `id_cuestionario` int NOT NULL AUTO_INCREMENT,
  `id_estudiante` int DEFAULT NULL,
  `completada` tinyint(1) DEFAULT NULL,
  `experiencia_ganada` int DEFAULT NULL,
  `retroalimentacion` text,
  `tipo` varchar(50) DEFAULT NULL,
  `num_desastres` int DEFAULT NULL,
  PRIMARY KEY (`id_cuestionario`),
  KEY `id_estudiante` (`id_estudiante`),
  CONSTRAINT `cuestionario_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionario`
--

LOCK TABLES `cuestionario` WRITE;
/*!40000 ALTER TABLE `cuestionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuestionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discapacidad`
--

DROP TABLE IF EXISTS `discapacidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discapacidad` (
  `id_discapacidad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_discapacidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discapacidad`
--

LOCK TABLES `discapacidad` WRITE;
/*!40000 ALTER TABLE `discapacidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `discapacidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiante` (
  `id_estudiante` int NOT NULL AUTO_INCREMENT,
  `primer_nombre` varchar(50) DEFAULT NULL,
  `segundo_nombre` varchar(50) DEFAULT NULL,
  `primer_apellido` varchar(50) DEFAULT NULL,
  `segundo_apellido` varchar(50) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `es_padre` tinyint(1) DEFAULT NULL,
  `id_ubicacion` int DEFAULT NULL,
  `ultima_conexion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_estudiante`),
  KEY `id_ubicacion` (`id_ubicacion`),
  CONSTRAINT `estudiante_ibfk_2` FOREIGN KEY (`id_ubicacion`) REFERENCES `ubicacion` (`id_ubicacion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES (1,'Carlos','Daniel','Leon','Pastor',22,0,1,'2026-05-23 18:32:34');
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante_discapacidad`
--

DROP TABLE IF EXISTS `estudiante_discapacidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiante_discapacidad` (
  `id_discapacidad` int NOT NULL,
  `id_estudiante` int NOT NULL,
  PRIMARY KEY (`id_discapacidad`,`id_estudiante`),
  KEY `id_estudiante` (`id_estudiante`),
  CONSTRAINT `estudiante_discapacidad_ibfk_1` FOREIGN KEY (`id_discapacidad`) REFERENCES `discapacidad` (`id_discapacidad`),
  CONSTRAINT `estudiante_discapacidad_ibfk_2` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante_discapacidad`
--

LOCK TABLES `estudiante_discapacidad` WRITE;
/*!40000 ALTER TABLE `estudiante_discapacidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `estudiante_discapacidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante_insignia`
--

DROP TABLE IF EXISTS `estudiante_insignia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiante_insignia` (
  `id_insignia` int NOT NULL,
  `id_estudiante` int NOT NULL,
  `fecha_obtencion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_insignia`,`id_estudiante`),
  KEY `id_estudiante` (`id_estudiante`),
  CONSTRAINT `estudiante_insignia_ibfk_1` FOREIGN KEY (`id_insignia`) REFERENCES `insignia` (`id_insignia`),
  CONSTRAINT `estudiante_insignia_ibfk_2` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante_insignia`
--

LOCK TABLES `estudiante_insignia` WRITE;
/*!40000 ALTER TABLE `estudiante_insignia` DISABLE KEYS */;
/*!40000 ALTER TABLE `estudiante_insignia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante_trofeo`
--

DROP TABLE IF EXISTS `estudiante_trofeo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiante_trofeo` (
  `id_trofeo` int NOT NULL,
  `id_estudiante` int NOT NULL,
  `fecha_obtencion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_trofeo`,`id_estudiante`),
  KEY `id_estudiante` (`id_estudiante`),
  CONSTRAINT `estudiante_trofeo_ibfk_1` FOREIGN KEY (`id_trofeo`) REFERENCES `trofeo` (`id_trofeo`),
  CONSTRAINT `estudiante_trofeo_ibfk_2` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante_trofeo`
--

LOCK TABLES `estudiante_trofeo` WRITE;
/*!40000 ALTER TABLE `estudiante_trofeo` DISABLE KEYS */;
/*!40000 ALTER TABLE `estudiante_trofeo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiencia`
--

DROP TABLE IF EXISTS `experiencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `experiencia` (
  `id_experiencia` int NOT NULL AUTO_INCREMENT,
  `id_estudiante` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`id_experiencia`),
  KEY `id_estudiante` (`id_estudiante`),
  CONSTRAINT `experiencia_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiencia`
--

LOCK TABLES `experiencia` WRITE;
/*!40000 ALTER TABLE `experiencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `experiencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insignia`
--

DROP TABLE IF EXISTS `insignia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insignia` (
  `id_insignia` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` text,
  `experiencia_ganada` int DEFAULT NULL,
  PRIMARY KEY (`id_insignia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insignia`
--

LOCK TABLES `insignia` WRITE;
/*!40000 ALTER TABLE `insignia` DISABLE KEYS */;
/*!40000 ALTER TABLE `insignia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
  `id_log` int NOT NULL AUTO_INCREMENT,
  `id_administrador` int DEFAULT NULL,
  `accion` varchar(255) DEFAULT NULL,
  `fecha_accion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_log`),
  KEY `id_administrador` (`id_administrador`),
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`id_administrador`) REFERENCES `administrador` (`id_administrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mapa`
--

DROP TABLE IF EXISTS `mapa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mapa` (
  `id_mapa` int NOT NULL AUTO_INCREMENT,
  `id_estudiante` int DEFAULT NULL,
  `id_tipo_desastre` int DEFAULT NULL,
  `es_personalizado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_mapa`),
  KEY `id_estudiante` (`id_estudiante`),
  KEY `id_tipo_desastre` (`id_tipo_desastre`),
  CONSTRAINT `mapa_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`),
  CONSTRAINT `mapa_ibfk_2` FOREIGN KEY (`id_tipo_desastre`) REFERENCES `tipo_desastre` (`id_tipo_desastre`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mapa`
--

LOCK TABLES `mapa` WRITE;
/*!40000 ALTER TABLE `mapa` DISABLE KEYS */;
INSERT INTO `mapa` VALUES (1,NULL,1,0);
/*!40000 ALTER TABLE `mapa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_mochila`
--

DROP TABLE IF EXISTS `material_mochila`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_mochila` (
  `id_material` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_material`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_mochila`
--

LOCK TABLES `material_mochila` WRITE;
/*!40000 ALTER TABLE `material_mochila` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_mochila` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mochila_general`
--

DROP TABLE IF EXISTS `mochila_general`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mochila_general` (
  `id_mochila` int NOT NULL AUTO_INCREMENT,
  `id_estudiante` int DEFAULT NULL,
  `porcentaje_completado` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id_mochila`),
  KEY `id_estudiante` (`id_estudiante`),
  CONSTRAINT `mochila_general_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mochila_general`
--

LOCK TABLES `mochila_general` WRITE;
/*!40000 ALTER TABLE `mochila_general` DISABLE KEYS */;
/*!40000 ALTER TABLE `mochila_general` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mochila_general_material`
--

DROP TABLE IF EXISTS `mochila_general_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mochila_general_material` (
  `id_mochila` int NOT NULL,
  `id_material` int NOT NULL,
  `cantidad` int DEFAULT NULL,
  `conseguido` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_mochila`,`id_material`),
  KEY `id_material` (`id_material`),
  CONSTRAINT `mochila_general_material_ibfk_1` FOREIGN KEY (`id_mochila`) REFERENCES `mochila_general` (`id_mochila`),
  CONSTRAINT `mochila_general_material_ibfk_2` FOREIGN KEY (`id_material`) REFERENCES `material_mochila` (`id_material`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mochila_general_material`
--

LOCK TABLES `mochila_general_material` WRITE;
/*!40000 ALTER TABLE `mochila_general_material` DISABLE KEYS */;
/*!40000 ALTER TABLE `mochila_general_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mochila_personalizada`
--

DROP TABLE IF EXISTS `mochila_personalizada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mochila_personalizada` (
  `id_mochila` int NOT NULL AUTO_INCREMENT,
  `id_estudiante` int DEFAULT NULL,
  `porcentaje_completado` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id_mochila`),
  KEY `id_estudiante` (`id_estudiante`),
  CONSTRAINT `mochila_personalizada_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mochila_personalizada`
--

LOCK TABLES `mochila_personalizada` WRITE;
/*!40000 ALTER TABLE `mochila_personalizada` DISABLE KEYS */;
/*!40000 ALTER TABLE `mochila_personalizada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mochila_personalizada_material`
--

DROP TABLE IF EXISTS `mochila_personalizada_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mochila_personalizada_material` (
  `id_mochila` int NOT NULL,
  `id_material` int NOT NULL,
  `cantidad` int DEFAULT NULL,
  `conseguido` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_mochila`,`id_material`),
  KEY `id_material` (`id_material`),
  CONSTRAINT `mochila_personalizada_material_ibfk_1` FOREIGN KEY (`id_mochila`) REFERENCES `mochila_personalizada` (`id_mochila`),
  CONSTRAINT `mochila_personalizada_material_ibfk_2` FOREIGN KEY (`id_material`) REFERENCES `material_mochila` (`id_material`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mochila_personalizada_material`
--

LOCK TABLES `mochila_personalizada_material` WRITE;
/*!40000 ALTER TABLE `mochila_personalizada_material` DISABLE KEYS */;
/*!40000 ALTER TABLE `mochila_personalizada_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nivel`
--

DROP TABLE IF EXISTS `nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nivel` (
  `id_nivel` int NOT NULL AUTO_INCREMENT,
  `id_mapa` int DEFAULT NULL,
  `numero_nivel` int DEFAULT NULL,
  PRIMARY KEY (`id_nivel`),
  KEY `id_mapa` (`id_mapa`),
  CONSTRAINT `nivel_ibfk_1` FOREIGN KEY (`id_mapa`) REFERENCES `mapa` (`id_mapa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel`
--

LOCK TABLES `nivel` WRITE;
/*!40000 ALTER TABLE `nivel` DISABLE KEYS */;
INSERT INTO `nivel` VALUES (1,1,1);
/*!40000 ALTER TABLE `nivel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notificacion`
--

DROP TABLE IF EXISTS `notificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notificacion` (
  `id_notificacion` int NOT NULL AUTO_INCREMENT,
  `id_estudiante` int DEFAULT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `contenido` text,
  `imagen` varchar(255) DEFAULT NULL,
  `id_cuestionario` int DEFAULT NULL,
  PRIMARY KEY (`id_notificacion`),
  KEY `id_estudiante` (`id_estudiante`),
  KEY `id_cuestionario` (`id_cuestionario`),
  CONSTRAINT `notificacion_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`),
  CONSTRAINT `notificacion_ibfk_2` FOREIGN KEY (`id_cuestionario`) REFERENCES `cuestionario` (`id_cuestionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacion`
--

LOCK TABLES `notificacion` WRITE;
/*!40000 ALTER TABLE `notificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `notificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pregunta` (
  `id_pregunta` int NOT NULL AUTO_INCREMENT,
  `id_cuestionario` int DEFAULT NULL,
  `descripcion` text,
  PRIMARY KEY (`id_pregunta`),
  KEY `id_cuestionario` (`id_cuestionario`),
  CONSTRAINT `pregunta_ibfk_1` FOREIGN KEY (`id_cuestionario`) REFERENCES `cuestionario` (`id_cuestionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta`
--

LOCK TABLES `pregunta` WRITE;
/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progreso`
--

DROP TABLE IF EXISTS `progreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `progreso` (
  `id_progreso` int NOT NULL AUTO_INCREMENT,
  `id_estudiante` int DEFAULT NULL,
  `id_contenido` int DEFAULT NULL,
  `completada` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_progreso`),
  KEY `id_estudiante` (`id_estudiante`),
  KEY `id_contenido` (`id_contenido`),
  CONSTRAINT `progreso_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`),
  CONSTRAINT `progreso_ibfk_2` FOREIGN KEY (`id_contenido`) REFERENCES `contenido` (`id_contenido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progreso`
--

LOCK TABLES `progreso` WRITE;
/*!40000 ALTER TABLE `progreso` DISABLE KEYS */;
/*!40000 ALTER TABLE `progreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuesta`
--

DROP TABLE IF EXISTS `respuesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `respuesta` (
  `id_respuesta` int NOT NULL AUTO_INCREMENT,
  `id_pregunta` int DEFAULT NULL,
  `descripcion` text,
  `es_correcta` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_respuesta`),
  KEY `id_pregunta` (`id_pregunta`),
  CONSTRAINT `respuesta_ibfk_1` FOREIGN KEY (`id_pregunta`) REFERENCES `pregunta` (`id_pregunta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuesta`
--

LOCK TABLES `respuesta` WRITE;
/*!40000 ALTER TABLE `respuesta` DISABLE KEYS */;
/*!40000 ALTER TABLE `respuesta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_desastre`
--

DROP TABLE IF EXISTS `tipo_desastre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_desastre` (
  `id_tipo_desastre` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_desastre`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_desastre`
--

LOCK TABLES `tipo_desastre` WRITE;
/*!40000 ALTER TABLE `tipo_desastre` DISABLE KEYS */;
INSERT INTO `tipo_desastre` VALUES (1,'Terremoto');
/*!40000 ALTER TABLE `tipo_desastre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trofeo`
--

DROP TABLE IF EXISTS `trofeo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trofeo` (
  `id_trofeo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` text,
  `experiencia_ganada` int DEFAULT NULL,
  PRIMARY KEY (`id_trofeo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trofeo`
--

LOCK TABLES `trofeo` WRITE;
/*!40000 ALTER TABLE `trofeo` DISABLE KEYS */;
/*!40000 ALTER TABLE `trofeo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ubicacion`
--

DROP TABLE IF EXISTS `ubicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ubicacion` (
  `id_ubicacion` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_ubicacion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubicacion`
--

LOCK TABLES `ubicacion` WRITE;
/*!40000 ALTER TABLE `ubicacion` DISABLE KEYS */;
INSERT INTO `ubicacion` VALUES (1,'Los Olivos');
/*!40000 ALTER TABLE `ubicacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zona_segura`
--

DROP TABLE IF EXISTS `zona_segura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zona_segura` (
  `id_zona` int NOT NULL AUTO_INCREMENT,
  `id_ubicacion` int DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` text,
  PRIMARY KEY (`id_zona`),
  KEY `id_ubicacion` (`id_ubicacion`),
  CONSTRAINT `zona_segura_ibfk_1` FOREIGN KEY (`id_ubicacion`) REFERENCES `ubicacion` (`id_ubicacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zona_segura`
--

LOCK TABLES `zona_segura` WRITE;
/*!40000 ALTER TABLE `zona_segura` DISABLE KEYS */;
/*!40000 ALTER TABLE `zona_segura` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-23 22:31:54
