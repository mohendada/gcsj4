/*
 Navicat Premium Data Transfer

 Source Server         : 3.5
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : warehouse

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 03/06/2024 12:31:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `employee_id` int NOT NULL,
  `employee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `employee_age` int NOT NULL,
  `employee_phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `employee_work_status` int NOT NULL,
  `employee_lfap` int NOT NULL,
  `employee_sick_leave` int NOT NULL,
  `employee_daily_wage` float NOT NULL,
  `employee_work_day` int NOT NULL,
  `employee_permission` int NOT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'Alice', 30, '1231231234', 1, 2, 1, 100, 20, 1);
INSERT INTO `employee` VALUES (2, 'Bob', 35, '4321432143', 0, 3, 2, 150, 15, 2);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_Id` int NOT NULL,
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `goods_price` float NOT NULL,
  `goods_photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `supplier_id` int NOT NULL,
  PRIMARY KEY (`goods_Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 'Laptop', 999.99, 'laptop.jpg', 1);
INSERT INTO `goods` VALUES (2, 'Smartphone', 499.99, 'smartphone.jpg', 2);

-- ----------------------------
-- Table structure for leaveapplication
-- ----------------------------
DROP TABLE IF EXISTS `leaveapplication`;
CREATE TABLE `leaveapplication`  (
  `applicant` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `approval` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `btime` datetime NULL DEFAULT NULL,
  `etime` datetime NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `yn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '未批改',
  `id` int NOT NULL AUTO_INCREMENT,
  `deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leaveapplication
-- ----------------------------
INSERT INTO `leaveapplication` VALUES ('xiaomqq', 'a1', '2024-05-31 09:11:03', '2024-06-01 09:11:10', '你好', '未批改', 1, '1');
INSERT INTO `leaveapplication` VALUES ('xiao', 'a1', '2024-05-31 09:11:03', '2024-06-01 09:11:10', '没有', '未批改', 2, '1');
INSERT INTO `leaveapplication` VALUES ('xia', 'a1', '2024-05-31 09:11:03', '2024-06-01 09:11:10', '没有', '未通过', 3, '0');
INSERT INTO `leaveapplication` VALUES ('q', 'a1', '2024-05-31 09:11:03', '2024-06-01 09:11:10', '没有', '未通过', 4, '0');
INSERT INTO `leaveapplication` VALUES ('xiaom', 'a1', '2024-05-31 09:11:03', '2024-06-01 09:11:10', '没有', '未批改', 5, '0');
INSERT INTO `leaveapplication` VALUES ('xiaomqqq', 'a1', '2024-05-31 09:11:03', '2024-06-01 09:11:10', '没有', '未通过', 6, '0');
INSERT INTO `leaveapplication` VALUES ('阿萨德', NULL, '2024-05-30 16:00:00', '2024-05-31 16:00:00', 'asdasd', '通过', 7, '0');
INSERT INTO `leaveapplication` VALUES ('qwe', 'a2', '2024-05-31 16:57:41', '2024-06-08 16:57:46', 'asd', '未批改', 8, '0');
INSERT INTO `leaveapplication` VALUES ('asdasd', NULL, NULL, NULL, NULL, '未批改', 9, '0');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int NOT NULL,
  `order_kind` int NOT NULL,
  `order_time` datetime NOT NULL,
  `order_status` int NOT NULL,
  `order_name` int NOT NULL,
  `order_number` int NOT NULL,
  `order_creater_id` int NOT NULL,
  `receiving_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 1, '2024-01-10 14:00:00', 1, 1, 2, 1, '2024-01-11 14:00:00');
INSERT INTO `order` VALUES (2, 2, '2024-01-15 15:00:00', 0, 2, 1, 2, '2024-01-15 15:00:00');

-- ----------------------------
-- Table structure for refound
-- ----------------------------
DROP TABLE IF EXISTS `refound`;
CREATE TABLE `refound`  (
  `return_goods_id` int NOT NULL,
  `return_goods_time` datetime NOT NULL,
  `return_goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `return_goods_number` int NOT NULL,
  `return_id` int NOT NULL,
  `return_statue` int NOT NULL,
  PRIMARY KEY (`return_goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of refound
-- ----------------------------
INSERT INTO `refound` VALUES (1, '2024-01-05 12:00:00', 'Laptop', 5, 1, 1);
INSERT INTO `refound` VALUES (2, '2024-01-06 13:00:00', 'Smartphone', 10, 2, 0);

-- ----------------------------
-- Table structure for restock
-- ----------------------------
DROP TABLE IF EXISTS `restock`;
CREATE TABLE `restock`  (
  `restock_id` int NOT NULL,
  `restock_time` datetime NOT NULL,
  `restock_goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `restock_goods_number` int NOT NULL,
  `supplier_id` int NOT NULL,
  `restock_statue` int NOT NULL,
  PRIMARY KEY (`restock_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of restock
-- ----------------------------
INSERT INTO `restock` VALUES (1, '2024-01-01 10:00:00', 'Laptop', 20, 1, 1);
INSERT INTO `restock` VALUES (2, '2024-01-02 11:00:00', 'Smartphone', 50, 2, 0);

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `goods_shelf_id` int NOT NULL,
  `goods_id` int NOT NULL,
  `goods_number` int NOT NULL,
  `goods_kind` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `goods_store_id` int NOT NULL,
  `goods_shelf_floor` int NOT NULL,
  PRIMARY KEY (`goods_shelf_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, 1, 50, 'Electronics', 101, 1);
INSERT INTO `store` VALUES (2, 2, 100, 'Electronics', 102, 2);

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplier_id` int NOT NULL,
  `supplier_phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `supplier_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `supplier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`supplier_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, '1112223333', '789 Pine St', 'Supplier A');
INSERT INTO `supplier` VALUES (2, '4445556666', '987 Oak St', 'Supplier B');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_Id` int NOT NULL AUTO_INCREMENT,
  `user_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `roles` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'customer',
  PRIMARY KEY (`user_Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'john_doe', '123456', '2', 'asdasd', 'Male', '1234567890', 'john@example.com', '123 Main St', 'manager');
INSERT INTO `user` VALUES (2, 'jane_smith', 'password456', '2', 'Jane Smith', 'Female', '0987654321', 'jane@example.com', '456 Elm St', 'manager');
INSERT INTO `user` VALUES (3, '1', '1', '2', '1', 'Male', '1', '1@qq.com', '1', 'manager');
INSERT INTO `user` VALUES (4, '123465', '123456', '1', '123456', 'Male', '123', '1@qc.cn', 'asd', 'customer');
INSERT INTO `user` VALUES (5, '111111', '111111', '3', '111111', 'Female', '111', '1@qq.', '1111', 'employee');

SET FOREIGN_KEY_CHECKS = 1;
