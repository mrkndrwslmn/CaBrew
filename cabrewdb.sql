-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2024 at 08:36 AM
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
-- Database: `cabrewdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `accountdetail`
--

CREATE TABLE `accountdetail` (
  `username` varchar(210) NOT NULL,
  `name` varchar(210) NOT NULL,
  `password` varchar(210) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accountdetail`
--

INSERT INTO `accountdetail` (`username`, `name`, `password`) VALUES
('mrkndrwslmn', 'Mark Andrew Soliman', '12345'),
('shann', 'Shann Azucena', '12345'),
('sofia', 'Sofia Gonzaga', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `dateAndTime` datetime NOT NULL,
  `productName` varchar(210) NOT NULL,
  `productCategory` varchar(210) NOT NULL,
  `productTotalPrice` int(11) NOT NULL,
  `cashier` varchar(210) NOT NULL,
  `productSize` varchar(210) NOT NULL,
  `price` int(11) NOT NULL,
  `productQuantity` int(11) NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`dateAndTime`, `productName`, `productCategory`, `productTotalPrice`, `cashier`, `productSize`, `price`, `productQuantity`, `order_id`) VALUES
('2024-05-20 15:18:48', 'Four Seasons SUP', 'Fruit Soda Suprema', 49, 'mrkndrwslmn', '16 OZ', 49, 1, 954251),
('2024-05-20 15:42:42', 'Mango', 'Milktea Suprema', 98, 'mrkndrwslmn', '16 OZ', 49, 1, 999659),
('2024-05-20 15:42:42', 'Matcha', 'Milktea Suprema', 98, 'mrkndrwslmn', '16 OZ', 49, 1, 999659),
('2024-05-20 15:52:57', 'Matcha', 'Milktea Suprema', 98, 'mrkndrwslmn', '16 OZ', 49, 1, 495992),
('2024-05-20 15:52:57', 'Caramel', 'Milktea Suprema', 98, 'mrkndrwslmn', '16 OZ', 49, 1, 495992),
('2024-05-20 15:57:45', 'Chocolate', 'Coffee/Choco', 98, 'mrkndrwslmn', '16 OZ', 49, 1, 814564),
('2024-05-20 15:57:45', 'Krema Caramel', 'Coffee/Choco', 98, 'mrkndrwslmn', '16 OZ', 49, 1, 814564),
('2024-05-20 15:59:01', 'Krema Caramel', 'Coffee/Choco', 98, 'mrkndrwslmn', '16 OZ', 49, 1, 495533),
('2024-05-20 15:59:01', 'Matcha', 'Frappe', 98, 'mrkndrwslmn', '16 OZ', 49, 1, 495533),
('2024-05-20 18:58:40', 'Strawberry CL', 'Fruit Tea Classica', 58, 'mrkndrwslmn', '16 OZ', 29, 1, 465318),
('2024-05-20 18:58:40', 'Green Lemonade CL', 'Fruit Tea Classica', 58, 'mrkndrwslmn', '16 OZ', 29, 1, 465318),
('2024-05-20 20:25:28', 'Milky Strawberry', 'Frappe', 165, 'mrkndrwslmn', '16 OZ', 55, 1, 514744),
('2024-05-20 20:25:28', 'Matcha', 'Frappe', 165, 'mrkndrwslmn', '16 OZ', 55, 1, 514744),
('2024-05-20 20:25:28', 'Oreo and Cream', 'Frappe', 165, 'mrkndrwslmn', '16 OZ', 55, 1, 514744),
('2024-05-20 20:48:31', 'Chocolate', 'Coffee/Choco', 88, 'mrkndrwslmn', '16 OZ', 49, 1, 930110),
('2024-05-20 20:48:31', 'Krema Caramel', 'Coffee/Choco', 88, 'mrkndrwslmn', '8 OZ', 39, 1, 930110),
('2023-05-20 15:18:48', 'Mango', 'Milktea Suprema', 98, 'mrkndrwslmn', '16 OZ', 49, 1, 998786),
('2024-05-13 08:15:00', 'Hazelnut', 'Milktea Classica', 39, 'mrkndrwslmn', '16 OZ', 29, 1, 123456),
('2024-05-14 10:30:00', 'Matcha', 'Milktea Suprema', 49, 'mrkndrwslmn', '22 OZ', 49, 1, 234567),
('2024-05-15 13:45:00', 'Mocha', 'Coffee/Choco', 39, 'mrkndrwslmn', '8 OZ', 39, 1, 345678),
('2024-05-16 16:00:00', 'Lychee', 'Fruit Tea Classica', 29, 'mrkndrwslmn', '16 OZ', 29, 1, 456789),
('2024-05-17 18:15:00', 'Blue Lemonade', 'Fruit Soda Suprema', 49, 'mrkndrwslmn', '22 OZ', 49, 1, 567890),
('2024-05-08 08:15:00', 'Vanilla', 'Milktea Classica', 29, 'mrkndrwslmn', '8 OZ', 29, 1, 654321),
('2024-05-09 10:30:00', 'Cappuccino', 'Coffee/Choco', 39, 'mrkndrwslmn', '16 OZ', 39, 1, 765432),
('2024-05-10 13:45:00', 'Strawberry', 'Fruit Tea Classica', 29, 'mrkndrwslmn', '22 OZ', 29, 1, 876543),
('2024-05-11 16:00:00', 'Blueberry', 'Fruit Soda Suprema', 49, 'mrkndrwslmn', '8 OZ', 49, 1, 987654),
('2024-05-12 18:15:00', 'Dark Chocolate', 'Milktea Classica', 39, 'mrkndrwslmn', '16 OZ', 39, 1, 98765),
('2024-04-30 08:15:00', 'Mango Graham', 'Frappe', 55, 'mrkndrwslmn', '22 OZ', 55, 1, 987654),
('2024-05-01 10:30:00', 'Dalgona', 'Milktea Suprema', 49, 'mrkndrwslmn', '8 OZ', 49, 1, 876543),
('2024-05-02 13:45:00', 'Milo Krunch', 'Frappe', 55, 'mrkndrwslmn', '16 OZ', 55, 1, 765432),
('2024-05-03 16:00:00', 'Four Seasons', 'Fruit Soda Suprema', 49, 'mrkndrwslmn', '22 OZ', 49, 1, 654321),
('2024-05-04 18:15:00', 'Oreo and Cream', 'Frappe', 55, 'mrkndrwslmn', '8 OZ', 55, 1, 543210),
('2023-05-20 08:15:00', 'Mango', 'Milktea Suprema', 49, 'mrkndrwslmn', '8 OZ', 49, 1, 123456),
('2023-06-15 10:30:00', 'Hazelnut', 'Milktea Classica', 39, 'mrkndrwslmn', '16 OZ', 39, 1, 234567),
('2023-08-01 13:45:00', 'Matcha', 'Milktea Suprema', 49, 'mrkndrwslmn', '22 OZ', 49, 1, 345678),
('2023-10-10 16:00:00', 'Lychee', 'Fruit Tea Classica', 29, 'mrkndrwslmn', '16 OZ', 29, 1, 456789),
('2023-12-25 18:15:00', 'Blue Lemonade', 'Fruit Soda Suprema', 49, 'mrkndrwslmn', '22 OZ', 49, 1, 567890),
('2024-05-21 14:27:06', 'Matcha', 'Frappe', 128, 'mrkndrwslmn', '16 OZ', 69, 1, 601990),
('2024-05-21 14:27:06', 'Four Seasons SUP', 'Fruit Soda Suprema', 128, 'mrkndrwslmn', '22 OZ', 59, 1, 601990),
('2024-05-21 15:34:30', 'Taro', 'Milktea Classica', 234, 'shann', '22 OZ', 234, 6, 859635),
('2024-05-21 15:35:48', 'Mango Graham', 'Frappe', 260, 'shann', '22 OZ', 130, 2, 473288),
('2024-05-21 15:35:48', 'Oreo and Cream', 'Frappe', 260, 'shann', '22 OZ', 130, 2, 473288);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `images` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `name`, `category`, `images`) VALUES
(1, 'Taro', 'Milktea Classica', '/Images/MilkteaCL/27.png'),
(2, 'Vanilla', 'Milktea Classica', '/Images/MilkteaCL/28.png'),
(3, 'Hazelnut', 'Milktea Classica', '/Images/MilkteaCL/29.png'),
(4, 'Okinawa', 'Milktea Classica', '/Images/MilkteaCL/30.png'),
(5, 'Black Forest', 'Milktea Classica', '/Images/MilkteaCL/31.png'),
(6, 'Winter Melon', 'Milktea Classica', '/Images/MilkteaCL/32.png'),
(7, 'Dark Chocolate', 'Milktea Classica', '/Images/MilkteaCL/33.png'),
(8, 'Mango', 'Milktea Suprema', '/Images/MilkteaSup/27.png'),
(9, 'Matcha', 'Milktea Suprema', '/Images/MilkteaSup/28.png'),
(10, 'Dalgona', 'Milktea Suprema', '/Images/MilkteaSup/29.png'),
(11, 'Caramel', 'Milktea Suprema', '/Images/MilkteaSup/30.png'),
(12, 'Chocolate', 'Milktea Suprema', '/Images/MilkteaSup/31.png'),
(13, 'Red Velvet', 'Milktea Suprema', '/Images/MilkteaSup/32.png'),
(14, 'Cookies and Cream', 'Milktea Suprema', '/Images/MilkteaSup/33.png'),
(15, 'Mocha', 'Coffee/Choco', '/Images/CoffeeChoco/27.png'),
(16, 'Chocolate', 'Coffee/Choco', '/Images/CoffeeChoco/28.png'),
(17, 'Barako', 'Coffee/Choco', '/Images/CoffeeChoco/29.png'),
(18, 'Hazelnut', 'Coffee/Choco', '/Images/CoffeeChoco/30.png'),
(19, 'Cappuccino', 'Coffee/Choco', '/Images/CoffeeChoco/31.png'),
(20, 'Strong Coffee', 'Coffee/Choco', '/Images/CoffeeChoco/32.png'),
(21, 'Krema Caramel', 'Coffee/Choco', '/Images/CoffeeChoco/33.png'),
(22, 'Milo Krunch', 'Frappe', '/Images/FrappeSeries/4.png'),
(23, 'Mango Graham', 'Frappe', '/Images/FrappeSeries/2.png'),
(24, 'Milky Strawberry', 'Frappe', '/Images/FrappeSeries/6.png'),
(25, 'Choco Hazelnut', 'Frappe', '/Images/FrappeSeries/3.png'),
(26, 'Coffee Crumble', 'Frappe', '/Images/FrappeSeries/5.png'),
(27, 'Oreo and Cream', 'Frappe', '/Images/FrappeSeries/1.png'),
(28, 'Salted Caramel', 'Frappe', '/Images/FrappeSeries/7.png'),
(29, 'Matcha', 'Frappe', '/Images/FrappeSeries/8.png'),
(30, 'Lychee CL', 'Fruit Tea Classica', '/Images/FruitTeaClassica/39.png'),
(31, 'Blueberry CL', 'Fruit Tea Classica', '/Images/FruitTeaClassica/42.png'),
(32, 'Strawberry CL', 'Fruit Tea Classica', '/Images/FruitTeaClassica/44.png'),
(33, 'Four Seasons CL', 'Fruit Tea Classica', '/Images/FruitTeaClassica/38.png'),
(34, 'Green Apple CL', 'Fruit Tea Classica', '/Images/FruitTeaClassica/43.png'),
(35, 'Blue Lemonade CL', 'Fruit Tea Classica', '/Images/FruitTeaClassica/40.png'),
(36, 'Green Lemonade CL', 'Fruit Tea Classica', '/Images/FruitTeaClassica/41.png'),
(37, 'Lychee SUP', 'Fruit Soda Suprema', '/Images/FruitSodaSuprema/39.png'),
(38, 'Blueberry SUP', 'Fruit Soda Suprema', '/Images/FruitSodaSuprema/42.png'),
(39, 'Strawberry SUP', 'Fruit Soda Suprema', '/Images/FruitSodaSuprema/44.png'),
(40, 'Four Seasons SUP', 'Fruit Soda Suprema', '/Images/FruitSodaSuprema/38.png'),
(41, 'Green Apple SUP', 'Fruit Soda Suprema', '/Images/FruitSodaSuprema/43.png'),
(42, 'Blue Lemonade SUP', 'Fruit Soda Suprema', '/Images/FruitSodaSuprema/40.png'),
(43, 'Green Lemonade SUP', 'Fruit Soda Suprema', '/Images/FruitSodaSuprema/41.png'),
(44, 'Iced Americano', 'Brewed Coffee Series', '/Images/BFC/1.png'),
(45, 'Caramel Macchiato', 'Brewed Coffee Series', '/Images/BFC/2.png'),
(46, 'Spanish Latte', 'Brewed Coffee Series', '/Images/BFC/3.png'),
(47, 'Creamy Vanilla', 'Brewed Coffee Series', '/Images/BFC/4.png'),
(48, 'Kape Matcha', 'Brewed Coffee Series', '/Images/BFC/5.png'),
(49, 'Salted Caramel', 'Brewed Coffee Series', '/Images/BFC/6.png'),
(50, 'BV Kopi', 'Brewed Coffee Series', '/Images/BFC/7.png'),
(51, 'Milky Caramel', 'Milk Series', '/Images/MilkSeries/1.png'),
(52, 'Milky Chocolate', 'Milk Series', '/Images/MilkSeries/2.png'),
(53, 'Milky Mango', 'Milk Series', '/Images/MilkSeries/3.png'),
(54, 'Milky Blueberry', 'Milk Series', '/Images/MilkSeries/4.png'),
(55, 'Milky Strawberry', 'Milk Series', '/Images/MilkSeries/5.png'),
(56, 'Milky Passion Fruit', 'Milk Series', '/Images/MilkSeries/6.png');

-- --------------------------------------------------------

--
-- Table structure for table `price`
--

CREATE TABLE `price` (
  `id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `8_oz` double NOT NULL,
  `16_oz` double NOT NULL,
  `22_oz` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `price`
--

INSERT INTO `price` (`id`, `item_id`, `8_oz`, `16_oz`, `22_oz`) VALUES
(1, 1, 0, 29, 39),
(2, 2, 0, 29, 39),
(3, 3, 0, 29, 39),
(4, 4, 0, 29, 39),
(5, 5, 0, 29, 39),
(6, 6, 0, 29, 39),
(7, 7, 0, 29, 39),
(8, 8, 0, 49, 59),
(9, 9, 0, 49, 59),
(10, 10, 0, 49, 59),
(11, 11, 0, 49, 59),
(12, 12, 0, 49, 59),
(13, 13, 0, 49, 59),
(14, 14, 0, 49, 59),
(15, 15, 39, 49, 0),
(16, 16, 39, 49, 0),
(17, 17, 39, 49, 0),
(18, 18, 39, 49, 0),
(19, 19, 39, 49, 0),
(20, 20, 39, 49, 0),
(21, 21, 39, 49, 0),
(22, 22, 0, 55, 65),
(23, 23, 0, 55, 65),
(24, 24, 0, 55, 65),
(25, 25, 0, 55, 65),
(26, 26, 0, 55, 65),
(27, 27, 0, 55, 65),
(28, 28, 0, 55, 65),
(29, 29, 0, 69, 79),
(30, 30, 0, 29, 39),
(31, 31, 0, 29, 39),
(32, 32, 0, 29, 39),
(33, 33, 0, 29, 39),
(34, 34, 0, 29, 39),
(35, 35, 0, 29, 39),
(36, 36, 0, 29, 39),
(37, 37, 0, 49, 59),
(38, 38, 0, 49, 59),
(39, 39, 0, 49, 59),
(40, 40, 0, 49, 59),
(41, 41, 0, 49, 59),
(42, 42, 0, 49, 59),
(43, 43, 0, 49, 59),
(45, 45, 79, 0, 0),
(46, 46, 69, 0, 0),
(47, 47, 79, 0, 0),
(48, 48, 79, 0, 0),
(49, 49, 79, 0, 0),
(50, 50, 49, 0, 0),
(51, 51, 69, 0, 0),
(52, 52, 69, 0, 0),
(53, 53, 69, 0, 0),
(54, 54, 69, 0, 0),
(55, 55, 69, 0, 0),
(56, 56, 69, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `pricesnacks`
--

CREATE TABLE `pricesnacks` (
  `id` int(11) NOT NULL,
  `medium` int(11) NOT NULL,
  `large` int(11) NOT NULL,
  `barkada` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pricesnacks`
--

INSERT INTO `pricesnacks` (`id`, `medium`, `large`, `barkada`) VALUES
(1, 29, 49, 69),
(2, 29, 39, 49),
(3, 10, 39, 49),
(4, 15, 0, 0),
(5, 18, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `snacks`
--

CREATE TABLE `snacks` (
  `id` int(11) NOT NULL,
  `name` varchar(210) NOT NULL,
  `category` varchar(210) NOT NULL,
  `images` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `snacks`
--

INSERT INTO `snacks` (`id`, `name`, `category`, `images`) VALUES
(1, 'French Fries CHEESE', 'Snacks', '/Images/Snacks/19.png'),
(2, 'French Fries SOUR & CREAM', 'Snacks', '/Images/Snacks/20.png'),
(3, 'NACHOS', 'Snacks', '/Images/Snacks/21.png'),
(4, 'Sandwich CHEESE', 'Snacks', '/Images/Snacks/24.png'),
(5, 'Sandwich CHEESE MAYO', 'Snacks', '/Images/Snacks/25.png'),
(6, 'Sandwich 2X CHEESE MAYO', 'Snacks', '/Images/Snacks/26.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accountdetail`
--
ALTER TABLE `accountdetail`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `price`
--
ALTER TABLE `price`
  ADD PRIMARY KEY (`id`),
  ADD KEY `price_items` (`item_id`);

--
-- Indexes for table `pricesnacks`
--
ALTER TABLE `pricesnacks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `snacks`
--
ALTER TABLE `snacks`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `1` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `price`
--
ALTER TABLE `price`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
