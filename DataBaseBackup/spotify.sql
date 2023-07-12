-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: spotify
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `artist_id` text,
  `name` text,
  `password` text,
  `email_address` text,
  `biography` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES ('1','Ebi','Ebi','Ebi','Iranian singer - Aghaye seda'),('2','Metallica','Metallica','Metallica','American Band - Metal head'),('3','Shervin Hajipour','Shervin Hajipour','Shervin Hajipour','Iranian singer - from Babolsar'),('4','Charsi','Charsi','Charsi','Iranian singer - Rapper'),('5','Hiphopologist','Hiphopologist','Hiphopologist','Nick Name : Doctor'),('6','Pink Floyd','Pink Floyd','Pink Floyd','American band'),('7','Jay-Z','Jay-Z','Jay-Z','American singer'),('8','Alicia Keys','Alicia Keys','Alicia Keys','American singer');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liked`
--

DROP TABLE IF EXISTS `liked`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liked` (
  `user_id` text NOT NULL,
  `track_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liked`
--

LOCK TABLES `liked` WRITE;
/*!40000 ALTER TABLE `liked` DISABLE KEYS */;
INSERT INTO `liked` VALUES ('e7dafd2d-c590-40d5-9934-1b76110086d1','3'),('e7dafd2d-c590-40d5-9934-1b76110086d1','2'),('e7dafd2d-c590-40d5-9934-1b76110086d1','5'),('e7dafd2d-c590-40d5-9934-1b76110086d1','10'),('8d6cfa74-cf67-4116-bac4-541605990135','9'),('8d6cfa74-cf67-4116-bac4-541605990135','5'),('8d6cfa74-cf67-4116-bac4-541605990135','11'),('8d6cfa74-cf67-4116-bac4-541605990135','3'),('8d6cfa74-cf67-4116-bac4-541605990135','8'),('8d6cfa74-cf67-4116-bac4-541605990135','10'),('8d6cfa74-cf67-4116-bac4-541605990135','1'),('8d6cfa74-cf67-4116-bac4-541605990135','2'),('e7dafd2d-c590-40d5-9934-1b76110086d1','9'),('e7dafd2d-c590-40d5-9934-1b76110086d1','11'),('e7dafd2d-c590-40d5-9934-1b76110086d1','4'),('1e2ee09b-820f-45e3-9ae9-c10d23999daf','7'),('1e2ee09b-820f-45e3-9ae9-c10d23999daf','4'),('87c95086-6db3-4871-8233-0401af8a377d','9'),('87c95086-6db3-4871-8233-0401af8a377d','4'),('87c95086-6db3-4871-8233-0401af8a377d','8'),('87c95086-6db3-4871-8233-0401af8a377d','10'),('87c95086-6db3-4871-8233-0401af8a377d','3'),('a3574147-3367-4f82-978f-043a4ccb2e2f','3'),('a3574147-3367-4f82-978f-043a4ccb2e2f','9'),('a3574147-3367-4f82-978f-043a4ccb2e2f','5');
/*!40000 ALTER TABLE `liked` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music`
--

DROP TABLE IF EXISTS `music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `music` (
  `track_id` text NOT NULL,
  `title` text NOT NULL,
  `album` text,
  `genre` text NOT NULL,
  `duration` time NOT NULL,
  `release_date` date NOT NULL,
  `score` double DEFAULT NULL,
  `file_path` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music`
--

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;
INSERT INTO `music` VALUES ('1','Enter sandman','Hi','metal','02:30:00','2001-02-04',2.5,'Enter sandman'),('2','baraye','Bye','social','02:30:00','2000-06-04',3,'baraye'),('3','Bado bisheh','Setare haye sorbi','pop','02:30:00','2010-02-04',6.9,'Bado bisheh'),('4','Jabeh Javaher','Aghayegh','pop','02:30:00','2000-02-27',3.5,'Jabeh Javaher'),('5','Khanoom gol','-','pop','02:30:00','2000-11-29',1.5,'Khanoom gol'),('6','Hey you','Hey you','rock','02:30:00','2005-10-04',7.3,'Hey you'),('7','Nasakhe noskhe','-','rap','02:30:00','2011-02-04',2.7,'Nasakhe noskhe'),('8','Mashalla','-','rap','02:30:00','2022-02-04',10,'Mashalla'),('9','Empire state of mind','US','rap','02:30:00','2015-05-05',8.5,'Empire state of mind'),('10','Us and them','-','pop','02:30:00','2000-02-04',4.9,'Us and them'),('11','Wish you were here','Wish','pop','02:30:00','2003-10-27',5.5,'Wish you were here');
/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music_artists`
--

DROP TABLE IF EXISTS `music_artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `music_artists` (
  `track_id` text NOT NULL,
  `artist_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music_artists`
--

LOCK TABLES `music_artists` WRITE;
/*!40000 ALTER TABLE `music_artists` DISABLE KEYS */;
INSERT INTO `music_artists` VALUES ('1','2'),('2','3'),('3','1'),('4','1'),('5','1'),('6','6'),('7','4'),('7','5'),('8','4'),('8','5'),('9','7'),('9','8'),('10','6'),('11','6');
/*!40000 ALTER TABLE `music_artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music_downloads`
--

DROP TABLE IF EXISTS `music_downloads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `music_downloads` (
  `user_id` text NOT NULL,
  `track_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music_downloads`
--

LOCK TABLES `music_downloads` WRITE;
/*!40000 ALTER TABLE `music_downloads` DISABLE KEYS */;
/*!40000 ALTER TABLE `music_downloads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `play_list`
--

DROP TABLE IF EXISTS `play_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `play_list` (
  `playlist_id` text NOT NULL,
  `user_id` text NOT NULL,
  `title` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_list`
--

LOCK TABLES `play_list` WRITE;
/*!40000 ALTER TABLE `play_list` DISABLE KEYS */;
INSERT INTO `play_list` VALUES ('c5350bcf-657c-411d-809c-ea27773a58e7','e7dafd2d-c590-40d5-9934-1b76110086d1','Best'),('044e94e1-4a2d-40f6-9b3b-fdc2e0ebc094','8d6cfa74-cf67-4116-bac4-541605990135','pop'),('6d7dc99a-0c2b-4864-ab47-6803edb0f55b','e7dafd2d-c590-40d5-9934-1b76110086d1','hshsh'),('8e30c0f2-0ec2-429b-b7df-482929647873','1e2ee09b-820f-45e3-9ae9-c10d23999daf','Mysongs'),('1f13fe9c-0256-49ed-a8b9-1e8fb50fd99f','e7dafd2d-c590-40d5-9934-1b76110086d1','AP'),('1b098b94-9b63-4f40-bd80-5b3b692e7fc8','87c95086-6db3-4871-8233-0401af8a377d','Drill');
/*!40000 ALTER TABLE `play_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_musics`
--

DROP TABLE IF EXISTS `playlist_musics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist_musics` (
  `playlist_id` text,
  `track_id` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_musics`
--

LOCK TABLES `playlist_musics` WRITE;
/*!40000 ALTER TABLE `playlist_musics` DISABLE KEYS */;
INSERT INTO `playlist_musics` VALUES ('c5350bcf-657c-411d-809c-ea27773a58e7','2'),('c5350bcf-657c-411d-809c-ea27773a58e7','1'),('c5350bcf-657c-411d-809c-ea27773a58e7','8'),('c5350bcf-657c-411d-809c-ea27773a58e7','11'),('044e94e1-4a2d-40f6-9b3b-fdc2e0ebc094','9'),('044e94e1-4a2d-40f6-9b3b-fdc2e0ebc094','1'),('044e94e1-4a2d-40f6-9b3b-fdc2e0ebc094','4'),('044e94e1-4a2d-40f6-9b3b-fdc2e0ebc094','5'),('044e94e1-4a2d-40f6-9b3b-fdc2e0ebc094','7'),('044e94e1-4a2d-40f6-9b3b-fdc2e0ebc094','11'),('044e94e1-4a2d-40f6-9b3b-fdc2e0ebc094','10'),('8e30c0f2-0ec2-429b-b7df-482929647873','8'),('8e30c0f2-0ec2-429b-b7df-482929647873','9'),('8e30c0f2-0ec2-429b-b7df-482929647873','7'),('8e30c0f2-0ec2-429b-b7df-482929647873','11'),('1f13fe9c-0256-49ed-a8b9-1e8fb50fd99f','9'),('1f13fe9c-0256-49ed-a8b9-1e8fb50fd99f','6'),('1f13fe9c-0256-49ed-a8b9-1e8fb50fd99f','8'),('1f13fe9c-0256-49ed-a8b9-1e8fb50fd99f','10'),('1b098b94-9b63-4f40-bd80-5b3b692e7fc8','2'),('1b098b94-9b63-4f40-bd80-5b3b692e7fc8','6'),('1b098b94-9b63-4f40-bd80-5b3b692e7fc8','5'),('1b098b94-9b63-4f40-bd80-5b3b692e7fc8','7'),('1b098b94-9b63-4f40-bd80-5b3b692e7fc8','10'),('1b098b94-9b63-4f40-bd80-5b3b692e7fc8','11');
/*!40000 ALTER TABLE `playlist_musics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` text NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `email_address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('e7dafd2d-c590-40d5-9934-1b76110086d1','Bardia','bardia','Bardia@gmail.com'),('8d6cfa74-cf67-4116-bac4-541605990135','Navid','Navid','Navid@gmail.com'),('76679e87-77c8-4c1d-a48f-412a7f446fe5','Ehsan','Ehsan','ehsan@gmail.com'),('a3123f32-c6c5-4dbb-82dc-c912b745b181','Ali','Ali','Ali@gmail.com'),('1e2ee09b-820f-45e3-9ae9-c10d23999daf','nariman','nariman','akbarin223@gmail.com'),('87c95086-6db3-4871-8233-0401af8a377d','ArianKoni','ArianKoni','Arian@gmail.com'),('a3574147-3367-4f82-978f-043a4ccb2e2f','mamadi','jjj','iiiii');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_artist_follow`
--

DROP TABLE IF EXISTS `user_artist_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_artist_follow` (
  `user_id` text NOT NULL,
  `artist_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_artist_follow`
--

LOCK TABLES `user_artist_follow` WRITE;
/*!40000 ALTER TABLE `user_artist_follow` DISABLE KEYS */;
INSERT INTO `user_artist_follow` VALUES ('e7dafd2d-c590-40d5-9934-1b76110086d1','2'),('e7dafd2d-c590-40d5-9934-1b76110086d1','6'),('e7dafd2d-c590-40d5-9934-1b76110086d1','7'),('8d6cfa74-cf67-4116-bac4-541605990135','3'),('8d6cfa74-cf67-4116-bac4-541605990135','4'),('8d6cfa74-cf67-4116-bac4-541605990135','6'),('8d6cfa74-cf67-4116-bac4-541605990135','1'),('8d6cfa74-cf67-4116-bac4-541605990135','8'),('8d6cfa74-cf67-4116-bac4-541605990135','5'),('1e2ee09b-820f-45e3-9ae9-c10d23999daf','5');
/*!40000 ALTER TABLE `user_artist_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user_follow`
--

DROP TABLE IF EXISTS `user_user_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_user_follow` (
  `user_id_1` text,
  `user_id_2` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user_follow`
--

LOCK TABLES `user_user_follow` WRITE;
/*!40000 ALTER TABLE `user_user_follow` DISABLE KEYS */;
INSERT INTO `user_user_follow` VALUES ('8d6cfa74-cf67-4116-bac4-541605990135','e7dafd2d-c590-40d5-9934-1b76110086d1'),('e7dafd2d-c590-40d5-9934-1b76110086d1','8d6cfa74-cf67-4116-bac4-541605990135'),('76679e87-77c8-4c1d-a48f-412a7f446fe5','8d6cfa74-cf67-4116-bac4-541605990135'),('76679e87-77c8-4c1d-a48f-412a7f446fe5','e7dafd2d-c590-40d5-9934-1b76110086d1'),('a3123f32-c6c5-4dbb-82dc-c912b745b181','8d6cfa74-cf67-4116-bac4-541605990135'),('a3123f32-c6c5-4dbb-82dc-c912b745b181','e7dafd2d-c590-40d5-9934-1b76110086d1'),('a3123f32-c6c5-4dbb-82dc-c912b745b181','76679e87-77c8-4c1d-a48f-412a7f446fe5'),('1e2ee09b-820f-45e3-9ae9-c10d23999daf','8d6cfa74-cf67-4116-bac4-541605990135'),('87c95086-6db3-4871-8233-0401af8a377d','e7dafd2d-c590-40d5-9934-1b76110086d1'),('87c95086-6db3-4871-8233-0401af8a377d','76679e87-77c8-4c1d-a48f-412a7f446fe5'),('87c95086-6db3-4871-8233-0401af8a377d','a3123f32-c6c5-4dbb-82dc-c912b745b181');
/*!40000 ALTER TABLE `user_user_follow` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-13  2:13:00
