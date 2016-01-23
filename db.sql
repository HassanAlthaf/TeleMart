-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Jan 23, 2016 at 09:12 AM
-- Server version: 5.5.42
-- PHP Version: 5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `telemart`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `nic_number` varchar(10) NOT NULL,
  `membership_number` int(17) DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `contact_number` int(10) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email_address` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `nic_number`, `membership_number`, `name`, `contact_number`, `address`, `email_address`) VALUES
(1, '123456789V', 1601011, 'Hassan Althaf', 777943153, 'Home Sweet Home', 'hassan@hassanalthaf.com'),
(3, '123456923V', 1601023, 'Mr. Unknown', 777777777, 'Some place on earth', 'john@doe.com');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `id` int(11) NOT NULL,
  `brand` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `colour` varchar(255) NOT NULL,
  `unit_price` decimal(10,0) NOT NULL,
  `available_quantity` int(20) NOT NULL,
  `specifications` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`id`, `brand`, `model`, `colour`, `unit_price`, `available_quantity`, `specifications`) VALUES
(4, 'Apple', 'iPhone 6S+', 'Gold', '120000', 100, 'Feature One, Feature Two, Feature Three, Feature Four, Feature Five, Feature Six, Feature Seven, Feature Eight, Feature Nine.'),
(5, 'SAMSUNG', 'Galaxy Note 3 LTE', 'Jet Black', '50000', 100, '5.7 inch screen, 3 GB RAM, S Pen, Android Lollipop, N9005.');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT '0',
  `user_id` int(11) NOT NULL,
  `date` int(11) NOT NULL,
  `has_discount` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `customer_id`, `user_id`, `date`, `has_discount`) VALUES
(1, 3, 1, 1452874779, 1);

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT '0',
  `quantity` int(11) NOT NULL,
  `unit_price` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`id`, `product_id`, `order_id`, `quantity`, `unit_price`) VALUES
(1, 5, 1, 900, 50000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nic_number` varchar(10) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `contact_number` int(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `salary` decimal(10,0) NOT NULL,
  `rank` int(2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nic_number`, `username`, `password`, `full_name`, `contact_number`, `email`, `address`, `salary`, `rank`) VALUES
(1, '123456789V', 'Hassan', '$2a$10$8dGm0TWzdoDXsEcK9XuH9.eNAqoglJplBLMpP2pkThXeVeOJ7xjTW', 'Hassan Althaf', 777943153, 'hassan@hassanalthaf.com', 'No. 1, Rose St., London, England, UK.', '1000000', 4),
(2, '123456788V', 'Haseeb', '$2a$10$YUNRbBZ4fI6NnbZsP939jOf2QEWirasM1RmLy9vieqlL15wiecsVS', 'Abdul Haseeb', 772045143, 'h@h', 'No at no no no no', '1', 3),
(3, '639528417V', 'James', '$2a$10$xb8jONZMZOFsL67nmjpoJuRJ2M9Qk6dC8F9VLnEDLFF8GjyVQIr9q', 'James Bond', 777948323, 'James@James.com', 'James Home, UK, England.', '1000', 0),
(4, '987654321V', 'John', '$2a$10$uZWMkmiIDu2cdR0Y08DFReyeA4.bBAeAqNAw3RxAnHMzuoE5CVIBC', 'John Doe', 777777777, 'john@doe.com', 'JOHNS HOME ADDRESS', '10000', 2),
(5, '987654381V', 'Jack', '$2a$10$dPynPSEU/882/A7LTyu1FOvgW2wg5GU7ILQc/.2.dl/GAHbGVkrXy', 'Jack Doe', 777777777, 'Jack@doe.com', 'JACKS HOME ADDRESS', '10000', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nic_number` (`nic_number`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nicNumber` (`nic_number`,`username`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;