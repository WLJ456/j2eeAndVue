/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-06-05 09:32:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blogtable`
-- ----------------------------
DROP TABLE IF EXISTS `blogtable`;
CREATE TABLE `blogtable` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT 'blog作者',
  `blogTitle` varchar(255) NOT NULL COMMENT '博客标题',
  `blogText` varchar(255) NOT NULL COMMENT '博客内容',
  `imagesUrl` varchar(255) DEFAULT NULL,
  `createdDate` datetime NOT NULL COMMENT '发表时间',
  `isPass` int(10) DEFAULT NULL COMMENT '判断是否审核',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogtable
-- ----------------------------
INSERT INTO `blogtable` VALUES ('40', 'wlj123', '这是测试删除微博时，同时删除服务器的图片', '这是测试删除微博时，同时删除服务器的图片', 'http://localhost:8080//upload/20200604132110118783-106.jpg', '2020-06-04 13:21:11', '0');
INSERT INTO `blogtable` VALUES ('28', 'zdd', '这是中文地址图片测试博文', '这是中文地址图片测试博文', 'http://localhost:8080//upload/20200604112518水果插画.png', '2020-06-04 11:25:20', '0');
INSERT INTO `blogtable` VALUES ('29', 'zdd', '这是特殊符号测试博文', '这是特殊符号测试博文', 'http://localhost:8080//upload/20200604112538psb (11).jpg', '2020-06-04 11:25:39', '0');
INSERT INTO `blogtable` VALUES ('30', 'zdd', '这是正常博文', '12312321', 'http://localhost:8080//upload/20200604112549591e5b5f259c7.jpg', '2020-06-04 11:25:51', '0');
INSERT INTO `blogtable` VALUES ('31', 'wlj', '我也来发一个博文', '123123112', 'http://localhost:8080//upload/2020060411273901-1.jpg', '2020-06-04 11:27:40', '0');
INSERT INTO `blogtable` VALUES ('32', 'user1', 'user1发表的博文', '冲冲冲', 'http://localhost:8080//upload/20200604113009291909-106.jpg', '2020-06-04 11:30:11', '0');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `bid` int(11) NOT NULL COMMENT '博客id',
  `name` varchar(255) NOT NULL COMMENT '评论者用户名',
  `context` varchar(255) NOT NULL COMMENT '评论内容',
  `sendDate` datetime DEFAULT NULL COMMENT '创立时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('34', '28', 'zdd', '评论一波', '2020-06-04 11:26:00');
INSERT INTO `comment` VALUES ('35', '28', 'zdd', '厉害', '2020-06-04 11:26:06');
INSERT INTO `comment` VALUES ('36', '28', 'wlj', '楼上厉害', '2020-06-04 11:27:20');
INSERT INTO `comment` VALUES ('37', '28', 'user1', '我也来评论一波', '2020-06-04 11:29:21');
INSERT INTO `comment` VALUES ('38', '32', 'user1', '快来评论把', '2020-06-04 11:30:23');
INSERT INTO `comment` VALUES ('39', '28', 'zdd', 'www', '2020-06-04 15:09:05');
INSERT INTO `comment` VALUES ('40', '29', 'zdd', '这只狗好丑', '2020-06-04 15:57:54');
INSERT INTO `comment` VALUES ('41', '40', 'zdd', '这是一条评论', '2020-06-05 09:19:25');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `userdesc` varchar(255) DEFAULT NULL COMMENT '用户个人简介',
  `phonenumber` varchar(255) DEFAULT NULL COMMENT '用户电话号码',
  `emailaddress` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('39', 'wlj', '123456', '这是宁一个账号', '18382222224', '25121812246@qq.com');
INSERT INTO `user` VALUES ('4', 'admin', '123456', 'ceshi', '1234', 'dadad@qq.com');
INSERT INTO `user` VALUES ('40', 'user1', '123456', '这是一个工具人', '18384927234', '111111111@qq.com');
INSERT INTO `user` VALUES ('8', 'wlj123', '123456', 'dad', '12333', 'dadad@qq.com');
INSERT INTO `user` VALUES ('26', 'zdd', '123456', '这是一次修改陈述事实所121212', '18384927234', '251218146@qq.com');
INSERT INTO `user` VALUES ('41', '123123', '123123', null, '18384927234', null);
