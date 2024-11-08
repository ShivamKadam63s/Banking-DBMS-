/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `BankingSystem`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `BankingSystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `BankingSystem`;

--
-- Table structure for table `BankAccount`
--

DROP TABLE IF EXISTS `BankAccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BankAccount` (
  `acc_id` bigint NOT NULL,
  `pass` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `acc_balance` decimal(19,4) DEFAULT NULL,
  `transaction_limit` decimal(19,4) DEFAULT NULL,
  `acc_type` enum('Savings account','Current account','Loan account','Deposit account') DEFAULT NULL,
  `aadhar_id` bigint DEFAULT NULL,
  PRIMARY KEY (`acc_id`),
  KEY `aadhar_id` (`aadhar_id`),
  CONSTRAINT `BankAccount_ibfk_1` FOREIGN KEY (`aadhar_id`) REFERENCES `Customer` (`aadhar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BankAccount`
--

LOCK TABLES `BankAccount` WRITE;
/*!40000 ALTER TABLE `BankAccount` DISABLE KEYS */;
INSERT INTO `BankAccount` VALUES (1001,'pass1001','rahuls',42944.0000,10000.0000,'Savings account',123456789001),(1002,'pass1002','priyap',75000.0000,15000.0000,'Current account',123456789002),(1003,'pass1003','amits',30005.0000,5000.0000,'Savings account',123456789003),(1004,'pass1004','nehag',100000.0000,20000.0000,'Deposit account',123456789004),(1005,'pass1005','vikramr',25000.0000,7500.0000,'Savings account',123456789005),(1006,'pass1006','anjalid',80000.0000,12000.0000,'Current account',123456789006),(1007,'pass1007','rajeshv',150000.0000,25000.0000,'Deposit account',123456789007),(1008,'pass1008','poojam',40000.0000,8000.0000,'Savings account',123456789008),(1009,'pass1009','sureshm',60000.0000,10000.0000,'Current account',123456789009),(1010,'pass1010','meerai',35000.0000,7000.0000,'Savings account',123456789010),(1011,'pass1011','rahuls',12349.0000,2342.0000,'Current account',123456789001);
/*!40000 ALTER TABLE `BankAccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BankTransaction`
--

DROP TABLE IF EXISTS `BankTransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BankTransaction` (
  `transaction_id` bigint NOT NULL,
  `Reciever_Name` varchar(100) DEFAULT NULL,
  `Reciever_Acc` bigint DEFAULT NULL,
  `transaction_date` date DEFAULT NULL,
  `transaction_type` varchar(20) DEFAULT NULL,
  `Amount` decimal(19,4) DEFAULT NULL,
  `acc_id` bigint DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `acc_id` (`acc_id`),
  CONSTRAINT `BankTransaction_ibfk_1` FOREIGN KEY (`acc_id`) REFERENCES `BankAccount` (`acc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BankTransaction`
--

LOCK TABLES `BankTransaction` WRITE;
/*!40000 ALTER TABLE `BankTransaction` DISABLE KEYS */;
INSERT INTO `BankTransaction` VALUES (1829,'',1003,'2024-11-07','deposit',1.0000,1001),(3002,'Amit Singh',1003,'2024-09-02','Transfer',2500.0000,1002),(3003,'Neha Gupta',1004,'2024-09-03','Deposit',10000.0000,1003),(3004,'Vikram Reddy',1005,'2024-09-04','Withdrawal',1000.0000,1004),(3005,'Anjali Desai',1006,'2024-09-05','Transfer',3000.0000,1005),(3006,'Rajesh Verma',1007,'2024-09-06','Deposit',5000.0000,1006),(3007,'Pooja Malhotra',1008,'2024-09-07','Withdrawal',2000.0000,1007),(3008,'Suresh Mishra',1009,'2024-09-08','Transfer',1500.0000,1008),(3009,'Meera Iyer',1010,'2024-09-09','Deposit',7500.0000,1009),(3010,'Rahul Sharma',1001,'2024-09-10','Withdrawal',500.0000,1010),(3222,'',1003,'2024-11-08','deposit',2.0000,1001);
/*!40000 ALTER TABLE `BankTransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `aadhar_id` bigint NOT NULL,
  `mobile_no` varchar(50) DEFAULT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `mname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`aadhar_id`),
  CONSTRAINT `Customer_chk_1` CHECK (((`fname` is not null) or (`mname` is not null) or (`lname` is not null)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'2','3','4',NULL,'6',NULL,NULL,NULL,'1234567'),(123456789001,'9876543221','Rahul',NULL,'Sharma','123 MG Road, Mumbai','1990-05-15','rahul.sharma@email.com','Male','1234567'),(123456789002,'9876543211','Priya','Devi','Patel','456 Linking Road, Delhi','1985-08-22','priya.patel@email.com','Female','34567891'),(123456789003,'9876543212','Amit',NULL,'Singh','789 Park Street, Kolkata','1992-03-10','amit.singh@email.com','Male','34567918'),(123456789004,'9876543213','Neha','Kumari','Gupta','101 Church Street, Bangalore','1988-11-30','neha.gupta@email.com','Female','wgh'),(123456789005,'9876543214','Vikram',NULL,'Reddy','202 Anna Salai, Chennai','1995-07-18','vikram.reddy@email.com','Male','gwohp917'),(123456789006,'9876543215','Anjali',NULL,'Desai','303 FC Road, Pune','1991-01-25','anjali.desai@email.com','Female','HQ6239'),(123456789007,'9876543216','Rajesh','Kumar','Verma','404 Jubilee Hills, Hyderabad','1987-09-05','rajesh.verma@email.com','Male','VQE23Y98'),(123456789008,'9876543217','Pooja',NULL,'Malhotra','505 Civil Lines, Jaipur','1993-06-12','pooja.malhotra@email.com','Female','KQIUITY98'),(123456789009,'9876543218','Suresh','Chandra','Mishra','606 Gomti Nagar, Lucknow','1989-04-20','suresh.mishra@email.com','Male','oabhjp'),(123456789010,'9876543219','Meera',NULL,'Iyer','707 Alwarpet, Chennai','1994-12-08','meera.iyer@email.com','Female','oieiwi43q98h'),(123456789011,'9876543220','Ravi',NULL,'Kumar','808 MG Road, Bangalore','1988-07-15','ravi.kumar@email.com','Male','noiqHGKU');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Loan`
--

DROP TABLE IF EXISTS `Loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Loan` (
  `Loan_ID` bigint NOT NULL,
  `Rate_of_Interest` decimal(6,4) DEFAULT NULL,
  `Loan_Amount` decimal(19,4) DEFAULT NULL,
  `Loan_Type` enum('Car Loan','Personal','House') DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `duration` int DEFAULT NULL,
  PRIMARY KEY (`Loan_ID`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `Loan_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `Customer` (`aadhar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Loan`
--

LOCK TABLES `Loan` WRITE;
/*!40000 ALTER TABLE `Loan` DISABLE KEYS */;
INSERT INTO `Loan` VALUES (1366,0.0100,2.0000,'Personal',123456789001,3),(2001,7.5000,500000.0000,'Car Loan',123456789001,NULL),(2002,9.2500,1000000.0000,'House',123456789002,NULL),(2003,12.0000,100000.0000,'Personal',123456789003,NULL),(2004,8.7500,750000.0000,'Car Loan',123456789004,NULL),(2005,10.5000,2000000.0000,'House',123456789005,NULL),(2006,11.2500,150000.0000,'Personal',123456789006,NULL),(2007,7.7500,600000.0000,'Car Loan',123456789007,NULL),(2008,9.7500,1500000.0000,'House',123456789008,NULL),(2009,13.0000,80000.0000,'Personal',123456789009,NULL),(2010,8.2500,550000.0000,'Car Loan',123456789010,NULL),(2782,0.0200,2.0000,'House',123456789001,2),(3937,0.0100,2.0000,'Personal',123456789001,3),(7365,0.0100,2.0000,'Personal',123456789001,3),(7515,0.0100,1.0000,'Personal',123456789001,2),(8166,0.0100,1.0000,'Personal',123456789001,2);
/*!40000 ALTER TABLE `Loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff` (
  `staff_id` bigint NOT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `staff_mobile` varchar(20) DEFAULT NULL,
  `staff_type` enum('Manager','Clerk','Accountant') DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES (101,'Arjun Nair','9876543220','Manager'),(102,'Sunita Rao','9876543221','Clerk'),(103,'Deepak Chopra','9876543222','Accountant'),(104,'Kavita Krishnan','9876543223','Manager'),(105,'Ravi Menon','9876543224','Clerk'),(106,'Ananya Joshi','9876543225','Accountant'),(107,'Prakash Raj','9876543226','Manager'),(108,'Divya Sharma','9876543227','Clerk'),(109,'Karthik Subramaniam','9876543228','Accountant'),(110,'Lakshmi Venkatesh','9876543229','Clerk');
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacts` (
  `aadhar_id` bigint DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL,
  KEY `staff_id` (`staff_id`),
  KEY `aadhar_id` (`aadhar_id`),
  CONSTRAINT `contacts_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `Staff` (`staff_id`),
  CONSTRAINT `contacts_ibfk_2` FOREIGN KEY (`aadhar_id`) REFERENCES `Customer` (`aadhar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
INSERT INTO `contacts` VALUES (123456789001,101),(123456789002,101),(123456789003,102),(123456789004,102),(123456789005,103),(123456789006,103),(123456789007,104),(123456789008,104),(123456789009,105),(123456789010,105);
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `aadhar_id` int NOT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `mname` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aadhar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manages`
--

DROP TABLE IF EXISTS `manages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manages` (
  `staff_id` bigint DEFAULT NULL,
  `acc_id` bigint DEFAULT NULL,
  KEY `staff_id` (`staff_id`),
  KEY `acc_id` (`acc_id`),
  CONSTRAINT `manages_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `Staff` (`staff_id`),
  CONSTRAINT `manages_ibfk_2` FOREIGN KEY (`acc_id`) REFERENCES `BankAccount` (`acc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manages`
--

LOCK TABLES `manages` WRITE;
/*!40000 ALTER TABLE `manages` DISABLE KEYS */;
INSERT INTO `manages` VALUES (101,1001),(101,1002),(102,1003),(102,1004),(103,1005),(103,1006),(104,1007),(104,1008),(105,1009),(105,1010);
/*!40000 ALTER TABLE `manages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

