-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2023 at 12:29 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `minecraft`
--

-- --------------------------------------------------------

--
-- Table structure for table `accountpunishments`
--

CREATE TABLE `accountpunishments` (
  `id` int(11) NOT NULL,
  `type` text NOT NULL,
  `caller` text NOT NULL,
  `target` text NOT NULL,
  `reason` text NOT NULL,
  `timestamp` bigint(11) NOT NULL,
  `expires` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `username` text NOT NULL,
  `uuid` text NOT NULL,
  `rank` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `playerwarps`
--

CREATE TABLE `playerwarps` (
  `id` int(11) NOT NULL,
  `owned_by_uuid` text NOT NULL,
  `name` text NOT NULL,
  `world` text NOT NULL,
  `x` double NOT NULL,
  `y` double NOT NULL,
  `z` double NOT NULL,
  `yaw` double NOT NULL,
  `pitch` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `serverlistdata`
--

CREATE TABLE `serverlistdata` (
  `id` int(11) NOT NULL,
  `server` text NOT NULL,
  `lineone` text NOT NULL,
  `linetwo` text NOT NULL,
  `maxplayers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `serverlistdata`
--

INSERT INTO `serverlistdata` (`id`, `server`, `lineone`, `linetwo`, `maxplayers`) VALUES
(1, 'Lobby-1', '                §b§l§m   §8§l§m[ §r §9§lSunshine§r §f§lValley§r §8§l§m ]§b§l§m   §r', '§f                 Closed for development', 69);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accountpunishments`
--
ALTER TABLE `accountpunishments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `playerwarps`
--
ALTER TABLE `playerwarps`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `serverlistdata`
--
ALTER TABLE `serverlistdata`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accountpunishments`
--
ALTER TABLE `accountpunishments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `playerwarps`
--
ALTER TABLE `playerwarps`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `serverlistdata`
--
ALTER TABLE `serverlistdata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
