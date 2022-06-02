-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : jeu. 24 mars 2022 à 14:33
-- Version du serveur : 10.1.48-MariaDB-0+deb9u2
-- Version de PHP : 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `climb_this`
--

-- --------------------------------------------------------

--
-- Structure de la table `album`
--

CREATE TABLE `album` (
  `id_album` int(10) UNSIGNED NOT NULL,
  `id_lane` int(10) UNSIGNED DEFAULT NULL,
  `id_member` int(10) UNSIGNED NOT NULL,
  `id_wall` int(10) UNSIGNED NOT NULL,
  `is_checked` varchar(50) NOT NULL,
  `color` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `album`
--

INSERT INTO `album` (`id_album`, `id_lane`, `id_member`, `id_wall`, `is_checked`, `color`) VALUES
(381, 1, 5, 1, 'Valide', 'Rouge'),
(382, 1, 5, 1, 'Valide', 'Rouge'),
(383, 2, 5, 1, 'Valide', 'Bleu'),
(387, 4, 5, 1, 'Valide', 'Jaune'),
(388, 4, 5, 1, 'Valide', 'Jaune');

-- --------------------------------------------------------

--
-- Structure de la table `lane`
--

CREATE TABLE `lane` (
  `id_lane` int(10) UNSIGNED NOT NULL,
  `color` varchar(45) NOT NULL,
  `id_wall` int(10) UNSIGNED NOT NULL,
  `id_member` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `lane`
--

INSERT INTO `lane` (`id_lane`, `color`, `id_wall`, `id_member`) VALUES
(1, 'Rouge', 1, 5),
(2, 'Bleu', 1, 5),
(3, 'Noir', 1, 5),
(4, 'Jaune', 1, 5),
(5, 'Marron', 1, 5),
(76, 'Vert', 1, 38);

-- --------------------------------------------------------

--
-- Structure de la table `mailbox`
--

CREATE TABLE `mailbox` (
  `id_mail` int(10) UNSIGNED NOT NULL,
  `subject` varchar(95) NOT NULL,
  `object` text NOT NULL,
  `id_member` int(10) UNSIGNED NOT NULL,
  `id_album` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `mailbox`
--

INSERT INTO `mailbox` (`id_mail`, `subject`, `object`, `id_member`, `id_album`) VALUES
(1250, 'album1', 'a', 5, 381),
(1251, 'album2', 'a', 5, 382),
(1252, 'album1', 'a', 5, 383),
(1256, '1', 'a', 5, 387),
(1257, '2', 'a', 5, 388);

-- --------------------------------------------------------

--
-- Structure de la table `mod_request`
--

CREATE TABLE `mod_request` (
  `id_request` int(10) UNSIGNED NOT NULL,
  `subject` varchar(100) NOT NULL,
  `object` varchar(300) NOT NULL,
  `id_member` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `mod_request`
--

INSERT INTO `mod_request` (`id_request`, `subject`, `object`, `id_member`) VALUES
(1, 'Test', 'test', 39),
(6, 'reretest', 're', 39),
(7, 'zzzzzzzzzzzz', 'zzzzz', 26),
(10, 'd', 'd', 39),
(12, 'toast', 'toast', 39),
(13, 'wesh poto', 'comment va  ', 51),
(14, 'Je pveux passer admin', 'je veux passer admin', 5);

-- --------------------------------------------------------

--
-- Structure de la table `photo`
--

CREATE TABLE `photo` (
  `id_photo` int(10) UNSIGNED NOT NULL,
  `photo` varchar(100) NOT NULL,
  `id_album` int(10) UNSIGNED DEFAULT NULL,
  `id_member` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `photo`
--

INSERT INTO `photo` (`id_photo`, `photo`, `id_album`, `id_member`) VALUES
(335, 'a1_21_03_2022-13-59-80_.jpg', 381, 5),
(336, 'a2_21_03_2022-13-59-80_.jpg', 381, 5),
(337, 'a3_21_03_2022-13-59-80_.jpg', 381, 5),
(338, 'a4_21_03_2022-13-59-80_.jpg', 381, 5),
(339, 'a1_21_03_2022-14-00-49_.jpg', 382, 5),
(340, 'a2_21_03_2022-14-00-49_.jpg', 382, 5),
(341, 'a3_21_03_2022-14-00-49_.jpg', 382, 5),
(342, 'a4_21_03_2022-14-00-49_.jpg', 382, 5),
(343, 'a1_21_03_2022-14-00-11_.jpg', 383, 5),
(344, 'a2_21_03_2022-14-00-11_.jpg', 383, 5),
(345, 'a3_21_03_2022-14-00-11_.jpg', 383, 5),
(346, 'a4_21_03_2022-14-00-11_.jpg', 383, 5),
(359, 'a1_21_03_2022-14-01-29_.jpg', 387, 5),
(360, 'a2_21_03_2022-14-01-29_.jpg', 387, 5),
(361, 'a3_21_03_2022-14-01-29_.jpg', 387, 5),
(362, 'a4_21_03_2022-14-01-29_.jpg', 387, 5),
(363, 'a1_21_03_2022-14-02-37_.jpg', 388, 5),
(364, 'a2_21_03_2022-14-02-37_.jpg', 388, 5),
(365, 'a3_21_03_2022-14-02-37_.jpg', 388, 5),
(366, 'a4_21_03_2022-14-02-37_.jpg', 388, 5);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_member` int(10) UNSIGNED NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(90) NOT NULL,
  `nickname` varchar(45) NOT NULL,
  `club_member` varchar(45) NOT NULL,
  `user_role` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_member`, `user_name`, `user_password`, `first_name`, `last_name`, `email`, `nickname`, `club_member`, `user_role`) VALUES
(1, 'regis', '098f6bcd4621d373cade4e832627b4f6', 'regis', 'robert', 'test@gmail.com', 'nick', 'brest', 'user'),
(5, 'a', '86f7e437faa5a7fce15d1ddcb9eaeaea377667b8', 'regis2a', 'robert2', 'a@gmail.com', 'a', 'brest', 'user'),
(9, 'test4', '1ff2b3704aede04eecb51e50ca698efd50a1379b', 'test4', 'test4', 'test4@test.fr', 'test4', 'brest', 'user'),
(10, 'test5', '911ddc3b8f9a13b5499b6bc4638a2b4f3f68bf23', 'test5', 'test5', 'test5@test5.fr', 'test5', 'brest', 'user'),
(19, 'bz', '0c17ddceb3071d32d637bf884d4ad3d465c295fe', 'bz', 'bz', 'az@za.com', 'bz', 'brest', 'user'),
(21, 'der', '7d44c33cf9a82ab9bd5300d266784504e9772177', 'der', 'der', 'der@der.fr', 'der', 'brest', 'user'),
(24, 'Regisleboss', '8636ab4b6c9c7b93eac30762b8f3c0ca8f57b9ee', 'Regisleboss', 'Regisleboss', 'Regisleboss@Regisleboss.fr', 'Regisleboss', 'brest', 'user'),
(26, 'mca', '5f77c9a9df5a7449cd044610cbe6e3be4647f197', 'mca', 'mca', 'mca@gmail.com', 'mca', 'brest', 'admin'),
(37, 'Regislebss', '8636ab4b6c9c7b93eac30762b8f3c0ca8f57b9ee', 'Regisleboss', 'Regisleboss', 'Regisleboss@Regislebss.fr', 'Regislebss', 'brest', 'user'),
(38, 'gur123', 'b5c9e9b5420917ad177ed06792f869dbad36dea8', 'b', 'gur', 'gur@gmail.com', 'gur29', 'brest', 'admin'),
(39, 'Julien', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'Le Noach', 'Julien', 'julien@julien.fr', 'Ju', 'brest', 'admin'),
(40, 'POO', 'ce2a1cf2bb4bb839b17ff6701137c6e7368f89b9', 'POO', 'POO', 'POO@POO.FR', 'POO', 'brest', 'moderator'),
(44, 'b', 'e9d71f5ee7c92d6dc9e92ffdad17b8bd49418f98', 'b', 'b', 'b@gmail.com', 'b', 'brest', 'user'),
(45, 'c', '84a516841ba77a5b4648de2cd0dfcb30ea46dbb4', 'c', 'c', 'c@gmail.com', 'c', 'brest', 'user'),
(47, 'gld', '24af59273fc7a4d53faadacb37a99202c68e9704', 'Grego', 'Greg', 'gld@gld.com', 'gregou', 'brest', 'admin'),
(48, 'yo', 'c41975d1dae1cc69b16ad8892b8c77164e84ca39', 'yo', 'yo', 'yo@yo.com', 'yo', 'brest', 'user'),
(49, 'd', '3c363836cf4e16666669a25da280a1865c2d2874', 'd', 'd', 'd@gmail.com', 'd', 'brest', 'admin'),
(51, 'x', '11f6ad8ec52a2984abaafd7c3b516503785c2072', 'x', 'x', 'x@gmail.com', 'x', 'brest', 'moderator');

-- --------------------------------------------------------

--
-- Structure de la table `wall`
--

CREATE TABLE `wall` (
  `id_wall` int(10) UNSIGNED NOT NULL,
  `id_member` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `wall`
--

INSERT INTO `wall` (`id_wall`, `id_member`) VALUES
(1, 5);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`id_album`),
  ADD KEY `id_member` (`id_member`),
  ADD KEY `id_lane` (`id_lane`),
  ADD KEY `index` (`id_wall`);

--
-- Index pour la table `lane`
--
ALTER TABLE `lane`
  ADD PRIMARY KEY (`id_lane`),
  ADD KEY `id_member` (`id_member`),
  ADD KEY `id_wall` (`id_wall`);

--
-- Index pour la table `mailbox`
--
ALTER TABLE `mailbox`
  ADD PRIMARY KEY (`id_mail`),
  ADD KEY `id_member` (`id_member`),
  ADD KEY `mailbox_ibfk_2` (`id_album`);

--
-- Index pour la table `mod_request`
--
ALTER TABLE `mod_request`
  ADD PRIMARY KEY (`id_request`),
  ADD KEY `id_member` (`id_member`);

--
-- Index pour la table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`id_photo`),
  ADD KEY `id_album` (`id_album`),
  ADD KEY `id_member` (`id_member`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_member`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `nickname` (`nickname`),
  ADD UNIQUE KEY `user_name` (`user_name`);

--
-- Index pour la table `wall`
--
ALTER TABLE `wall`
  ADD PRIMARY KEY (`id_wall`),
  ADD KEY `id_member` (`id_member`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `album`
--
ALTER TABLE `album`
  MODIFY `id_album` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=430;

--
-- AUTO_INCREMENT pour la table `lane`
--
ALTER TABLE `lane`
  MODIFY `id_lane` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT pour la table `mailbox`
--
ALTER TABLE `mailbox`
  MODIFY `id_mail` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1298;

--
-- AUTO_INCREMENT pour la table `mod_request`
--
ALTER TABLE `mod_request`
  MODIFY `id_request` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `photo`
--
ALTER TABLE `photo`
  MODIFY `id_photo` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=485;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_member` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT pour la table `wall`
--
ALTER TABLE `wall`
  MODIFY `id_wall` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`id_member`) REFERENCES `utilisateur` (`id_member`),
  ADD CONSTRAINT `album_ibfk_3` FOREIGN KEY (`id_wall`) REFERENCES `wall` (`id_wall`);

--
-- Contraintes pour la table `lane`
--
ALTER TABLE `lane`
  ADD CONSTRAINT `lane_ibfk_1` FOREIGN KEY (`id_member`) REFERENCES `utilisateur` (`id_member`),
  ADD CONSTRAINT `lane_ibfk_2` FOREIGN KEY (`id_wall`) REFERENCES `wall` (`id_wall`);

--
-- Contraintes pour la table `mailbox`
--
ALTER TABLE `mailbox`
  ADD CONSTRAINT `mailbox_ibfk_1` FOREIGN KEY (`id_member`) REFERENCES `utilisateur` (`id_member`),
  ADD CONSTRAINT `mailbox_ibfk_2` FOREIGN KEY (`id_album`) REFERENCES `album` (`id_album`) ON DELETE CASCADE;

--
-- Contraintes pour la table `mod_request`
--
ALTER TABLE `mod_request`
  ADD CONSTRAINT `mod_request_ibfk_1` FOREIGN KEY (`id_member`) REFERENCES `utilisateur` (`id_member`);

--
-- Contraintes pour la table `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `photo_ibfk_1` FOREIGN KEY (`id_album`) REFERENCES `album` (`id_album`) ON DELETE CASCADE,
  ADD CONSTRAINT `photo_ibfk_2` FOREIGN KEY (`id_member`) REFERENCES `utilisateur` (`id_member`),
  ADD CONSTRAINT `photo_ibfk_3` FOREIGN KEY (`id_album`) REFERENCES `album` (`id_album`) ON DELETE CASCADE;

--
-- Contraintes pour la table `wall`
--
ALTER TABLE `wall`
  ADD CONSTRAINT `wall_ibfk_1` FOREIGN KEY (`id_member`) REFERENCES `utilisateur` (`id_member`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
