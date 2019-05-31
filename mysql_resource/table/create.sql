-- USE
USE my_demo

-- CREATE
-- USERS
CREATE TABLE IF NOT EXISTS `users` (
    `id` int NOT NULL AUTO_INCREMENT,
    `account_id` varchar(20) NOT NULL,
    `password` char(60) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- POSTS
CREATE TABLE IF NOT EXISTS `posts` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `title` varchar(45) NOT NULL,
      `detail` text NOT NULL,
      `poster_id` int(11) NOT NULL,
      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;