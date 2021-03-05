/*
 Navicat Premium Data Transfer

 Source Server         : MyMeow
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : shopping

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 05/03/2021 20:15:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `u_id` int NOT NULL AUTO_INCREMENT,
  `u_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `u_login_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `u_pwd` varchar(32) DEFAULT NULL,
  `mobile` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `admin` bit(1) NOT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES (1, '草莓', '1', '1', '1223432432', 'love@loli.vip', b'1');
INSERT INTO `account` VALUES (2, '小猫咪', 'meow', '1', '12345', '1695560542@qq.com', b'0');
INSERT INTO `account` VALUES (3, 'south', 'south', '1', NULL, NULL, b'0');
INSERT INTO `account` VALUES (4, 'MINI', 'mini', '1', NULL, NULL, b'0');
INSERT INTO `account` VALUES (5, 'li', 'li', '1', NULL, NULL, b'0');
INSERT INTO `account` VALUES (6, 'se', 'se', '1', NULL, NULL, b'0');
INSERT INTO `account` VALUES (7, 'åå··é£éª', 'loveliness', '123456', '18647688609', '1695560542@qq.com', b'0');
COMMIT;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `u_id` int DEFAULT NULL,
  `p_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `num` int DEFAULT NULL,
  `total_money` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
BEGIN;
INSERT INTO `cart` VALUES (5, 1, 10, '2020-11-15 18:40:00', 10, 1.00);
INSERT INTO `cart` VALUES (9, 1, 3, NULL, 10, 11.00);
INSERT INTO `cart` VALUES (10, 3, 1, NULL, 12, 1231.00);
INSERT INTO `cart` VALUES (11, 123, 121, '2020-11-25 17:39:40', 12, 121.00);
INSERT INTO `cart` VALUES (12, 1, 1, NULL, 1, 1111.00);
INSERT INTO `cart` VALUES (14, 1, 3, NULL, 10, 11.00);
INSERT INTO `cart` VALUES (15, 3, 1, NULL, 12, 1231.00);
INSERT INTO `cart` VALUES (16, 123, 121, '2020-11-25 17:39:40', 12, 121.00);
INSERT INTO `cart` VALUES (17, 1, 1, NULL, 1, 1111.00);
INSERT INTO `cart` VALUES (18, 2, 1, '2020-11-15 18:40:00', 10, 1.00);
INSERT INTO `cart` VALUES (19, 1, 3, NULL, 10, 11.00);
INSERT INTO `cart` VALUES (20, 3, 1, NULL, 12, 1231.00);
INSERT INTO `cart` VALUES (21, 123, 121, '2020-11-25 17:39:40', 12, 121.00);
INSERT INTO `cart` VALUES (22, 1, 1, NULL, 1, 1111.00);
INSERT INTO `cart` VALUES (23, 2, 1, '2020-11-15 18:40:00', 10, 1.00);
INSERT INTO `cart` VALUES (24, 1, 3, NULL, 10, 11.00);
INSERT INTO `cart` VALUES (25, 3, 1, NULL, 12, 1231.00);
INSERT INTO `cart` VALUES (26, 123, 121, '2020-11-25 17:39:40', 12, 121.00);
INSERT INTO `cart` VALUES (27, 1, 1, NULL, 1, 1111.00);
COMMIT;

-- ----------------------------
-- Table structure for orderdata
-- ----------------------------
DROP TABLE IF EXISTS `orderdata`;
CREATE TABLE `orderdata` (
  `od_id` int NOT NULL AUTO_INCREMENT,
  `order⁯_id` int DEFAULT NULL,
  `p_id` int DEFAULT NULL,
  `num` int DEFAULT NULL,
  `od_money` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`od_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderdata
-- ----------------------------
BEGIN;
INSERT INTO `orderdata` VALUES (1, 1, 1, 2, 10.00);
INSERT INTO `orderdata` VALUES (2, 2, 2, 14, 3.00);
INSERT INTO `orderdata` VALUES (3, 1, 3, 2, 35.00);
INSERT INTO `orderdata` VALUES (4, 1, 1, 1, 32.00);
INSERT INTO `orderdata` VALUES (6, 0, 2, 12, 100.00);
INSERT INTO `orderdata` VALUES (7, 0, 2, 1000, 666000.00);
INSERT INTO `orderdata` VALUES (8, 0, 1, 10, 100.00);
INSERT INTO `orderdata` VALUES (9, 5, 2, 100, 155.00);
COMMIT;

-- ----------------------------
-- Table structure for ordertable
-- ----------------------------
DROP TABLE IF EXISTS `ordertable`;
CREATE TABLE `ordertable` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `order_time` datetime DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `order_money` decimal(10,2) DEFAULT NULL,
  `order_status` char(10) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ordertable
-- ----------------------------
BEGIN;
INSERT INTO `ordertable` VALUES (1, '2020-11-15 19:16:35', 1, 100.00, 'cancel');
INSERT INTO `ordertable` VALUES (2, '2020-11-15 19:20:42', 2, 10.00, 'unpaid');
INSERT INTO `ordertable` VALUES (3, NULL, 1, NULL, 'cancel');
INSERT INTO `ordertable` VALUES (4, NULL, 1, NULL, 'completed');
INSERT INTO `ordertable` VALUES (5, '2020-11-16 00:00:00', 1, 155.00, 'unpaid');
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `p_name` varchar(255) DEFAULT NULL,
  `p_pic` varchar(255) DEFAULT NULL,
  `p_price` decimal(10,2) DEFAULT NULL,
  `p_num` int DEFAULT NULL,
  `sell_num` int DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (1, '炸鸡', NULL, 1999.00, 111, 121);
INSERT INTO `product` VALUES (2, '可乐', NULL, 10.00, 111, 213);
INSERT INTO `product` VALUES (3, '狗蛋', NULL, 100.00, 111, 121);
INSERT INTO `product` VALUES (4, '可乐', NULL, 10.00, 111, 213);
INSERT INTO `product` VALUES (5, '炸鸡', NULL, 100.00, 111, 121);
INSERT INTO `product` VALUES (6, '可乐', NULL, 10.00, 111, 213);
INSERT INTO `product` VALUES (7, '炸鸡', NULL, 100.00, 111, 121);
INSERT INTO `product` VALUES (8, '可乐', NULL, 10.00, 111, 213);
INSERT INTO `product` VALUES (9, '炸鸡', NULL, 100.00, 111, 121);
INSERT INTO `product` VALUES (10, '肥宅快乐水', NULL, 10.00, 111, 213);
INSERT INTO `product` VALUES (11, '炸鸡', NULL, 100.00, 111, 121);
INSERT INTO `product` VALUES (12, '狗蛋', NULL, 10.00, 111, 213);
INSERT INTO `product` VALUES (16, '啊这', NULL, 12.00, 1, 4);
INSERT INTO `product` VALUES (17, '苟利', NULL, 122.00, 2, 10);
COMMIT;

-- ----------------------------
-- Table structure for product_copy1
-- ----------------------------
DROP TABLE IF EXISTS `product_copy1`;
CREATE TABLE `product_copy1` (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `p_name` varchar(255) DEFAULT NULL,
  `p_pic` varchar(255) DEFAULT NULL,
  `p_price` decimal(10,2) DEFAULT NULL,
  `p_num` int DEFAULT NULL,
  `sell_num` int DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_copy1
-- ----------------------------
BEGIN;
INSERT INTO `product_copy1` VALUES (1, '炸鸡', '', 100.00, 111, 121);
INSERT INTO `product_copy1` VALUES (2, '可乐', NULL, 10.00, 111, 213);
INSERT INTO `product_copy1` VALUES (3, '炸鸡', '', 100.00, 111, 121);
INSERT INTO `product_copy1` VALUES (4, '可乐', NULL, 10.00, 111, 213);
INSERT INTO `product_copy1` VALUES (5, '炸鸡', '', 100.00, 111, 121);
INSERT INTO `product_copy1` VALUES (6, '可乐', NULL, 10.00, 111, 213);
INSERT INTO `product_copy1` VALUES (7, '炸鸡', '', 100.00, 111, 121);
INSERT INTO `product_copy1` VALUES (8, '可乐', NULL, 10.00, 111, 213);
INSERT INTO `product_copy1` VALUES (9, '炸鸡', '', 100.00, 111, 121);
INSERT INTO `product_copy1` VALUES (10, '可乐', NULL, 10.00, 111, 213);
INSERT INTO `product_copy1` VALUES (11, '炸鸡', '', 100.00, 111, 121);
INSERT INTO `product_copy1` VALUES (12, '可乐', NULL, 10.00, 111, 213);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
