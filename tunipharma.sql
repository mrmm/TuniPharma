-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 03, 2014 at 12:54 PM
-- Server version: 5.5.35
-- PHP Version: 5.4.6-1ubuntu1.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tunipharma`
--

-- --------------------------------------------------------

--
-- Table structure for table `Boites_Message`
--

CREATE TABLE IF NOT EXISTS `Boites_Message` (
  `id_bt` int(11) NOT NULL AUTO_INCREMENT,
  `id_cpt` int(11) NOT NULL,
  PRIMARY KEY (`id_bt`),
  KEY `id_cpt` (`id_cpt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Calendriers`
--

CREATE TABLE IF NOT EXISTS `Calendriers` (
  `id_cal` int(11) NOT NULL AUTO_INCREMENT,
  `id_pha` int(11) NOT NULL,
  `heure` int(11) NOT NULL,
  `minute` int(11) NOT NULL,
  `jour` int(11) NOT NULL,
  `mois` int(11) NOT NULL,
  `annee` int(11) NOT NULL,
  PRIMARY KEY (`id_cal`),
  KEY `id_phar` (`id_pha`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Comptes`
--

CREATE TABLE IF NOT EXISTS `Comptes` (
  `id_cpt` int(11) NOT NULL AUTO_INCREMENT,
  `nom_cpt` varchar(50) NOT NULL,
  `prenom_cpt` varchar(50) NOT NULL,
  `email_cpt` varchar(100) NOT NULL,
  `pass_cpt` varchar(150) NOT NULL,
  `addresse_cpt` text NOT NULL,
  `tel_cpt` int(11) NOT NULL,
  `type_cpt` int(11) NOT NULL,
  `etat_cpt` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_cpt`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `Comptes`
--

INSERT INTO `Comptes` (`id_cpt`, `nom_cpt`, `prenom_cpt`, `email_cpt`, `pass_cpt`, `addresse_cpt`, `tel_cpt`, `type_cpt`, `etat_cpt`) VALUES
(1, 'Mourad', 'Maatoug', 'mr.maatoug@gmail.com', '098f6bcd4621d373cade4e832627b4f6', '24 Rue de pasteur cite el hidhab fouchana ', 55445544, 41745485, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Demandes`
--

CREATE TABLE IF NOT EXISTS `Demandes` (
  `id_dmd` int(11) NOT NULL AUTO_INCREMENT,
  `id_type_dmd` int(11) NOT NULL,
  `date_dmd` datetime NOT NULL,
  `id_cpt_dmd` int(11) NOT NULL,
  `id_concerne_dmd` int(11) NOT NULL,
  PRIMARY KEY (`id_dmd`),
  KEY `id_type_dmd` (`id_type_dmd`,`id_cpt_dmd`),
  KEY `id_cpt_dmd` (`id_cpt_dmd`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Evenements`
--

CREATE TABLE IF NOT EXISTS `Evenements` (
  `id_event` int(11) NOT NULL AUTO_INCREMENT,
  `id_pha` int(11) NOT NULL,
  `date_event` datetime NOT NULL,
  `nom_event` varchar(50) NOT NULL,
  `desc_event` text NOT NULL,
  `etat_event` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_event`),
  KEY `id_pha` (`id_pha`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Gouvernorats`
--

CREATE TABLE IF NOT EXISTS `Gouvernorats` (
  `id_gouv` int(11) NOT NULL AUTO_INCREMENT,
  `nom_gouv` varchar(100) NOT NULL,
  PRIMARY KEY (`id_gouv`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `Gouvernorats`
--

INSERT INTO `Gouvernorats` (`id_gouv`, `nom_gouv`) VALUES
(1, ' Ben Arous '),
(2, ' Bizerte '),
(3, ' Beja '),
(4, ' Gabes '),
(5, ' Gafsa '),
(6, ' Jendouba '),
(7, ' Kairouan '),
(8, ' Kasserine '),
(9, ' Kebili '),
(10, ' la Manouba '),
(11, ' Mahdia '),
(12, ' Monastir '),
(13, ' Medenine '),
(14, ' Nabeul '),
(15, ' Sfax '),
(16, ' Sidi Bouzid '),
(17, ' Siliana '),
(18, ' Sousse '),
(19, ' Tataouine '),
(20, ' Tozeur '),
(21, ' Tunis '),
(22, ' Zaghouan '),
(23, ' Gouvernorat du Kef ');

-- --------------------------------------------------------

--
-- Table structure for table `Messages`
--

CREATE TABLE IF NOT EXISTS `Messages` (
  `id_msg` int(11) NOT NULL AUTO_INCREMENT,
  `id_bt_src` int(11) NOT NULL,
  `id_bt_dst` int(11) NOT NULL,
  `sujet_msg` varchar(150) NOT NULL,
  `corps_msg` text NOT NULL,
  `date_msg` datetime NOT NULL,
  `etat_msg` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_msg`),
  KEY `id_src` (`id_bt_src`,`id_bt_dst`),
  KEY `id_dst` (`id_bt_dst`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Pharmacies`
--

CREATE TABLE IF NOT EXISTS `Pharmacies` (
  `id_pha` int(11) NOT NULL AUTO_INCREMENT,
  `id_resp` int(11) NOT NULL,
  `nom_pha` varchar(50) NOT NULL,
  `addresse_pha` text NOT NULL,
  `tel_pha` int(11) NOT NULL,
  `fax_pha` int(11) NOT NULL,
  `id_cal` int(11) NOT NULL,
  `lat_gm_pha` varchar(100) NOT NULL,
  `long_gm_pha` varchar(100) NOT NULL,
  `email_pha` varchar(100) NOT NULL,
  `type_pha` int(11) NOT NULL,
  `ville_pha` varchar(50) NOT NULL,
  `gouv_pha` varchar(50) NOT NULL,
  PRIMARY KEY (`id_pha`),
  KEY `id_resp` (`id_resp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Services`
--

CREATE TABLE IF NOT EXISTS `Services` (
  `id_srv` int(11) NOT NULL AUTO_INCREMENT,
  `id_pha` int(11) NOT NULL,
  `id_type_srv` int(11) NOT NULL,
  `description_srv` text NOT NULL,
  `nom_srv` varchar(50) NOT NULL,
  PRIMARY KEY (`id_srv`),
  KEY `id_pha` (`id_pha`,`id_type_srv`),
  KEY `id_type_srv` (`id_type_srv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Types_Demande`
--

CREATE TABLE IF NOT EXISTS `Types_Demande` (
  `id_type_dmd` int(11) NOT NULL AUTO_INCREMENT,
  `dmd_table` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type_dmd`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Types_Service`
--

CREATE TABLE IF NOT EXISTS `Types_Service` (
  `id_type_srv` int(11) NOT NULL AUTO_INCREMENT,
  `nom_type_srv` varchar(50) NOT NULL,
  `description_type_srv` text NOT NULL,
  PRIMARY KEY (`id_type_srv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Villes`
--

CREATE TABLE IF NOT EXISTS `Villes` (
  `id_ville` int(11) NOT NULL AUTO_INCREMENT,
  `nom_ville` varchar(100) NOT NULL,
  `id_gouv` int(11) NOT NULL,
  PRIMARY KEY (`id_ville`),
  KEY `id_gouv` (`id_gouv`),
  KEY `id_gouv_2` (`id_gouv`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=263 ;

--
-- Dumping data for table `Villes`
--

INSERT INTO `Villes` (`id_ville`, `nom_ville`, `id_gouv`) VALUES
(1, ' Ben Arous ', 1),
(2, ' Bou Mhel el-Bassatine ', 1),
(3, ' El Mourouj ', 1),
(4, ' Ezzahra ', 1),
(5, ' Hammam Chott ', 1),
(6, ' Hammam Lif ', 1),
(7, ' Khalidia ', 1),
(8, ' Mohamedia-Fouchana ', 1),
(9, ' Mornag ', 1),
(10, ' Megrine ', 1),
(11, ' Rades ', 1),
(12, ' Aousja ', 2),
(13, ' Bizerte ', 2),
(14, ' El Alia ', 2),
(15, ' Ghar El Melh ', 2),
(16, ' Mateur ', 2),
(17, ' Menzel Abderrahmane ', 2),
(18, ' Menzel Bourguiba ', 2),
(19, ' Menzel Jemil ', 2),
(20, ' Metline ', 2),
(21, ' Raf Raf ', 2),
(22, ' Ras Jebel ', 2),
(23, ' Sejenane ', 2),
(24, ' Tinja ', 2),
(25, ' Amdoun ', 3),
(26, ' Beja ', 3),
(27, ' El Maagoula ', 3),
(28, ' Goubellat ', 3),
(29, ' Medjez el-Bab ', 3),
(30, ' Nefza ', 3),
(31, ' Testour ', 3),
(32, ' Teboursouk ', 3),
(33, ' Chenini Nahal ', 4),
(34, ' El Hamma ', 4),
(35, ' Gabes ', 4),
(36, ' Ghannouch ', 4),
(37, ' Mareth ', 4),
(38, ' Matmata ', 4),
(39, ' Metouia ', 4),
(40, ' Nouvelle Matmata ', 4),
(41, ' Oudhref ', 4),
(42, ' Zarat ', 4),
(43, ' El Guettar ', 5),
(44, ' El Ksar ', 5),
(45, ' Gafsa ', 5),
(46, ' Mdhila ', 5),
(47, ' Moulares ', 5),
(48, ' Metlaoui ', 5),
(49, ' Redeyef ', 5),
(50, ' Sened ', 5),
(51, ' Ain Draham ', 6),
(52, ' Bou Salem ', 6),
(53, ' Fernana ', 6),
(54, ' Ghardimaou ', 6),
(55, ' Jendouba ', 6),
(56, ' Oued Meliz ', 6),
(57, ' Tabarka ', 6),
(58, ' Ain Djeloula ', 7),
(59, ' Bou Hajla ', 7),
(60, ' Chebika ', 7),
(61, ' Echrarda ', 7),
(62, ' El Alaa ', 7),
(63, ' Haffouz ', 7),
(64, ' Hajeb El Ayoun ', 7),
(65, ' Kairouan ', 7),
(66, ' Menzel Mehiri ', 7),
(67, ' Nasrallah ', 7),
(68, ' Oueslatia ', 7),
(69, ' Sbikha ', 7),
(70, ' Foussana ', 8),
(71, ' Feriana ', 8),
(72, ' Haidra ', 8),
(73, ' Jedelienne ', 8),
(74, ' Kasserine ', 8),
(75, ' Majel Bel Abbes ', 8),
(76, ' Sbeitla ', 8),
(77, ' Sbiba ', 8),
(78, ' Thala ', 8),
(79, ' Thelepte ', 8),
(80, ' Douz ', 9),
(81, ' El Golaa ', 9),
(82, ' Jemna ', 9),
(83, ' Kebili ', 9),
(84, ' Souk Lahad ', 9),
(91, ' Borj El Amri ', 10),
(92, ' Den Den ', 10),
(93, ' Djedeida ', 10),
(94, ' Douar Hicher ', 10),
(95, ' El Batan ', 10),
(96, ' La Manouba ', 10),
(97, ' Mornaguia ', 10),
(98, ' Oued Ellil ', 10),
(99, ' Tebourba ', 10),
(100, ' Bou Merdes ', 11),
(101, ' Chebba ', 11),
(102, ' Chorbane ', 11),
(103, ' El Bradaa ', 11),
(104, ' El Jem ', 11),
(105, ' Essouassi ', 11),
(106, ' Hebira ', 11),
(107, ' Kerker ', 11),
(108, ' Ksour Essef ', 11),
(109, ' Mahdia ', 11),
(110, ' Mellouleche ', 11),
(111, ' Ouled Chamekh ', 11),
(112, ' Rejiche ', 11),
(113, ' Sidi Alouane ', 11),
(114, ' Amiret El Fhoul ', 12),
(115, ' Amiret Hajjaj ', 12),
(116, ' Amiret Touazra ', 12),
(117, ' Bekalta ', 12),
(118, ' Bembla ', 12),
(119, ' Beni Hassen ', 12),
(120, ' Bennane-Bodheur ', 12),
(121, ' Bouhjar ', 12),
(122, ' Cherahil ', 12),
(123, ' El Ghnada ', 12),
(124, ' El Masdour ', 12),
(125, ' Jemmal ', 12),
(126, ' Khniss ', 12),
(127, ' Ksar Hellal ', 12),
(128, ' Ksibet el-Mediouni ', 12),
(129, ' Lamta ', 12),
(130, ' Menzel Ennour ', 12),
(131, ' Menzel Farsi ', 12),
(132, ' Menzel Hayet ', 12),
(133, ' Menzel Kamel ', 12),
(134, ' Moknine ', 12),
(135, ' Monastir ', 12),
(136, ' Ouerdanine ', 12),
(137, ' Sahline Mootmar ', 12),
(138, ' Sayada ', 12),
(139, ' Sidi Ameur ', 12),
(140, ' Sidi Bennour ', 12),
(141, ' Touza ', 12),
(142, ' Teboulba ', 12),
(143, ' Zaouiet Kontoch ', 12),
(144, ' Zeramdine ', 12),
(145, ' Ben Gardane ', 13),
(146, ' Beni Khedache ', 13),
(147, ' Djerba - Ajim ', 13),
(148, ' Djerba - Houmt Souk ', 13),
(149, ' Djerba - Midoun ', 13),
(150, ' Medenine ', 13),
(151, ' Zarzis ', 13),
(152, ' Azmour ', 14),
(153, ' Bou Argoub ', 14),
(154, ' Beni Khalled ', 14),
(155, ' Beni Khiar ', 14),
(156, ' Dar Allouch ', 14),
(157, ' Dar Chaabane ', 14),
(158, ' El Haouaria ', 14),
(159, ' El Maamoura ', 14),
(160, ' El Mida ', 14),
(161, ' Grombalia ', 14),
(162, ' Hammam Ghezeze ', 14),
(163, ' Hammamet ', 14),
(164, ' Korba ', 14),
(165, ' Korbous ', 14),
(166, ' Kelibia ', 14),
(167, ' Menzel Bouzelfa ', 14),
(168, ' Menzel Horr ', 14),
(169, ' Menzel Temime ', 14),
(170, ' Nabeul ', 14),
(171, ' Soliman ', 14),
(172, ' Somaa ', 14),
(173, ' Takelsa ', 14),
(174, ' Tazarka ', 14),
(175, ' Zaouiet Djedidi ', 14),
(176, ' Agareb ', 15),
(177, ' Bir Ali Ben Khalifa ', 15),
(178, ' Bir Salah ', 15),
(179, ' Chihia ', 15),
(180, ' El Ain ', 15),
(181, ' El Hencha ', 15),
(182, ' Graiba ', 15),
(183, ' Gremda ', 15),
(184, ' Jebiniana ', 15),
(185, ' Kerkennah ', 15),
(186, ' Mahres ', 15),
(187, ' Sakiet Eddaier ', 15),
(188, ' Sakiet Ezzit ', 15),
(189, ' Sfax ', 15),
(190, ' Skhira ', 15),
(191, ' Thyna ', 15),
(192, ' Bir El Hafey ', 16),
(193, ' Cebbala Ouled Asker ', 16),
(194, ' Jilma ', 16),
(195, ' Meknassy ', 16),
(196, ' Menzel Bouzaiane ', 16),
(197, ' Mezzouna ', 16),
(198, ' Ouled Haffouz ', 16),
(199, ' Regueb ', 16),
(200, ' Sidi Ali Ben Aoun ', 16),
(201, ' Sidi Bouzid ', 16),
(202, ' Bargou ', 17),
(203, ' Bou Arada ', 17),
(204, ' El Aroussa ', 17),
(205, ' El Krib ', 17),
(206, ' Gaafour ', 17),
(207, ' Kesra ', 17),
(208, ' Makthar ', 17),
(209, ' Rouhia ', 17),
(210, ' Sidi Bou Rouis ', 17),
(211, ' Siliana ', 17),
(212, ' Akouda ', 18),
(213, ' Bouficha ', 18),
(214, ' Enfida ', 18),
(215, ' Ezzouhour ', 18),
(216, ' Hammam Sousse ', 18),
(217, ' Hergla ', 18),
(218, ' Kalaa Kebira ', 18),
(219, ' Kalaa Seghira ', 18),
(220, ' Kondar ', 18),
(221, ' Ksibet Thrayet ', 18),
(222, ' Messaadine ', 18),
(223, ' Sidi Bou Ali ', 18),
(224, ' Sidi El Hani ', 18),
(225, ' Sousse ', 18),
(226, ' Zaouiet Sousse ', 18),
(227, ' Bir Lahmar ', 19),
(228, ' Dehiba ', 19),
(229, ' Ghomrassen ', 19),
(230, ' Remada ', 19),
(231, ' Tataouine ', 19),
(232, ' Degache ', 20),
(233, ' El Hamma du Jerid ', 20),
(234, ' Nefta ', 20),
(235, ' Tamerza ', 20),
(236, ' Tozeur ', 20),
(237, ' Carthage ', 21),
(238, ' La Goulette ', 21),
(239, ' La Marsa ', 21),
(240, ' Le Bardo ', 21),
(241, ' Le Kram ', 21),
(242, ' Sidi Bou Said ', 21),
(243, ' Sidi Hassine ', 21),
(244, ' Tunis ', 21),
(245, ' Bir Mcherga ', 22),
(246, ' Djebel Oust ', 22),
(247, ' El Fahs ', 22),
(248, ' Nadhour ', 22),
(249, ' Zaghouan ', 22),
(250, ' Zriba ', 22),
(251, ' Dahmani ', 23),
(252, ' El Ksour ', 23),
(253, ' Jerissa ', 23),
(254, ' Kalaat Senan ', 23),
(255, ' Kalaat Khasba ', 23),
(256, ' Le Kef ', 23),
(257, ' Menzel Salem ', 23),
(258, ' Nebeur ', 23),
(259, ' Sakiet Sidi Youssef ', 23),
(260, ' Sers ', 23),
(261, ' Tajerouine ', 23),
(262, ' Touiref ', 23);

-- --------------------------------------------------------

--
-- Table structure for table `Votes`
--

CREATE TABLE IF NOT EXISTS `Votes` (
  `id_vote` int(11) NOT NULL AUTO_INCREMENT,
  `id_cpt` int(11) NOT NULL,
  `id_pha` int(11) NOT NULL,
  `valeur_vote` int(11) NOT NULL,
  PRIMARY KEY (`id_vote`),
  KEY `id_cpt` (`id_cpt`),
  KEY `id_cpt_2` (`id_cpt`,`id_pha`),
  KEY `id_pha` (`id_pha`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Boites_Message`
--
ALTER TABLE `Boites_Message`
  ADD CONSTRAINT `Boites_Message_ibfk_1` FOREIGN KEY (`id_cpt`) REFERENCES `Comptes` (`id_cpt`);

--
-- Constraints for table `Calendriers`
--
ALTER TABLE `Calendriers`
  ADD CONSTRAINT `Calendriers_ibfk_1` FOREIGN KEY (`id_pha`) REFERENCES `Pharmacies` (`id_pha`);

--
-- Constraints for table `Demandes`
--
ALTER TABLE `Demandes`
  ADD CONSTRAINT `Demandes_ibfk_1` FOREIGN KEY (`id_cpt_dmd`) REFERENCES `Comptes` (`id_cpt`),
  ADD CONSTRAINT `Demandes_ibfk_2` FOREIGN KEY (`id_type_dmd`) REFERENCES `Types_Demande` (`id_type_dmd`);

--
-- Constraints for table `Evenements`
--
ALTER TABLE `Evenements`
  ADD CONSTRAINT `Evenements_ibfk_1` FOREIGN KEY (`id_pha`) REFERENCES `Pharmacies` (`id_pha`);

--
-- Constraints for table `Messages`
--
ALTER TABLE `Messages`
  ADD CONSTRAINT `Messages_ibfk_1` FOREIGN KEY (`id_bt_src`) REFERENCES `Boites_Message` (`id_bt`),
  ADD CONSTRAINT `Messages_ibfk_2` FOREIGN KEY (`id_bt_dst`) REFERENCES `Boites_Message` (`id_bt`);

--
-- Constraints for table `Pharmacies`
--
ALTER TABLE `Pharmacies`
  ADD CONSTRAINT `Pharmacies_ibfk_1` FOREIGN KEY (`id_resp`) REFERENCES `Comptes` (`id_cpt`);

--
-- Constraints for table `Services`
--
ALTER TABLE `Services`
  ADD CONSTRAINT `Services_ibfk_1` FOREIGN KEY (`id_pha`) REFERENCES `Pharmacies` (`id_pha`),
  ADD CONSTRAINT `Services_ibfk_2` FOREIGN KEY (`id_type_srv`) REFERENCES `Types_Service` (`id_type_srv`);

--
-- Constraints for table `Villes`
--
ALTER TABLE `Villes`
  ADD CONSTRAINT `Villes_ibfk_1` FOREIGN KEY (`id_gouv`) REFERENCES `Gouvernorats` (`id_gouv`);

--
-- Constraints for table `Votes`
--
ALTER TABLE `Votes`
  ADD CONSTRAINT `Votes_ibfk_1` FOREIGN KEY (`id_cpt`) REFERENCES `Comptes` (`id_cpt`),
  ADD CONSTRAINT `Votes_ibfk_2` FOREIGN KEY (`id_pha`) REFERENCES `Pharmacies` (`id_pha`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
