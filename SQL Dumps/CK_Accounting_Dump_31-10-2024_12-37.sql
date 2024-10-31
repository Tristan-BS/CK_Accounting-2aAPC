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
  KEY `Type` (`Type`),
  CONSTRAINT `To_CategoryTypes_ID` FOREIGN KEY (`Type`) REFERENCES `ck_categorytypes` (`CGType_ID`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_categories: ~5 rows (ungefähr)
INSERT INTO `ck_categories` (`Category_ID`, `Name`, `Type`, `Others`) VALUES
	(1, 'Salary', 2, 'Monthly salary for employees'),
	(2, 'Sale of goods', 1, 'Stock of goods were sold.'),
	(3, 'TestCategory', 1, 'This is for testing. WE ARE GETTING RICH HAHAHAHA'),
	(4, 'asdasd', 1, 'asdasdas'),
	(5, 'asde23edsda', 2, 'asdasdasd');

-- Exportiere Struktur von Tabelle ckaccounting.ck_categorytypes
CREATE TABLE IF NOT EXISTS `ck_categorytypes` (
  `CGType_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CGType_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_categorytypes: ~2 rows (ungefähr)
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_companydetails: ~3 rows (ungefähr)
INSERT INTO `ck_companydetails` (`Company_ID`, `C_Name`, `C_Street`, `C_HouseNumber`, `C_Location`, `C_PostalCode`, `C_Country`, `C_Other`) VALUES
	(1, 'Knapp AG', 'Dortdahalt', 47, 'Harti', 724, 'Austria', ''),
	(4, 'Kugel Studio', 'Entschendorf', 47, 'St. Margarethen an der Raab', 8321, 'Austria', '\nDie Firma is cool!'),
	(6, 'Peter Griffin GmbH', 'asdopkasd', 234, 'asdasd', 2134, 'Germany', '\nasdasdqwdasd');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_contactperson: ~4 rows (ungefähr)
INSERT INTO `ck_contactperson` (`ContactPers_ID`, `Rank`, `Title`, `Gender_ID`, `Firstname`, `Lastname`, `EMail`, `PhoneNr`, `Company_ID`) VALUES
	(1, 1, 'Choose', 1, 'Tristan', 'Birnstingl', '123@gmasilco.ocm', '123456789', 4),
	(2, 1, 'AR', 1, 'Quantn', 'Tyrone', 'yoyoyoyoyo@sdmf', '124345', 1),
	(3, 1, 'Bakk. techn.*', 1, 'asdqwd', 'dasdas', 'asdw3q', '1243234', 6),
	(4, 2, 'Bakk. techn.*', 1, 'asdgfhjgfdsa', 'asfdthzfhsd', 'asdasd', '32423', 6);

-- Exportiere Struktur von Tabelle ckaccounting.ck_countries
CREATE TABLE IF NOT EXISTS `ck_countries` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_countries: ~6 rows (ungefähr)
INSERT INTO `ck_countries` (`ID`, `Country`) VALUES
	(1, 'Austria'),
	(2, 'Germany'),
	(3, 'England'),
	(4, 'Spain'),
	(5, 'France'),
	(6, 'Italy');

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

-- Exportiere Struktur von Tabelle ckaccounting.ck_invoices
CREATE TABLE IF NOT EXISTS `ck_invoices` (
  `Invoice_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Category_ID` int(11) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`Invoice_ID`),
  KEY `Category_ID` (`Category_ID`),
  CONSTRAINT `To_CategoryID` FOREIGN KEY (`Category_ID`) REFERENCES `ck_categories` (`Category_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_invoices: ~1 rows (ungefähr)
INSERT INTO `ck_invoices` (`Invoice_ID`, `Category_ID`, `Name`, `Description`, `Amount`, `Date`, `Timestamp`) VALUES
	(1, 1, 'Tristn', 'asjdn', 3724.23, '2024-10-31', '2024-10-31 10:54:16');

-- Exportiere Struktur von Tabelle ckaccounting.ck_titles
CREATE TABLE IF NOT EXISTS `ck_titles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(50) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_titles: ~69 rows (ungefähr)
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

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
