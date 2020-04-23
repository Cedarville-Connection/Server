-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 23, 2020 at 01:40 AM
-- Server version: 5.7.29-0ubuntu0.18.04.1
-- PHP Version: 7.2.24-0ubuntu0.18.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cu_connect`
--

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('admin', 'ROLE_admin'),
('nolander', 'ROLE_user'),
('noneel', 'ROLE_user'),
('Tom Lowry', 'ROLE_user'),
('user', 'ROLE_user');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(10) UNSIGNED NOT NULL,
  `contents` varchar(250) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `timestamp` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `contents`, `user_id`, `post_id`, `timestamp`) VALUES
(1, '1', 1, 1, '2020-04-22 14:13:27'),
(2, 'Another one.', 1, 1, '2020-04-22 14:24:31'),
(3, '<script>alert(\"xss attack test\");</script>', 1, 1, '2020-04-22 14:26:25'),
(4, 'Comment 4', 1, 1, NULL),
(5, 'Comment 5', 1, 1, '2020-04-22 20:02:40'),
(6, '', 1, 1, '2020-04-22 20:02:45'),
(7, 'Commenting on post', 1, 3, '2020-04-22 21:08:53'),
(8, 'Comment on second post', 1, 2, '2020-04-23 01:48:31'),
(9, 'TOM', 3, 3, '2020-04-23 04:55:13'),
(10, 'I need more CedarFriends', 3, 9, '2020-04-23 05:16:35'),
(11, 'Like and Comment on the video', 3, 9, '2020-04-23 05:16:55');

-- --------------------------------------------------------

--
-- Table structure for table `followers`
--

CREATE TABLE `followers` (
  `user_id` int(10) UNSIGNED NOT NULL,
  `friend_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `followers`
--

INSERT INTO `followers` (`user_id`, `friend_id`) VALUES
(3, 1),
(1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(150) NOT NULL,
  `about` varchar(400) NOT NULL,
  `picture` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `group_people`
--

CREATE TABLE `group_people` (
  `group_id` int(10) UNSIGNED NOT NULL,
  `person_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `media`
--

CREATE TABLE `media` (
  `id` int(10) UNSIGNED NOT NULL,
  `post_id` int(10) UNSIGNED NOT NULL,
  `media` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `people`
--

CREATE TABLE `people` (
  `id` int(10) UNSIGNED NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `profile_pic` varchar(400) CHARACTER SET utf8 NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 NOT NULL,
  `dob` datetime NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 NOT NULL,
  `gender` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `people`
--

INSERT INTO `people` (`id`, `first_name`, `last_name`, `profile_pic`, `address`, `dob`, `email`, `gender`) VALUES
(1, 'Nathan', 'O\'Neel', 'https://media1.giphy.com/media/11QEuO6MtKCl6E/giphy.gif', '4', '2020-04-01 00:00:00', 'email', 0),
(2, 'Nate', 'Olander', 'https://media.giphy.com/media/mFdnWF1RTI7fi/giphy.gif', '12345 Test Street Test, XO 12345 USA', '1999-06-29 00:00:00', 'nolander@cedarville.edu', 1),
(3, 'Tom', 'Lowry', 'https://media0.giphy.com/media/1034EEGrn91SrS/giphy.gif?cid=ecf05e47dd4c92dadf41da65843aef5af70ba204e707fef4&rid=giphy.gif', '123 Real St. KeepinItReal OH 98765', '2020-04-09 00:00:00', 'real@email.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` int(10) UNSIGNED NOT NULL,
  `content` varchar(300) NOT NULL,
  `user_id` int(11) NOT NULL,
  `timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
  `type` int(11) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `content`, `user_id`, `timestamp`, `type`, `post_id`) VALUES
(1, 'First Post!', 1, '2020-04-22 14:12:49', NULL, NULL),
(2, 'Second Post', 1, '2020-04-22 20:02:26', NULL, NULL),
(3, 'Hello', 1, '2020-04-22 21:04:52', NULL, NULL),
(4, 'Nate OLander', 1, '2020-04-22 23:40:51', NULL, NULL),
(5, 'test', 1, '2020-04-22 23:40:59', NULL, NULL),
(6, 'Post 1', 3, '2020-04-23 04:57:38', NULL, NULL),
(7, 'Post 2', 3, '2020-04-23 04:57:43', NULL, NULL),
(10, 'Nate Post', 2, '2020-04-23 05:30:43', NULL, NULL),
(11, 'More post', 2, '2020-04-23 05:30:48', NULL, NULL),
(12, 'Eat your veggies!', 2, '2020-04-23 05:30:57', NULL, NULL),
(13, 'Redeem the Time the King is coming', 2, '2020-04-23 05:31:10', NULL, NULL),
(14, 'Post 3', 1, '2020-04-23 05:34:57', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `reaction`
--

CREATE TABLE `reaction` (
  `type` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reaction`
--

INSERT INTO `reaction` (`type`, `user_id`, `post_id`, `status`) VALUES
(1, 1, 1, NULL),
(1, 1, 3, NULL),
(1, 1, 4, NULL),
(1, 1, 5, NULL),
(1, 1, 7, NULL),
(1, 3, 1, NULL),
(1, 3, 2, NULL),
(1, 3, 4, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `person_id` int(10) UNSIGNED DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `person_id`, `username`, `password`, `enabled`) VALUES
(1, NULL, 'user', '{noop}test', 1),
(2, NULL, 'admin', '{noop}admin', 1),
(3, 1, 'noneel', '{noop}test', 1),
(4, 2, 'nolander', '{noop}password', 1),
(5, 3, 'Tom Lowry', '{noop}password', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authorities`
--
ALTER TABLE `authorities`
  ADD PRIMARY KEY (`username`,`authority`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `followers`
--
ALTER TABLE `followers`
  ADD PRIMARY KEY (`user_id`,`friend_id`),
  ADD KEY `FKou2kcd6ry3v142d6lrl961r0y` (`friend_id`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `group_people`
--
ALTER TABLE `group_people`
  ADD KEY `person_id` (`person_id`),
  ADD KEY `group_id` (`group_id`);

--
-- Indexes for table `media`
--
ALTER TABLE `media`
  ADD PRIMARY KEY (`id`),
  ADD KEY `post_id` (`post_id`);

--
-- Indexes for table `people`
--
ALTER TABLE `people`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reaction`
--
ALTER TABLE `reaction`
  ADD PRIMARY KEY (`user_id`,`post_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `person_id` (`person_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `media`
--
ALTER TABLE `media`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `people`
--
ALTER TABLE `people`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

--
-- Constraints for table `followers`
--
ALTER TABLE `followers`
  ADD CONSTRAINT `FKou2kcd6ry3v142d6lrl961r0y` FOREIGN KEY (`friend_id`) REFERENCES `people` (`id`),
  ADD CONSTRAINT `followers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `people` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `followers_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `people` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `group_people`
--
ALTER TABLE `group_people`
  ADD CONSTRAINT `group_people_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `people` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `group_people_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `media`
--
ALTER TABLE `media`
  ADD CONSTRAINT `media_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `people` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
