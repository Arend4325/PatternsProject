-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2025 at 07:33 AM
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
  `datePlayed` datetime NOT NULL,
  `totalRounds` int(11) NOT NULL,
  `totalWars` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `games`
--

INSERT INTO `games` (`gameID`, `player1ID`, `player2ID`, `winnerID`, `datePlayed`, `totalRounds`, `totalWars`) VALUES
(1, 1, 2, 2, '2025-11-29 15:57:37', 0, 0),
(2, 1, 2, 1, '2025-11-29 16:03:29', 387, 71),
(3, 1, 2, 2, '2025-11-29 16:24:58', 25, 3),
(4, 1, 2, 1, '2025-11-29 16:26:35', 25, 2),
(5, 1, 2, 2, '2025-11-29 16:31:26', 26, 1),
(6, 1, 2, 1, '2025-11-30 23:30:51', 25, 1),
(7, 1, 2, 1, '2025-12-01 00:57:45', 25, 1),
(8, 1, 2, 2, '2025-12-01 01:14:51', 25, 0),
(9, 4, 1, 4, '2025-12-01 01:27:41', 25, 0);

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
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `games`
--
ALTER TABLE `games`
  MODIFY `gameID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
