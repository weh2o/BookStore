/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.23 : Database - bookstore
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bookstore` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `bookstore`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '書名',
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作者',
  `price` double DEFAULT NULL COMMENT '價格',
  `sales` int DEFAULT NULL COMMENT '銷量',
  `stock` int DEFAULT NULL COMMENT '庫存',
  `photo` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '圖片',
  `book_type_id` int DEFAULT NULL COMMENT '類型',
  PRIMARY KEY (`id`),
  KEY `foreign key` (`book_type_id`),
  CONSTRAINT `foreign key` FOREIGN KEY (`book_type_id`) REFERENCES `book_type` (`book_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`id`,`name`,`author`,`price`,`sales`,`stock`,`photo`,`book_type_id`) values 
(14,'搶便當的技巧','鈴a',10,5,5,'202107051830055.jpg',6),
(16,'下船快速入門','富奸',10,11,0,'20210721152329581.jpg',3),
(17,'野格炸彈','鈴a',10,4,6,'202107051945023.jpg',3),
(18,'香蕉','路過的主婦',10,7,3,'202107051951669.jpeg',3),
(19,'蘋果','路過的主婦',20,2,8,'202107051918684.jpeg',3),
(20,'西瓜','路過的主婦',30,4,6,'202107051938892.jpeg',2),
(23,'入門到入土','禿頭',5,4,6,'202107151715736.jpg',4),
(24,'拐跑別人的媳婦','肥宅',81000,999,0,'202107172238831.jpg',6),
(25,'JAVA','禿頭',10,1,19,'202107192149775.jpg',2);

/*Table structure for table `book_type` */

DROP TABLE IF EXISTS `book_type`;

CREATE TABLE `book_type` (
  `book_type_id` int NOT NULL AUTO_INCREMENT COMMENT '書本類型ID',
  `book_type_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '書本類型名稱',
  PRIMARY KEY (`book_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `book_type` */

insert  into `book_type`(`book_type_id`,`book_type_name`) values 
(1,'未分類'),
(2,'文學'),
(3,'小說'),
(4,'恐怖'),
(6,'教育');

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '購物車id',
  `user_id` int DEFAULT NULL COMMENT '用戶id',
  `book_id` int DEFAULT NULL COMMENT '商品id',
  `quantity` int DEFAULT NULL COMMENT '商品數量',
  `price` double DEFAULT NULL COMMENT '單價',
  `cost` double DEFAULT NULL COMMENT '總價',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `update_time` datetime DEFAULT NULL COMMENT '修改時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '訂單id',
  `orders_no` varchar(200) DEFAULT NULL COMMENT '訂單號',
  `user_id` int DEFAULT NULL COMMENT '用戶id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用戶名',
  `user_address` varchar(300) DEFAULT NULL COMMENT '用戶地址',
  `cost` double DEFAULT NULL COMMENT '訂單總金額',
  `status` int DEFAULT NULL COMMENT '訂單狀態',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `update_time` datetime DEFAULT NULL COMMENT '修改時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`id`,`orders_no`,`user_id`,`user_name`,`user_address`,`cost`,`status`,`create_time`,`update_time`) values 
(27,'202107170032507966',6,'root','root',10,1,'2021-07-17 00:32:50','2021-07-18 21:39:18'),
(29,'202107170105580986',6,'root','root',10,0,'2021-07-17 01:05:59','2021-07-17 01:05:59'),
(30,'2021071717490943513',13,'禿頭俠','深海的大鳳梨',10,0,'2021-07-17 17:49:10','2021-07-17 17:49:10'),
(31,'202107171806239386',6,'管理員','root',80,2,'2021-07-17 18:06:24','2021-07-18 21:39:08'),
(32,'202107171808275416',6,'管理員','root',120,0,'2021-07-17 18:08:28','2021-07-17 18:08:28'),
(33,'202107182141047146',6,'管理員','大平台',45,0,'2021-07-18 21:41:05','2021-07-18 21:41:05'),
(34,'202107182248339426',6,'管理員','大平台',35,0,'2021-07-18 22:48:34','2021-07-18 22:48:34'),
(35,'202107221622210256',6,'管理員','在家工作',30,0,'2021-07-22 16:22:22','2021-07-22 16:22:22');

/*Table structure for table `orders_detail` */

DROP TABLE IF EXISTS `orders_detail`;

CREATE TABLE `orders_detail` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '訂單詳情id',
  `orders_id` int DEFAULT NULL COMMENT '訂單id',
  `book_id` int DEFAULT NULL COMMENT '商品id',
  `quantity` int DEFAULT NULL COMMENT '商品數量',
  `cost` double DEFAULT NULL COMMENT '商品總金額',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `orders_detail` */

insert  into `orders_detail`(`id`,`orders_id`,`book_id`,`quantity`,`cost`) values 
(17,27,18,1,10),
(18,29,14,1,10),
(19,30,18,1,10),
(20,31,16,2,20),
(21,31,14,2,20),
(22,31,19,2,40),
(23,32,17,3,30),
(24,32,20,3,90),
(25,33,23,3,15),
(26,33,16,1,10),
(27,33,14,1,10),
(28,33,17,1,10),
(29,34,23,1,5),
(30,34,20,1,30),
(31,35,16,1,10),
(32,35,25,1,10),
(33,35,14,1,10);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用戶ID',
  `user_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用戶帳號',
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用戶名',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密碼',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '信箱',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '電話',
  `address` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `update_time` datetime DEFAULT NULL COMMENT '修改時間',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`user_account`,`user_name`,`password`,`email`,`phone`,`address`,`create_time`,`update_time`) values 
(1,'wwww','測試員','123456','12345@gmail.com','0987123123','韓國','2021-07-14 21:15:07','2021-07-14 21:15:07'),
(6,'root','管理員','123456','root@gmail.com','0987123456','在家工作','2021-07-14 21:48:26','2021-07-22 15:25:49'),
(12,'pigg','豬哥','123456','12345@gmail.com','0987123456','韓國','2021-07-14 22:59:26','2021-07-14 22:59:26'),
(13,'kkkk','禿頭俠','123456','12345@gmail.com','0987123456','深海的大鳳梨','2021-07-17 17:42:54','2021-07-17 17:42:54');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
