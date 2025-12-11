-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2025 at 01:21 AM
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
(9, 4, 1, 4, 0, 0, '2025-12-01 01:27:41', 0),
(10, 4, 2, 4, 0, 0, '2025-12-02 20:19:03', 0),
(11, 4, 2, 2, 0, 0, '2025-12-02 20:21:08', 3),
(12, 4, 2, 4, 0, 0, '2025-12-02 20:22:51', 1),
(13, 4, 1, 1, 8, 12, '2025-12-10 19:18:09', 5),
(14, 4, 2, 2, 12, 13, '2025-12-10 19:20:15', 0);

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
(1, 'John', 'Doe', 'johnd@gmail.com', 5),
(2, 'Sarah', 'Lee', 'sarahlee@gmail.com', 7),
(4, 'Jon', 'Jones', 'jonjufc@yahoo.ca', 3);

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
(128, 9, 1, '8 of Hearts', 'Jack of Hearts', 2, 0),
(129, 9, 2, '10 of Clubs', '8 of Hearts', 1, 0),
(130, 9, 3, '10 of Clubs', 'Jack of Hearts', 2, 0),
(131, 9, 4, '8 of Hearts', '10 of Clubs', 2, 0),
(132, 9, 5, 'Queen of Diamonds', '8 of Hearts', 1, 0),
(133, 9, 6, 'Queen of Diamonds', '10 of Clubs', 1, 0),
(134, 9, 7, 'Queen of Diamonds', 'Jack of Hearts', 1, 0),
(135, 9, 8, 'Queen of Diamonds', '7 of Diamonds', 1, 0),
(136, 9, 9, 'Queen of Diamonds', '2 of Clubs', 1, 0),
(137, 9, 10, 'Queen of Diamonds', '3 of Clubs', 1, 0),
(138, 9, 11, 'Queen of Diamonds', '5 of Hearts', 1, 0),
(139, 9, 12, 'Queen of Diamonds', 'Jack of Diamonds', 1, 0),
(140, 9, 13, 'Queen of Diamonds', '8 of Diamonds', 1, 0),
(141, 9, 14, 'Queen of Diamonds', 'King of Spades', 2, 0),
(142, 9, 15, '8 of Diamonds', 'Queen of Diamonds', 2, 0),
(143, 9, 16, 'Jack of Diamonds', '8 of Diamonds', 1, 0),
(144, 9, 17, 'Jack of Diamonds', 'Queen of Diamonds', 2, 0),
(145, 9, 18, '8 of Diamonds', 'Jack of Diamonds', 2, 0),
(146, 9, 19, '5 of Hearts', '8 of Diamonds', 2, 0),
(147, 9, 20, '3 of Clubs', '5 of Hearts', 2, 0),
(148, 9, 21, '2 of Clubs', '3 of Clubs', 2, 0),
(149, 9, 22, '7 of Diamonds', '2 of Clubs', 1, 0),
(150, 9, 23, '7 of Diamonds', '3 of Clubs', 1, 0),
(151, 9, 24, '7 of Diamonds', '5 of Hearts', 1, 0),
(152, 9, 25, '7 of Diamonds', '8 of Diamonds', 2, 0),
(153, 10, 1, '8 of Diamonds', 'Ace of Clubs', 1, 0),
(154, 10, 2, '8 of Diamonds', 'King of Diamonds', 2, 0),
(155, 10, 3, 'Ace of Clubs', '8 of Diamonds', 2, 0),
(156, 10, 4, '4 of Diamonds', 'Ace of Clubs', 1, 0),
(157, 10, 5, '4 of Diamonds', '8 of Diamonds', 2, 0),
(158, 10, 6, 'Ace of Clubs', '4 of Diamonds', 2, 0),
(159, 10, 7, '5 of Clubs', 'Ace of Clubs', 1, 0),
(160, 10, 8, '5 of Clubs', '4 of Diamonds', 1, 0),
(161, 10, 9, '5 of Clubs', '8 of Diamonds', 2, 0),
(162, 10, 10, '4 of Diamonds', '5 of Clubs', 2, 0),
(163, 10, 11, 'Ace of Clubs', '4 of Diamonds', 2, 0),
(164, 10, 12, 'Jack of Clubs', 'Ace of Clubs', 1, 0),
(165, 10, 13, 'Jack of Clubs', '4 of Diamonds', 1, 0),
(166, 10, 14, 'Jack of Clubs', '5 of Clubs', 1, 0),
(167, 10, 15, 'Jack of Clubs', '8 of Diamonds', 1, 0),
(168, 10, 16, 'Jack of Clubs', 'King of Diamonds', 2, 0),
(169, 10, 17, '8 of Diamonds', 'Jack of Clubs', 2, 0),
(170, 10, 18, '5 of Clubs', '8 of Diamonds', 2, 0),
(171, 10, 19, '4 of Diamonds', '5 of Clubs', 2, 0),
(172, 10, 20, 'Ace of Clubs', '4 of Diamonds', 2, 0),
(173, 10, 21, 'Queen of Spades', 'Ace of Clubs', 1, 0),
(174, 10, 22, 'Queen of Spades', '4 of Diamonds', 1, 0),
(175, 10, 23, 'Queen of Spades', '5 of Clubs', 1, 0),
(176, 10, 24, 'Queen of Spades', '8 of Diamonds', 1, 0),
(177, 10, 25, 'Queen of Spades', 'Jack of Clubs', 1, 0),
(178, 11, 1, '5 of Clubs', '9 of Hearts', 2, 0),
(179, 11, 2, 'Jack of Clubs', '5 of Clubs', 1, 0),
(180, 11, 3, 'Jack of Clubs', '9 of Hearts', 1, 0),
(181, 11, 4, 'Jack of Clubs', 'Queen of Hearts', 2, 0),
(182, 11, 5, '9 of Hearts', 'Jack of Clubs', 2, 0),
(183, 11, 6, '5 of Clubs', '9 of Hearts', 2, 0),
(184, 11, 7, '10 of Clubs', '5 of Clubs', 1, 0),
(185, 11, 8, '10 of Clubs', '9 of Hearts', 1, 0),
(186, 11, 9, '10 of Clubs', 'Jack of Clubs', 2, 0),
(187, 11, 10, '9 of Hearts', '10 of Clubs', 2, 0),
(188, 11, 11, '5 of Clubs', '9 of Hearts', 2, 0),
(189, 11, 12, '7 of Clubs', '5 of Clubs', 1, 0),
(190, 11, 13, '7 of Clubs', '9 of Hearts', 2, 0),
(191, 11, 14, '5 of Clubs', '7 of Clubs', 2, 0),
(192, 11, 15, '4 of Diamonds', '5 of Clubs', 2, 0),
(193, 11, 16, '5 of Diamonds', '4 of Diamonds', 1, 0),
(194, 11, 17, '5 of Diamonds', '5 of Clubs', 0, 1),
(195, 11, 18, '4 of Diamonds', '7 of Clubs', 2, 0),
(196, 11, 19, '7 of Spades', '5 of Diamonds', 1, 0),
(197, 11, 20, '7 of Spades', '5 of Clubs', 1, 0),
(198, 11, 21, '7 of Spades', '4 of Diamonds', 1, 0),
(199, 11, 22, '7 of Spades', '7 of Clubs', 0, 1),
(200, 11, 23, '4 of Diamonds', '9 of Hearts', 2, 0),
(201, 11, 24, '5 of Clubs', '7 of Spades', 2, 0),
(202, 11, 25, '5 of Diamonds', '5 of Clubs', 0, 1),
(203, 12, 1, 'Ace of Spades', '10 of Clubs', 2, 0),
(204, 12, 2, 'King of Spades', 'Ace of Spades', 1, 0),
(205, 12, 3, 'King of Spades', '10 of Clubs', 1, 0),
(206, 12, 4, 'King of Spades', 'King of Diamonds', 0, 1),
(207, 12, 5, '10 of Clubs', 'Joker', 1, 0),
(208, 12, 6, 'King of Spades', '6 of Spades', 1, 0),
(209, 12, 7, 'King of Spades', '9 of Hearts', 1, 0),
(210, 12, 8, 'King of Spades', '8 of Spades', 1, 0),
(211, 12, 9, 'King of Spades', '5 of Hearts', 1, 0),
(212, 12, 10, 'King of Spades', 'Ace of Hearts', 1, 0),
(213, 12, 11, 'King of Spades', '9 of Diamonds', 1, 0),
(214, 12, 12, 'King of Spades', 'Jack of Spades', 1, 0),
(215, 12, 13, 'King of Spades', 'Queen of Clubs', 1, 0),
(216, 12, 14, 'King of Spades', '7 of Spades', 1, 0),
(217, 12, 15, 'King of Spades', '3 of Spades', 1, 0),
(218, 12, 16, 'King of Spades', '3 of Hearts', 1, 0),
(219, 12, 17, 'King of Spades', '8 of Clubs', 1, 0),
(220, 12, 18, 'King of Spades', '2 of Spades', 1, 0),
(221, 12, 19, 'King of Spades', '2 of Diamonds', 1, 0),
(222, 12, 20, 'King of Spades', '4 of Spades', 1, 0),
(223, 12, 21, 'King of Spades', '4 of Hearts', 1, 0),
(224, 12, 22, 'King of Spades', 'Ace of Diamonds', 1, 0),
(225, 12, 23, 'King of Spades', '8 of Hearts', 1, 0),
(226, 12, 24, 'King of Spades', '6 of Hearts', 1, 0),
(227, 12, 25, 'King of Spades', '3 of Clubs', 1, 0),
(228, 13, 1, 'Jack of Hearts', '9 of Hearts', 1, 0),
(229, 13, 2, 'Jack of Hearts', 'Queen of Spades', 2, 0),
(230, 13, 3, '9 of Hearts', 'Jack of Hearts', 2, 0),
(231, 13, 4, '3 of Clubs', '9 of Hearts', 2, 0),
(232, 13, 5, 'Jack of Spades', '3 of Clubs', 1, 0),
(233, 13, 6, 'Jack of Spades', '9 of Hearts', 1, 0),
(234, 13, 7, 'Jack of Spades', 'Jack of Hearts', 0, 1),
(235, 13, 8, '9 of Hearts', 'Queen of Spades', 2, 0),
(236, 13, 9, '3 of Clubs', 'Jack of Spades', 2, 0),
(237, 13, 10, '6 of Clubs', '3 of Clubs', 1, 0),
(238, 13, 11, '6 of Clubs', 'Jack of Spades', 2, 0),
(239, 13, 12, '3 of Clubs', '6 of Clubs', 2, 0),
(240, 13, 13, '3 of Diamonds', '3 of Clubs', 0, 1),
(241, 13, 14, '10 of Diamonds', '6 of Clubs', 1, 0),
(242, 13, 15, '3 of Diamonds', 'Jack of Spades', 2, 0),
(243, 13, 16, '3 of Clubs', '3 of Diamonds', 0, 1),
(244, 13, 17, '10 of Diamonds', 'Jack of Spades', 2, 0),
(245, 13, 18, '6 of Clubs', '3 of Clubs', 1, 0),
(246, 13, 19, '6 of Clubs', '3 of Diamonds', 1, 0),
(247, 13, 20, '6 of Clubs', '10 of Diamonds', 2, 0),
(248, 13, 21, '3 of Diamonds', '6 of Clubs', 2, 0),
(249, 13, 22, '3 of Clubs', '3 of Diamonds', 0, 1),
(250, 13, 23, '6 of Spades', '6 of Clubs', 0, 1),
(251, 13, 24, 'Jack of Clubs', '10 of Diamonds', 1, 0),
(252, 13, 25, '3 of Clubs', 'Jack of Spades', 2, 0),
(253, 14, 1, '5 of Clubs', '3 of Hearts', 1, 0),
(254, 14, 2, '5 of Clubs', '2 of Spades', 1, 0),
(255, 14, 3, '5 of Clubs', 'King of Diamonds', 2, 0),
(256, 14, 4, '2 of Spades', '5 of Clubs', 2, 0),
(257, 14, 5, '3 of Hearts', '2 of Spades', 1, 0),
(258, 14, 6, '3 of Hearts', '5 of Clubs', 2, 0),
(259, 14, 7, '2 of Spades', '3 of Hearts', 2, 0),
(260, 14, 8, '10 of Spades', '2 of Spades', 1, 0),
(261, 14, 9, '10 of Spades', '3 of Hearts', 1, 0),
(262, 14, 10, '10 of Spades', '5 of Clubs', 1, 0),
(263, 14, 11, '10 of Spades', 'King of Diamonds', 2, 0),
(264, 14, 12, '5 of Clubs', '10 of Spades', 2, 0),
(265, 14, 13, '3 of Hearts', '5 of Clubs', 2, 0),
(266, 14, 14, '2 of Spades', '3 of Hearts', 2, 0),
(267, 14, 15, '8 of Clubs', '2 of Spades', 1, 0),
(268, 14, 16, '8 of Clubs', '3 of Hearts', 1, 0),
(269, 14, 17, '8 of Clubs', '5 of Clubs', 1, 0),
(270, 14, 18, '8 of Clubs', '10 of Spades', 2, 0),
(271, 14, 19, '5 of Clubs', '8 of Clubs', 2, 0),
(272, 14, 20, '3 of Hearts', '5 of Clubs', 2, 0),
(273, 14, 21, '2 of Spades', '3 of Hearts', 2, 0),
(274, 14, 22, '6 of Spades', '2 of Spades', 1, 0),
(275, 14, 23, '6 of Spades', '3 of Hearts', 1, 0),
(276, 14, 24, '6 of Spades', '5 of Clubs', 1, 0),
(277, 14, 25, '6 of Spades', '8 of Clubs', 2, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`gameID`),
  ADD KEY `player1ID` (`player1ID`),
  ADD KEY `player2ID` (`player2ID`),
  ADD KEY `winnerID` (`winnerID`);

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
  MODIFY `gameID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `players`
--
ALTER TABLE `players`
  MODIFY `playerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `rounds`
--
ALTER TABLE `rounds`
  MODIFY `roundID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=278;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `games`
--
ALTER TABLE `games`
  ADD CONSTRAINT `games_ibfk_1` FOREIGN KEY (`player1ID`) REFERENCES `players` (`playerID`),
  ADD CONSTRAINT `games_ibfk_2` FOREIGN KEY (`player2ID`) REFERENCES `players` (`playerID`),
  ADD CONSTRAINT `games_ibfk_3` FOREIGN KEY (`winnerID`) REFERENCES `players` (`playerID`);

--
-- Constraints for table `rounds`
--
ALTER TABLE `rounds`
  ADD CONSTRAINT `rounds_ibfk_1` FOREIGN KEY (`gameID`) REFERENCES `games` (`gameID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
