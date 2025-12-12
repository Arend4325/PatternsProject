-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2025 at 06:47 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `war_game_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `games`
--

CREATE TABLE `games` (
  `gameID` int(11) NOT NULL,
  `player1ID` int(11) NOT NULL,
  `player2ID` int(11) NOT NULL,
  `winnerID` int(11) NOT NULL,
  `p1RoundWins` int(11) NOT NULL DEFAULT 0,
  `p2RoundWins` int(11) NOT NULL DEFAULT 0,
  `datePlayed` datetime NOT NULL,
  `totalWars` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `games`
--

INSERT INTO `games` (`gameID`, `player1ID`, `player2ID`, `winnerID`, `p1RoundWins`, `p2RoundWins`, `datePlayed`, `totalWars`) VALUES
(1, 1, 2, 2, 0, 0, '2025-11-29 15:57:37', 0),
(2, 1, 2, 1, 0, 0, '2025-11-29 16:03:29', 71),
(3, 1, 2, 2, 0, 0, '2025-11-29 16:24:58', 3),
(4, 1, 2, 1, 0, 0, '2025-11-29 16:26:35', 2),
(5, 1, 2, 2, 0, 0, '2025-11-29 16:31:26', 1),
(6, 1, 2, 1, 0, 0, '2025-11-30 23:30:51', 1),
(7, 1, 2, 1, 0, 0, '2025-12-01 00:57:45', 1),
(8, 1, 2, 2, 0, 0, '2025-12-01 01:14:51', 0),
(15, 1, 2, 1, 15, 9, '2025-12-12 00:29:43', 1),
(16, 1, 2, 1, 14, 10, '2025-12-12 00:41:34', 1),
(17, 1, 2, 2, 11, 12, '2025-12-12 00:43:51', 2),
(18, 1, 2, 2, 10, 14, '2025-12-12 00:45:40', 1);

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

CREATE TABLE `players` (
  `playerID` int(11) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `totalWins` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`playerID`, `firstName`, `lastName`, `email`, `totalWins`) VALUES
(1, 'John', 'Doe', 'johnd@gmail.com', 7),
(2, 'Sarah', 'Lee', 'sarahlee@gmail.com', 9);

-- --------------------------------------------------------

--
-- Table structure for table `rounds`
--

CREATE TABLE `rounds` (
  `roundID` int(11) NOT NULL,
  `gameID` int(11) NOT NULL,
  `roundNumber` int(11) NOT NULL,
  `player1Card` varchar(50) NOT NULL,
  `player2Card` varchar(50) NOT NULL,
  `roundWinner` int(11) DEFAULT NULL,
  `isWarRound` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rounds`
--

INSERT INTO `rounds` (`roundID`, `gameID`, `roundNumber`, `player1Card`, `player2Card`, `roundWinner`, `isWarRound`) VALUES
(1, 3, 12, '8 of Clubs', '3 of Clubs', 1, 0),
(2, 4, 1, 'Queen of Clubs', '3 of Clubs', 1, 0),
(3, 4, 2, 'Queen of Clubs', 'Jack of Hearts', 1, 0),
(4, 4, 3, 'Queen of Clubs', '5 of Spades', 1, 0),
(6, 4, 5, '5 of Spades', '3 of Diamonds', 1, 0),
(7, 4, 6, 'Queen of Clubs', '4 of Spades', 1, 0),
(8, 4, 7, 'Queen of Clubs', '4 of Diamonds', 1, 0),
(9, 4, 8, 'Queen of Clubs', '9 of Spades', 1, 0),
(10, 4, 9, 'Queen of Clubs', '7 of Clubs', 1, 0),
(11, 4, 10, 'Queen of Clubs', '4 of Hearts', 1, 0),
(12, 4, 11, 'Queen of Clubs', 'Jack of Diamonds', 1, 0),
(13, 4, 12, 'Queen of Clubs', 'King of Diamonds', 2, 0),
(14, 4, 13, 'Jack of Diamonds', 'Queen of Clubs', 2, 0),
(15, 4, 14, '4 of Hearts', 'Jack of Diamonds', 2, 0),
(16, 4, 15, '7 of Clubs', '4 of Hearts', 1, 0),
(17, 4, 16, '7 of Clubs', 'Jack of Diamonds', 2, 0),
(18, 4, 17, '4 of Hearts', '7 of Clubs', 2, 0),
(19, 4, 18, '9 of Spades', '4 of Hearts', 1, 0),
(20, 4, 19, '9 of Spades', '7 of Clubs', 1, 0),
(21, 4, 20, '9 of Spades', 'Jack of Diamonds', 2, 0),
(22, 4, 21, '7 of Clubs', '9 of Spades', 2, 0),
(23, 4, 22, '4 of Hearts', '7 of Clubs', 2, 0),
(25, 4, 24, '4 of Spades', '7 of Clubs', 2, 0),
(26, 4, 25, 'Queen of Diamonds', '4 of Diamonds', 1, 0),
(27, 5, 1, 'Ace of Diamonds', '5 of Hearts', 2, 0),
(28, 5, 2, '2 of Clubs', 'Ace of Diamonds', 1, 0),
(29, 5, 3, '2 of Clubs', '5 of Hearts', 2, 0),
(30, 5, 4, 'Ace of Diamonds', '2 of Clubs', 2, 0),
(31, 5, 5, '6 of Clubs', 'Ace of Diamonds', 1, 0),
(32, 5, 6, '6 of Clubs', '2 of Clubs', 1, 0),
(33, 5, 7, '6 of Clubs', '5 of Hearts', 1, 0),
(34, 5, 8, '6 of Clubs', 'Queen of Spades', 2, 0),
(35, 5, 9, '5 of Hearts', '6 of Clubs', 2, 0),
(36, 5, 10, '2 of Clubs', '5 of Hearts', 2, 0),
(37, 5, 11, 'Ace of Diamonds', '2 of Clubs', 2, 0),
(38, 5, 12, '8 of Spades', 'Ace of Diamonds', 1, 0),
(39, 5, 13, '8 of Spades', '2 of Clubs', 1, 0),
(40, 5, 14, '8 of Spades', '5 of Hearts', 1, 0),
(41, 5, 15, '8 of Spades', '6 of Clubs', 1, 0),
(42, 5, 16, '8 of Spades', 'Queen of Spades', 2, 0),
(43, 5, 17, '6 of Clubs', '8 of Spades', 2, 0),
(44, 5, 18, '5 of Hearts', '6 of Clubs', 2, 0),
(45, 5, 19, '2 of Clubs', '5 of Hearts', 2, 0),
(46, 5, 20, 'Ace of Diamonds', '2 of Clubs', 2, 0),
(47, 5, 21, '8 of Clubs', 'Ace of Diamonds', 1, 0),
(48, 5, 22, '8 of Clubs', '2 of Clubs', 1, 0),
(49, 5, 23, '8 of Clubs', '5 of Hearts', 1, 0),
(50, 5, 24, '8 of Clubs', '6 of Clubs', 1, 0),
(51, 5, 25, '8 of Clubs', '8 of Spades', 0, 1),
(52, 5, 26, '6 of Clubs', 'Queen of Spades', 2, 0),
(53, 6, 1, 'King of Clubs', 'Ace of Clubs', 1, 0),
(54, 6, 2, 'King of Clubs', 'Ace of Diamonds', 1, 0),
(55, 6, 3, 'King of Clubs', 'Joker', 1, 0),
(56, 6, 4, 'King of Clubs', '2 of Hearts', 1, 0),
(57, 6, 5, 'King of Clubs', '4 of Spades', 1, 0),
(58, 6, 6, 'King of Clubs', '9 of Clubs', 1, 0),
(59, 6, 7, 'King of Clubs', '3 of Diamonds', 1, 0),
(60, 6, 8, 'King of Clubs', '10 of Hearts', 1, 0),
(61, 6, 9, 'King of Clubs', '9 of Hearts', 1, 0),
(62, 6, 10, 'King of Clubs', 'Jack of Spades', 1, 0),
(63, 6, 11, 'King of Clubs', '10 of Spades', 1, 0),
(64, 6, 12, 'King of Clubs', '9 of Spades', 1, 0),
(65, 6, 13, 'King of Clubs', 'Queen of Spades', 1, 0),
(66, 6, 14, 'King of Clubs', '8 of Clubs', 1, 0),
(67, 6, 15, 'King of Clubs', '7 of Diamonds', 1, 0),
(68, 6, 16, 'King of Clubs', '2 of Clubs', 1, 0),
(69, 6, 17, 'King of Clubs', '4 of Clubs', 1, 0),
(70, 6, 18, 'King of Clubs', '4 of Diamonds', 1, 0),
(71, 6, 19, 'King of Clubs', 'Jack of Diamonds', 1, 0),
(72, 6, 20, 'King of Clubs', '3 of Clubs', 1, 0),
(73, 6, 21, 'King of Clubs', '3 of Hearts', 1, 0),
(74, 6, 22, 'King of Clubs', 'King of Hearts', 0, 1),
(75, 6, 23, '3 of Hearts', '7 of Hearts', 2, 0),
(76, 6, 24, '3 of Clubs', 'King of Clubs', 2, 0),
(77, 6, 25, 'Jack of Diamonds', '3 of Clubs', 1, 0),
(78, 7, 1, '4 of Diamonds', '8 of Diamonds', 2, 0),
(79, 7, 2, '6 of Hearts', '4 of Diamonds', 1, 0),
(80, 7, 3, '6 of Hearts', '8 of Diamonds', 2, 0),
(81, 7, 4, '4 of Diamonds', '6 of Hearts', 2, 0),
(82, 7, 5, 'Ace of Spades', '4 of Diamonds', 2, 0),
(83, 7, 6, 'King of Diamonds', 'Ace of Spades', 1, 0),
(84, 7, 7, 'King of Diamonds', '4 of Diamonds', 1, 0),
(85, 7, 8, 'King of Diamonds', '6 of Hearts', 1, 0),
(86, 7, 9, 'King of Diamonds', '8 of Diamonds', 1, 0),
(87, 7, 10, 'King of Diamonds', 'King of Clubs', 0, 1),
(88, 7, 11, '8 of Diamonds', 'Joker', 1, 0),
(89, 7, 12, 'King of Diamonds', 'Jack of Clubs', 1, 0),
(90, 7, 13, 'King of Diamonds', 'Jack of Hearts', 1, 0),
(91, 7, 14, 'King of Diamonds', '7 of Spades', 1, 0),
(92, 7, 15, 'King of Diamonds', '2 of Clubs', 1, 0),
(93, 7, 16, 'King of Diamonds', '7 of Diamonds', 1, 0),
(94, 7, 17, 'King of Diamonds', 'Ace of Clubs', 1, 0),
(95, 7, 18, 'King of Diamonds', '3 of Diamonds', 1, 0),
(96, 7, 19, 'King of Diamonds', '8 of Spades', 1, 0),
(97, 7, 20, 'King of Diamonds', '7 of Clubs', 1, 0),
(98, 7, 21, 'King of Diamonds', '6 of Clubs', 1, 0),
(99, 7, 22, 'King of Diamonds', '9 of Clubs', 1, 0),
(100, 7, 23, 'King of Diamonds', '9 of Spades', 1, 0),
(101, 7, 24, 'King of Diamonds', '2 of Spades', 1, 0),
(102, 7, 25, 'King of Diamonds', '3 of Clubs', 1, 0),
(103, 8, 1, '3 of Clubs', 'Queen of Hearts', 2, 0),
(104, 8, 2, 'Ace of Hearts', '3 of Clubs', 2, 0),
(105, 8, 3, '6 of Spades', 'Ace of Hearts', 1, 0),
(106, 8, 4, '6 of Spades', '3 of Clubs', 1, 0),
(107, 8, 5, '6 of Spades', 'Queen of Hearts', 2, 0),
(108, 8, 6, '3 of Clubs', '6 of Spades', 2, 0),
(109, 8, 7, 'Ace of Hearts', '3 of Clubs', 2, 0),
(110, 8, 8, 'Jack of Clubs', 'Ace of Hearts', 1, 0),
(111, 8, 9, 'Jack of Clubs', '3 of Clubs', 1, 0),
(112, 8, 10, 'Jack of Clubs', '6 of Spades', 1, 0),
(113, 8, 11, 'Jack of Clubs', 'Queen of Hearts', 2, 0),
(114, 8, 12, '6 of Spades', 'Jack of Clubs', 2, 0),
(115, 8, 13, '3 of Clubs', '6 of Spades', 2, 0),
(116, 8, 14, 'Ace of Hearts', '3 of Clubs', 2, 0),
(117, 8, 15, '5 of Diamonds', 'Ace of Hearts', 1, 0),
(118, 8, 16, '5 of Diamonds', '3 of Clubs', 1, 0),
(119, 8, 17, '5 of Diamonds', '6 of Spades', 2, 0),
(120, 8, 18, '3 of Clubs', '5 of Diamonds', 2, 0),
(121, 8, 19, 'Ace of Hearts', '3 of Clubs', 2, 0),
(122, 8, 20, '2 of Diamonds', 'Ace of Hearts', 1, 0),
(123, 8, 21, '2 of Diamonds', '3 of Clubs', 2, 0),
(124, 8, 22, 'Ace of Hearts', '2 of Diamonds', 2, 0),
(125, 8, 23, 'Queen of Clubs', 'Ace of Hearts', 1, 0),
(126, 8, 24, 'Queen of Clubs', '2 of Diamonds', 1, 0),
(127, 8, 25, 'Queen of Clubs', '3 of Clubs', 1, 0),
(278, 15, 1, 'Jack of Hearts', 'King of Hearts', 2, 0),
(279, 15, 2, '6 of Hearts', 'Jack of Hearts', 2, 0),
(280, 15, 3, '9 of Hearts', '6 of Hearts', 1, 0),
(281, 15, 4, '6 of Hearts', 'Jack of Hearts', 2, 0),
(282, 15, 5, '9 of Hearts', '6 of Hearts', 1, 0),
(283, 15, 6, '9 of Hearts', 'Jack of Hearts', 2, 0),
(284, 15, 7, '6 of Hearts', '9 of Hearts', 2, 0),
(285, 15, 8, 'King of Spades', '9 of Hearts', 1, 0),
(286, 15, 9, '9 of Hearts', '6 of Hearts', 1, 0),
(287, 15, 10, '6 of Hearts', 'Jack of Hearts', 2, 0),
(288, 15, 11, '9 of Hearts', 'Jack of Hearts', 2, 0),
(289, 15, 12, 'King of Spades', '9 of Hearts', 1, 0),
(290, 15, 13, '9 of Hearts', 'Jack of Hearts', 2, 0),
(291, 15, 14, 'King of Spades', 'Jack of Hearts', 1, 0),
(292, 15, 15, 'King of Spades', '9 of Hearts', 1, 0),
(293, 15, 16, 'King of Spades', '6 of Hearts', 1, 0),
(294, 15, 17, 'King of Spades', 'King of Hearts', 0, 1),
(295, 15, 18, '6 of Hearts', '2 of Clubs', 1, 0),
(296, 15, 19, 'King of Hearts', '3 of Clubs', 1, 0),
(297, 15, 20, 'King of Hearts', 'Joker', 1, 0),
(298, 15, 21, 'King of Hearts', '2 of Diamonds', 1, 0),
(299, 15, 22, 'King of Hearts', '7 of Clubs', 1, 0),
(300, 15, 23, 'King of Hearts', 'Ace of Clubs', 1, 0),
(301, 15, 24, 'Ace of Clubs', '9 of Diamonds', 2, 0),
(302, 15, 25, 'King of Hearts', 'Ace of Clubs', 1, 0),
(303, 16, 1, 'Ace of Spades', '7 of Diamonds', 2, 0),
(304, 16, 2, '4 of Clubs', 'Ace of Spades', 1, 0),
(305, 16, 3, 'Ace of Spades', '7 of Diamonds', 2, 0),
(306, 16, 4, '4 of Clubs', 'Ace of Spades', 1, 0),
(307, 16, 5, 'Ace of Spades', '7 of Diamonds', 2, 0),
(308, 16, 6, '4 of Clubs', '7 of Diamonds', 2, 0),
(309, 16, 7, '2 of Spades', '7 of Diamonds', 2, 0),
(310, 16, 8, 'Queen of Diamonds', '2 of Spades', 1, 0),
(311, 16, 9, '2 of Spades', '7 of Diamonds', 2, 0),
(312, 16, 10, 'Queen of Diamonds', '2 of Spades', 1, 0),
(313, 16, 11, 'Queen of Diamonds', '7 of Diamonds', 1, 0),
(314, 16, 12, 'Queen of Diamonds', '4 of Clubs', 1, 0),
(315, 16, 13, 'Queen of Diamonds', 'Ace of Spades', 1, 0),
(316, 16, 14, 'Ace of Spades', 'Queen of Hearts', 2, 0),
(317, 16, 15, 'Queen of Diamonds', 'Queen of Hearts', 0, 1),
(318, 16, 16, '4 of Clubs', 'Ace of Spades', 1, 0),
(319, 16, 17, '4 of Clubs', '6 of Diamonds', 2, 0),
(320, 16, 18, 'Queen of Hearts', '4 of Clubs', 1, 0),
(321, 16, 19, '4 of Clubs', '6 of Diamonds', 2, 0),
(322, 16, 20, 'Queen of Hearts', '6 of Diamonds', 1, 0),
(323, 16, 21, 'Queen of Hearts', '4 of Clubs', 1, 0),
(324, 16, 22, 'Queen of Hearts', 'Jack of Hearts', 1, 0),
(325, 16, 23, 'Jack of Hearts', 'Joker', 1, 0),
(326, 16, 24, 'Joker', '3 of Spades', 2, 0),
(327, 16, 25, 'Jack of Hearts', '3 of Spades', 1, 0),
(328, 17, 1, '9 of Clubs', '4 of Clubs', 1, 0),
(329, 17, 2, '9 of Clubs', '7 of Spades', 1, 0),
(330, 17, 3, '9 of Clubs', 'King of Spades', 2, 0),
(331, 17, 4, '7 of Spades', '9 of Clubs', 2, 0),
(332, 17, 5, '4 of Clubs', '7 of Spades', 2, 0),
(333, 17, 6, '4 of Diamonds', '4 of Clubs', 0, 1),
(334, 17, 7, 'Joker', '7 of Spades', 2, 0),
(335, 17, 8, '5 of Diamonds', '4 of Diamonds', 1, 0),
(336, 17, 9, '5 of Diamonds', '4 of Clubs', 1, 0),
(337, 17, 10, '5 of Diamonds', 'Joker', 1, 0),
(338, 17, 11, '5 of Diamonds', '7 of Spades', 2, 0),
(339, 17, 12, 'Joker', '5 of Diamonds', 2, 0),
(340, 17, 13, '4 of Clubs', 'Joker', 1, 0),
(341, 17, 14, '4 of Clubs', '5 of Diamonds', 2, 0),
(342, 17, 15, 'Joker', '4 of Clubs', 2, 0),
(343, 17, 16, '4 of Diamonds', 'Joker', 1, 0),
(344, 17, 17, '4 of Diamonds', '4 of Clubs', 0, 1),
(345, 17, 18, 'Joker', '5 of Diamonds', 2, 0),
(346, 17, 19, '6 of Clubs', '4 of Diamonds', 1, 0),
(347, 17, 20, '6 of Clubs', '4 of Clubs', 1, 0),
(348, 17, 21, '6 of Clubs', 'Joker', 1, 0),
(349, 17, 22, '6 of Clubs', '5 of Diamonds', 1, 0),
(350, 17, 23, '6 of Clubs', '7 of Spades', 2, 0),
(351, 17, 24, '5 of Diamonds', '6 of Clubs', 2, 0),
(352, 17, 25, 'Joker', '5 of Diamonds', 2, 0),
(353, 18, 1, '7 of Hearts', 'Joker', 1, 0),
(354, 18, 2, '5 of Spades', '5 of Clubs', 0, 1),
(355, 18, 3, '10 of Hearts', '8 of Diamonds', 1, 0),
(356, 18, 4, '8 of Hearts', 'Jack of Hearts', 2, 0),
(357, 18, 5, '2 of Spades', 'King of Spades', 2, 0),
(358, 18, 6, '5 of Hearts', '3 of Clubs', 1, 0),
(359, 18, 7, '2 of Clubs', '4 of Spades', 2, 0),
(360, 18, 8, '8 of Spades', 'Jack of Diamonds', 2, 0),
(361, 18, 9, 'Ace of Hearts', '9 of Diamonds', 2, 0),
(362, 18, 10, 'Joker', '7 of Diamonds', 2, 0),
(363, 18, 11, 'King of Diamonds', 'Ace of Spades', 1, 0),
(364, 18, 12, '3 of Spades', '6 of Hearts', 2, 0),
(365, 18, 13, 'Ace of Clubs', '2 of Hearts', 2, 0),
(366, 18, 14, 'King of Hearts', '7 of Clubs', 1, 0),
(367, 18, 15, '3 of Hearts', '5 of Diamonds', 2, 0),
(368, 18, 16, '6 of Diamonds', '2 of Diamonds', 1, 0),
(369, 18, 17, '10 of Spades', '4 of Diamonds', 1, 0),
(370, 18, 18, '4 of Clubs', '7 of Spades', 2, 0),
(371, 18, 19, '9 of Clubs', 'Queen of Clubs', 2, 0),
(372, 18, 20, '10 of Diamonds', 'King of Clubs', 2, 0),
(373, 18, 21, 'Queen of Spades', 'Jack of Clubs', 1, 0),
(374, 18, 22, '9 of Hearts', '4 of Hearts', 1, 0),
(375, 18, 23, 'Jack of Spades', '3 of Diamonds', 1, 0),
(376, 18, 24, '6 of Spades', 'Queen of Hearts', 2, 0),
(377, 18, 25, '6 of Clubs', '9 of Spades', 2, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`gameID`),
  ADD KEY `games_ibfk_1` (`player1ID`),
  ADD KEY `games_ibfk_2` (`player2ID`),
  ADD KEY `games_ibfk_3` (`winnerID`);

--
-- Indexes for table `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`playerID`);

--
-- Indexes for table `rounds`
--
ALTER TABLE `rounds`
  ADD PRIMARY KEY (`roundID`),
  ADD KEY `roundWinner` (`roundWinner`),
  ADD KEY `rounds_ibfk_1` (`gameID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `games`
--
ALTER TABLE `games`
  MODIFY `gameID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `players`
--
ALTER TABLE `players`
  MODIFY `playerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `rounds`
--
ALTER TABLE `rounds`
  MODIFY `roundID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=378;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `games`
--
ALTER TABLE `games`
  ADD CONSTRAINT `games_ibfk_1` FOREIGN KEY (`player1ID`) REFERENCES `players` (`playerID`) ON DELETE CASCADE,
  ADD CONSTRAINT `games_ibfk_2` FOREIGN KEY (`player2ID`) REFERENCES `players` (`playerID`) ON DELETE CASCADE,
  ADD CONSTRAINT `games_ibfk_3` FOREIGN KEY (`winnerID`) REFERENCES `players` (`playerID`) ON DELETE CASCADE;

--
-- Constraints for table `rounds`
--
ALTER TABLE `rounds`
  ADD CONSTRAINT `rounds_ibfk_1` FOREIGN KEY (`gameID`) REFERENCES `games` (`gameID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
