CREATE DATABASE  IF NOT EXISTS `wareapp` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wareapp`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: wareapp
-- ------------------------------------------------------
-- Server version	5.7.3-m13

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
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `id` varchar(255) NOT NULL,
  `loginname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户名，用作前端显示',
  `role` int(11) DEFAULT '0' COMMENT '1为管理员，2位超级管理员，0位普通用户',
  `remark` varchar(255) DEFAULT NULL,
  `dlt` int(11) DEFAULT '0' COMMENT '0为默认状态--》正常，1为删除了的用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('0001','admin','35cdb9f87a18ff0814d070b11e3b3bd0','超级管理员',1,NULL,0);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary` (
  `id` varchar(45) NOT NULL,
  `stuffid` varchar(45) DEFAULT NULL,
  `pluss` varchar(255) DEFAULT NULL COMMENT '加。。。格式为json',
  `minuss` varchar(255) DEFAULT NULL COMMENT '减 --格式为json',
  `should` double DEFAULT NULL COMMENT '应发工资',
  `actual` double DEFAULT NULL COMMENT '实发工资',
  `dayoff` varchar(5) DEFAULT NULL COMMENT '请假天数',
  `quantity` double DEFAULT NULL COMMENT '数量，完成的数量',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `dlt` int(11) DEFAULT NULL COMMENT '是否删除，0为正常，1为删除',
  `year` int(4) DEFAULT NULL COMMENT '年份',
  `month` int(2) DEFAULT NULL COMMENT '月份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuff`
--

DROP TABLE IF EXISTS `stuff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuff` (
  `id` varchar(45) NOT NULL,
  `stuffname` varchar(255) DEFAULT NULL COMMENT '员工名称',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `idcard` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `basesalary` decimal(20,2) DEFAULT '0.00' COMMENT '基本工资',
  `dlt` int(1) DEFAULT '0' COMMENT '删除员工信息，0代表正常，1代表不正常',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `position` varchar(45) DEFAULT NULL COMMENT '职位/岗位',
  `status` int(11) DEFAULT '0' COMMENT '员工的状态，0为正常，1位离职',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,馆藏时间',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工的基本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff`
--

LOCK TABLES `stuff` WRITE;
/*!40000 ALTER TABLE `stuff` DISABLE KEYS */;
INSERT INTO `stuff` VALUES ('0001','祝攀','18108190650','511323199411182519','四川省南充市嘉陵区陈寿路208号',3900.00,0,NULL,'consultant',0,'2017-07-14 10:37:09','2017-07-14 10:42:38'),('0002','陈小蝶','15181730317','51132319950101585X','四川省成都市',3900.01,0,NULL,'consultant',0,'2017-07-14 10:37:09','2017-07-18 09:32:17'),('17071816294779211390','test0','18108190650','511323199411182519','12-101',2530.25,0,NULL,'大杂工',0,'2017-07-18 08:29:48','2017-07-19 07:05:18');
/*!40000 ALTER TABLE `stuff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-19 18:59:33
