-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 20 mai 2019 à 19:12
-- Version du serveur :  5.7.17
-- Version de PHP :  5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `club`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `cin` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `id_reservation` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `cin`, `email`, `id_reservation`) VALUES
(4, 'zryh', 'dghj', NULL),
(4, 'aaa', 'fghj', NULL),
(24, 'AE82130', 'latifahimdi@gmail.com', NULL),
(25, 'ae82130', 'zinebakhrif0@gmail.com', NULL),
(15, 'AE1478', 'zinebakhrifmp@gmail.com', 1),
(15, 'AE1478', 'zinebakhrifmp@gmail.com', 1);

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `id` int(11) NOT NULL,
  `ref_facture` varchar(30) NOT NULL,
  `montant_facture` float NOT NULL,
  `id_reservation` int(11) NOT NULL,
  `id_client` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `gerant`
--

CREATE TABLE `gerant` (
  `id` int(6) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `hebergement`
--

CREATE TABLE `hebergement` (
  `id` int(11) NOT NULL,
  `localisation` varchar(30) NOT NULL,
  `nbr_personne` int(11) NOT NULL,
  `type` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hebergement`
--

INSERT INTO `hebergement` (`id`, `localisation`, `nbr_personne`, `type`) VALUES
(1, 'Batiment K', 4, 'Appartement'),
(4, 'Batiment F', 2, 'Bungalow');

-- --------------------------------------------------------

--
-- Structure de la table `personnes`
--

CREATE TABLE `personnes` (
  `id` int(6) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `personnes`
--

INSERT INTO `personnes` (`id`, `firstname`, `lastname`, `login`, `password`) VALUES
(1, 'fadi', 'azzouz', 'fazouz', '123456789'),
(4, 'Zineb', 'Akhrif', 'zaza', 'djddfl'),
(5, 'omar', 'abina', 'omar', 'dfdsfgr'),
(6, 'hdkfl', 'jkfl', 'kdqm', 'djfksl'),
(7, 'jfkfelr', 'jdkfl', 'jegel', 'jfeker'),
(8, 'sdfs', 'sdf', 'dwsdQ', 'DQDFF'),
(9, 'xcvb', 'dfghj', 'wqazs', 'fghj'),
(10, 'mpol', 'wxcv', 'zqwx', 'wxcvh'),
(11, 'zineb', 'akhrif', 'zakhrif', '123456'),
(12, 'ejze', 'zjej', 'zakhrif', 'hfjskf'),
(13, 'erhsj', 'frgf', 'zakhrif', '123456'),
(14, 'fadi', 'Azzouz', 'fadiazzoua', '123456'),
(15, 'omar', 'abina', 'dfrgegemz', '123456'),
(16, 'rgrhg', 'sdg', 'gthtj', 'hrtjyj'),
(17, 'zrejtgehrl', 'zjr', 'kjgdjf', 'rgnktyty'),
(18, 'fghj', 'ghj', 'gjll', 'gfghj'),
(19, 'ekrgkg', 'kgkrg', 'rkzerg', 'rkng'),
(20, 'ttjj', 'hgf', 'drg', 'fhh'),
(21, 'zeze', 'zaza', 'njinji', 'bnhbh'),
(22, 'sfdfg', 'ghj', 'sddff', 'bhui'),
(23, 'sdf', 'hjk', 'dfghj', 'dfgh'),
(24, 'latifa', 'himdi', 'latifa1', '123456'),
(25, 'akhrif', 'zineb', 'hjklm', '14785');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `durree_sejour` int(11) NOT NULL,
  `date_arrive` date NOT NULL,
  `date_depart` date NOT NULL,
  `id_hebergement` int(11) NOT NULL,
  `id_restauration` int(11) NOT NULL,
  `simule_paiement` tinyint(1) DEFAULT NULL,
  `regle_paiement` tinyint(1) DEFAULT NULL,
  `montant_total` float NOT NULL,
  `Ref_reservation` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `durree_sejour`, `date_arrive`, `date_depart`, `id_hebergement`, `id_restauration`, `simule_paiement`, `regle_paiement`, `montant_total`, `Ref_reservation`) VALUES
(1, 2, '2019-05-01', '2019-05-15', 4, 1, 1, 0, 500, 'APT-150');

-- --------------------------------------------------------

--
-- Structure de la table `restauration`
--

CREATE TABLE `restauration` (
  `id` int(11) NOT NULL,
  `type_restauration` varchar(30) NOT NULL,
  `prix` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `restauration`
--

INSERT INTO `restauration` (`id`, `type_restauration`, `prix`) VALUES
(1, 'Pension complete', 150),
(4, 'Demi pension', 80);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD KEY `client_ibfk_1` (`id`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_client` (`id_client`),
  ADD KEY `id_reservation` (`id_reservation`);

--
-- Index pour la table `gerant`
--
ALTER TABLE `gerant`
  ADD UNIQUE KEY `id` (`id`);

--
-- Index pour la table `hebergement`
--
ALTER TABLE `hebergement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `personnes`
--
ALTER TABLE `personnes`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_hebergement` (`id_hebergement`),
  ADD KEY `id_restauration` (`id_restauration`);

--
-- Index pour la table `restauration`
--
ALTER TABLE `restauration`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `hebergement`
--
ALTER TABLE `hebergement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `personnes`
--
ALTER TABLE `personnes`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `restauration`
--
ALTER TABLE `restauration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `facture_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `facture_ibfk_2` FOREIGN KEY (`id_reservation`) REFERENCES `reservation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `gerant`
--
ALTER TABLE `gerant`
  ADD CONSTRAINT `gerant_ibfk_1` FOREIGN KEY (`id`) REFERENCES `personnes` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`id_hebergement`) REFERENCES `hebergement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`id_restauration`) REFERENCES `restauration` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
