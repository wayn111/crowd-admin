/*
 Navicat Premium Data Transfer

 Source Server         : waynmysql.mysql.rds.aliyuncs.com
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : waynmysql.mysql.rds.aliyuncs.com:3306
 Source Schema         : crowd-admin

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 12/06/2021 21:09:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oa_notify
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify`;
CREATE TABLE `oa_notify`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` int NULL DEFAULT NULL COMMENT '通知类型 1 公告 2 通知',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '内容',
  `files` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '附件',
  `notifyState` int NULL DEFAULT NULL COMMENT '通知状态 1 已发布 -1 未发布',
  `createBy` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建者',
  `publishTime` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateBy` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新者',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注信息',
  `delFlag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标记 0 存在 1 删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `oa_notify_del_flag`(`delFlag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 171 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '通知通告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oa_notify
-- ----------------------------
INSERT INTO `oa_notify` VALUES (133, 2, 'XXXX', '<p>xixi<img src=\"http://localhost:8082/crowdfounding-web/upload/2019/12/18/69ce757e8693183a7d8234960758801a.jpg\" data-filename=\"2019/12/18/69ce757e8693183a7d8234960758801a.jpg\" style=\"width: 1044px;\"></p>', NULL, 1, 'c79ba431f9f74dfbae585b87b0cde933', '2019-12-16 07:09:59', '2019-12-16 07:09:59', 'c79ba431f9f74dfbae585b87b0cde933', '2020-09-25 16:30:43', '', '1');
INSERT INTO `oa_notify` VALUES (134, 1, 'Hello', 'hhh', NULL, 1, 'c79ba431f9f74dfbae585b87b0cde933', '2020-09-25 16:32:20', '2019-12-16 07:27:07', 'c79ba431f9f74dfbae585b87b0cde933', '2020-09-25 16:32:38', '', '0');
INSERT INTO `oa_notify` VALUES (135, 1, '这是一封通知', '<p><img src=\"http://localhost:8080/crowdfounding-web/upload/2020/09/26/74a3dfb608e69226bc51a07560c34326.png\" data-filename=\"2020/09/26/74a3dfb608e69226bc51a07560c34326.png\" style=\"width: 594px;\">3333<br></p>', NULL, 1, 'ae856c5c1c5843c5af18f593c432f09e', '2019-12-16 07:27:42', '2019-12-16 07:27:42', 'c79ba431f9f74dfbae585b87b0cde933', '2020-09-25 16:28:37', '', '0');
INSERT INTO `oa_notify` VALUES (137, 2, 's\'s\'s', '<p>三生三世</p>', NULL, 1, 'c79ba431f9f74dfbae585b87b0cde933', '2020-09-21 09:27:36', '2020-09-21 09:27:36', 'c79ba431f9f74dfbae585b87b0cde933', '2020-09-25 16:30:19', '', '0');
INSERT INTO `oa_notify` VALUES (138, 2, 's\'s\'s', '<p>三生三世</p>', NULL, 1, 'c79ba431f9f74dfbae585b87b0cde933', '2020-09-21 09:27:37', '2020-09-21 09:27:37', 'c79ba431f9f74dfbae585b87b0cde933', '2020-09-25 16:30:03', '', '0');
INSERT INTO `oa_notify` VALUES (139, 2, 'HHHHH', '<span style=\"background-color: rgb(255, 0, 0);\">Hello</span> <b>crowd</b>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2020-10-18 02:47:51', '2020-10-18 02:47:51', NULL, NULL, '', '1');
INSERT INTO `oa_notify` VALUES (140, 2, 'lllllll', '<p>ookokkokoo</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2020-10-18 03:06:32', '2020-10-18 03:06:32', NULL, NULL, 'beizhu', '1');
INSERT INTO `oa_notify` VALUES (141, 2, '测试', '<p>af</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2020-11-10 08:04:32', '2020-11-10 08:04:32', '3b3d6fc381dd41f5b13b7c0010bc9c85', '2020-12-17 01:27:57', '', '0');
INSERT INTO `oa_notify` VALUES (142, 2, '智慧果业2', '<p>afav</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2020-11-10 08:05:35', '2020-11-10 08:05:35', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (143, 2, 'afda', '<p>agfergerg</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2020-11-10 08:06:47', '2020-11-10 08:06:47', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (144, 1, '公告', 'ergre<p></p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2020-11-18 06:09:14', '2020-11-18 06:09:14', '3b3d6fc381dd41f5b13b7c0010bc9c85', '2020-12-24 02:29:50', '', '0');
INSERT INTO `oa_notify` VALUES (145, 1, 'aaa', '<p>aaa</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2020-12-26 05:55:40', '2020-12-26 05:55:40', '3b3d6fc381dd41f5b13b7c0010bc9c85', '2020-12-27 09:28:51', '', '0');
INSERT INTO `oa_notify` VALUES (146, 2, '1111', '<p>11111111111</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-01-04 07:45:19', '2021-01-04 07:45:19', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (147, 1, '测试测试', '<p>颠三倒四任务我我v&nbsp; 大胃王翁 违法VS的大多数是大V&nbsp;</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-01-05 08:14:25', '2021-01-05 08:14:25', '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-03-03 08:12:17', '', '0');
INSERT INTO `oa_notify` VALUES (148, 1, '吞吞吐吐', '<p>啊啊啊啊啊啊啊啊啊xc</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-01-06 01:43:33', '2021-01-06 01:43:33', '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-01-23 06:14:01', '', '0');
INSERT INTO `oa_notify` VALUES (149, 2, 'cc', '<p>cc</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-01-27 00:39:37', '2021-01-27 00:39:37', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (150, 1, 'dd', '<p>ddddd</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-01-27 00:40:30', '2021-01-27 00:40:33', '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-01-27 00:40:47', '', '0');
INSERT INTO `oa_notify` VALUES (151, 2, 'test', '<p>123</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-02-20 08:42:15', '2021-02-20 08:42:15', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (152, 2, 'test', '<p>0.0.0.0</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-02-23 14:13:40', '2021-02-23 14:13:40', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (153, 2, 'test', '<p>test123</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-02-23 14:15:20', '2021-02-23 14:15:20', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (154, 2, 'test', '<p>test</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-04-25 03:49:29', '2021-04-25 03:49:29', NULL, NULL, 'test', '0');
INSERT INTO `oa_notify` VALUES (155, 1, '测试通知1111', '<p>的非官方的GV第三方</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-04-26 02:03:08', '2021-04-26 02:03:08', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (156, 1, '地方三房', '<p><img src=\"http://wayn.xin:8080/crowd/upload/2021/04/26/420afd6c679a02430e14738bedfdabc4.png\" data-filename=\"2021/04/26/420afd6c679a02430e14738bedfdabc4.png\" style=\"width: 328px;\"><br></p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-04-26 02:34:10', '2021-04-26 02:34:10', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (157, 2, 'testddd', '<p>testdddd</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-04-26 02:37:41', '2021-04-26 02:37:41', NULL, NULL, 'dd', '0');
INSERT INTO `oa_notify` VALUES (158, 1, 'testddd3333', '<p>14444</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-04-26 05:51:26', '2021-04-26 05:51:26', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (159, 2, '222', '<p>222222</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-04-26 06:45:28', '2021-04-26 06:45:28', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (160, 2, '555', '<p>6666666</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-05-06 05:18:05', '2021-05-06 05:18:05', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (161, 2, 'ewqewq', '<p>321321</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-05-06 05:20:29', '2021-05-06 05:20:29', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (162, 2, 'ewqewq111', '<p>111222333</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-05-06 05:20:46', '2021-05-06 05:20:46', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (163, 2, '555333', '<p>123</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-05-06 05:21:06', '2021-05-06 05:21:06', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (164, 2, '111', '<p>111</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-05-06 05:21:42', '2021-05-06 05:21:42', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (165, 2, '3321321', '<p>321321321</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-05-06 05:21:55', '2021-05-06 05:21:55', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (166, 2, '444', '<p>1321321</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-05-06 05:22:06', '2021-05-06 05:22:06', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (167, 2, '555', 'dsadsadsadasdsadasda', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-05-19 05:32:38', '2021-05-19 05:32:38', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (168, 2, '一月又一月', '<p>一样一样</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-05-25 04:43:06', '2021-05-25 04:43:06', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (169, 2, '8888', '<p>7777</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-05-25 05:12:25', '2021-05-25 05:12:25', NULL, NULL, '', '0');
INSERT INTO `oa_notify` VALUES (170, 2, '11111111', '<p>111111</p>', NULL, 1, '3b3d6fc381dd41f5b13b7c0010bc9c85', '2021-06-01 13:07:42', '2021-06-01 13:07:42', NULL, NULL, '111', '0');

-- ----------------------------
-- Table structure for oa_notify_record
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify_record`;
CREATE TABLE `oa_notify_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `notifyId` bigint NOT NULL COMMENT '关联通知id',
  `receiveUserId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人id',
  `receiveUserName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人name',
  `isRead` tinyint NULL DEFAULT NULL COMMENT '是否已读',
  `readTime` datetime NULL DEFAULT NULL COMMENT '阅读时间',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 866 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oa_notify_record
-- ----------------------------
INSERT INTO `oa_notify_record` VALUES (765, 135, 'ae856c5c1c5843c5af18f593c432f09e', 'wayn', 0, NULL, '2020-09-25 16:28:37');
INSERT INTO `oa_notify_record` VALUES (766, 135, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2020-09-25 16:28:37');
INSERT INTO `oa_notify_record` VALUES (767, 138, 'ae856c5c1c5843c5af18f593c432f09e', 'wayn', 0, NULL, '2020-09-25 16:30:03');
INSERT INTO `oa_notify_record` VALUES (768, 138, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2020-09-25 16:30:03');
INSERT INTO `oa_notify_record` VALUES (769, 137, 'ae856c5c1c5843c5af18f593c432f09e', 'wayn', 0, NULL, '2020-09-25 16:30:20');
INSERT INTO `oa_notify_record` VALUES (770, 137, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2020-09-25 16:30:20');
INSERT INTO `oa_notify_record` VALUES (771, 133, 'ae856c5c1c5843c5af18f593c432f09e', 'wayn', 0, NULL, '2020-09-25 16:30:44');
INSERT INTO `oa_notify_record` VALUES (774, 134, 'ae856c5c1c5843c5af18f593c432f09e', 'wayn', 0, NULL, '2020-09-25 16:32:38');
INSERT INTO `oa_notify_record` VALUES (775, 134, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2020-09-25 16:32:38');
INSERT INTO `oa_notify_record` VALUES (777, 139, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2020-10-18 02:47:51');
INSERT INTO `oa_notify_record` VALUES (778, 140, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2020-10-18 03:06:32');
INSERT INTO `oa_notify_record` VALUES (779, 140, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2020-10-18 03:06:32');
INSERT INTO `oa_notify_record` VALUES (783, 142, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2020-11-10 08:05:35');
INSERT INTO `oa_notify_record` VALUES (784, 143, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2020-11-10 08:06:47');
INSERT INTO `oa_notify_record` VALUES (787, 141, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2020-12-17 01:27:57');
INSERT INTO `oa_notify_record` VALUES (788, 141, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2020-12-17 01:27:57');
INSERT INTO `oa_notify_record` VALUES (791, 144, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2020-12-24 02:29:50');
INSERT INTO `oa_notify_record` VALUES (792, 144, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2020-12-24 02:29:50');
INSERT INTO `oa_notify_record` VALUES (795, 145, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2020-12-27 09:28:51');
INSERT INTO `oa_notify_record` VALUES (796, 145, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2020-12-27 09:28:51');
INSERT INTO `oa_notify_record` VALUES (797, 146, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-01-04 07:45:19');
INSERT INTO `oa_notify_record` VALUES (798, 146, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2021-01-04 07:45:19');
INSERT INTO `oa_notify_record` VALUES (805, 148, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-01-23 06:14:01');
INSERT INTO `oa_notify_record` VALUES (806, 148, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2021-01-23 06:14:01');
INSERT INTO `oa_notify_record` VALUES (807, 149, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-01-27 00:39:37');
INSERT INTO `oa_notify_record` VALUES (808, 149, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2021-01-27 00:39:37');
INSERT INTO `oa_notify_record` VALUES (812, 150, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-01-27 00:40:47');
INSERT INTO `oa_notify_record` VALUES (813, 150, '64bc50b897ab42318620625660d5d683', 'user', 0, NULL, '2021-01-27 00:40:47');
INSERT INTO `oa_notify_record` VALUES (814, 150, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2021-01-27 00:40:47');
INSERT INTO `oa_notify_record` VALUES (815, 151, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-02-20 08:42:15');
INSERT INTO `oa_notify_record` VALUES (816, 152, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 0, NULL, '2021-02-23 14:13:40');
INSERT INTO `oa_notify_record` VALUES (817, 152, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-02-23 14:13:40');
INSERT INTO `oa_notify_record` VALUES (818, 153, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 0, NULL, '2021-02-23 14:15:20');
INSERT INTO `oa_notify_record` VALUES (819, 153, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-02-23 14:15:20');
INSERT INTO `oa_notify_record` VALUES (822, 147, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-03-03 08:12:17');
INSERT INTO `oa_notify_record` VALUES (823, 147, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 1, NULL, '2021-03-03 08:12:17');
INSERT INTO `oa_notify_record` VALUES (824, 154, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 0, NULL, '2021-04-25 03:49:29');
INSERT INTO `oa_notify_record` VALUES (825, 154, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-04-25 03:49:29');
INSERT INTO `oa_notify_record` VALUES (826, 155, '397826777191c8254d71c8b2a9fbdfda', '123', 0, NULL, '2021-04-26 02:03:08');
INSERT INTO `oa_notify_record` VALUES (827, 155, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-04-26 02:03:08');
INSERT INTO `oa_notify_record` VALUES (828, 155, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 0, NULL, '2021-04-26 02:03:08');
INSERT INTO `oa_notify_record` VALUES (829, 155, 'ecd0b125e3e8a74935cb5055e8bc7470', '123', 0, NULL, '2021-04-26 02:03:08');
INSERT INTO `oa_notify_record` VALUES (830, 156, '0809c5f69013f4366fa39b3adabc9693', 'test', 0, NULL, '2021-04-26 02:34:10');
INSERT INTO `oa_notify_record` VALUES (831, 156, '397826777191c8254d71c8b2a9fbdfda', '123', 0, NULL, '2021-04-26 02:34:10');
INSERT INTO `oa_notify_record` VALUES (832, 156, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-04-26 02:34:10');
INSERT INTO `oa_notify_record` VALUES (833, 156, '5d88b72c6f8ce90a37d2abafea9fb24a', 'test', 0, NULL, '2021-04-26 02:34:10');
INSERT INTO `oa_notify_record` VALUES (834, 156, '6683419cd64d769c878d42e6421310b8', 'test', 0, NULL, '2021-04-26 02:34:10');
INSERT INTO `oa_notify_record` VALUES (835, 156, '8bbfead39ca505a5ad83036f1d914a79', 'test', 0, NULL, '2021-04-26 02:34:10');
INSERT INTO `oa_notify_record` VALUES (836, 156, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 0, NULL, '2021-04-26 02:34:10');
INSERT INTO `oa_notify_record` VALUES (837, 156, 'ecd0b125e3e8a74935cb5055e8bc7470', '123', 0, NULL, '2021-04-26 02:34:10');
INSERT INTO `oa_notify_record` VALUES (838, 157, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 0, NULL, '2021-04-26 02:37:41');
INSERT INTO `oa_notify_record` VALUES (839, 157, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-04-26 02:37:41');
INSERT INTO `oa_notify_record` VALUES (840, 158, '0809c5f69013f4366fa39b3adabc9693', 'test', 0, NULL, '2021-04-26 05:51:26');
INSERT INTO `oa_notify_record` VALUES (841, 158, '397826777191c8254d71c8b2a9fbdfda', '123', 0, NULL, '2021-04-26 05:51:26');
INSERT INTO `oa_notify_record` VALUES (842, 158, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-04-26 05:51:26');
INSERT INTO `oa_notify_record` VALUES (843, 158, '5d88b72c6f8ce90a37d2abafea9fb24a', 'test', 0, NULL, '2021-04-26 05:51:26');
INSERT INTO `oa_notify_record` VALUES (844, 158, '6683419cd64d769c878d42e6421310b8', 'test', 0, NULL, '2021-04-26 05:51:26');
INSERT INTO `oa_notify_record` VALUES (845, 158, '8bbfead39ca505a5ad83036f1d914a79', 'test', 0, NULL, '2021-04-26 05:51:26');
INSERT INTO `oa_notify_record` VALUES (846, 158, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 0, NULL, '2021-04-26 05:51:26');
INSERT INTO `oa_notify_record` VALUES (847, 158, 'ecd0b125e3e8a74935cb5055e8bc7470', '123', 0, NULL, '2021-04-26 05:51:26');
INSERT INTO `oa_notify_record` VALUES (848, 159, '5d88b72c6f8ce90a37d2abafea9fb24a', 'test', 0, NULL, '2021-04-26 06:45:28');
INSERT INTO `oa_notify_record` VALUES (849, 159, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-04-26 06:45:28');
INSERT INTO `oa_notify_record` VALUES (850, 160, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-05-06 05:18:05');
INSERT INTO `oa_notify_record` VALUES (851, 161, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-05-06 05:20:29');
INSERT INTO `oa_notify_record` VALUES (852, 162, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-05-06 05:20:46');
INSERT INTO `oa_notify_record` VALUES (853, 163, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-05-06 05:21:06');
INSERT INTO `oa_notify_record` VALUES (854, 164, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-05-06 05:21:42');
INSERT INTO `oa_notify_record` VALUES (855, 165, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-05-06 05:21:55');
INSERT INTO `oa_notify_record` VALUES (856, 166, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-05-06 05:22:06');
INSERT INTO `oa_notify_record` VALUES (857, 167, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-05-19 05:32:38');
INSERT INTO `oa_notify_record` VALUES (858, 168, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-05-25 04:43:06');
INSERT INTO `oa_notify_record` VALUES (859, 169, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-05-25 05:12:25');
INSERT INTO `oa_notify_record` VALUES (860, 170, '0809c5f69013f4366fa39b3adabc9693', 'test', 0, NULL, '2021-06-01 13:07:43');
INSERT INTO `oa_notify_record` VALUES (861, 170, '5d88b72c6f8ce90a37d2abafea9fb24a', 'test', 0, NULL, '2021-06-01 13:07:43');
INSERT INTO `oa_notify_record` VALUES (862, 170, '6683419cd64d769c878d42e6421310b8', 'test', 0, NULL, '2021-06-01 13:07:43');
INSERT INTO `oa_notify_record` VALUES (863, 170, '8bbfead39ca505a5ad83036f1d914a79', 'test', 0, NULL, '2021-06-01 13:07:43');
INSERT INTO `oa_notify_record` VALUES (864, 170, 'c79ba431f9f74dfbae585b87b0cde933', 'admin', 0, NULL, '2021-06-01 13:07:43');
INSERT INTO `oa_notify_record` VALUES (865, 170, '3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 1, NULL, '2021-06-01 13:07:43');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('MyScheduler', 'TASK_CLASS_NAME108', 'system', '0 */5 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fired_time` bigint NOT NULL,
  `sched_time` bigint NOT NULL,
  `priority` int NOT NULL,
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('MyScheduler', 'TASK_CLASS_NAME108', 'system', NULL, 'com.wayn.quartz.util.ScheduleJobExecution', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001A636F6D2E7761796E2E71756172747A2E646F6D61696E2E4A6F6279C704DB3D7F195902000B4C000A636F6E63757272656E747400134C6A6176612F6C616E672F496E74656765723B4C000863726561746542797400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E000A4C000269647400104C6A6176612F6C616E672F4C6F6E673B4C000C696E766F6B6554617267657471007E000A4C00086A6F6247726F757071007E000A4C00076A6F624E616D6571007E000A4C00086A6F62537461746571007E00094C000D6D697366697265506F6C69637971007E000A4C0008757064617465427971007E000A4C000A75706461746554696D657400104C6A6176612F7574696C2F446174653B78720023636F6D2E7761796E2E636F6D6D6F6D2E626173652E427573696E657373456E7469747901AC47E24A8404310200014C000772656D61726B7371007E000A7872001F636F6D2E7761796E2E636F6D6D6F6D2E626173652E42617365456E746974795D9F2E1BBD83455C0200034C000A63726561746554696D6571007E000C4C0007656E6454696D6571007E000A4C0009737461727454696D6571007E000A787070707074001677656C636F6D6520746F2063726F77642D61646D696E737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000017074000D30202A2F35202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C75657871007E0012000000000000006C74001477656C436F6D654A6F622E77656C436F6D65282974000673797374656D740008746573745461736B71007E00137400013274000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001782FA1E022787800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('MyScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('MyScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_checkin_time` bigint NOT NULL,
  `checkin_interval` bigint NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('MyScheduler', 'i-l57oh7hd1623073806922', 1623503058085, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `repeat_count` bigint NOT NULL,
  `repeat_interval` bigint NOT NULL,
  `times_triggered` bigint NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `int_prop_1` int NULL DEFAULT NULL,
  `int_prop_2` int NULL DEFAULT NULL,
  `long_prop_1` bigint NULL DEFAULT NULL,
  `long_prop_2` bigint NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_fire_time` bigint NULL DEFAULT NULL,
  `prev_fire_time` bigint NULL DEFAULT NULL,
  `priority` int NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` bigint NOT NULL,
  `end_time` bigint NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `misfire_instr` smallint NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('MyScheduler', 'TASK_CLASS_NAME108', 'system', 'TASK_CLASS_NAME108', 'system', NULL, 1615707000000, -1, 5, 'ERROR', 'CRON', 1615706843000, 0, NULL, 1, '');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `configId` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `configName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数名称',
  `configKey` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数键名',
  `configValue` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数键值',
  `configType` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `createBy` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateBy` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`configId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '参数配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-21 11:17:49', '初始化密码：123456');
INSERT INTO `sys_config` VALUES (100, '系统名称', 'sys.name', 'crowd-admin', 'Y', 'admin', '2020-09-17 04:37:14', 'admin', '2020-09-17 04:37:30', '系统默认名称：crowd-admin');
INSERT INTO `sys_config` VALUES (101, '底部版权显示', 'sys.footer.copyright', '© 2019 wayn copyright', 'N', 'admin', '2020-09-25 15:43:20', 'admin', '2021-02-15 03:32:30', '底部版权显示,默认：© 2019 wayn Copyright 鄂ICP备18015313号-1');
INSERT INTO `sys_config` VALUES (102, '单一用户登陆认证', 'sys.user.singeUserAuth', 'false', 'Y', 'admin', '2020-12-18 18:05:01', 'crowd', '2020-12-18 18:45:54', 'true进行单一用户登陆处理，false不进行');
INSERT INTO `sys_config` VALUES (103, '单一用户登出逻辑，是否强制登出前一用户', 'sys.user.singeKickoutBefore', 'false', 'Y', 'admin', '2020-12-18 18:13:50', 'crowd', '2020-12-18 18:45:27', 'true登出前一用户false当前用户无法登陆');
INSERT INTO `sys_config` VALUES (104, '演示模式', 'sys.viewModel', 'true', 'Y', 'admin', '2021-03-21 11:04:25', 'admin', '2021-03-21 11:22:38', '演示模式：false不是，true是');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint NOT NULL COMMENT '夫部门id',
  `deptName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `sort` decimal(10, 2) NULL DEFAULT NULL COMMENT '排序',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 4, '产品部', 1.00, NULL, '产品部');
INSERT INTO `sys_dept` VALUES (2, 0, '总销售部', 2.00, NULL, '总销售部');
INSERT INTO `sys_dept` VALUES (3, 0, 'test部门', 3.00, NULL, 'test部门');
INSERT INTO `sys_dept` VALUES (4, 1, 'cpb', 1.00, NULL, NULL);
INSERT INTO `sys_dept` VALUES (6, 3, '测试部门', 0.00, NULL, '');
INSERT INTO `sys_dept` VALUES (8, 2, '销售部', 1.00, NULL, NULL);
INSERT INTO `sys_dept` VALUES (13, 3, 'test123', 1.00, NULL, '');
INSERT INTO `sys_dept` VALUES (14, 0, '养老部', 4.00, '2021-01-08 01:57:21', '程序员聚集地');
INSERT INTO `sys_dept` VALUES (15, 14, '钓鱼群', 0.00, '2021-01-08 01:57:52', '');
INSERT INTO `sys_dept` VALUES (16, 14, '喝茶群', 1.00, '2021-01-08 01:58:23', '');
INSERT INTO `sys_dept` VALUES (17, 0, 'test', NULL, '2021-04-21 01:46:05', '');
INSERT INTO `sys_dept` VALUES (18, 0, '1234', NULL, '2021-04-21 01:46:27', '');
INSERT INTO `sys_dept` VALUES (19, 0, '是否附近1234', NULL, '2021-04-21 01:47:04', '');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据值',
  `dictState` int NULL DEFAULT NULL COMMENT '1 启用  -1 禁用',
  `type` int NULL DEFAULT NULL COMMENT '1 字典类型  2 类型对应值',
  `sort` decimal(10, 2) NULL DEFAULT NULL COMMENT '排序（升序）',
  `dictType` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '字典类型',
  `createBy` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建者',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateBy` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新者',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注信息',
  `delFlag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标记 0 存在 1 删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_value`(`value`) USING BTREE,
  INDEX `sys_dict_label`(`name`) USING BTREE,
  INDEX `sys_dict_del_flag`(`delFlag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 161 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (123, '启用状态', 'state', 1, 1, 1.00, NULL, 'admin', '2019-06-29 16:20:21', 'admin', '2019-06-30 15:52:26', '1 启用  -1 禁用', '0');
INSERT INTO `sys_dict` VALUES (133, '启用', '1', 1, 2, 0.00, 'state', 'admin', '2019-06-30 15:41:45', 'admin', '2019-09-05 11:51:14', '', '1');
INSERT INTO `sys_dict` VALUES (134, '禁用', '-1', 1, 2, 1.00, 'state', 'admin', '2019-06-30 15:48:17', 'admin', '2019-06-30 15:48:32', '禁用', '0');
INSERT INTO `sys_dict` VALUES (135, '爱好', 'hobby', 1, 1, 2.00, NULL, 'admin', '2019-06-30 15:49:16', 'admin', '2019-07-17 02:30:39', 'swim 游泳 playball 打球。。。', '0');
INSERT INTO `sys_dict` VALUES (136, '打球', 'playball', 1, 2, 0.00, 'hobby', 'admin', '2019-06-30 15:49:43', 'admin', '2019-06-30 15:50:18', '', '0');
INSERT INTO `sys_dict` VALUES (138, '菜单类型', 'menuType', 1, 1, 0.10, NULL, 'admin', '2019-07-07 12:33:25', 'admin', '2020-12-18 18:19:34', '1目录 2菜单 3按钮', '0');
INSERT INTO `sys_dict` VALUES (139, '目录', '1', 1, 2, 1.00, 'menuType', 'admin', '2019-07-07 12:33:46', 'admin', '2020-09-17 15:09:52', 'mulu', '0');
INSERT INTO `sys_dict` VALUES (140, '菜单', '2', 1, 2, 2.00, 'menuType', 'admin', '2019-07-07 12:33:54', 'admin', '2019-09-05 09:00:47', '', '0');
INSERT INTO `sys_dict` VALUES (141, '按钮', '3', 1, 2, 3.00, 'menuType', 'admin', '2019-07-07 12:33:58', 'admin', '2019-09-05 09:00:52', '', '0');
INSERT INTO `sys_dict` VALUES (142, '游泳', 'swim', 1, 2, 1.00, 'hobby', 'admin', '2019-07-08 07:01:25', 'admin', '2019-07-19 03:35:44', 'swim 游泳 playball 打球。。。', '0');
INSERT INTO `sys_dict` VALUES (145, '跑步', 'run', 1, 2, 2.00, 'hobby', 'admin', '2019-08-22 07:11:35', NULL, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (146, '洗澡', 'bathing', 1, 2, NULL, 'hobby', 'admin', '2019-08-22 07:11:55', NULL, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (148, '执行策略', 'misfirePolicy', 1, 1, 3.00, NULL, 'admin', '2019-09-05 11:30:06', 'admin', '2019-09-05 12:27:41', '定时任务调度 ”失火策略“', '0');
INSERT INTO `sys_dict` VALUES (149, '立即执行', '1', 1, 2, 1.00, 'misfirePolicy', 'admin', '2019-09-05 11:30:31', NULL, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (152, '执行一次', '2', 1, 2, 2.00, 'misfirePolicy', 'admin', '2019-09-05 12:12:55', NULL, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (155, '放弃执行', '3', 1, 2, 3.00, 'misfirePolicy', 'admin', '2019-09-05 12:13:43', NULL, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (156, '启用', '1', 1, 2, 0.00, 'state', 'admin', '2019-09-05 12:26:43', 'admin', '2019-09-05 12:26:50', '', '0');
INSERT INTO `sys_dict` VALUES (157, '系统内置', 'sysBuildIn', 1, 1, 5.00, '0', 'admin', '2020-09-17 04:36:04', 'admin', '2020-09-17 04:36:13', 'Y是，N否', '0');
INSERT INTO `sys_dict` VALUES (158, '是', 'Y', 1, 2, 1.00, 'sysBuildIn', 'admin', '2020-09-17 04:36:24', NULL, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (159, '否', 'N', 1, 2, 2.00, 'sysBuildIn', 'admin', '2020-09-17 04:36:30', NULL, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (160, '测试字典', '自定义二', 1, 1, 1.00, '0', 'crowd', '2021-01-07 11:26:54', 'crowd', '2021-01-09 01:56:48', '222', '0');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `jobName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `jobGroup` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invokeTarget` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串（1，springBean调用 2，通过Class.forName反射调用）',
  `cronExpression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfirePolicy` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` int NULL DEFAULT 1 COMMENT '是否并发执行（1允许 -1禁止）',
  `jobState` int NULL DEFAULT 0 COMMENT '状态（1正常 -1暂停）',
  `createBy` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateBy` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`id`, `jobName`, `jobGroup`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (108, 'testTask', 'system', 'welComeJob.welCome()', '0 */5 * * * ?', '2', 1, 1, 'admin', '2019-09-06 12:20:34', 'admin', '2021-03-14 07:27:21', 'welcome to crowd-admin');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `jobName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `jobGroup` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组名',
  `invokeTarget` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `jobMessage` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `jobState` int NULL DEFAULT 0 COMMENT '执行状态（1正常  -1失败）',
  `exceptionInfo` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42533 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `loginName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `loginLocation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '登录状态（1成功 -1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '提示消息',
  `loginTime` datetime NULL DEFAULT NULL COMMENT '登陆时间',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1611 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------

-- ----------------------------
-- Table structure for sys_mail_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_mail_config`;
CREATE TABLE `sys_mail_config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `host` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `port` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮件服务器SMTP端口',
  `pass` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `fromUser` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发件者用户名，默认为发件人邮箱前缀',
  `userSendState` tinyint(1) NULL DEFAULT NULL COMMENT '用户发送状态,1-启用,-1禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_mail_config
-- ----------------------------
INSERT INTO `sys_mail_config` VALUES (1, '1', '2', '4', '3', 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `pid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级菜单ID',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '连接地址',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` decimal(10, 2) NULL DEFAULT NULL COMMENT '排序',
  `type` int NULL DEFAULT NULL COMMENT '菜单类型',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `resource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称（菜单对应权限）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', '0', '', 'fa fa-gear', 1.00, 1, '01', '');
INSERT INTO `sys_menu` VALUES (4, '菜单管理', '1', '/system/menu', '', 3.00, 2, '0103', '');
INSERT INTO `sys_menu` VALUES (5, '角色管理', '1', '/system/role', '', 2.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (15, '用户管理', '1', '/system/user', '', 1.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (16, '部门管理', '1', '/system/dept', '', 4.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (22, '基础管理', '0', '', 'fa fa-id-card-o', 3.00, 1, NULL, '');
INSERT INTO `sys_menu` VALUES (23, '新增', '4', '', '', 1.00, 3, NULL, 'sys:menu:add');
INSERT INTO `sys_menu` VALUES (24, '编辑', '4', '', '', 2.00, 3, NULL, 'sys:menu:edit');
INSERT INTO `sys_menu` VALUES (25, '删除', '4', '', '', 3.00, 3, NULL, 'sys:menu:remove');
INSERT INTO `sys_menu` VALUES (26, '新增', '15', '', '', 1.00, 3, NULL, 'sys:user:add');
INSERT INTO `sys_menu` VALUES (27, '编辑', '15', '', '', 2.00, 3, NULL, 'sys:user:edit');
INSERT INTO `sys_menu` VALUES (28, '删除', '15', '', '', 3.00, 3, NULL, 'sys:user:remove');
INSERT INTO `sys_menu` VALUES (29, '新增', '5', '', '', 1.00, 3, NULL, 'sys:role:add');
INSERT INTO `sys_menu` VALUES (30, '删除', '5', '', '', 3.00, 3, NULL, 'sys:role:remove');
INSERT INTO `sys_menu` VALUES (31, '新增', '16', '', '', 1.00, 3, NULL, 'sys:dept:add');
INSERT INTO `sys_menu` VALUES (32, '编辑', '16', '', '', 2.00, 3, NULL, 'sys:dept:edit');
INSERT INTO `sys_menu` VALUES (33, '删除', '16', '', '', 3.00, 3, NULL, 'sys:dept:remove');
INSERT INTO `sys_menu` VALUES (34, '编辑', '5', '', '', 2.00, 3, NULL, 'sys:role:edit');
INSERT INTO `sys_menu` VALUES (35, '系统监控', '0', '', 'fa fa-cc', 5.00, 1, NULL, '');
INSERT INTO `sys_menu` VALUES (37, '数据监控', '35', '/monitor/datasource', '', 3.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (38, '重置密码', '15', '', '', 4.00, 3, NULL, 'sys:user:resetPwd');
INSERT INTO `sys_menu` VALUES (39, '在线用户', '35', '/monitor/online', '', 1.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (40, '强退', '39', '', '', 1.00, 3, NULL, 'monitor:online:logout');
INSERT INTO `sys_menu` VALUES (41, '查看', '15', '', '', NULL, 3, NULL, 'sys:user:user');
INSERT INTO `sys_menu` VALUES (42, '查看', '5', '', '', NULL, 3, NULL, 'sys:role:role');
INSERT INTO `sys_menu` VALUES (43, '查看', '4', '', '', NULL, 3, NULL, 'sys:menu:menu');
INSERT INTO `sys_menu` VALUES (44, '查看', '16', '', '', NULL, 3, NULL, 'sys:dept:dept');
INSERT INTO `sys_menu` VALUES (45, '查看', '39', '', '', 0.00, 3, NULL, 'monitor:online:online');
INSERT INTO `sys_menu` VALUES (46, '查看', '37', '', '', 0.00, 3, NULL, 'monitor:datasource:datasource');
INSERT INTO `sys_menu` VALUES (50, '字典管理', '22', '/commom/dict/type', '', 0.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (52, '查看', '50', '', '', 0.00, 3, NULL, 'commom:dict:type');
INSERT INTO `sys_menu` VALUES (53, '新增', '50', '', '', 1.00, 3, NULL, 'commom:dict:add');
INSERT INTO `sys_menu` VALUES (54, '删除', '50', '', '', 2.00, 3, NULL, 'commom:dict:remove');
INSERT INTO `sys_menu` VALUES (55, '编辑', '50', '', '', 3.00, 3, NULL, 'commom:dict:edit');
INSERT INTO `sys_menu` VALUES (56, '系统工具', '0', '', 'fa fa-wrench', 4.00, 1, NULL, '');
INSERT INTO `sys_menu` VALUES (57, '代码生成', '56', '/tool/gen', '', 0.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (58, '查看', '57', '', '', 1.00, 3, NULL, 'tool:gen:list');
INSERT INTO `sys_menu` VALUES (61, '代码生成', '57', '', '', 2.00, 3, NULL, 'tool:gen:gen');
INSERT INTO `sys_menu` VALUES (62, '系统服务', '35', '/monitor/server', '', 2.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (63, '查看', '62', '', '', 0.00, 3, NULL, 'monitor:server:server');
INSERT INTO `sys_menu` VALUES (64, '修改用户名称', '15', '', '', 5.00, 3, NULL, 'sys:user:editAccount');
INSERT INTO `sys_menu` VALUES (65, '办公通知', '0', '', 'fa fa-envelope-o', 2.00, 1, NULL, '');
INSERT INTO `sys_menu` VALUES (66, '我的通知', '65', '/oa/notifyRecord', '', 0.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (67, '通知管理', '65', '/oa/notify', '', 1.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (68, '查看', '67', '', '', 0.00, 3, NULL, 'oa:notify:list');
INSERT INTO `sys_menu` VALUES (70, '编辑', '67', '', '', 2.00, 3, NULL, 'oa:notify:edit');
INSERT INTO `sys_menu` VALUES (71, '删除', '67', '', '', 3.00, 3, NULL, 'oa:notify:remove');
INSERT INTO `sys_menu` VALUES (72, '查看', '66', '', '', 0.00, 3, NULL, 'oa:notifyRecord:list');
INSERT INTO `sys_menu` VALUES (74, '新建', '67', '', '', 1.00, 3, NULL, 'oa:notify:add');
INSERT INTO `sys_menu` VALUES (75, '删除', '66', '', '', 2.00, 3, NULL, 'oa:notifyRecord:remove');
INSERT INTO `sys_menu` VALUES (79, '主页', '0', '', 'fa fa-home', 0.00, 1, NULL, '');
INSERT INTO `sys_menu` VALUES (80, '首页', '79', '/main/mainIndex1', '', NULL, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (81, '任务调度', '56', '/quartz/job', '', 1.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (82, '查看', '81', '', '', 0.00, 3, NULL, 'quartz:job:list');
INSERT INTO `sys_menu` VALUES (83, '新增', '81', '', '', 1.00, 3, NULL, 'quartz:job:add');
INSERT INTO `sys_menu` VALUES (84, '编码', '81', '', '', 2.00, 3, NULL, 'quartz:job:edit');
INSERT INTO `sys_menu` VALUES (85, '删除', '81', '', '', 3.00, 3, NULL, 'quartz:job:remove');
INSERT INTO `sys_menu` VALUES (86, '运行', '81', '', '', 4.00, 3, NULL, 'quartz:job:executor');
INSERT INTO `sys_menu` VALUES (87, '任务日志', '56', '/quartz/jobLog', '', 3.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (88, '查看', '87', '', '', 1.00, 3, NULL, 'quartz:jobLog:list');
INSERT INTO `sys_menu` VALUES (89, '删除', '87', '', '', 2.00, 3, NULL, 'quartz:jobLog:remove');
INSERT INTO `sys_menu` VALUES (90, '日志管理', '1', '', '', 6.00, 1, NULL, '');
INSERT INTO `sys_menu` VALUES (91, '操作日志', '90', '/system/log', '', 1.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (92, '删除', '91', '', '', 1.00, 3, NULL, 'sys:log:remove');
INSERT INTO `sys_menu` VALUES (93, '新增', '91', '', '', 0.10, 3, NULL, 'sys:log:add');
INSERT INTO `sys_menu` VALUES (94, '查看', '91', '', '', 0.00, 3, NULL, 'sys:log:log');
INSERT INTO `sys_menu` VALUES (95, '文件管理', '22', '/elfinder', '', 2.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (96, '管理', '95', '', '', 1.00, 3, NULL, 'filemanager:file:file');
INSERT INTO `sys_menu` VALUES (97, '登陆日志', '90', '/system/logininfor', 'fa fa-arrows-h', 2.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (98, '查看', '97', '', '', 1.00, 3, NULL, 'sys:logininfor:logininfor');
INSERT INTO `sys_menu` VALUES (99, '删除', '97', '', '', 2.00, 3, NULL, 'sys:logininfor:remove');
INSERT INTO `sys_menu` VALUES (100, '参数设置', '1', '/system/config', '', 4.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (101, '邮件配置', '22', '/commom/mail', '', 1.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (102, '查看', '100', '', '', 1.00, 3, NULL, 'sys:config:config');
INSERT INTO `sys_menu` VALUES (103, '新增', '100', '', '', 2.00, 3, NULL, 'sys:config:add');
INSERT INTO `sys_menu` VALUES (104, '删除', '100', '', '', 3.00, 3, NULL, 'sys:config:remove');
INSERT INTO `sys_menu` VALUES (105, '编辑', '100', '', '', 4.00, 3, NULL, 'sys:config:edit');
INSERT INTO `sys_menu` VALUES (109, 'test', '1', '', '', NULL, 1, NULL, '');
INSERT INTO `sys_menu` VALUES (110, '11', '5', '11', '', 11.00, 1, NULL, '11');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `operState` int NULL DEFAULT NULL COMMENT '操作状态 1 正常 -1 失败',
  `operation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `moduleName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `requestParams` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `requestMethod` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求类型 post/get',
  `requestResponse` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求响应',
  `errorMsg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '错误消息',
  `createTime` datetime NULL DEFAULT NULL COMMENT '日志时间',
  `ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求ip',
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器信息',
  `executeTime` int NULL DEFAULT NULL COMMENT '执行时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `roleName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `roleState` int NULL DEFAULT 1 COMMENT '状态,1-启用,-1禁用',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('2bfcfcc02fc824ff05b1e27eac990faa', '333', 1, '2021-02-22 13:56:07', '');
INSERT INTO `sys_role` VALUES ('6f6a7b83d962b670ef3e09216bd5ca70', 'ppo', 1, '2021-04-21 01:47:47', '');
INSERT INTO `sys_role` VALUES ('b1f9ce5543a049be9f169a3f5e6b72a8', '超级管理员', 1, '2017-09-14 15:02:16', '超级管理员');
INSERT INTO `sys_role` VALUES ('ca904d80931f4c368ac1c0919d16b6ae', '总裁', 1, '2019-04-26 18:20:12', '执行总裁');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `roleId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  `menuId` bigint NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('001a74b5e63541c5847d4164c5aaa9cd', 'b1f9ce5543a049be9f169a3f5e6b72a8', 53);
INSERT INTO `sys_role_menu` VALUES ('00c125080db64862ac102d754ab4f06a', 'b1f9ce5543a049be9f169a3f5e6b72a8', 95);
INSERT INTO `sys_role_menu` VALUES ('025e26c4569143ee9c7b5e9ddb52e42f', 'b1f9ce5543a049be9f169a3f5e6b72a8', 4);
INSERT INTO `sys_role_menu` VALUES ('04d37554502d4d67901500666bce52bd', 'b1f9ce5543a049be9f169a3f5e6b72a8', 99);
INSERT INTO `sys_role_menu` VALUES ('04e7498eb40941b3bae7fe9bf643aeb0', 'ca904d80931f4c368ac1c0919d16b6ae', 30);
INSERT INTO `sys_role_menu` VALUES ('06d716e80f044453b4b4791755e5cc48', 'ca904d80931f4c368ac1c0919d16b6ae', 81);
INSERT INTO `sys_role_menu` VALUES ('07f61afbb6454e8d8fbc70e9444589e0', '6f6a7b83d962b670ef3e09216bd5ca70', 53);
INSERT INTO `sys_role_menu` VALUES ('08df77a3f2d74d0c980ceff9a5f5746f', 'b1f9ce5543a049be9f169a3f5e6b72a8', 54);
INSERT INTO `sys_role_menu` VALUES ('11dddbd221e34be58d3d29f38cc411b1', 'b1f9ce5543a049be9f169a3f5e6b72a8', 88);
INSERT INTO `sys_role_menu` VALUES ('1224a2d7f6064675ab139cca6a91037f', 'b1f9ce5543a049be9f169a3f5e6b72a8', 65);
INSERT INTO `sys_role_menu` VALUES ('13751d2badc44a14a36251f04768cdfc', 'ca904d80931f4c368ac1c0919d16b6ae', 70);
INSERT INTO `sys_role_menu` VALUES ('147990b7b3c0496fbd04d5f0dad69181', 'b1f9ce5543a049be9f169a3f5e6b72a8', 80);
INSERT INTO `sys_role_menu` VALUES ('148e2bff1e574c00b7aafd4adb33506e', 'ca904d80931f4c368ac1c0919d16b6ae', 23);
INSERT INTO `sys_role_menu` VALUES ('151f39d354014b5893c25fd5b7baaaa3', 'b1f9ce5543a049be9f169a3f5e6b72a8', 75);
INSERT INTO `sys_role_menu` VALUES ('1605bc1c33cc4c58999df324e9a8884c', 'b1f9ce5543a049be9f169a3f5e6b72a8', 34);
INSERT INTO `sys_role_menu` VALUES ('160e63647625482e8e1a74da418f2bd6', 'ca904d80931f4c368ac1c0919d16b6ae', 84);
INSERT INTO `sys_role_menu` VALUES ('16eafd32dfd5445492ba45c88cf882f4', 'ca904d80931f4c368ac1c0919d16b6ae', 32);
INSERT INTO `sys_role_menu` VALUES ('1c6903d830c844d49db091b278666d62', 'ca904d80931f4c368ac1c0919d16b6ae', 16);
INSERT INTO `sys_role_menu` VALUES ('20d105e894354c29aebae2fe49e7bd36', 'b1f9ce5543a049be9f169a3f5e6b72a8', 37);
INSERT INTO `sys_role_menu` VALUES ('22b682e2d5634f40865f8c73f9a34e93', 'ca904d80931f4c368ac1c0919d16b6ae', 74);
INSERT INTO `sys_role_menu` VALUES ('22e342a51fa74ba79d15b587e42ae88e', 'b1f9ce5543a049be9f169a3f5e6b72a8', 15);
INSERT INTO `sys_role_menu` VALUES ('23330e90b8324b7994499d4cfe821491', 'b1f9ce5543a049be9f169a3f5e6b72a8', 103);
INSERT INTO `sys_role_menu` VALUES ('23fd4989ca1c4e84bec4ea2df0a03630', 'ca904d80931f4c368ac1c0919d16b6ae', 42);
INSERT INTO `sys_role_menu` VALUES ('25259e5929f542e8b94830e30223cbaf', 'b1f9ce5543a049be9f169a3f5e6b72a8', 98);
INSERT INTO `sys_role_menu` VALUES ('25df0a4239444c33b55b596ae04a063e', 'ca904d80931f4c368ac1c0919d16b6ae', 44);
INSERT INTO `sys_role_menu` VALUES ('261299aecf234f158f40bfa8e3d9153b', 'ca904d80931f4c368ac1c0919d16b6ae', 52);
INSERT INTO `sys_role_menu` VALUES ('2ae5d05f61544c718ed6d0e1ceb7eb15', 'ca904d80931f4c368ac1c0919d16b6ae', 103);
INSERT INTO `sys_role_menu` VALUES ('2d1bff190f964f85929340ca96477a81', 'ca904d80931f4c368ac1c0919d16b6ae', 27);
INSERT INTO `sys_role_menu` VALUES ('2f42c00627374dccbea0173b85edd425', 'ca904d80931f4c368ac1c0919d16b6ae', 108);
INSERT INTO `sys_role_menu` VALUES ('2f5d31147a964ffc815b86b89ea8a4bc', 'ca904d80931f4c368ac1c0919d16b6ae', 82);
INSERT INTO `sys_role_menu` VALUES ('2f954faa34774baf9f51c25c04bedbe1', 'ca904d80931f4c368ac1c0919d16b6ae', 68);
INSERT INTO `sys_role_menu` VALUES ('3244ee0faee74545b3a13da7d462fb3f', 'b1f9ce5543a049be9f169a3f5e6b72a8', 100);
INSERT INTO `sys_role_menu` VALUES ('32d26f41db6e4f1ab53804e4807a60dd', 'ca904d80931f4c368ac1c0919d16b6ae', 46);
INSERT INTO `sys_role_menu` VALUES ('331ae754254d408db6f413a3471f7ca8', 'ca904d80931f4c368ac1c0919d16b6ae', 63);
INSERT INTO `sys_role_menu` VALUES ('33202558873a44b5b522f906c66b4ac7', '6f6a7b83d962b670ef3e09216bd5ca70', 63);
INSERT INTO `sys_role_menu` VALUES ('3589018574204f639d3f380c6916759f', 'ca904d80931f4c368ac1c0919d16b6ae', 5);
INSERT INTO `sys_role_menu` VALUES ('35c1e4063f4c459e847793e42b74b10d', 'b1f9ce5543a049be9f169a3f5e6b72a8', 72);
INSERT INTO `sys_role_menu` VALUES ('360db16ed8bb4011af9e67d37dd32bf5', 'b1f9ce5543a049be9f169a3f5e6b72a8', 40);
INSERT INTO `sys_role_menu` VALUES ('36fe61259105412294c769f9f7a07389', 'ca904d80931f4c368ac1c0919d16b6ae', 71);
INSERT INTO `sys_role_menu` VALUES ('3b790783b97d49a699c15940aab833b8', 'ca904d80931f4c368ac1c0919d16b6ae', 62);
INSERT INTO `sys_role_menu` VALUES ('3b7c357250b64c399ab851a99d598b2a', 'b1f9ce5543a049be9f169a3f5e6b72a8', 101);
INSERT INTO `sys_role_menu` VALUES ('3c162f7d1ad54b78b5fcc4995d69facb', 'b1f9ce5543a049be9f169a3f5e6b72a8', 61);
INSERT INTO `sys_role_menu` VALUES ('3defc0f72f02451a8ff717dc9b61c006', 'b1f9ce5543a049be9f169a3f5e6b72a8', 90);
INSERT INTO `sys_role_menu` VALUES ('3fa2977600d741e9b8b24e89f5bfbcc4', 'ca904d80931f4c368ac1c0919d16b6ae', 15);
INSERT INTO `sys_role_menu` VALUES ('40a8896a71b3465d9421ebdbd6e31dc3', 'ca904d80931f4c368ac1c0919d16b6ae', 24);
INSERT INTO `sys_role_menu` VALUES ('4154e4620e854bdebc98faf3847046b3', '6f6a7b83d962b670ef3e09216bd5ca70', 50);
INSERT INTO `sys_role_menu` VALUES ('43535454a50f4372a27e59ae2434cd75', 'b1f9ce5543a049be9f169a3f5e6b72a8', 97);
INSERT INTO `sys_role_menu` VALUES ('43553e44c8de459eb9f682778a8873f0', 'ca904d80931f4c368ac1c0919d16b6ae', 53);
INSERT INTO `sys_role_menu` VALUES ('43905ccd5a8f43018ba07f171db698af', 'b1f9ce5543a049be9f169a3f5e6b72a8', 62);
INSERT INTO `sys_role_menu` VALUES ('44ec3d4378e74144829fc690a5cfad2c', 'b1f9ce5543a049be9f169a3f5e6b72a8', 94);
INSERT INTO `sys_role_menu` VALUES ('48bfcefd334241b98e74e16139d39501', '6f6a7b83d962b670ef3e09216bd5ca70', 54);
INSERT INTO `sys_role_menu` VALUES ('4db23e78b9d64064949a052db8ea06b6', 'b1f9ce5543a049be9f169a3f5e6b72a8', 28);
INSERT INTO `sys_role_menu` VALUES ('5181d485a4544b4abccebf7ffc7d12f5', 'b1f9ce5543a049be9f169a3f5e6b72a8', 41);
INSERT INTO `sys_role_menu` VALUES ('5301d4f3a8634d09bf6bc439c1deb504', 'b1f9ce5543a049be9f169a3f5e6b72a8', 91);
INSERT INTO `sys_role_menu` VALUES ('540b807cab4e4bc899447bdb23293284', 'b1f9ce5543a049be9f169a3f5e6b72a8', 33);
INSERT INTO `sys_role_menu` VALUES ('54ffb55aa70c4cc4afe84f8109f71849', 'ca904d80931f4c368ac1c0919d16b6ae', 94);
INSERT INTO `sys_role_menu` VALUES ('553b2cdc852e49d7b1a812a607f8895b', 'ca904d80931f4c368ac1c0919d16b6ae', 93);
INSERT INTO `sys_role_menu` VALUES ('56b4d4a92fdf4978bedee1b2c00586b6', 'ca904d80931f4c368ac1c0919d16b6ae', 45);
INSERT INTO `sys_role_menu` VALUES ('580fb69e27eb4317be27214b8068a22a', 'b1f9ce5543a049be9f169a3f5e6b72a8', 43);
INSERT INTO `sys_role_menu` VALUES ('58c877efd0584a06844a7cb310e92d35', 'ca904d80931f4c368ac1c0919d16b6ae', 26);
INSERT INTO `sys_role_menu` VALUES ('590bfdb1d4fa4abea722ba9c90bd1b1b', 'b1f9ce5543a049be9f169a3f5e6b72a8', 56);
INSERT INTO `sys_role_menu` VALUES ('5cd0c1c7b37b4462b35862369c9fe34c', 'ca904d80931f4c368ac1c0919d16b6ae', 107);
INSERT INTO `sys_role_menu` VALUES ('5d8f6ff2710c4473828d85e1bad1dc82', 'b1f9ce5543a049be9f169a3f5e6b72a8', 57);
INSERT INTO `sys_role_menu` VALUES ('5e84a2421241429ab18760fd170f0233', 'b1f9ce5543a049be9f169a3f5e6b72a8', 63);
INSERT INTO `sys_role_menu` VALUES ('6111ed56b6464a4ca17b706202efbc0e', 'b1f9ce5543a049be9f169a3f5e6b72a8', 67);
INSERT INTO `sys_role_menu` VALUES ('645c4ed3d39e4e4aa945cd575454fbff', 'b1f9ce5543a049be9f169a3f5e6b72a8', 102);
INSERT INTO `sys_role_menu` VALUES ('653b6a23e37f49b58adb57e51fbfc2b7', 'ca904d80931f4c368ac1c0919d16b6ae', 43);
INSERT INTO `sys_role_menu` VALUES ('658f5fe34e49423487dd3cf0eaf97204', 'ca904d80931f4c368ac1c0919d16b6ae', 40);
INSERT INTO `sys_role_menu` VALUES ('663634aa51ad49fb8cb6f447d737de40', 'b1f9ce5543a049be9f169a3f5e6b72a8', 96);
INSERT INTO `sys_role_menu` VALUES ('66864f82efe04716b7b287e956218fc6', 'ca904d80931f4c368ac1c0919d16b6ae', 25);
INSERT INTO `sys_role_menu` VALUES ('68c723ce42b249b8b0c6cc21f475d276', 'ca904d80931f4c368ac1c0919d16b6ae', 41);
INSERT INTO `sys_role_menu` VALUES ('69132dfea93841a09c9521077cba15df', 'ca904d80931f4c368ac1c0919d16b6ae', 56);
INSERT INTO `sys_role_menu` VALUES ('6950dc51bb994ebfac614d30ae62aa05', '6f6a7b83d962b670ef3e09216bd5ca70', 95);
INSERT INTO `sys_role_menu` VALUES ('6977778dd06349e6b5ec3b3b58bc6c23', 'ca904d80931f4c368ac1c0919d16b6ae', 85);
INSERT INTO `sys_role_menu` VALUES ('6a6f815e6f0c4e21b6b120065a0d7185', 'ca904d80931f4c368ac1c0919d16b6ae', 89);
INSERT INTO `sys_role_menu` VALUES ('6acc393c8c8a41399915b9ab5fbcb679', '6f6a7b83d962b670ef3e09216bd5ca70', 96);
INSERT INTO `sys_role_menu` VALUES ('6cd13424812a4bcb9ac08a90731cf8db', 'b1f9ce5543a049be9f169a3f5e6b72a8', 92);
INSERT INTO `sys_role_menu` VALUES ('6f12a71b2fd34fd08a45f4d9b7c0ea17', '6f6a7b83d962b670ef3e09216bd5ca70', 62);
INSERT INTO `sys_role_menu` VALUES ('6ff981bf872547c5b19809a3eb24c003', 'ca904d80931f4c368ac1c0919d16b6ae', 87);
INSERT INTO `sys_role_menu` VALUES ('70d2cc32373c42ffbc6a161f989febb0', 'b1f9ce5543a049be9f169a3f5e6b72a8', 82);
INSERT INTO `sys_role_menu` VALUES ('72e6ddf1ac70445aafcf4f60ae6cafdf', 'ca904d80931f4c368ac1c0919d16b6ae', 31);
INSERT INTO `sys_role_menu` VALUES ('73f35afbdb5245e888a21bceeacabdd8', 'ca904d80931f4c368ac1c0919d16b6ae', 83);
INSERT INTO `sys_role_menu` VALUES ('7508ac6313634173841e40c98b8aefbe', 'b1f9ce5543a049be9f169a3f5e6b72a8', 22);
INSERT INTO `sys_role_menu` VALUES ('78cd560b8f054f71ac4cf2761c05104f', 'b1f9ce5543a049be9f169a3f5e6b72a8', 1);
INSERT INTO `sys_role_menu` VALUES ('7972e45efb314a6eabf75f9e339ffec2', 'ca904d80931f4c368ac1c0919d16b6ae', 66);
INSERT INTO `sys_role_menu` VALUES ('7a8c2dabd6e94995a42c03167b45c128', 'b1f9ce5543a049be9f169a3f5e6b72a8', 23);
INSERT INTO `sys_role_menu` VALUES ('7bd4f38cd8ed4c7abe04e95006527db7', 'b1f9ce5543a049be9f169a3f5e6b72a8', 104);
INSERT INTO `sys_role_menu` VALUES ('7d7ac630e8404e5daecc906189c56494', 'ca904d80931f4c368ac1c0919d16b6ae', 39);
INSERT INTO `sys_role_menu` VALUES ('7dbac53bd0c249c6b3ad4aac489992f8', 'ca904d80931f4c368ac1c0919d16b6ae', 96);
INSERT INTO `sys_role_menu` VALUES ('7dd8378de87d4cd5b21475f294d2acd1', 'b1f9ce5543a049be9f169a3f5e6b72a8', 39);
INSERT INTO `sys_role_menu` VALUES ('836e1e54f3d6433980985cf96eb29833', 'ca904d80931f4c368ac1c0919d16b6ae', 86);
INSERT INTO `sys_role_menu` VALUES ('84e17af8be314c1dbb5139ca598fc571', 'ca904d80931f4c368ac1c0919d16b6ae', 54);
INSERT INTO `sys_role_menu` VALUES ('872d58f36ffd469ba808e1395cf230d5', '6f6a7b83d962b670ef3e09216bd5ca70', 22);
INSERT INTO `sys_role_menu` VALUES ('88507b4ea21942a6a47135c8dd946cf7', 'b1f9ce5543a049be9f169a3f5e6b72a8', 68);
INSERT INTO `sys_role_menu` VALUES ('89a83e88c31045e5b95591bfb9156206', 'ca904d80931f4c368ac1c0919d16b6ae', 104);
INSERT INTO `sys_role_menu` VALUES ('8d50ff5d197c4d0885100c8736274cd4', 'b1f9ce5543a049be9f169a3f5e6b72a8', 50);
INSERT INTO `sys_role_menu` VALUES ('8f17f83cf0f64d64a6b27d1ed4af45e8', '6f6a7b83d962b670ef3e09216bd5ca70', 45);
INSERT INTO `sys_role_menu` VALUES ('91ad35a7d56a41e2861e2ec8b9fdcbe7', 'b1f9ce5543a049be9f169a3f5e6b72a8', 45);
INSERT INTO `sys_role_menu` VALUES ('92036575c90c491794dc6147c005e15f', '6f6a7b83d962b670ef3e09216bd5ca70', 39);
INSERT INTO `sys_role_menu` VALUES ('92c84ea189ea42aca366abafbe493067', 'ca904d80931f4c368ac1c0919d16b6ae', 97);
INSERT INTO `sys_role_menu` VALUES ('93254ff9502c432f8ae165f3db220c68', 'ca904d80931f4c368ac1c0919d16b6ae', 37);
INSERT INTO `sys_role_menu` VALUES ('94e2c40f230545da867176fc4101736b', 'ca904d80931f4c368ac1c0919d16b6ae', 67);
INSERT INTO `sys_role_menu` VALUES ('989a925b3a014f67a29f775c89c579c8', 'b1f9ce5543a049be9f169a3f5e6b72a8', 5);
INSERT INTO `sys_role_menu` VALUES ('98ce635f1ecf4413a85cbb88c074c6a4', 'ca904d80931f4c368ac1c0919d16b6ae', 75);
INSERT INTO `sys_role_menu` VALUES ('99f6ac59597445bc938cada369b3f3a9', 'b1f9ce5543a049be9f169a3f5e6b72a8', 30);
INSERT INTO `sys_role_menu` VALUES ('9d559193f17c424a9077300b8b2c4431', 'ca904d80931f4c368ac1c0919d16b6ae', 61);
INSERT INTO `sys_role_menu` VALUES ('9d5f2b0d84f543728fd925308980ef8c', 'ca904d80931f4c368ac1c0919d16b6ae', 1);
INSERT INTO `sys_role_menu` VALUES ('9f2c48a8502649429564887b874934fa', 'ca904d80931f4c368ac1c0919d16b6ae', 100);
INSERT INTO `sys_role_menu` VALUES ('9f658a2fd0a243a8b181824e7f629b92', 'b1f9ce5543a049be9f169a3f5e6b72a8', 32);
INSERT INTO `sys_role_menu` VALUES ('9f761d38263b466a8b7e59fe810e06e1', 'ca904d80931f4c368ac1c0919d16b6ae', 35);
INSERT INTO `sys_role_menu` VALUES ('a0c4d12c7c964ad0943776dc83bda996', 'b1f9ce5543a049be9f169a3f5e6b72a8', 66);
INSERT INTO `sys_role_menu` VALUES ('a1ccbfb3f21549878cbf7438ba2a95aa', 'b1f9ce5543a049be9f169a3f5e6b72a8', 55);
INSERT INTO `sys_role_menu` VALUES ('a232f34d5530481a80ea633b066cb71f', 'b1f9ce5543a049be9f169a3f5e6b72a8', 64);
INSERT INTO `sys_role_menu` VALUES ('a315a4f9ace8447db4c947a647e02e26', 'b1f9ce5543a049be9f169a3f5e6b72a8', 25);
INSERT INTO `sys_role_menu` VALUES ('a37c685ccb5a452daea69cace50ccd90', '6f6a7b83d962b670ef3e09216bd5ca70', 55);
INSERT INTO `sys_role_menu` VALUES ('a550566fa6d44a529942fdb669875c7f', 'b1f9ce5543a049be9f169a3f5e6b72a8', 93);
INSERT INTO `sys_role_menu` VALUES ('a625593cf23341fe9ac69dbde28ccc91', 'ca904d80931f4c368ac1c0919d16b6ae', 55);
INSERT INTO `sys_role_menu` VALUES ('a662fef9e8f94ebdb3accc4d3f594184', '6f6a7b83d962b670ef3e09216bd5ca70', 35);
INSERT INTO `sys_role_menu` VALUES ('a79c8cc1ee7f4d05992494c2c5467eea', 'ca904d80931f4c368ac1c0919d16b6ae', 90);
INSERT INTO `sys_role_menu` VALUES ('a80a674230e0451a969fd5c2238e84bf', 'b1f9ce5543a049be9f169a3f5e6b72a8', 26);
INSERT INTO `sys_role_menu` VALUES ('aa58067ac5fc4e168ecfd98a33572920', 'b1f9ce5543a049be9f169a3f5e6b72a8', 44);
INSERT INTO `sys_role_menu` VALUES ('af1aac76267443319f9b35fbe59524af', 'b1f9ce5543a049be9f169a3f5e6b72a8', 74);
INSERT INTO `sys_role_menu` VALUES ('af562c250f064b33827239ded1fe445a', 'b1f9ce5543a049be9f169a3f5e6b72a8', 58);
INSERT INTO `sys_role_menu` VALUES ('af75bfa8527a4d79ac79ebc761ac8ec4', 'ca904d80931f4c368ac1c0919d16b6ae', 106);
INSERT INTO `sys_role_menu` VALUES ('b0ec54f6c16c4bba82643f09ebf3631d', 'b1f9ce5543a049be9f169a3f5e6b72a8', 38);
INSERT INTO `sys_role_menu` VALUES ('b221f1108b5c46fa8990437a60cbe9a9', 'ca904d80931f4c368ac1c0919d16b6ae', 38);
INSERT INTO `sys_role_menu` VALUES ('b612928e4de04ce9b8801a5adea489c4', 'b1f9ce5543a049be9f169a3f5e6b72a8', 85);
INSERT INTO `sys_role_menu` VALUES ('bc5cc2493cc94f4cb5d92160b1236e95', '6f6a7b83d962b670ef3e09216bd5ca70', 46);
INSERT INTO `sys_role_menu` VALUES ('bcb4923029f64a1a9f11a18e3205e4b2', 'ca904d80931f4c368ac1c0919d16b6ae', 91);
INSERT INTO `sys_role_menu` VALUES ('bed18370fd724b96b22546fe1a53dfb5', 'ca904d80931f4c368ac1c0919d16b6ae', 64);
INSERT INTO `sys_role_menu` VALUES ('c010729472bc4dcf9a41fffbc523c100', 'ca904d80931f4c368ac1c0919d16b6ae', 105);
INSERT INTO `sys_role_menu` VALUES ('c030a2e2a37644a3b929cd35267197aa', '6f6a7b83d962b670ef3e09216bd5ca70', 40);
INSERT INTO `sys_role_menu` VALUES ('c431735923bb4aa390a3de9c940bf260', 'b1f9ce5543a049be9f169a3f5e6b72a8', 89);
INSERT INTO `sys_role_menu` VALUES ('c7e4502b596e4a4b8cc7f267c52fe935', 'b1f9ce5543a049be9f169a3f5e6b72a8', 46);
INSERT INTO `sys_role_menu` VALUES ('c932846c28404046bf4a8ad755f02bb3', 'ca904d80931f4c368ac1c0919d16b6ae', 88);
INSERT INTO `sys_role_menu` VALUES ('c9894c2980b3467fad8ee7c904717508', '6f6a7b83d962b670ef3e09216bd5ca70', 101);
INSERT INTO `sys_role_menu` VALUES ('cabefe861c074fe2a94d707cd18ef93f', 'b1f9ce5543a049be9f169a3f5e6b72a8', 24);
INSERT INTO `sys_role_menu` VALUES ('cb96d04262644c2aa6cd83c37bbea5a0', 'ca904d80931f4c368ac1c0919d16b6ae', 101);
INSERT INTO `sys_role_menu` VALUES ('cd548e7802604cc7a3842f9a5000324a', 'b1f9ce5543a049be9f169a3f5e6b72a8', 83);
INSERT INTO `sys_role_menu` VALUES ('ceadbc7c1ea64b67ab1ef6f377d719b3', 'b1f9ce5543a049be9f169a3f5e6b72a8', 29);
INSERT INTO `sys_role_menu` VALUES ('cfaf998f993f460991acdf5e2af8e26c', 'ca904d80931f4c368ac1c0919d16b6ae', 98);
INSERT INTO `sys_role_menu` VALUES ('d05f12a3e8b14a85a5d4a8dc1fa1abaa', 'ca904d80931f4c368ac1c0919d16b6ae', 72);
INSERT INTO `sys_role_menu` VALUES ('d2b434ff32e548f5be35fc0ea59dca96', 'b1f9ce5543a049be9f169a3f5e6b72a8', 35);
INSERT INTO `sys_role_menu` VALUES ('d67b642ee7c148aa8e6de5cb16ddcf22', 'ca904d80931f4c368ac1c0919d16b6ae', 4);
INSERT INTO `sys_role_menu` VALUES ('d6eaa1f3203e4380a8ed453e3f5e9a9b', 'ca904d80931f4c368ac1c0919d16b6ae', 58);
INSERT INTO `sys_role_menu` VALUES ('d7890affc6a14326a4926977264ee129', '6f6a7b83d962b670ef3e09216bd5ca70', 37);
INSERT INTO `sys_role_menu` VALUES ('d99cc8c46bf447bebbcec8ba175909cf', 'ca904d80931f4c368ac1c0919d16b6ae', 79);
INSERT INTO `sys_role_menu` VALUES ('da7d2878251e46a6962e3e84b5d001a3', 'ca904d80931f4c368ac1c0919d16b6ae', 22);
INSERT INTO `sys_role_menu` VALUES ('dfb65607881d44ada589c8c12b7efd4d', '6f6a7b83d962b670ef3e09216bd5ca70', 52);
INSERT INTO `sys_role_menu` VALUES ('e0a899fbe95d4f0eba8e3af8d8c4b264', 'ca904d80931f4c368ac1c0919d16b6ae', 50);
INSERT INTO `sys_role_menu` VALUES ('e1717d74aba243beb658689f5ffd867d', 'b1f9ce5543a049be9f169a3f5e6b72a8', 105);
INSERT INTO `sys_role_menu` VALUES ('e2bd56cf65f046b8b49ed21a9ad0c859', 'b1f9ce5543a049be9f169a3f5e6b72a8', 86);
INSERT INTO `sys_role_menu` VALUES ('e56e06bfb0a64720bd58b3ed60eb4248', 'b1f9ce5543a049be9f169a3f5e6b72a8', 52);
INSERT INTO `sys_role_menu` VALUES ('e7eaaebfae1544398570471a66a988e8', 'ca904d80931f4c368ac1c0919d16b6ae', 92);
INSERT INTO `sys_role_menu` VALUES ('e8bec3f4057a46a0b8aa47fe5ba1a0a4', 'b1f9ce5543a049be9f169a3f5e6b72a8', 71);
INSERT INTO `sys_role_menu` VALUES ('e95f5312caa141908cb086248649dae8', 'ca904d80931f4c368ac1c0919d16b6ae', 99);
INSERT INTO `sys_role_menu` VALUES ('ead5d2730c984ee2b3daf3495f6bb2b1', 'b1f9ce5543a049be9f169a3f5e6b72a8', 70);
INSERT INTO `sys_role_menu` VALUES ('ead70dbd4ef84aee8919c0dc291e90c1', 'ca904d80931f4c368ac1c0919d16b6ae', 57);
INSERT INTO `sys_role_menu` VALUES ('eadea04a7b7847e6924bd5226300e660', 'b1f9ce5543a049be9f169a3f5e6b72a8', 87);
INSERT INTO `sys_role_menu` VALUES ('eb8dce7a58684ed6949cdfce3d2b270a', 'b1f9ce5543a049be9f169a3f5e6b72a8', 42);
INSERT INTO `sys_role_menu` VALUES ('eca4aec18541428b9a7789b69aa77671', 'b1f9ce5543a049be9f169a3f5e6b72a8', 16);
INSERT INTO `sys_role_menu` VALUES ('ed9982445fd44fe184d2d9e29d5973f9', 'ca904d80931f4c368ac1c0919d16b6ae', 34);
INSERT INTO `sys_role_menu` VALUES ('ef301a134bed4979b7d0b75e930d1a5e', 'ca904d80931f4c368ac1c0919d16b6ae', 29);
INSERT INTO `sys_role_menu` VALUES ('ef7340e67ba845fd9e1e926407b62659', 'ca904d80931f4c368ac1c0919d16b6ae', 65);
INSERT INTO `sys_role_menu` VALUES ('f036aa940a21470a82d9b76f43e5d5d4', 'ca904d80931f4c368ac1c0919d16b6ae', 33);
INSERT INTO `sys_role_menu` VALUES ('f110e3e96137455db8d81fd84965d31f', 'b1f9ce5543a049be9f169a3f5e6b72a8', 27);
INSERT INTO `sys_role_menu` VALUES ('f1711df1a8d44a4e8db66d0bd44730c5', 'b1f9ce5543a049be9f169a3f5e6b72a8', 31);
INSERT INTO `sys_role_menu` VALUES ('f7db127f7b094ed1b88452d6e9fa5cdd', 'ca904d80931f4c368ac1c0919d16b6ae', 102);
INSERT INTO `sys_role_menu` VALUES ('f8417e848b4f44a780fbce8d5fe9eb45', 'ca904d80931f4c368ac1c0919d16b6ae', 95);
INSERT INTO `sys_role_menu` VALUES ('f9d6a39dfa694a90a4599a4f94eabcc7', 'b1f9ce5543a049be9f169a3f5e6b72a8', 81);
INSERT INTO `sys_role_menu` VALUES ('fa8a746b36594172a25f223e30be1366', 'b1f9ce5543a049be9f169a3f5e6b72a8', 84);
INSERT INTO `sys_role_menu` VALUES ('fbf300a7a3c64553b2f8c818c2d066ef', 'ca904d80931f4c368ac1c0919d16b6ae', 28);
INSERT INTO `sys_role_menu` VALUES ('fc0cc7d625b84cafa141a02f9de64704', 'b1f9ce5543a049be9f169a3f5e6b72a8', 79);
INSERT INTO `sys_role_menu` VALUES ('ffd8205f024b4e96a18fce6e0b0001b7', 'ca904d80931f4c368ac1c0919d16b6ae', 80);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `nickName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `userState` int NOT NULL DEFAULT 1 COMMENT '用户状态,1-启用,-1禁用',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `userImg` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'http://news.mydrivers.com/Img/20110518/04481549.png' COMMENT '头像',
  `deptId` bigint NULL DEFAULT NULL COMMENT '部门主键',
  `remarks` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0809c5f69013f4366fa39b3adabc9693', 'test', 'test', '56776db1d622c465cb9c2c0553f6fc13', '14354233243', '12497435422@qq.com', 1, '2021-04-26 02:06:38', 'http://news.mydrivers.com/Img/20110518/04481549.png', 2, 'sdsd');
INSERT INTO `sys_user` VALUES ('397826777191c8254d71c8b2a9fbdfda', '123', '123', 'b2793335f43645fd8e00c7d18e14e05f', '15888888888', 'scc15599065860@163.com', 1, NULL, 'http://news.mydrivers.com/Img/20110518/04481549.png', 1, 'asd');
INSERT INTO `sys_user` VALUES ('39e99300a7ddce4931c690f40f097da9', '从出生', 'cs', '8332f0c581971d55ed37e1e7cd97aab9', '', '86028466@qq.com', 1, '2021-06-05 10:43:44', 'http://news.mydrivers.com/Img/20110518/04481549.png', 16, '');
INSERT INTO `sys_user` VALUES ('3b3d6fc381dd41f5b13b7c0010bc9c85', 'crowd', 'crowd威威', '3af2b8ac442904ce4801ee1fdcfe3b85', '1362222222', 'admin@example.com', 1, '2020-09-25 16:37:36', 'http://wayn.xin:8080/crowd/upload/avatar/2021/04/02/9e0aa7de5c21d538d69e04d40bbc01e1.png', 15, '333');
INSERT INTO `sys_user` VALUES ('5d88b72c6f8ce90a37d2abafea9fb24a', 'test', 'test', '4292bb58be34c59d28a0dcbd11932d49', '', '12497435422@qq.com', 1, '2021-04-26 02:06:24', 'http://news.mydrivers.com/Img/20110518/04481549.png', 2, '');
INSERT INTO `sys_user` VALUES ('6683419cd64d769c878d42e6421310b8', 'test', 'test', '4292bb58be34c59d28a0dcbd11932d49', '', '12497435422@qq.com', 1, '2021-04-26 02:06:11', 'http://news.mydrivers.com/Img/20110518/04481549.png', 2, '');
INSERT INTO `sys_user` VALUES ('6dbc8f3d60ef6ceafd07590a99a8d161', '从出生', '出生', '8332f0c581971d55ed37e1e7cd97aab9', '', '86028466@qq.com', 1, '2021-06-05 10:43:37', 'http://news.mydrivers.com/Img/20110518/04481549.png', 16, '');
INSERT INTO `sys_user` VALUES ('8ab4d886d261c1d016c9b8c350aaeb15', 'cas', 'cs', 'ff08285b565a1d2fd16403a98fe17c66', '', '86028466@qq.com', 1, '2021-06-05 10:43:49', 'http://news.mydrivers.com/Img/20110518/04481549.png', 16, '');
INSERT INTO `sys_user` VALUES ('8bbfead39ca505a5ad83036f1d914a79', 'test', 'test', '4292bb58be34c59d28a0dcbd11932d49', '', '12497435422@qq.com', 1, '2021-04-26 02:06:18', 'http://news.mydrivers.com/Img/20110518/04481549.png', 2, '');
INSERT INTO `sys_user` VALUES ('c79ba431f9f74dfbae585b87b0cde933', 'admin', 'wayn', 'bb214d3c5ece69119bc6e4bf0b71f081', '13617159841', 'wayn1669738430@gmail.com', 1, '2017-09-14 15:02:17', 'http://wayn.xin:8080/crowd/upload/avatar/2021/04/02/bbce46baceb81436ec50554421029131.png', 2, '超级管理员');
INSERT INTO `sys_user` VALUES ('ecd0b125e3e8a74935cb5055e8bc7470', '123', '123', 'b2793335f43645fd8e00c7d18e14e05f', '15888888888', 'scc15599065860@163.com', 1, NULL, 'http://news.mydrivers.com/Img/20110518/04481549.png', 1, 'asd');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `Id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键',
  `roleId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('3c27917dd7ccda7c6253df1d0dab7aeb', '0809c5f69013f4366fa39b3adabc9693', 'ca904d80931f4c368ac1c0919d16b6ae');
INSERT INTO `sys_user_role` VALUES ('52ddc13bea85c9cd941c863eaffa4457', '6683419cd64d769c878d42e6421310b8', '2bfcfcc02fc824ff05b1e27eac990faa');
INSERT INTO `sys_user_role` VALUES ('66f09e00ef5e4f26b763d2179ee95d76', '3b3d6fc381dd41f5b13b7c0010bc9c85', 'ca904d80931f4c368ac1c0919d16b6ae');
INSERT INTO `sys_user_role` VALUES ('7c15b7db58ed013b673f0aec5a4efd83', '6683419cd64d769c878d42e6421310b8', 'ca904d80931f4c368ac1c0919d16b6ae');
INSERT INTO `sys_user_role` VALUES ('9aea67fe17c437505fca7fcde24922da', '5d88b72c6f8ce90a37d2abafea9fb24a', 'ca904d80931f4c368ac1c0919d16b6ae');
INSERT INTO `sys_user_role` VALUES ('a76e6ae51bec2f53acca5a7dcbdb6bef', '8bbfead39ca505a5ad83036f1d914a79', 'ca904d80931f4c368ac1c0919d16b6ae');
INSERT INTO `sys_user_role` VALUES ('a7910d9a1980826f05ee7bd4ebeaccb3', '39e99300a7ddce4931c690f40f097da9', 'b1f9ce5543a049be9f169a3f5e6b72a8');
INSERT INTO `sys_user_role` VALUES ('c956397dde16869d47df9bf1b3ce0ed0', '6dbc8f3d60ef6ceafd07590a99a8d161', 'b1f9ce5543a049be9f169a3f5e6b72a8');
INSERT INTO `sys_user_role` VALUES ('c972d913c1c7fd638bf3d0ae77b0903f', '8ab4d886d261c1d016c9b8c350aaeb15', 'b1f9ce5543a049be9f169a3f5e6b72a8');
INSERT INTO `sys_user_role` VALUES ('ed5f5feb840a4e2e8894ecb2627c9a3a', 'c79ba431f9f74dfbae585b87b0cde933', 'b1f9ce5543a049be9f169a3f5e6b72a8');
INSERT INTO `sys_user_role` VALUES ('f58a03a6501087f1f39c90b402c60c5d', '6683419cd64d769c878d42e6421310b8', '6f6a7b83d962b670ef3e09216bd5ca70');

SET FOREIGN_KEY_CHECKS = 1;
