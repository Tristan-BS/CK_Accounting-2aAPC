-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server-Version:               11.5.2-MariaDB - mariadb.org binary distribution
-- Server-Betriebssystem:        Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Exportiere Datenbank-Struktur für ckaccounting
CREATE DATABASE IF NOT EXISTS `ckaccounting` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `ckaccounting`;

-- Exportiere Struktur von Tabelle ckaccounting.ck_categories
CREATE TABLE IF NOT EXISTS `ck_categories` (
  `Category_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL DEFAULT '0',
  `Type` int(11) NOT NULL DEFAULT 0,
  `Others` text NOT NULL,
  PRIMARY KEY (`Category_ID`),
  KEY `Type` (`Type`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_categories: ~13 rows (ungefähr)
INSERT INTO `ck_categories` (`Category_ID`, `Name`, `Type`, `Others`) VALUES
	(1, 'Salary', 2, 'The Salary of Employees'),
	(3, 'Sales Revenue', 1, 'Income from product sales'),
	(4, 'Service Revenue', 1, 'Income from provided services'),
	(5, 'Interest Income', 1, 'Income from investments'),
	(6, 'Advertising Income', 1, 'Income from advertising and sponsorships'),
	(7, 'Salary Expenses', 2, 'Costs for wages and salaries'),
	(8, 'Rent', 2, 'Costs for office or retail space'),
	(9, 'Marketing Expenses', 2, 'Costs for advertising and marketing efforts'),
	(10, 'Vehicle Expenses', 2, 'Costs for company vehicles and maintenance'),
	(11, 'Travel Expenses', 2, 'Costs for business trips and accommodations'),
	(12, 'Office Supplies', 2, 'Expenses for office supplies and consumables'),
	(13, 'Other Expenses', 2, 'Something not listed'),
	(14, 'Taxes', 2, 'Taxes to Pay');

-- Exportiere Struktur von Tabelle ckaccounting.ck_categorytypes
CREATE TABLE IF NOT EXISTS `ck_categorytypes` (
  `CGType_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CGType_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_categorytypes: ~3 rows (ungefähr)
INSERT INTO `ck_categorytypes` (`CGType_ID`, `Type`) VALUES
	(1, 'Income'),
	(2, 'Expenses');

-- Exportiere Struktur von Tabelle ckaccounting.ck_companydetails
CREATE TABLE IF NOT EXISTS `ck_companydetails` (
  `Company_ID` int(11) NOT NULL AUTO_INCREMENT,
  `C_Name` varchar(50) NOT NULL DEFAULT '0',
  `C_Street` varchar(50) NOT NULL DEFAULT '0',
  `C_HouseNumber` int(11) NOT NULL DEFAULT 0,
  `C_Location` varchar(50) NOT NULL DEFAULT '0',
  `C_PostalCode` int(11) NOT NULL DEFAULT 0,
  `C_Country` varchar(50) NOT NULL DEFAULT '0',
  `C_Other` text NOT NULL,
  PRIMARY KEY (`Company_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_companydetails: ~11 rows (ungefähr)
INSERT INTO `ck_companydetails` (`Company_ID`, `C_Name`, `C_Street`, `C_HouseNumber`, `C_Location`, `C_PostalCode`, `C_Country`, `C_Other`) VALUES
	(4, 'Kugel Studio', 'Entschendorf', 47, 'St. Margarethen an der Raab', 8321, 'Austria', 'Die Firma is cool!'),
	(7, 'Tech Solutions GmbH', 'Main Street', 123, 'Vienna', 1010, 'Austria', ''),
	(8, 'Eco Friendly Corp', 'Greenway Avenue', 47, 'Salzburg', 5020, 'Austria', ''),
	(9, 'Global Trade Ltd.', 'Trade St.', 12, 'Linz', 4020, 'Austria', ''),
	(10, 'Alpha Media', 'Media Avenue', 234, 'Innsbruck', 6020, 'Austria', ''),
	(11, 'Mountain Equipment Inc.', 'Mountain Rd.', 45, 'Graz', 8010, 'Austria', ''),
	(12, 'HealthFirst Pharma', 'Health Blvd.', 67, 'Vienna', 1030, 'Austria', ''),
	(13, 'Foodie\'s Delight', 'Food Court', 90, 'Salzburg', 5020, 'Austria', ''),
	(14, 'Renewable Energy Group', 'Power Rd.', 11, 'Innsbruck', 6020, 'Austria', ''),
	(15, 'Luxury Cars Vienna', 'Speedway St.', 78, 'Vienna', 1010, 'Austria', ''),
	(16, 'Software Innovations', 'Innovation Ave.', 4, 'Linz', 4020, 'Austria', '');

-- Exportiere Struktur von Tabelle ckaccounting.ck_contactperson
CREATE TABLE IF NOT EXISTS `ck_contactperson` (
  `ContactPers_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Rank` int(11) NOT NULL DEFAULT 0,
  `Title` varchar(50) NOT NULL DEFAULT '0',
  `Gender_ID` int(11) NOT NULL DEFAULT 0,
  `Firstname` varchar(50) NOT NULL DEFAULT '0',
  `Lastname` varchar(50) NOT NULL DEFAULT '0',
  `EMail` varchar(50) NOT NULL DEFAULT '0',
  `PhoneNr` varchar(50) NOT NULL DEFAULT '0',
  `Company_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ContactPers_ID`) USING BTREE,
  KEY `Gender` (`Gender_ID`) USING BTREE,
  KEY `Company_ID` (`Company_ID`),
  CONSTRAINT `To_CompanyID` FOREIGN KEY (`Company_ID`) REFERENCES `ck_companydetails` (`Company_ID`) ON DELETE NO ACTION,
  CONSTRAINT `To_GenderID` FOREIGN KEY (`Gender_ID`) REFERENCES `ck_gender` (`Gender_ID`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_contactperson: ~5 rows (ungefähr)
INSERT INTO `ck_contactperson` (`ContactPers_ID`, `Rank`, `Title`, `Gender_ID`, `Firstname`, `Lastname`, `EMail`, `PhoneNr`, `Company_ID`) VALUES
	(1, 1, 'AR', 1, 'Tristan', 'Birnstingl', '123@gmasilco.ocm', '123456789', 4),
	(16, 0, 'Dr. med.univ.', 1, 'Max', 'Mustermann', 'max.mustermann@gmail.com', '987654321', 10),
	(17, 1, 'Dr. med.univ.', 2, 'Rosalinde', 'MusterFrau', 'Rosalinde.MF@gmail.com', '246810121', 10),
	(18, 0, 'Choose', 1, 'Frederik', 'Hahn', 'Frederikhahn@gmail.com', '857384544', 15),
	(19, 0, 'Choose', 1, 'Hans', 'Baumann', 'BaumannHans@gmail.com', '688343556', 16);

-- Exportiere Struktur von Tabelle ckaccounting.ck_countries
CREATE TABLE IF NOT EXISTS `ck_countries` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_countries: ~54 rows (ungefähr)
INSERT INTO `ck_countries` (`ID`, `Country`) VALUES
	(1, 'Austria'),
	(2, 'Germany'),
	(3, 'England'),
	(4, 'Spain'),
	(5, 'France'),
	(6, 'Italy'),
	(7, 'Afghanistan'),
	(8, 'Albania'),
	(9, 'Bahamas'),
	(10, 'Bahrain'),
	(11, 'Cambodia'),
	(12, 'Cameroon'),
	(13, 'Denmark'),
	(14, 'Djibouti'),
	(15, 'Ecuador'),
	(16, 'Egypt'),
	(17, 'Fiji'),
	(18, 'Finland'),
	(19, 'Gabon'),
	(20, 'Georgia'),
	(21, 'Haiti'),
	(22, 'Honduras'),
	(23, 'Iceland'),
	(24, 'India'),
	(25, 'Jamaica'),
	(26, 'Japan'),
	(27, 'Kazakhstan'),
	(28, 'Kenya'),
	(29, 'Laos'),
	(30, 'Latvia'),
	(31, 'Madagascar'),
	(32, 'Malawi'),
	(33, 'Nepal'),
	(34, 'Netherlands'),
	(35, 'Oman'),
	(36, 'Austria'),
	(37, 'Pakistan'),
	(38, 'Panama'),
	(39, 'Qatar'),
	(40, 'Qatar'),
	(41, 'Romania'),
	(42, 'Russia'),
	(43, 'Samoa'),
	(44, 'Senegal'),
	(45, 'Thailand'),
	(46, 'Togo'),
	(47, 'Uganda'),
	(48, 'Ukraine'),
	(49, 'Vanuatu'),
	(50, 'Venezuela'),
	(51, 'Yemen'),
	(52, 'Yemen'),
	(53, 'Zambia'),
	(54, 'Zimbabwe');

-- Exportiere Struktur von Tabelle ckaccounting.ck_gender
CREATE TABLE IF NOT EXISTS `ck_gender` (
  `Gender_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(10) NOT NULL,
  PRIMARY KEY (`Gender_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_gender: ~3 rows (ungefähr)
INSERT INTO `ck_gender` (`Gender_ID`, `Type`) VALUES
	(1, 'Male'),
	(2, 'Female'),
	(3, 'Diverse');

-- Exportiere Struktur von View ckaccounting.ck_getbestcustomer
-- Erstelle temporäre Tabelle, um View-Abhängigkeiten zuvorzukommen
CREATE TABLE `ck_getbestcustomer` (
	`CompanyName` VARCHAR(50) NULL COLLATE 'utf8mb4_general_ci',
	`TotalAmount` DOUBLE NULL
) ENGINE=MyISAM;

-- Exportiere Struktur von View ckaccounting.ck_getbestmonth
-- Erstelle temporäre Tabelle, um View-Abhängigkeiten zuvorzukommen
CREATE TABLE `ck_getbestmonth` (
	`Year` INT(5) NULL,
	`Month` INT(3) NULL,
	`NetIncome` DOUBLE NULL
) ENGINE=MyISAM;

-- Exportiere Struktur von View ckaccounting.ck_getbestyear
-- Erstelle temporäre Tabelle, um View-Abhängigkeiten zuvorzukommen
CREATE TABLE `ck_getbestyear` (
	`Year` INT(5) NULL,
	`NetIncome` DOUBLE NULL
) ENGINE=MyISAM;

-- Exportiere Struktur von View ckaccounting.ck_getcp_with_cpn
-- Erstelle temporäre Tabelle, um View-Abhängigkeiten zuvorzukommen
CREATE TABLE `ck_getcp_with_cpn` (
	`ContactPers_ID` INT(11) NOT NULL,
	`Title` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_general_ci',
	`Firstname` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_general_ci',
	`Lastname` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_general_ci',
	`EMail` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_general_ci',
	`PhoneNr` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_general_ci',
	`Gender` VARCHAR(10) NULL COLLATE 'utf8mb4_general_ci',
	`CompanyName` VARCHAR(50) NULL COLLATE 'utf8mb4_general_ci',
	`CompanyOther` TEXT NULL COLLATE 'utf8mb4_general_ci'
) ENGINE=MyISAM;

-- Exportiere Struktur von View ckaccounting.ck_getworstmonth
-- Erstelle temporäre Tabelle, um View-Abhängigkeiten zuvorzukommen
CREATE TABLE `ck_getworstmonth` (
	`Year` INT(5) NULL,
	`Month` INT(3) NULL,
	`NetIncome` DOUBLE NULL
) ENGINE=MyISAM;

-- Exportiere Struktur von View ckaccounting.ck_getworstyear
-- Erstelle temporäre Tabelle, um View-Abhängigkeiten zuvorzukommen
CREATE TABLE `ck_getworstyear` (
	`Year` INT(5) NULL,
	`NetIncome` DOUBLE NULL
) ENGINE=MyISAM;

-- Exportiere Struktur von View ckaccounting.ck_get_least_used_category
-- Erstelle temporäre Tabelle, um View-Abhängigkeiten zuvorzukommen
CREATE TABLE `ck_get_least_used_category` (
	`CategoryName` VARCHAR(50) NULL COLLATE 'utf8mb4_general_ci',
	`EntriesCount` BIGINT(21) NOT NULL
) ENGINE=MyISAM;

-- Exportiere Struktur von View ckaccounting.ck_get_most_used_category
-- Erstelle temporäre Tabelle, um View-Abhängigkeiten zuvorzukommen
CREATE TABLE `ck_get_most_used_category` (
	`CategoryName` VARCHAR(50) NULL COLLATE 'utf8mb4_general_ci',
	`EntriesCount` BIGINT(21) NOT NULL
) ENGINE=MyISAM;

-- Exportiere Struktur von Tabelle ckaccounting.ck_invoices
CREATE TABLE IF NOT EXISTS `ck_invoices` (
  `Invoice_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Category_ID` int(11) DEFAULT NULL,
  `Name` varchar(250) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Timestamp` timestamp NULL DEFAULT NULL,
  `isPaid` int(11) DEFAULT NULL,
  `Company_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Invoice_ID`),
  KEY `Category_ID` (`Category_ID`),
  CONSTRAINT `To_CategoryID` FOREIGN KEY (`Category_ID`) REFERENCES `ck_categories` (`Category_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_invoices: ~63 rows (ungefähr)
INSERT INTO `ck_invoices` (`Invoice_ID`, `Category_ID`, `Name`, `Description`, `Amount`, `Date`, `Timestamp`, `isPaid`, `Company_ID`) VALUES
	(64, 9, 'Invoice #1', 'Consulting Services', 1200.5, '2024-01-15', '2024-01-15 08:00:00', 1, -1),
	(65, 12, 'Invoice #2', 'Office Supplies', 500, '2024-02-20', '2024-02-20 09:00:00', 1, 4),
	(66, 3, 'Invoice #3', 'Website Development', 34000, '2024-03-05', '2024-03-05 10:00:00', 1, -1),
	(67, 9, 'Invoice #4', 'Marketing Campaign', 8900, '2024-04-10', '2024-04-10 09:00:00', 1, 4),
	(68, 12, 'Invoice #5', 'SEO Optimization', 750, '2024-05-25', '2024-05-25 10:00:00', 1, -1),
	(69, 4, 'Invoice #6', 'Graphic Design', 6845.34, '2024-06-15', '2024-06-15 11:00:00', 1, 4),
	(70, 12, 'Invoice #7', 'IT Support', 1500, '2024-07-20', '2024-07-20 12:00:00', 1, -1),
	(71, 9, 'Invoice #8', 'Cloud Hosting', 3000, '2024-08-05', '2024-08-05 13:00:00', 1, 4),
	(72, 13, 'Invoice #9', 'Data Analysis', 2200, '2024-09-10', '2024-09-10 14:00:00', 1, -1),
	(73, 13, 'Invoice #10', 'Cybersecurity Audit', 15000, '2024-10-25', '2024-10-25 15:00:00', 1, 4),
	(74, 3, 'Invoice #11', 'E-commerce Development', 35000, '2024-11-15', '2024-11-15 08:00:00', 1, -1),
	(75, 3, 'Invoice #12', 'Mobile App Development', 40000, '2024-12-01', '2024-12-01 09:00:00', 1, 4),
	(76, 3, 'Invoice #13', 'Social Media Management', 2700, '2024-01-18', '2024-01-18 10:00:00', 1, -1),
	(77, 13, 'Invoice #14', 'Content Creation', 1250, '2024-02-22', '2024-02-22 11:00:00', 1, 4),
	(78, 9, 'Invoice #15', 'Email Marketing', 800, '2024-03-07', '2024-03-07 12:00:00', 1, -1),
	(79, 13, 'Invoice #16', 'Web Hosting', 900, '2024-04-12', '2024-04-12 11:00:00', 1, 4),
	(80, 3, 'Invoice #17', 'UX/UI Design', 1700, '2024-05-27', '2024-05-27 12:00:00', 1, -1),
	(81, 1, 'Invoice #18', 'Project Management', 3500, '2024-06-17', '2024-06-17 13:00:00', 0, 4),
	(82, 9, 'Invoice #19', 'Market Research', 4500, '2024-07-22', '2024-07-22 14:00:00', 1, -1),
	(83, 9, 'Invoice #20', 'Business Consulting', 6000, '2024-08-07', '2024-08-07 15:00:00', 1, 4),
	(84, 13, 'Invoice #21', 'HR Services', 2500, '2024-09-12', '2024-09-12 06:00:00', 1, -1),
	(85, 13, 'Invoice #22', 'Legal Consulting', 35000, '2024-10-27', '2024-10-27 09:00:00', 1, 4),
	(86, 4, 'Invoice #23', 'Accounting Services', 1400, '2024-11-17', '2024-11-17 10:00:00', 1, -1),
	(87, 4, 'Invoice #24', 'Technical Writing', 1200, '2024-12-03', '2024-12-03 11:00:00', 1, 4),
	(88, 4, 'Invoice #25', 'Translation Services', 900, '2024-01-20', '2024-01-20 12:00:00', 1, -1),
	(89, 4, 'Invoice #26', 'Video Production', 18000, '2024-02-24', '2024-02-24 13:00:00', 0, 4),
	(90, 3, 'Invoice #27', 'Photography', 500, '2024-03-09', '2024-03-09 14:00:00', 1, -1),
	(91, 13, 'Invoice #28', 'Event Planning', 25000, '2024-04-14', '2024-04-14 13:00:00', 1, 4),
	(92, 13, 'Invoice #29', 'Public Relations', 7000, '2024-05-29', '2024-05-29 14:00:00', 1, -1),
	(93, 9, 'Invoice #30', 'Brand Strategy', 16000, '2024-06-19', '2024-06-19 15:00:00', 1, 4),
	(94, 13, 'Invoice #31', 'Investment Consulting', 28000, '2024-07-24', '2024-07-24 06:00:00', 1, -1),
	(95, 14, 'Invoice #32', 'Tax Preparation', 1000, '2024-08-09', '2024-08-09 07:00:00', 1, 4),
	(96, 4, 'Invoice #33', 'Freelance Writing', 800, '2024-09-14', '2024-09-14 08:00:00', 1, -1),
	(97, 14, 'Invoice #34', 'Consultation Fee', 2500, '2024-10-29', '2024-10-29 11:00:00', 0, 4),
	(98, 3, 'Invoice #35', 'Web Development', 5000, '2024-11-20', '2024-11-20 12:00:00', 1, -1),
	(99, 9, 'Invoice #36', 'Marketing Services', 7000, '2024-12-05', '2024-12-05 13:00:00', 1, 4),
	(100, 3, 'Invoice #37', 'Graphic Design', 1200, '2024-01-25', '2024-01-25 14:00:00', 1, -1),
	(101, 11, 'Invoice #38', 'SEO Services', 800, '2024-02-28', '2024-02-28 15:00:00', 1, 4),
	(102, 4, 'Invoice #39', 'IT Support', 3000, '2024-03-12', '2024-03-12 16:00:00', 1, -1),
	(103, 4, 'Invoice #40', 'Cloud Services', 4500, '2024-04-17', '2024-04-17 15:00:00', 1, 4),
	(104, 9, 'Invoice #41', 'Email Marketing', 1400, '2024-05-30', '2024-05-30 06:00:00', 1, -1),
	(105, 3, 'Invoice #42', 'App Development', 18000, '2024-06-22', '2024-06-22 07:00:00', 1, 4),
	(106, 9, 'Invoice #43', 'Business Consultancy', 25000, '2024-07-27', '2024-07-27 08:00:00', 1, -1),
	(107, 4, 'Invoice #44', 'Technical Support', 500, '2024-08-12', '2024-08-12 09:00:00', 1, 4),
	(108, 1, 'Invoice #45', 'Project Management', 16000, '2024-09-16', '2024-09-16 10:00:00', 1, -1),
	(109, 9, 'Invoice #46', 'Market Analysis', 7000, '2024-10-31', '2024-10-31 13:00:00', 1, 4),
	(110, 13, 'Invoice #47', 'Financial Planning', 28000, '2024-11-25', '2024-11-25 14:00:00', 1, -1),
	(111, 3, 'Invoice #48', 'Legal Advice', 1000, '2024-12-08', '2024-12-08 15:00:00', 1, 4),
	(112, 4, 'Invoice #49', 'Translation Services', 900, '2024-01-29', '2024-01-29 16:00:00', 1, -1),
	(113, 4, 'Invoice #50', 'Content Writing', 500, '2024-02-25', '2024-02-25 17:00:00', 1, 4),
	(114, 4, 'Invoice #1', 'Consulting Services', 1200.5, '2023-01-15', '2023-01-15 08:00:00', 1, -1),
	(115, 12, 'Invoice #2', 'Office Supplies', 500, '2023-02-20', '2023-02-20 09:00:00', 0, 4),
	(116, 3, 'Invoice #3', 'Website Development', 5000, '2023-03-05', '2023-03-05 10:00:00', 1, -1),
	(117, 9, 'Invoice #4', 'Marketing Campaign', 8900, '2023-04-10', '2023-04-10 09:00:00', 1, 4),
	(118, 10, 'Invoice #5', 'SEO Optimization', 750, '2023-05-25', '2023-05-25 10:00:00', 1, -1),
	(119, 4, 'Invoice #6', 'IT Support', 1500, '2022-01-15', '2022-01-15 08:00:00', 1, -1),
	(120, 3, 'Invoice #7', 'Cloud Hosting', 3000, '2022-02-20', '2022-02-20 09:00:00', 1, 4),
	(121, 9, 'Invoice #8', 'Data Analysis', 2200, '2022-03-05', '2022-03-05 10:00:00', 1, -1),
	(122, 1, 'Invoice #9', 'Cybersecurity Audit', 15000, '2022-04-10', '2022-04-10 09:00:00', 0, 4),
	(123, 3, 'Invoice #10', 'E-commerce Development', 35000, '2022-05-25', '2022-05-25 10:00:00', 1, -1),
	(124, 3, 'Invoice #11', 'Mobile App Development', 40000, '2021-01-15', '2021-01-15 08:00:00', 1, 4),
	(125, 3, 'Invoice #12', 'Social Media Management', 2700, '2021-02-20', '2021-02-20 09:00:00', 1, -1),
	(126, 13, 'Invoice #13', 'Content Creation', 1250, '2021-03-05', '2021-03-05 10:00:00', 1, 4);

-- Exportiere Struktur von View ckaccounting.ck_invoices_with_category
-- Erstelle temporäre Tabelle, um View-Abhängigkeiten zuvorzukommen
CREATE TABLE `ck_invoices_with_category` (
	`InvoiceName` VARCHAR(250) NULL COLLATE 'utf8mb4_general_ci',
	`Amount` DOUBLE NULL,
	`Date` DATE NULL,
	`CategoryType` VARCHAR(50) NULL COLLATE 'utf8mb4_general_ci',
	`isPaid` INT(11) NULL
) ENGINE=MyISAM;

-- Exportiere Struktur von Tabelle ckaccounting.ck_titles
CREATE TABLE IF NOT EXISTS `ck_titles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(50) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_titles: ~70 rows (ungefähr)
INSERT INTO `ck_titles` (`ID`, `Title`) VALUES
	(4, 'Abg.z.NR'),
	(5, 'ADir.'),
	(6, 'AR'),
	(7, 'BA*'),
	(8, 'Bakk. techn.*'),
	(9, 'BBA*'),
	(10, 'Bea.'),
	(11, 'BEd*'),
	(12, 'B.Eng.*'),
	(13, 'Bgm.'),
	(14, 'Bmstr.'),
	(15, 'B.phil'),
	(16, 'BSc*'),
	(17, 'Dr. / Dres.'),
	(18, 'DDr.'),
	(19, 'Dr. h.c.'),
	(20, 'Dr. iur.'),
	(21, 'Dr. med.univ.'),
	(22, 'Dr. med.vet.'),
	(23, 'Dr. mont.'),
	(24, 'Dr. phil.'),
	(25, 'Dr. rer.nat.techn.'),
	(26, 'Dr. rer.pol.'),
	(27, 'Dr. rer.soc.oec.'),
	(28, 'Dr. techn.'),
	(29, 'Dr. theol.'),
	(30, 'Dipl.-Chem.'),
	(31, 'Dipl.-Dolm.'),
	(32, 'Dipl.-Gwl'),
	(33, 'Dipl.-Hdl.'),
	(34, 'Dipl.-HLFL-Ing.'),
	(35, 'Dipl.Holzw.'),
	(36, 'Dipl.-Ing., DI'),
	(37, 'Dipl.-Ing. (FH)'),
	(38, 'Dipl.-Päd.'),
	(39, 'Dir.'),
	(41, 'EMHRD*'),
	(42, 'Gem. R.'),
	(43, 'GR'),
	(44, 'Hon.Prof.'),
	(45, 'HR'),
	(46, 'Ing.'),
	(47, 'KommR'),
	(48, 'LL.B. oder LLB*'),
	(49, 'LL.M. oder LLM*'),
	(50, 'Mag. '),
	(51, 'Maga.'),
	(52, 'MA*'),
	(53, 'Mag. (FH)'),
	(54, 'Mag. arch.'),
	(55, 'Mag. iur.'),
	(56, 'Mag. pharm.'),
	(57, 'Mag. phil.'),
	(58, 'Mag. rer.nat.'),
	(59, 'Mag. rer.soc.oec.'),
	(60, 'Mag. theol.'),
	(61, 'M.A.I.S.*'),
	(62, 'MBA*'),
	(63, 'MedR'),
	(64, 'MIB*'),
	(65, 'Parl. R'),
	(66, 'MSc*'),
	(67, 'PhD'),
	(68, 'Prof.'),
	(69, 'StSekr.'),
	(70, 'Univ.-Ass., Univ.Ass'),
	(71, 'Univ.-Doz., Univ.Doz.'),
	(72, 'Univ.-Lekt., Univ.Lekt.'),
	(73, 'Univ.-Prof., Univ.Prof.');

-- Exportiere Struktur von Prozedur ckaccounting.GetInvoicesByAmountRange
DELIMITER //
CREATE PROCEDURE `GetInvoicesByAmountRange`(
    IN startAmount DOUBLE,
    IN endAmount DOUBLE
)
BEGIN
    SELECT 
        i.Name,
        i.Amount,
        i.Date,
        ct.Type
    FROM 
        ck_invoices i
    LEFT JOIN 
        ck_categories c ON i.Category_ID = c.Category_ID
    LEFT JOIN 
        ck_categorytypes ct ON c.Type = ct.CGType_ID
    WHERE 
        i.Amount BETWEEN startAmount AND endAmount;
END//
DELIMITER ;

-- Exportiere Struktur von Prozedur ckaccounting.GetInvoicesByAmountRange_TABLE
DELIMITER //
CREATE PROCEDURE `GetInvoicesByAmountRange_TABLE`(
	IN `startAmount` DOUBLE,
	IN `endAmount` DOUBLE
)
BEGIN
    SELECT 
        ct.Type AS CategoryType,
        i.Name,
        i.Description,
        i.Amount,
	 	  co.C_Name AS CompanyName,
        i.Date,
        i.Timestamp,
        i.isPaid
    FROM 
        ck_invoices i
    LEFT JOIN 
        ck_companydetails co ON i.Company_ID = co.Company_ID
    LEFT JOIN 
        ck_categories c ON i.Category_ID = c.Category_ID
    LEFT JOIN 
        ck_categorytypes ct ON c.Type = ct.CGType_ID
    WHERE 
        i.Amount BETWEEN startAmount AND endAmount;
END//
DELIMITER ;

-- Exportiere Struktur von Prozedur ckaccounting.GetInvoicesByDateRange
DELIMITER //
CREATE PROCEDURE `GetInvoicesByDateRange`(
	IN `startDate` DATE,
	IN `endDate` DATE
)
BEGIN
    SELECT 
        i.Name,
        i.Amount,
        i.Date,
        ct.Type
    FROM 
        ck_invoices i
    LEFT JOIN 
        ck_categories c ON i.Category_ID = c.Category_ID
    LEFT JOIN 
        ck_categorytypes ct ON c.Type = ct.CGType_ID
    WHERE 
        i.Date BETWEEN startDate AND endDate;
END//
DELIMITER ;

-- Exportiere Struktur von Prozedur ckaccounting.GetInvoicesByDateRange_TABLE
DELIMITER //
CREATE PROCEDURE `GetInvoicesByDateRange_TABLE`(
	IN `startDate` DATE,
	IN `endDate` DATE
)
BEGIN
    SELECT 
        ct.Type AS CategoryType,
        i.Name,
        i.Description,
        i.Amount,
	 	  co.C_Name AS CompanyName,
        i.Date,
        i.Timestamp,
        i.isPaid
    FROM 
        ck_invoices i
    LEFT JOIN 
        ck_companydetails co ON i.Company_ID = co.Company_ID
    LEFT JOIN 
        ck_categories c ON i.Category_ID = c.Category_ID
    LEFT JOIN 
        ck_categorytypes ct ON c.Type = ct.CGType_ID
    WHERE 
        i.Date BETWEEN startDate AND endDate;
END//
DELIMITER ;

-- Exportiere Struktur von Prozedur ckaccounting.GetInvoicesBySearchPattern
DELIMITER //
CREATE PROCEDURE `GetInvoicesBySearchPattern`(IN SearchPattern VARCHAR(255))
BEGIN
    SELECT ck_invoices.*, ck_categories.Name as CategoryName, ck_companydetails.C_Name as CompanyName 
    FROM ck_invoices
    LEFT JOIN ck_categories ON ck_invoices.Category_ID = ck_categories.Category_ID
    LEFT JOIN ck_companydetails ON ck_invoices.Company_ID = ck_companydetails.Company_ID
    WHERE ck_invoices.Name LIKE CONCAT('%', SearchPattern, '%')
       OR ck_invoices.Description LIKE CONCAT('%', SearchPattern, '%');
END//
DELIMITER ;

-- Exportiere Struktur von Prozedur ckaccounting.GetInvoicesByYear
DELIMITER //
CREATE PROCEDURE `GetInvoicesByYear`(
    IN `year` INT
)
BEGIN
    SELECT 
        i.Name,
        i.Amount,
        i.Date,
        ct.Type
    FROM 
        ck_invoices i
    LEFT JOIN 
        ck_categories c ON i.Category_ID = c.Category_ID
    LEFT JOIN 
        ck_categorytypes ct ON c.Type = ct.CGType_ID
    WHERE 
        YEAR(i.Date) = year;
END//
DELIMITER ;

-- Entferne temporäre Tabelle und erstelle die eigentliche View
DROP TABLE IF EXISTS `ck_getbestcustomer`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `ck_getbestcustomer` AS SELECT 
    cd.C_Name AS CompanyName,
    SUM(i.Amount) AS TotalAmount
FROM 
    ck_invoices i
LEFT JOIN 
    ck_companydetails cd ON i.Company_ID = cd.Company_ID
GROUP BY 
    cd.C_Name
ORDER BY 
    TotalAmount DESC
LIMIT 1 ;

-- Entferne temporäre Tabelle und erstelle die eigentliche View
DROP TABLE IF EXISTS `ck_getbestmonth`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `ck_getbestmonth` AS SELECT 
    YEAR(Date) AS Year,
    MONTH(Date) AS Month,
    SUM(CASE WHEN c.Type = 1 AND i.isPaid = 1 THEN i.Amount ELSE 0 END) - SUM(CASE WHEN c.Type = 2 AND i.isPaid = 1 THEN i.Amount ELSE 0 END) AS NetIncome
FROM 
    ck_invoices i
JOIN 
    ck_categories c ON i.Category_ID = c.Category_ID
GROUP BY 
    YEAR(Date), MONTH(Date)
ORDER BY 
    NetIncome DESC
LIMIT 1 ;

-- Entferne temporäre Tabelle und erstelle die eigentliche View
DROP TABLE IF EXISTS `ck_getbestyear`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `ck_getbestyear` AS SELECT 
    YEAR(Date) AS Year,
    SUM(CASE WHEN c.Type = 1 AND i.isPaid = 1 THEN i.Amount ELSE 0 END) - SUM(CASE WHEN c.Type = 2 AND i.isPaid = 1 THEN i.Amount ELSE 0 END) AS NetIncome
FROM 
    ck_invoices i
JOIN 
    ck_categories c ON i.Category_ID = c.Category_ID
GROUP BY 
    YEAR(Date)
ORDER BY 
    NetIncome DESC
LIMIT 1 ;

-- Entferne temporäre Tabelle und erstelle die eigentliche View
DROP TABLE IF EXISTS `ck_getcp_with_cpn`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `ck_getcp_with_cpn` AS SELECT 
    cp.ContactPers_ID,
    cp.Title,
    cp.Firstname,
    cp.Lastname,
    cp.EMail,
    cp.PhoneNr,
    g.Type AS Gender,
    cd.C_Name AS CompanyName,
    cd.C_Other AS CompanyOther
FROM 
    ck_contactperson cp
LEFT JOIN 
    ck_gender g ON cp.Gender_ID = g.Gender_ID
LEFT JOIN 
    ck_companydetails cd ON cp.Company_ID = cd.Company_ID ;

-- Entferne temporäre Tabelle und erstelle die eigentliche View
DROP TABLE IF EXISTS `ck_getworstmonth`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `ck_getworstmonth` AS SELECT 
    YEAR(Date) AS Year,
    MONTH(Date) AS Month,
    SUM(CASE WHEN c.Type = 1 AND i.isPaid = 1 THEN i.Amount ELSE 0 END) - SUM(CASE WHEN c.Type = 2 AND i.isPaid = 1 THEN i.Amount ELSE 0 END) AS NetIncome
FROM 
    ck_invoices i
JOIN 
    ck_categories c ON i.Category_ID = c.Category_ID
GROUP BY 
    YEAR(Date), MONTH(Date)
ORDER BY 
    NetIncome ASC
LIMIT 1 ;

-- Entferne temporäre Tabelle und erstelle die eigentliche View
DROP TABLE IF EXISTS `ck_getworstyear`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `ck_getworstyear` AS SELECT 
    YEAR(Date) AS Year,
    SUM(CASE WHEN c.Type = 1 AND i.isPaid = 1 THEN i.Amount ELSE 0 END) - SUM(CASE WHEN c.Type = 2 AND i.isPaid = 1 THEN i.Amount ELSE 0 END) AS NetIncome
FROM 
    ck_invoices i
JOIN 
    ck_categories c ON i.Category_ID = c.Category_ID
GROUP BY 
    YEAR(Date)
ORDER BY 
    NetIncome ASC
LIMIT 1 ;

-- Entferne temporäre Tabelle und erstelle die eigentliche View
DROP TABLE IF EXISTS `ck_get_least_used_category`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `ck_get_least_used_category` AS SELECT 
    c.Name AS CategoryName,
    COUNT(i.Invoice_ID) AS EntriesCount
FROM 
    ck_invoices i
LEFT JOIN 
    ck_categories c ON i.Category_ID = c.Category_ID
GROUP BY 
    c.Name
ORDER BY 
    EntriesCount ASC
LIMIT 1 ;

-- Entferne temporäre Tabelle und erstelle die eigentliche View
DROP TABLE IF EXISTS `ck_get_most_used_category`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `ck_get_most_used_category` AS SELECT 
    c.Name AS CategoryName,
    COUNT(i.Invoice_ID) AS EntriesCount
FROM 
    ck_invoices i
LEFT JOIN 
    ck_categories c ON i.Category_ID = c.Category_ID
GROUP BY 
    c.Name
ORDER BY 
    EntriesCount DESC
LIMIT 1 ;

-- Entferne temporäre Tabelle und erstelle die eigentliche View
DROP TABLE IF EXISTS `ck_invoices_with_category`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `ck_invoices_with_category` AS SELECT 
    i.Name AS InvoiceName,
    i.Amount,
    i.Date,
    ct.Type AS CategoryType,
    i.isPaid
FROM 
    ck_invoices i
LEFT JOIN 
    ck_categories c ON i.Category_ID = c.Category_ID
LEFT JOIN 
    ck_categorytypes ct ON c.Type = ct.CGType_ID ;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
