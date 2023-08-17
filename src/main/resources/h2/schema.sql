-- farmerstory.`user` definition
CREATE TABLE `user` (
  `idx` int NOT NULL AUTO_INCREMENT COMMENT '유저 번호',
  `nickname` varchar(100) NOT NULL COMMENT '유저닉네임',
  `id` varchar(100) NOT NULL COMMENT '유저id',
  `password` varchar(100) NOT NULL COMMENT '유저password',
  `email` varchar(100) NOT NULL COMMENT '유저email',
  `profile_img` longtext DEFAULT NULL COMMENT '유저 profile',
  `authority` int NOT NULL COMMENT '권한(1은 farmer, 2는 일반)',
  PRIMARY KEY (`idx`)
);

-- farmerstory.category definition
CREATE TABLE `category` (
  `idx` int NOT NULL COMMENT 'Category Index',
  `name` varchar(100) NOT NULL COMMENT 'Category Name',
  PRIMARY KEY (`idx`)
);

-- farmerstory.sale definition
CREATE TABLE `sale` (
  `idx` int NOT NULL AUTO_INCREMENT COMMENT '판매글번호',
  `title` varchar(100) NOT NULL COMMENT '판매글제목',
  `introduction` varchar(1000) NOT NULL COMMENT '판매소개',
  `price` varchar(100) NOT NULL COMMENT '판매가격',
  `sale_img` longtext DEFAULT NULL COMMENT '판매이미지',
  `amount` int DEFAULT NULL COMMENT '판매수량',
  `user_idx` int NOT NULL COMMENT '유저번호',
  `category_idx` int NOT NULL COMMENT '카테고리번호',
  PRIMARY KEY (`idx`),
  FOREIGN KEY (`category_idx`) REFERENCES `category` (`idx`),
  FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`)
);

-- farmerstory.review definition
CREATE TABLE `review` (
  `idx` int NOT NULL AUTO_INCREMENT COMMENT 'Review Post Number',
  `content` varchar(1000) NOT NULL COMMENT 'Review Content',
  `create_date` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Review Creation Date',
  `user_idx` int NOT NULL COMMENT 'User Number who wrote the Review',
  `sale_idx` int NOT NULL COMMENT 'Sale Post Number',
  PRIMARY KEY (`idx`),
  FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`),
  FOREIGN KEY (`sale_idx`) REFERENCES `sale` (`idx`)
);

-- -- farmerstory.diary definition
-- CREATE TABLE `diary` (
--   `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Diary Number',
--   `title` varchar(100) NOT NULL COMMENT 'Diary Title',
--   `content` varchar(1000) NOT NULL COMMENT 'Diary Content',
--   `create_date` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Diary Creation Date',
--   `user_idx` int(11) NOT NULL COMMENT 'User Number who wrote the Diary',
--   PRIMARY KEY (`idx`),
--   KEY `diary_FK` (`user_idx`),
--   CONSTRAINT `diary_FK` FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`) ON DELETE CASCADE
-- );

-- -- farmerstory.community definition
-- CREATE TABLE `community` (
--   `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Community Post Number',
--   `title` varchar(100) NOT NULL COMMENT 'Community Post Title',
--   `content` varchar(1000) NOT NULL COMMENT 'Community Post Content',
--   `create_date` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Community Post Creation Date',
--   `user_idx` int(11) NOT NULL COMMENT 'User Number who wrote the Community Post',
--   PRIMARY KEY (`idx`),
--   KEY `community_FK` (`user_idx`),
--   CONSTRAINT `community_FK` FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`)
-- );

-- -- farmerstory.comment definition
-- CREATE TABLE `comment` (
--   `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Comment Number',
--   `content` varchar(200) NOT NULL COMMENT 'Comment Content',
--   `create_date` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Comment Creation Date',
--   `user_idx` int(11) NOT NULL COMMENT 'User Number who wrote the Comment',
--   `community_idx` int(11) NOT NULL COMMENT 'Community Post Number',
--   PRIMARY KEY (`idx`),
--   KEY `comment_FK` (`user_idx`),
--   KEY `comment_FK_1` (`community_idx`),
--   CONSTRAINT `comment_FK` FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`),
--   CONSTRAINT `comment_FK_1` FOREIGN KEY (`community_idx`) REFERENCES `community` (`idx`)
-- );




