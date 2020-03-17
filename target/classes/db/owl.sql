/*
 Navicat Premium Data Transfer

 Source Server         : 47.104.64.122
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 47.104.64.122:3306
 Source Schema         : owl

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 14/03/2020 12:29:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `c_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `c_kind` varchar(255) DEFAULT NULL COMMENT 'fund/stock',
  `c_code` varchar(255) DEFAULT NULL COMMENT '代码',
  `c_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `c_poundage` double DEFAULT NULL COMMENT '手续费比例',
  `c_remark` varchar(255) DEFAULT NULL,
  `c_create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `c_update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `c_state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_purchase
-- ----------------------------
DROP TABLE IF EXISTS `t_purchase`;
CREATE TABLE `t_purchase` (
  `c_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `c_user_id` int(10) unsigned DEFAULT NULL COMMENT '购入用户',
  `c_product_id` int(11) DEFAULT NULL COMMENT '产品名称',
  `c_rental` double DEFAULT NULL COMMENT '买入总额',
  `c_nav` double DEFAULT NULL COMMENT '单位净值',
  `c_quantity` double DEFAULT NULL COMMENT '购入份数',
  `c_confirm_rental` double DEFAULT NULL COMMENT '确认总额',
  `c_poundage` double DEFAULT NULL COMMENT '手续费',
  `c_buy_time` datetime DEFAULT NULL COMMENT '购买时间',
  `c_confirm_time` datetime DEFAULT NULL COMMENT '确认时间',
  `c_flags` varchar(255) DEFAULT NULL COMMENT 'flags',
  `c_create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `c_update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `c_state` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `c_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `c_account` varchar(255) DEFAULT NULL COMMENT '账号',
  `c_username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `c_password` varchar(255) DEFAULT NULL COMMENT '密码',
  `c_state` tinyint(4) DEFAULT NULL COMMENT '状态',
  `c_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `c_update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
