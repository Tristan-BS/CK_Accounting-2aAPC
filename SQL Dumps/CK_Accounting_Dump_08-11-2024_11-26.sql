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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_categories: ~11 rows (ungefähr)
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
	(12, 'Office Supplies', 2, 'Expenses for office supplies and consumables');

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

-- Exportiere Daten aus Tabelle ckaccounting.ck_companydetails: ~11 rows (ungefähr)
INSERT INTO `ck_companydetails` (`Company_ID`, `C_Name`, `C_Street`, `C_HouseNumber`, `C_Location`, `C_PostalCode`, `C_Country`, `C_Other`) VALUES
	(4, 'Kugel Studio', 'Entschendorf', 47, 'St. Margarethen an der Raab', 8321, 'Austria', 'Die Firma is cool!'),
	(7, 'Tech Solutions GmbH', 'Main Street', 123, 'Vienna', 1010, 'Austria', ''),
	(8, 'Eco Friendly Corp', 'Greenway Avenue', 47, 'Salzburg', 5020, 'Austria', ''),
	(9, 'Global Trade Ltd.', 'Trade St.', 12, 'Linz', 4020, 'Austria', ''),
	(10, 'Alpha Media', 'Media Avenue', 234, 'Innsbruck', 6020, 'Austria', ''),
	(11, 'Mountain Equipment Inc.', 'Mountain Rd.', 45, 'Graz', 8010, 'Austria', ''),
	(12, 'HealthFirst Pharma', 'Health Blvd.', 67, 'Vienna', 1030, 'Austria', ''),
	(13, 'Foodie\s Delight', 'Food Court', 90, 'Salzburg', 5020, 'Austria', ''),
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Exportiere Daten aus Tabelle ckaccounting.ck_invoices: ~0 rows (ungefähr)

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