/*
 Navicat Premium Data Transfer

 Source Server         : win7
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : 192.168.233.128:3306
 Source Schema         : crowdfounding

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 03/07/2019 11:49:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NOT NULL COMMENT '夫部门id',
  `deptName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `deptDesc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` decimal(10, 2) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '产品部', 'cpb', 1.00);
INSERT INTO `sys_dept` VALUES (2, 0, '总销售部', 'ssb', 2.00);
INSERT INTO `sys_dept` VALUES (3, 0, '测试部门', '<script>alert(0);</script>', 3.00);
INSERT INTO `sys_dept` VALUES (4, 1, 'cpb', '', 1.00);
INSERT INTO `sys_dept` VALUES (6, 3, '测试部门1', '测试部门1', 1.00);
INSERT INTO `sys_dept` VALUES (8, 2, '销售部', '', 1.00);
INSERT INTO `sys_dept` VALUES (9, 3, '测试2', '测试2', 2.00);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据值',
  `dictState` int(11) NULL DEFAULT NULL COMMENT '1 启用  -1 禁用',
  `type` int(11) NULL DEFAULT NULL COMMENT '1 字典类型  2 类型对应值',
  `sort` decimal(10, 2) NULL DEFAULT NULL COMMENT '排序（升序）',
  `dictType` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '父级字典类型',
  `createBy` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建者',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updateBy` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新者',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注信息',
  `delFlag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标记 0 存在 1 删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_value`(`value`) USING BTREE,
  INDEX `sys_dict_label`(`name`) USING BTREE,
  INDEX `sys_dict_del_flag`(`delFlag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (123, '状态', 'state', 1, 1, 0.00, NULL, 'admin', '2019-05-16 03:16:46', 'admin', '2019-07-01 11:14:05', '1 启用  -1 禁用', '0');
INSERT INTO `sys_dict` VALUES (124, '启用', '1', 1, 2, 0.00, 'state', 'admin', '2019-05-25 03:18:39', 'admin', '2019-07-01 13:15:11', '', '0');
INSERT INTO `sys_dict` VALUES (125, '禁用', '-1', 1, 2, 1.00, 'state', 'admin', '2019-07-01 03:18:47', 'admin', '2019-07-01 06:46:26', '', '0');
INSERT INTO `sys_dict` VALUES (126, '爱好', 'hobby', 1, 1, 1.00, '0', 'admin', '2019-07-01 06:43:45', 'admin', '2019-07-03 03:45:43', '12', '0');
INSERT INTO `sys_dict` VALUES (127, '游泳', 'swim', 1, 2, 0.00, 'hobby', 'admin', '2019-07-01 06:44:13', NULL, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (129, '菜单类型', 'menuType', 1, 1, 2.00, '0', 'admin', '2019-07-01 13:19:55', NULL, NULL, '类别，1表示目录，2表示菜单，3表示按钮', '0');
INSERT INTO `sys_dict` VALUES (130, '目录', '1', 1, 2, 0.00, 'menuType', 'admin', '2019-07-01 13:20:30', NULL, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (131, '菜单', '2', 1, 2, 1.00, 'menuType', 'admin', '2019-07-01 13:20:40', NULL, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (132, '按钮', '3', 1, 2, 2.00, 'menuType', 'admin', '2019-07-01 13:20:46', 'admin', '2019-07-02 06:11:37', '', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户',
  `operation` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志',
  `moduleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `url` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `requestParams` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '日志时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('0162fa4f87ae499e9929f2006be740c1', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.9285185430769254\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 03:21:13');
INSERT INTO `sys_log` VALUES ('0170e2b7783547aab58a0ad1d6f395aa', 'admin', '更新', '部门管理', '/crowdfounding-web/system/dept/editSave', '{\"id\":\"3\",\"pid\":\"0\",\"pName\":\"顶级节点\",\"deptName\":\"测试部门\",\"deptDesc\":\"<script>alert(0);</script>\",\"sort\":\"3.00\"}', '2019-07-03 02:18:12');
INSERT INTO `sys_log` VALUES ('0260680c4f854b998019b72b6c427e91', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.9390844085489098\",\"deptName\":[\"\"]}', '2019-07-02 06:33:29');
INSERT INTO `sys_log` VALUES ('0290fb9a80514493b97e0d8230864afc', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.28529903036967874\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":\"2019-07-01\",\"endTime\":[\"\"]}', '2019-07-01 14:16:01');
INSERT INTO `sys_log` VALUES ('02e1e36f14244616be68dda5e8c40f43', 'admin', '更新', '用户管理', '/crowdfounding-web/system/user/editSave', '{\"id\":\"8fa3880738f04299af1c6a063433ae71\",\"password\":\"ec1e4f79d6dccda32feb12feaf91afef\",\"userName\":\"ruo\",\"deptId\":\"1\",\"deptName\":\"产品部\",\"userState\":\"1\",\"userStateSwicth\":\"on\",\"roleIds\":\"7aedf9931d8a4ff3bc46c46e811a2304\",\"roleId\":\"7aedf9931d8a4ff3bc46c46e811a2304\"}', '2019-07-01 14:24:20');
INSERT INTO `sys_log` VALUES ('059ea59ca34145eb8be02d89ee227005', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.1416195532787008\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:25:20');
INSERT INTO `sys_log` VALUES ('07a83c65480f400c9e3a448815c84771', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.30850854676349426\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 14:43:48');
INSERT INTO `sys_log` VALUES ('08241af825a741239c2f8f9862befc7d', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.3891917117965815\"}', '2019-07-02 07:11:25');
INSERT INTO `sys_log` VALUES ('0910b20f586a4f60965d1b1e11a4dc50', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 11:30:30');
INSERT INTO `sys_log` VALUES ('0afdad625bff4466991204d83f6cd167', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"userName\",\"sortOrder\":\"asc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"8\",\"userName\":\"admin\",\"userState\":\"1\"}', '2019-06-20 03:34:38');
INSERT INTO `sys_log` VALUES ('0b707bfd82aa4193bdeab8634c67f93a', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 06:43:53');
INSERT INTO `sys_log` VALUES ('0bf509a8fe2c4eb496dfb06246c79c08', 'admin', '更新', '角色管理', '/crowdfounding-web/system/role/editSave', '{\"id\":\"b1f9ce5543a049be9f169a3f5e6b72a8\",\"roleName\":\"超级管理员\",\"roleState\":\"1\",\"roleStateSwicth\":\"on\",\"roleDesc\":\"超级管理员<script>alert(0);</script>\",\"menuIds\":\"23,24,25,43,29,30,34,42,26,27,28,38,41,31,32,33,44,48,49,14,52,53,54,55,46,40,45,4,5,15,16,47,1,12,50,22,37,39,35\"}', '2019-07-03 02:18:30');
INSERT INTO `sys_log` VALUES ('0cf9ec3ba9c94a9e81bcf70dac24f2ca', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.6078196008960599\",\"menuName\":[\"\"],\"type\":\"3\"}', '2019-07-01 13:21:05');
INSERT INTO `sys_log` VALUES ('0d347cc6d5954a45b39f7e958e80eb40', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 11:17:15');
INSERT INTO `sys_log` VALUES ('1016535417f9451caeb5a1ca7fc23896', 'admin', 'select', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.5768238693716441\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 06:01:53');
INSERT INTO `sys_log` VALUES ('10e50b96999f4c96870fe61445b68aae', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.47199688799815953\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":\"2019-07-02\",\"endTime\":[\"\"]}', '2019-07-02 07:22:50');
INSERT INTO `sys_log` VALUES ('1149979a63f143dabdd919d68cc1242b', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 13:51:43');
INSERT INTO `sys_log` VALUES ('119ec55d37014ee7be3530e5b68e0a57', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.7352749535025358\",\"deptName\":[\"\"]}', '2019-07-01 14:46:55');
INSERT INTO `sys_log` VALUES ('12058bb9173240a695e3a82bf2b09391', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:52:20');
INSERT INTO `sys_log` VALUES ('13171857bd3141a0a531698300381fbb', 'admin', 'select', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.34681742800327986\",\"deptName\":[\"\"]}', '2019-07-02 06:01:51');
INSERT INTO `sys_log` VALUES ('13371bdb5a1a4f3daa4e201c04f76500', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.03738019324285702\",\"deptName\":[\"\"]}', '2019-07-02 03:40:04');
INSERT INTO `sys_log` VALUES ('14025fbbe0cf4be19f2f66e818436932', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.711359055347454\",\"deptName\":[\"\"]}', '2019-07-01 12:21:16');
INSERT INTO `sys_log` VALUES ('14ccd140d0b845549dbb3fe7e00bb78e', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.9520590926045356\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:49:51');
INSERT INTO `sys_log` VALUES ('14d25ff579994e7a9d48a7ff4f13abd1', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 06:46:46');
INSERT INTO `sys_log` VALUES ('15508385224042289a132eb3535c4204', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 07:11:31');
INSERT INTO `sys_log` VALUES ('1575742c2e6a4489905801d9385cfad0', 'admin', '更新', '角色管理', '/crowdfounding-web/system/role/editSave', '{\"id\":\"7aedf9931d8a4ff3bc46c46e811a2304\",\"roleName\":\"普通员工\",\"roleState\":\"1\",\"roleStateSwicth\":\"on\",\"roleDesc\":\"普通员工\",\"menuIds\":\"14,22,52,53,54,55,50,12\"}', '2019-07-01 14:24:31');
INSERT INTO `sys_log` VALUES ('17d6e95afb9944d787172b648dd0c8dd', 'admin', '更新', '角色管理', '/crowdfounding-web/system/role/editSave', '{\"id\":\"7aedf9931d8a4ff3bc46c46e811a2304\",\"roleName\":\"普通员工\",\"roleState\":\"1\",\"roleStateSwicth\":\"on\",\"roleDesc\":\"普通员工\",\"menuIds\":\"14,12,52,22,50\"}', '2019-07-01 14:25:30');
INSERT INTO `sys_log` VALUES ('19359c74e8734ed780432b7163d996bf', 'admin', 'select', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.29397355357207\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 06:08:41');
INSERT INTO `sys_log` VALUES ('19eb0a5481f84c76910df1cf596bc850', 'admin', 'select', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 05:53:52');
INSERT INTO `sys_log` VALUES ('1b3ecbe1368f4744ab20e26be188c8c9', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 07:23:22');
INSERT INTO `sys_log` VALUES ('1bf47c28be9f4807bdb0262eb5cc7f50', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:24:10');
INSERT INTO `sys_log` VALUES ('1c8bcbcbc3a141e1947285d0fd5b442d', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 13:51:51');
INSERT INTO `sys_log` VALUES ('1d67a55a825349bf83254f9263443f30', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 14:25:47');
INSERT INTO `sys_log` VALUES ('1e58ae6cec8844f5aaf1b1d0d04e7ff0', 'admin', '新建', '部门管理', '/crowdfounding-web/system/dept/addSave', '{\"pid\":\"3\",\"pName\":\"测试部门\",\"deptName\":\"测试2\",\"deptDesc\":\"测试2\",\"sort\":\"2\"}', '2019-07-01 14:50:26');
INSERT INTO `sys_log` VALUES ('1e8fbb0578054ee083de3e2d1af3728f', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.35413861128650836\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 07:23:23');
INSERT INTO `sys_log` VALUES ('1f11e5b7e7064dd4b1f915706f20733c', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-27 08:39:35');
INSERT INTO `sys_log` VALUES ('22d05cc20ac64e83a4c369f33b83f98b', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.5538730965068801\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:25:52');
INSERT INTO `sys_log` VALUES ('23051c7ba2044e91a0c4905a9a8856e4', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.4921374123120119\",\"deptName\":[\"\"]}', '2019-07-01 14:41:56');
INSERT INTO `sys_log` VALUES ('2336b4dfeae2422482977ef37f90b18c', 'admin', '更新', '用户管理', '/crowdfounding-web/system/user/editSave', '{\"id\":\"c79ba431f9f74dfbae585b87b0cde933\",\"password\":\"038bdaf98f2037b31f1e75b5b4c9b26e\",\"userName\":\"admin\",\"deptId\":\"4\",\"deptName\":\"cpb1\",\"userState\":\"1\",\"userStateSwicth\":\"on\",\"roleIds\":\"7aedf9931d8a4ff3bc46c46e811a2304,b1f9ce5543a049be9f169a3f5e6b72a8\",\"roleId\":[\"7aedf9931d8a4ff3bc46c46e811a2304\",\"b1f9ce5543a049be9f169a3f5e6b72a8\"]}', '2019-06-20 08:41:03');
INSERT INTO `sys_log` VALUES ('23824bb778a94ec091d258f5e298e7d0', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.9881273100426664\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"]}', '2019-07-01 11:27:13');
INSERT INTO `sys_log` VALUES ('239f8701246442d8886237755f98dbe3', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-27 09:03:04');
INSERT INTO `sys_log` VALUES ('2578a7dd39bb4613bd3a1eb539b5836d', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-27 09:02:52');
INSERT INTO `sys_log` VALUES ('298dbb47b6f94174bca5bc674de47714', 'xixi', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.08789569663315766\"}', '2019-07-01 14:48:13');
INSERT INTO `sys_log` VALUES ('29ee5b61baa241ffa4e64b472bd41959', 'admin', '登出', '在线用户管理', '/crowdfounding-web/monitor/online/forceLogout/f6b6a6f3-20be-4e44-a17a-2c39e5dd67d6', '{}', '2019-07-01 14:29:51');
INSERT INTO `sys_log` VALUES ('2a5b0e24f7914236bf16861b3829282b', 'admin', 'select', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":\"admin\",\"userState\":[\"\"],\"startTime\":\"2019-07-02\",\"endTime\":[\"\"]}', '2019-07-02 05:53:08');
INSERT INTO `sys_log` VALUES ('2a69415a84854ea0a7855b3761b5bba0', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:47:00');
INSERT INTO `sys_log` VALUES ('2a9fe561f1304e4ba2cabb21b51a9239', 'admin', 'select', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-02 05:47:45');
INSERT INTO `sys_log` VALUES ('2c5d4cbde7184f01b4845a51b9547ba2', 'admin', 'select', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.04633737675805838\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 06:02:49');
INSERT INTO `sys_log` VALUES ('2d2e9d11ac884b7bb4715a0fe43cb704', 'admin', '登出', '在线用户管理', '/crowdfounding-web/monitor/online/forceLogout/9eff3a0a-fb3b-4b17-b4a2-c822c1bdcb78', '{}', '2019-07-01 14:44:07');
INSERT INTO `sys_log` VALUES ('2e07458377464046b4c876bcd2093f83', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.09734151551979053\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 13:18:54');
INSERT INTO `sys_log` VALUES ('2f061a4143f14a6db82777a457415b5f', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 06:44:02');
INSERT INTO `sys_log` VALUES ('2fdd6335f1aa4e8fa987aecc4b6b14c6', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.11124046312269331\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"]}', '2019-07-01 03:21:15');
INSERT INTO `sys_log` VALUES ('30463fba146348e7a3c5d0168b53a60c', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"6\",\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:22:12');
INSERT INTO `sys_log` VALUES ('3187f97ce77f412d884a7b8f323062ce', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:50:39');
INSERT INTO `sys_log` VALUES ('32fe501a84654b5ea249f1a234fdac08', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.24562642476627383\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 14:51:00');
INSERT INTO `sys_log` VALUES ('335649d8f6c64f8394f52904ca0da972', 'admin', '更新', '角色管理', '/crowdfounding-web/system/role/editSave', '{\"id\":\"ca904d80931f4c368ac1c0919d16b6ae\",\"roleName\":\"管理员\",\"roleState\":\"1\",\"roleStateSwicth\":\"on\",\"roleDesc\":\"管理员\",\"menuIds\":\"14,22,46,40,45,52,53,54,55,50,12,37,39,35\"}', '2019-07-01 14:47:19');
INSERT INTO `sys_log` VALUES ('336a4fa06b3b4d19838b8498143a86e9', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.07548926052589033\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 14:41:55');
INSERT INTO `sys_log` VALUES ('35b5d0bff8b74892b85587a8f9868cd2', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.9692268919861138\",\"deptName\":[\"\"]}', '2019-07-01 12:21:20');
INSERT INTO `sys_log` VALUES ('3781b68d5a1443838ec040d8a3398064', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:23:34');
INSERT INTO `sys_log` VALUES ('37cc8527cb8d424091e1ac0737ebfec9', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"8\",\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:23:38');
INSERT INTO `sys_log` VALUES ('37e1635da61f4e0f8d407694952f29b4', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.5011817589769265\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 13:21:08');
INSERT INTO `sys_log` VALUES ('38a92f6dcf9d439ab66531d4bc2f6d16', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"userName\",\"sortOrder\":\"asc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"8\",\"userName\":\"admin\",\"userState\":\"1\"}', '2019-06-20 03:34:30');
INSERT INTO `sys_log` VALUES ('38bc191bdf7344468a8e29f3589a472e', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"4\",\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 06:25:38');
INSERT INTO `sys_log` VALUES ('3944d46606114ae59438bf5161d50403', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"1\",\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:23:39');
INSERT INTO `sys_log` VALUES ('3952cfdb05d24db49bf6a0ff7ec35e38', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', '{\"userName\":\"admin\",\"password\":\"123456\"}', '2019-06-20 08:40:06');
INSERT INTO `sys_log` VALUES ('3a3890ce17f04bae95ec9e4fdf42361d', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.8524070714471068\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 14:16:17');
INSERT INTO `sys_log` VALUES ('3b7b0f36f1584cdc87df6222191e9dde', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.5002188709676798\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:24:24');
INSERT INTO `sys_log` VALUES ('3bc12224c073466583172c17fc0be9a9', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-27 13:46:53');
INSERT INTO `sys_log` VALUES ('3c9924cefbbb43beab68f93220d6bd17', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.7031069662777258\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-03 03:46:01');
INSERT INTO `sys_log` VALUES ('40effda6b3174a60814bc1de2c4f7d7d', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-03 02:16:35');
INSERT INTO `sys_log` VALUES ('4182d651ca2d4d3bb501663acf78edb9', 'admin', 'select', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 05:50:12');
INSERT INTO `sys_log` VALUES ('41a27646f2734fccba4e96701fb05c96', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 12:19:34');
INSERT INTO `sys_log` VALUES ('4212007ead834d4ab0879b19e16838ef', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 04:29:36');
INSERT INTO `sys_log` VALUES ('421ca3e2d8ae47c484a54e3433aa7e04', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.6871471428699898\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-03 02:18:30');
INSERT INTO `sys_log` VALUES ('43d1a3bef3e5497ba6de0ac7f4c49a21', 'admin', 'select', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":\"admin\",\"userState\":[\"\"],\"startTime\":\"2019-07-02\",\"endTime\":[\"\"]}', '2019-07-02 05:53:04');
INSERT INTO `sys_log` VALUES ('444f12ef27f5488695d50418366f4d59', 'admin', 'select', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":\"admin\",\"userState\":[\"\"],\"startTime\":\"2019-07-02\",\"endTime\":[\"\"]}', '2019-07-02 05:53:04');
INSERT INTO `sys_log` VALUES ('44645267fcdb4a2189747c74971501a7', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.34944272117637865\"}', '2019-07-01 14:41:41');
INSERT INTO `sys_log` VALUES ('4481c764abbd4cfba774a257e626b4d6', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:41:48');
INSERT INTO `sys_log` VALUES ('448fe0b0bc7f4cc7970599dab5759e8b', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.16181044570347236\",\"deptName\":[\"\"]}', '2019-07-01 14:50:11');
INSERT INTO `sys_log` VALUES ('4531189c645642d9a3e15241100b0b3d', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 13:12:24');
INSERT INTO `sys_log` VALUES ('4533ea88bc3d4566aa9feed60bfd7d16', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":\"2019-07-01\"}', '2019-07-01 14:13:04');
INSERT INTO `sys_log` VALUES ('45ac7ca0c0a04757a7269186ba412e78', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-03 03:46:00');
INSERT INTO `sys_log` VALUES ('4845f2adf032429bba609d3226a2bfed', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"8\",\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:49:52');
INSERT INTO `sys_log` VALUES ('48ddff1f80cd42c89ee608bd9ce1cab8', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 07:46:59');
INSERT INTO `sys_log` VALUES ('490e146962c140d5a025de1a1c6703d9', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"userState\":\"1\"}', '2019-06-20 03:33:30');
INSERT INTO `sys_log` VALUES ('4a898be9e67a48fba1bb352aeca24b63', 'admin', 'select', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.8638041704832979\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 05:50:16');
INSERT INTO `sys_log` VALUES ('4b1cdef6f3ed496e8806471c0b28836f', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 06:05:06');
INSERT INTO `sys_log` VALUES ('4b7642b765184e7991841823edb0fc56', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 06:21:39');
INSERT INTO `sys_log` VALUES ('4bfd3176b5174ee2be6440ea5b41fa40', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 14:49:11');
INSERT INTO `sys_log` VALUES ('4de6d0b6c49b41508d97b3c0fa6350fa', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.29079879124645025\"}', '2019-07-01 14:50:50');
INSERT INTO `sys_log` VALUES ('4ea18ebc56c9442baa1784da93e80219', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.14388570205734852\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 13:19:00');
INSERT INTO `sys_log` VALUES ('4fb7b029b91047a6ab3afc7cfc207183', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:25:52');
INSERT INTO `sys_log` VALUES ('509eaf4188044cf78cef7e0f120ab5ff', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.82772118584498\",\"deptName\":[\"\"]}', '2019-07-03 02:18:04');
INSERT INTO `sys_log` VALUES ('520ee1d8c9764f99be57850d7f2ac4c4', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 14:28:30');
INSERT INTO `sys_log` VALUES ('528d703d22d44a9bb6edea7317f19ee4', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:23:57');
INSERT INTO `sys_log` VALUES ('52cdac37f87645be803af8f6e3986689', 'admin', 'select', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.18649723735713253\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 06:01:52');
INSERT INTO `sys_log` VALUES ('55ad991bee104307b7a10a12997acd77', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 06:06:33');
INSERT INTO `sys_log` VALUES ('5665eaea0d8f47278873060a10c8467b', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 14:29:38');
INSERT INTO `sys_log` VALUES ('577afb128bcd4576bcadcf12f97efcf2', 'admin', 'select', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 06:08:52');
INSERT INTO `sys_log` VALUES ('57a4710fef6343deb7323613e2ddeb71', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.2332964395706787\",\"deptName\":[\"\"]}', '2019-07-01 12:21:19');
INSERT INTO `sys_log` VALUES ('59bd12f7ea1f46aea050186b332fe039', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 10:50:40');
INSERT INTO `sys_log` VALUES ('59e57dda9f204088be08a6376de89a4b', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-02 07:20:35');
INSERT INTO `sys_log` VALUES ('5b1081e4b7354d379913146bcf548590', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.558837053765989\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 03:40:03');
INSERT INTO `sys_log` VALUES ('5b3c2d246f7a48ebb358a5198638d59c', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 05:44:15');
INSERT INTO `sys_log` VALUES ('5d1b01c9e74f4e6c89df0bf0df481d61', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 06:21:51');
INSERT INTO `sys_log` VALUES ('5f759ff7faea45518423a456a083bbfa', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.14002546372490965\",\"deptName\":[\"\"]}', '2019-07-03 02:18:12');
INSERT INTO `sys_log` VALUES ('60c87499bbd84fa294c99f8f7211b2be', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"userName\",\"sortOrder\":\"asc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"userState\":\"1\"}', '2019-06-20 03:33:57');
INSERT INTO `sys_log` VALUES ('610eb6ef5e754efaa83fdf56e25223d8', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.8894520258884691\",\"deptName\":[\"\"]}', '2019-07-01 13:21:17');
INSERT INTO `sys_log` VALUES ('6110771ed9a94137b450086255f7f789', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.4564780545582159\"}', '2019-07-02 06:33:33');
INSERT INTO `sys_log` VALUES ('614ce813bd3b43df9c385d76969e7b8f', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.44199021868185917\"}', '2019-07-01 11:06:36');
INSERT INTO `sys_log` VALUES ('6375c86c8a4342a3af45f592bd6afe3f', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 13:51:38');
INSERT INTO `sys_log` VALUES ('63874ef802574505a9e55abf3b346982', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 08:40:10');
INSERT INTO `sys_log` VALUES ('63cb581e9f2048db94ca23049890eb2a', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 14:12:04');
INSERT INTO `sys_log` VALUES ('64e34e5416754781a301217e1de59112', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 11:23:08');
INSERT INTO `sys_log` VALUES ('65319f31b8bb4a0c989fa3b63544915a', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.4816251475685529\",\"deptName\":[\"\"]}', '2019-07-01 14:43:49');
INSERT INTO `sys_log` VALUES ('65394b5a86504cf3986a2c6e06fed7c7', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.022234202815654136\"}', '2019-07-01 14:30:09');
INSERT INTO `sys_log` VALUES ('6596e6a25dbb4858ad98a4d43881d484', 'admin', '更新', '角色管理', '/crowdfounding-web/system/role/editSave', '{\"id\":\"b1f9ce5543a049be9f169a3f5e6b72a8\",\"roleName\":\"超级管理员\",\"roleState\":\"1\",\"roleStateSwicth\":\"on\",\"roleDesc\":\"超级管理员\",\"menuIds\":\"23,24,25,43,29,30,34,42,26,27,28,38,41,31,32,33,44,48,49,14,52,53,54,55,46,40,45,4,5,15,16,47,1,12,50,22,37,39,35\"}', '2019-07-03 02:18:37');
INSERT INTO `sys_log` VALUES ('65b2321204a94a8a966ddb1d8faa10b0', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.1935902543817034\",\"deptName\":[\"\"]}', '2019-07-03 02:18:17');
INSERT INTO `sys_log` VALUES ('65e412c32e424f15a65543abea2787f6', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"9\",\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:50:40');
INSERT INTO `sys_log` VALUES ('65e9094282f543cc8f7eb5aa2fdafe3c', 'ruo', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 14:25:34');
INSERT INTO `sys_log` VALUES ('66a4adc9a3fd4fc3b567c6558574ce36', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:49:53');
INSERT INTO `sys_log` VALUES ('66c20cad4f0545eb90b8e7d4ecb4aa1d', 'admin', 'select', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.16922834955884158\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 05:50:15');
INSERT INTO `sys_log` VALUES ('6cfa1a9ed8f94067a3f7e1606ee00b4c', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:24:20');
INSERT INTO `sys_log` VALUES ('6d8ceb903f3e4254b640b47c6e5ecd78', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"4\",\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:19:49');
INSERT INTO `sys_log` VALUES ('6ef8b1e7322f41d3a682ebc97fe0cd58', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 03:14:06');
INSERT INTO `sys_log` VALUES ('6fea04d87eea4745bf76bee1ee5db16f', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.47199688799815953\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 07:23:03');
INSERT INTO `sys_log` VALUES ('704a24dc0cef4c118c0c22dda35ac802', 'admin', '更新', '部门管理', '/crowdfounding-web/system/dept/editSave', '{\"id\":\"3\",\"pid\":\"0\",\"pName\":\"顶级节点\",\"deptName\":\"测试部门\",\"deptDesc\":\"<script>alert(0);</script>\",\"sort\":\"3.00\"}', '2019-07-03 02:18:17');
INSERT INTO `sys_log` VALUES ('720f3eb24d3647c1ae2a57764feaa43e', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.472690032129925\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 06:33:28');
INSERT INTO `sys_log` VALUES ('72d7054f4d614703921977572a689a3e', 'admin', '更新', '菜单管理', '/crowdfounding-web/system/menu/editSave', '{\"id\":\"54\",\"pid\":\"50\",\"pName\":\"字典管理\",\"type\":\"3\",\"menuName\":\"删除\",\"url\":[\"\"],\"resource\":\"commom:dict:remove\",\"sort\":\"2.00\",\"icon\":[\"\"]}', '2019-07-01 14:26:10');
INSERT INTO `sys_log` VALUES ('735203ab58fa4565abddc48ca43f813b', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.1416195532787008\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:25:30');
INSERT INTO `sys_log` VALUES ('73af094f7e554b8c8dcde2c18063c32a', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:13:05');
INSERT INTO `sys_log` VALUES ('7555d1f7ddc04887a4ca42411321745e', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.47199688799815953\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 07:20:38');
INSERT INTO `sys_log` VALUES ('76ea15f7e1724a1f8a7ad8cc190922b5', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:19:42');
INSERT INTO `sys_log` VALUES ('76ef9b01ce7b4a4289d264417087dbca', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"userName\",\"sortOrder\":\"asc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"8\",\"userName\":\"admin\",\"userState\":\"1\"}', '2019-06-20 03:34:29');
INSERT INTO `sys_log` VALUES ('76f0495dd24448209b0c2e67bebbf0c6', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.6309920379946525\"}', '2019-07-02 07:23:17');
INSERT INTO `sys_log` VALUES ('7774702a161c477298fafaa293a40767', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 05:44:18');
INSERT INTO `sys_log` VALUES ('7909344548e94ab887752304ea34328b', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 06:25:26');
INSERT INTO `sys_log` VALUES ('793b5f3cf68642c58575dbcc0326a31a', 'admin', '更新', '用户管理', '/crowdfounding-web/system/user/editSave', '{\"id\":\"c79ba431f9f74dfbae585b87b0cde933\",\"password\":\"038bdaf98f2037b31f1e75b5b4c9b26e\",\"userName\":\"admin\",\"deptId\":\"1\",\"deptName\":\"产品部\",\"userState\":\"1\",\"userStateSwicth\":\"on\",\"roleIds\":\"7aedf9931d8a4ff3bc46c46e811a2304,b1f9ce5543a049be9f169a3f5e6b72a8\",\"roleId\":[\"7aedf9931d8a4ff3bc46c46e811a2304\",\"b1f9ce5543a049be9f169a3f5e6b72a8\"]}', '2019-06-20 06:21:39');
INSERT INTO `sys_log` VALUES ('7d0688a0460a435e967656d8608736c3', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 04:32:22');
INSERT INTO `sys_log` VALUES ('7dd0cf2b55214354a03c0dab75ed60e7', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.3363330257490569\"}', '2019-07-01 14:29:42');
INSERT INTO `sys_log` VALUES ('808c479af25e4f748fb56a9868d588f3', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 04:29:23');
INSERT INTO `sys_log` VALUES ('8098f1ba67e64cb19f990e05e7019705', 'ruo', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 14:25:04');
INSERT INTO `sys_log` VALUES ('80bad18926574c8cb7473a58ec5f9bdd', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.22814234719481963\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:26:30');
INSERT INTO `sys_log` VALUES ('810fa4c5aab5498ab962092de1e24e97', 'admin', 'select', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":\"admin\",\"userState\":[\"\"],\"startTime\":\"2019-07-02\",\"endTime\":[\"\"]}', '2019-07-02 05:53:04');
INSERT INTO `sys_log` VALUES ('812900708f0b4fbda819a500af59f28b', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.8996015945468616\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 12:20:59');
INSERT INTO `sys_log` VALUES ('82ca7af448e04003b56c428feb8308a1', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 03:40:00');
INSERT INTO `sys_log` VALUES ('82d060ff727e492ca854f23f1e60fa44', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.9859617989241327\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 13:19:00');
INSERT INTO `sys_log` VALUES ('8596bc4da03740fa8b667829c005fd9d', 'admin', 'select', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.545481563640863\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 06:08:53');
INSERT INTO `sys_log` VALUES ('85b8340fd5f74e318bb8b82954d026b2', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.7497375156848511\",\"deptName\":[\"\"]}', '2019-07-01 14:50:26');
INSERT INTO `sys_log` VALUES ('86fde761564d444bbc7f9d63d0032a6e', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-02 07:11:23');
INSERT INTO `sys_log` VALUES ('87880869fb924393a4842330be1395b5', 'admin', '新建', '用户管理', '/crowdfounding-web/system/user/addSave', '{\"userName\":\"xixix\",\"password\":\"123456\",\"deptId\":\"1\",\"deptName\":\"产品部\",\"userState\":\"1\",\"userStateSwicth\":\"on\",\"roleIds\":\"7aedf9931d8a4ff3bc46c46e811a2304\",\"roleId\":\"7aedf9931d8a4ff3bc46c46e811a2304\"}', '2019-07-01 14:23:52');
INSERT INTO `sys_log` VALUES ('88c346a28fc942278675636ad2998038', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 11:06:38');
INSERT INTO `sys_log` VALUES ('892efdd450af4a2ab0e592f8ac20861a', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"6\",\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:49:53');
INSERT INTO `sys_log` VALUES ('89753aca9ad1416a91139446700a0de6', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"1\",\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:22:15');
INSERT INTO `sys_log` VALUES ('8e4c9ab536bb462287063c3f4bab9ca6', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.538013812405653\",\"deptName\":[\"\"]}', '2019-07-01 14:50:17');
INSERT INTO `sys_log` VALUES ('8e69172e08f84a97ada83464f91b7e02', 'admin', 'select', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":\"admin\",\"userState\":[\"\"],\"startTime\":\"2019-07-02\",\"endTime\":[\"\"]}', '2019-07-02 05:53:05');
INSERT INTO `sys_log` VALUES ('8f6f821f274e4a0aa5ead612586cc82d', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.2959463671351348\",\"menuName\":[\"\"],\"type\":\"2\"}', '2019-07-01 13:21:08');
INSERT INTO `sys_log` VALUES ('90efdbb390e44080b19b0f85fe674c52', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:23:54');
INSERT INTO `sys_log` VALUES ('9211b1b9615d4f62852db39fa22e40ab', 'admin', 'select', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.39083698095859853\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 06:08:29');
INSERT INTO `sys_log` VALUES ('929fd21362744ce3ad8930e380836738', 'admin', 'select', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.9687656227004111\",\"deptName\":[\"\"]}', '2019-07-02 06:03:14');
INSERT INTO `sys_log` VALUES ('93d6224851534c1da9c64e0b552e6cb8', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 06:43:43');
INSERT INTO `sys_log` VALUES ('93ee83880158424da01a8ba96cfc23c9', 'admin', '更新', '部门管理', '/crowdfounding-web/system/dept/editSave', '{\"id\":\"3\",\"pid\":\"0\",\"pName\":\"顶级节点\",\"deptName\":\"测试部门\",\"deptDesc\":\"<script>alert(\'sb\')</script>\",\"sort\":\"3.00\"}', '2019-07-01 14:50:11');
INSERT INTO `sys_log` VALUES ('94878b1f3cc841ddb51e6b2a9cddecd8', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.7509210408039901\"}', '2019-07-01 03:21:18');
INSERT INTO `sys_log` VALUES ('956c9b7037724d97b5c47f056fb69f97', 'xixi', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 14:47:31');
INSERT INTO `sys_log` VALUES ('969ad5a908c642cc889ece95013bd649', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.7974994141501943\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 03:40:01');
INSERT INTO `sys_log` VALUES ('96ebae843c0a4adf8d82d9043186f31f', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.3363330257490569\"}', '2019-07-01 14:29:53');
INSERT INTO `sys_log` VALUES ('96f0a664c4954fc1bd8348728e2f80d1', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"6\",\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:23:38');
INSERT INTO `sys_log` VALUES ('996c6a8b1dc34394add40bf33ca3d8c3', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.6270922606191704\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 11:27:15');
INSERT INTO `sys_log` VALUES ('9bbea627e1be464ab4a3d5e780a93781', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"6\",\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:19:48');
INSERT INTO `sys_log` VALUES ('9bd6bc1d75d049dcb1b855cb8a972a54', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:43:47');
INSERT INTO `sys_log` VALUES ('9d999115618b461d9ba37eee6e396659', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 06:33:27');
INSERT INTO `sys_log` VALUES ('9ec80d0aa3b04a26ac545fb282d20c81', 'admin', '更新', '用户管理', '/crowdfounding-web/system/user/editSave', '{\"id\":\"61eb24f91a1049afa78716d23c79e080\",\"password\":\"dc2e0e1ad4c8acc62f87cbbf7a0e05da\",\"userName\":\"xixi\",\"deptId\":\"6\",\"deptName\":\"测试部门\",\"userState\":\"1\",\"userStateSwicth\":\"on\",\"roleIds\":\"7aedf9931d8a4ff3bc46c46e811a2304,ca904d80931f4c368ac1c0919d16b6ae\",\"roleId\":[\"7aedf9931d8a4ff3bc46c46e811a2304\",\"ca904d80931f4c368ac1c0919d16b6ae\"]}', '2019-06-27 09:03:04');
INSERT INTO `sys_log` VALUES ('9eec50de917b40a1a5fcb5aa977b1531', 'admin', 'select', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.12901282446115414\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 06:11:30');
INSERT INTO `sys_log` VALUES ('a084198139d74ee89352251e7e2fb193', 'admin', '删除', '用户管理', '/crowdfounding-web/system/user/remove/2308a898eaf34e108ef39e8ff3700a74', '{}', '2019-06-27 13:47:12');
INSERT INTO `sys_log` VALUES ('a0a75b8655e947f6b4863aa6d31d71c1', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.024487610739628574\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:47:19');
INSERT INTO `sys_log` VALUES ('a1047e6e459844e9b8008fb25b38b16d', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.8001759520673626\",\"deptName\":[\"\"]}', '2019-07-03 03:46:03');
INSERT INTO `sys_log` VALUES ('a1f90498db9f4876b2236901cee9320c', 'xixi', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.7162923202619564\"}', '2019-07-01 14:47:35');
INSERT INTO `sys_log` VALUES ('a3c2a5714c514ab48e0272cd476a0453', 'admin', 'select', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.7829878434136768\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 06:08:37');
INSERT INTO `sys_log` VALUES ('a4f0fbb124d04943ae37edff4833a539', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.28529903036967874\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:16:06');
INSERT INTO `sys_log` VALUES ('a52b117d57dd4c3db398fbfb070b8f49', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', '{\"userName\":\"admin\",\"password\":\"123456\"}', '2019-06-20 07:44:45');
INSERT INTO `sys_log` VALUES ('a55addb712f24fcca95b2454f3fb0540', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"userName\",\"sortOrder\":\"asc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"8\",\"userName\":\"admin\",\"userState\":\"1\"}', '2019-06-20 03:34:38');
INSERT INTO `sys_log` VALUES ('a5d360904e19459a900ad2ed7469edf1', 'admin', 'select', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.18159701994703092\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 06:08:54');
INSERT INTO `sys_log` VALUES ('a649780a631f4ea694df2b246c8577d6', 'admin', 'select', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.36055237440893295\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 05:52:39');
INSERT INTO `sys_log` VALUES ('a7f984b54a3e43bd9b72a1786fd1531a', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.5002188709676798\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:24:31');
INSERT INTO `sys_log` VALUES ('a819f462fbea4e72a04d0dee21245845', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.024487610739628574\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:46:59');
INSERT INTO `sys_log` VALUES ('a92066288bdd495d81954cd48d6d5320', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.7477107035812096\"}', '2019-07-01 03:20:08');
INSERT INTO `sys_log` VALUES ('a97d5a21f5724940ae7aeec8018488ef', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 13:12:29');
INSERT INTO `sys_log` VALUES ('ab194e87ed2340c89e5c820218527dfc', 'admin', 'select', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.13001505982559158\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 06:06:52');
INSERT INTO `sys_log` VALUES ('ab91683b868a466cb17f8f50c55e00b3', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:20:55');
INSERT INTO `sys_log` VALUES ('ab931d5568cd4f1f95303d8a1a64cc8d', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 04:29:53');
INSERT INTO `sys_log` VALUES ('ac88803097d4487e8f5c6333ba5b9070', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:49:50');
INSERT INTO `sys_log` VALUES ('af14635e00fc4b0f9db93410c548d803', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-03 02:16:39');
INSERT INTO `sys_log` VALUES ('af7f5482b08b4b60b5ffcd0045a5af2f', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 06:20:51');
INSERT INTO `sys_log` VALUES ('b034df9b17ef443b9d237695ac9888ad', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"8\",\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:22:13');
INSERT INTO `sys_log` VALUES ('b03a812a53d6436b80ede9a4f46aa157', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 06:20:49');
INSERT INTO `sys_log` VALUES ('b0bab053a9ca494895b89b8387248a48', 'admin', 'select', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.8546966743161033\",\"deptName\":[\"\"]}', '2019-07-02 05:50:18');
INSERT INTO `sys_log` VALUES ('b1474330d8a544008c2cc01dc3e23fea', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:24:51');
INSERT INTO `sys_log` VALUES ('b1786fb1cd0b4f2994e66145e2cfbbc0', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.6053773977316934\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-03 02:17:54');
INSERT INTO `sys_log` VALUES ('b548a5ea8ad44caeba8bb3fcbd3548d8', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:22:16');
INSERT INTO `sys_log` VALUES ('b6dad17861224802868114606373e2b2', 'admin', 'update', '菜单管理', '/crowdfounding-web/system/menu/editSave', '{\"id\":\"37\",\"pid\":\"35\",\"pName\":\"系统监控\",\"type\":\"2\",\"menuName\":\"服务监控\",\"url\":\"/system/monitor\",\"resource\":[\"\"],\"sort\":\"1.00\",\"icon\":\"fa fa-copyright\"}', '2019-07-02 06:08:37');
INSERT INTO `sys_log` VALUES ('b6e44bb698ac4c93962b8652fa71395e', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.19182921204192982\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 06:33:28');
INSERT INTO `sys_log` VALUES ('b6ff6cdec47b48d3964a8c98a6080dd0', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 07:46:11');
INSERT INTO `sys_log` VALUES ('b8819902e7a34dee9210b9d2b73c4c70', 'admin', 'select', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.7580664780561066\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 05:52:39');
INSERT INTO `sys_log` VALUES ('b9131d99aff04fd0bcb3d3e0fc84440e', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.47995431784728826\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 14:25:53');
INSERT INTO `sys_log` VALUES ('ba68875efcab4e458fb39955e6003102', 'admin', 'select', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.9826968541488059\",\"deptName\":[\"\"]}', '2019-07-02 06:08:55');
INSERT INTO `sys_log` VALUES ('ba83cc426d3b43d99595f9a07c6d0537', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"userName\",\"sortOrder\":\"asc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"8\",\"userName\":\"admin\",\"userState\":\"1\"}', '2019-06-20 03:34:28');
INSERT INTO `sys_log` VALUES ('bae9311cef864101b07aac7bdc216c33', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.28529903036967874\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:16:02');
INSERT INTO `sys_log` VALUES ('bcf6fff21b8d4c069cdd3d84740770fb', 'admin', 'select', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.20657116279547516\",\"deptName\":[\"\"]}', '2019-07-02 05:52:40');
INSERT INTO `sys_log` VALUES ('bd8a3592ba8848979f0251df3e4a3b84', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 08:41:03');
INSERT INTO `sys_log` VALUES ('be2f889e66cf43669c9969433adec620', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-27 13:47:09');
INSERT INTO `sys_log` VALUES ('be33ed430e7b426ea21639b06229de02', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.6871471428699898\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-03 02:17:53');
INSERT INTO `sys_log` VALUES ('c12893da7d474df9958ac2511b25d3de', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 03:21:02');
INSERT INTO `sys_log` VALUES ('c2566c16aced492da9e1a84fc1c5ff25', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"4\",\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:50:42');
INSERT INTO `sys_log` VALUES ('c2574ae6d92e4523bbd369caeed66bdf', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 05:45:43');
INSERT INTO `sys_log` VALUES ('c38f90e84639478a838a52e3ee20d990', 'admin', 'select', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 05:52:38');
INSERT INTO `sys_log` VALUES ('c47cb2ae367a4195a560763efbc55bed', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.8755275888153122\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:43:48');
INSERT INTO `sys_log` VALUES ('c5025eb920f643de8af52d94e44bfc01', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 06:46:40');
INSERT INTO `sys_log` VALUES ('c5077e231c474cca8f9eb2afed48d6b8', 'admin', 'select', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.2321698516292534\"}', '2019-07-02 05:52:35');
INSERT INTO `sys_log` VALUES ('c522e1e0a4984da991f7bdcc66740d99', 'admin', '登出', '在线用户管理', '/crowdfounding-web/monitor/online/forceLogout/f88c0bd3-683f-4ca1-91ff-360506e5bce9', '{}', '2019-07-01 14:28:44');
INSERT INTO `sys_log` VALUES ('c78d68cf660d40f6a2c99de8c6d53b5d', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.3998781371051703\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 14:26:10');
INSERT INTO `sys_log` VALUES ('c7aa3a838f6b4c23bc799942b6caf948', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 14:25:17');
INSERT INTO `sys_log` VALUES ('c8d5f48630724a4f94a752f7f6a1a016', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.28529903036967874\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":\"2019-07-01\"}', '2019-07-01 14:16:05');
INSERT INTO `sys_log` VALUES ('c95e469a3b2e4e7bb20a379825532827', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:19:46');
INSERT INTO `sys_log` VALUES ('ca780c7ff3864ae6a43b75bd480740ed', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"8\",\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:19:48');
INSERT INTO `sys_log` VALUES ('cb268497568f4b73bfef1fd60126f785', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 14:49:34');
INSERT INTO `sys_log` VALUES ('cc398f3bca2c4514a66a3333a4f0a193', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 08:40:06');
INSERT INTO `sys_log` VALUES ('cc598891325240b4aa90f11873f5dc0b', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.29540165606916813\",\"deptName\":[\"\"]}', '2019-07-01 14:46:53');
INSERT INTO `sys_log` VALUES ('cd8030adc55b48fd835ab6027b3c4303', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"4\",\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:22:14');
INSERT INTO `sys_log` VALUES ('cd8903a07ac24f96aeb58249019ed844', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.28529903036967874\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:15:59');
INSERT INTO `sys_log` VALUES ('cdeda188cc154cc3b1ce304999f4af5e', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:25:19');
INSERT INTO `sys_log` VALUES ('cf65ca27b252484fa56cc78cb84ffad2', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:24:58');
INSERT INTO `sys_log` VALUES ('d1a895e64ef443dc89a475db43ac54fa', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 13:18:50');
INSERT INTO `sys_log` VALUES ('d2059e70da26457495a1b633c442cdbf', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 06:25:28');
INSERT INTO `sys_log` VALUES ('d285d79bc2e847aeb0d61730d55db100', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.44848367731889227\",\"deptName\":[\"\"]}', '2019-07-01 14:23:35');
INSERT INTO `sys_log` VALUES ('d3b9266b84074fe38c553acadefb5e10', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:51:29');
INSERT INTO `sys_log` VALUES ('d4b24b29a54b4505aa91bb691dc4dacd', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.8792439562224954\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 14:46:48');
INSERT INTO `sys_log` VALUES ('d591917f4884404dae778ec62d3d2ce2', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"8\",\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:50:41');
INSERT INTO `sys_log` VALUES ('d5ec5d48c1c846f59cdd9dafc5916a49', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.40986295570239806\",\"deptName\":[\"\"]}', '2019-07-01 12:21:21');
INSERT INTO `sys_log` VALUES ('d6141f46adbd4d8e8f7ad7995c82797f', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 13:51:40');
INSERT INTO `sys_log` VALUES ('d75328ecfd4b424a83b260e647f191a4', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.05807361611969086\"}', '2019-07-01 14:28:33');
INSERT INTO `sys_log` VALUES ('d7e1e3789ab747f89c6760d4413a7f43', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-27 09:02:59');
INSERT INTO `sys_log` VALUES ('d7f6b2d6cd9144b49706a9eacc39346b', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 04:29:43');
INSERT INTO `sys_log` VALUES ('d814788e260245a8b8e175bf62e371a5', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"6\",\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:50:40');
INSERT INTO `sys_log` VALUES ('d8149d45e9ba4955a6daf7423b3fef87', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"userName\",\"sortOrder\":\"asc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"6\",\"userName\":\"admin\",\"userState\":\"1\"}', '2019-06-20 03:34:24');
INSERT INTO `sys_log` VALUES ('d9ae08cca0c74a3285ef58b8314f4d4f', 'admin', '更新', '用户管理', '/crowdfounding-web/system/user/editSave', '{\"id\":\"61eb24f91a1049afa78716d23c79e080\",\"password\":\"dc2e0e1ad4c8acc62f87cbbf7a0e05da\",\"userName\":\"xixi\",\"deptId\":\"6\",\"deptName\":\"测试部门\",\"userState\":\"1\",\"userStateSwicth\":\"on\",\"roleIds\":\"7aedf9931d8a4ff3bc46c46e811a2304\",\"roleId\":\"7aedf9931d8a4ff3bc46c46e811a2304\"}', '2019-06-20 06:21:36');
INSERT INTO `sys_log` VALUES ('d9be23df3aa14cfabb04cfce7f4858a4', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.34517071040450475\",\"deptName\":[\"\"]}', '2019-07-01 14:49:55');
INSERT INTO `sys_log` VALUES ('ddfd1ac5766d495db5552fd00d41fa08', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 14:30:06');
INSERT INTO `sys_log` VALUES ('df1f6099cc674e7990637527ca88b846', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-27 08:27:25');
INSERT INTO `sys_log` VALUES ('df26863c9d134e1b8e955c1dbbd82d8f', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.8927770797297494\"}', '2019-07-01 14:43:52');
INSERT INTO `sys_log` VALUES ('df4e189848ee4a5b82338c598c7d7711', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:19:43');
INSERT INTO `sys_log` VALUES ('dff52404410e43579618ed691cfa09bb', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.5188181733728956\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 12:21:05');
INSERT INTO `sys_log` VALUES ('e0821bf94c3e4efdba481a5499e0c5e3', 'admin', '新建', '用户管理', '/crowdfounding-web/system/user/addSave', '{\"userName\":\"333\",\"password\":\"123456\",\"deptId\":\"4\",\"deptName\":\"cpb\",\"userState\":\"1\",\"userStateSwicth\":\"on\",\"roleIds\":[\"\"]}', '2019-06-27 13:47:09');
INSERT INTO `sys_log` VALUES ('e1a1166a5ef04b80b9b27749e1657ed4', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-06-20 06:21:36');
INSERT INTO `sys_log` VALUES ('e1ad89a875e240cfb19d49ce7e9c00f5', 'admin', '更新', '部门管理', '/crowdfounding-web/system/dept/editSave', '{\"id\":\"6\",\"pid\":\"3\",\"pName\":\"测试部门\",\"deptName\":\"测试部门1\",\"deptDesc\":\"测试部门1\",\"sort\":\"1.00\"}', '2019-07-01 14:50:17');
INSERT INTO `sys_log` VALUES ('e1ee243dcbfa47e685ecae1c94d0004d', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"2\",\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 12:22:14');
INSERT INTO `sys_log` VALUES ('e21cab00b36542c2ac538760e44070ec', 'admin', 'select', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.2984348281896274\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-02 06:02:30');
INSERT INTO `sys_log` VALUES ('e2f0fdb081a34869bdd4d807a4ea8e55', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"4\",\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:23:39');
INSERT INTO `sys_log` VALUES ('e374af3aa202404f9b624948b31b64b4', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 13:51:45');
INSERT INTO `sys_log` VALUES ('e506b5c3651344fb8ed239fe91c07aed', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.47199688799815953\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":\"2019-07-02\",\"endTime\":[\"\"]}', '2019-07-02 07:22:55');
INSERT INTO `sys_log` VALUES ('e56894d59f77431798313edab5b6c954', 'admin', 'select', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.7138035014954944\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-02 06:03:14');
INSERT INTO `sys_log` VALUES ('e5a32cb7618a4c61ace2ccda99a10695', 'admin', '查询', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-01 14:24:48');
INSERT INTO `sys_log` VALUES ('e607999c09bb40c3933134831b1b2f82', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 03:25:31');
INSERT INTO `sys_log` VALUES ('e723a2c25ba4469dbbf33d36737687f6', 'admin', 'select', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.6460201728552699\",\"deptName\":[\"\"]}', '2019-07-02 06:11:29');
INSERT INTO `sys_log` VALUES ('e7d273d43186433098ded97e3d9a62ad', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.7510154244469196\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-01 13:21:02');
INSERT INTO `sys_log` VALUES ('e8a17629c4284f7781d8728da64094e1', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"1\",\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:23:52');
INSERT INTO `sys_log` VALUES ('e8ca32df613d440e9a67858d82906d42', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.144652311533364\",\"menuName\":[\"\"],\"type\":\"2\"}', '2019-07-01 12:21:04');
INSERT INTO `sys_log` VALUES ('e96051be609643babbf286eebfb9e96d', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.5184478276610187\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"]}', '2019-07-01 13:18:51');
INSERT INTO `sys_log` VALUES ('ea3839fb107644c0afef094ef62fe95c', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"userName\",\"sortOrder\":\"asc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"userName\":\"admin\",\"userState\":\"1\"}', '2019-06-20 03:33:59');
INSERT INTO `sys_log` VALUES ('edcc6fc8b719424b82ca945fb9e9b09a', 'admin', 'update', '菜单管理', '/crowdfounding-web/system/menu/editSave', '{\"id\":\"46\",\"pid\":\"37\",\"pName\":\"服务监控\",\"type\":\"3\",\"menuName\":\"查看\",\"url\":[\"\"],\"resource\":\"monitor:system:driud\",\"sort\":[\"\"],\"icon\":[\"\"]}', '2019-07-02 06:08:41');
INSERT INTO `sys_log` VALUES ('f2a4211017ab4ec98d1cc1ed62b2adde', 'admin', '登出', '在线用户管理', '/crowdfounding-web/monitor/online/forceLogout/f88c0bd3-683f-4ca1-91ff-360506e5bce9', '{}', '2019-07-01 14:29:53');
INSERT INTO `sys_log` VALUES ('f3ea6726d2e245ea96aebce2e62d5dc9', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"userName\",\"sortOrder\":\"asc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":\"8\",\"userName\":\"admin\",\"userState\":\"1\"}', '2019-06-20 03:34:38');
INSERT INTO `sys_log` VALUES ('f4742a8d10df4c52a0654f55046a6134', 'admin', '查询', '在线用户管理', '/crowdfounding-web/monitor/online/list', '{\"_r\":\"0.7429209542728641\"}', '2019-07-01 13:21:11');
INSERT INTO `sys_log` VALUES ('f47a514b81ff4f0682a6ae64b05fa7f8', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.8580695297799872\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"]}', '2019-07-01 12:20:58');
INSERT INTO `sys_log` VALUES ('f62a6940c02144e3a8ebba9f95c71d8a', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.11838596117832378\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:41:54');
INSERT INTO `sys_log` VALUES ('f6390f453eb14975931cfbeaf29058d3', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":\"2019-07-01\",\"endTime\":[\"\"]}', '2019-07-01 14:12:58');
INSERT INTO `sys_log` VALUES ('f7323098bfc447a8b1e308d76dcf5681', 'admin', '查询', '系统首页', '/crowdfounding-web/main', '{}', '2019-06-20 07:46:53');
INSERT INTO `sys_log` VALUES ('f8762b026db24e39b89769b2957a1ed8', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"]}', '2019-07-01 13:14:56');
INSERT INTO `sys_log` VALUES ('f996b3c33f3449ccb7a75a79cff078a3', 'admin', '更新', '菜单管理', '/crowdfounding-web/system/menu/editSave', '{\"id\":\"35\",\"pid\":\"0\",\"pName\":\"顶级节点\",\"type\":\"1\",\"menuName\":\"系统监控\",\"url\":[\"\"],\"resource\":[\"\"],\"sort\":\"4.10\",\"icon\":\"fa fa-cc\"}', '2019-07-01 13:21:53');
INSERT INTO `sys_log` VALUES ('fa28b33b6bae43e6bdfa996188376c32', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":\"1\"}', '2019-07-01 13:15:01');
INSERT INTO `sys_log` VALUES ('fa5ab65bcb97408d8c4368806b320dd4', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:12:41');
INSERT INTO `sys_log` VALUES ('fe3418d6f4ef47599b35c89fa79e780e', 'admin', '查询', '角色管理', '/crowdfounding-web/system/role/list', '{\"_r\":\"0.4778347666016529\",\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":[\"\"]}', '2019-07-01 11:06:39');
INSERT INTO `sys_log` VALUES ('fea748be928a4069a56cbb23ecf5ca6f', 'admin', '查询', '部门管理', '/crowdfounding-web/system/dept/list', '{\"_r\":\"0.8466723296320957\",\"deptName\":[\"\"]}', '2019-07-01 03:21:11');
INSERT INTO `sys_log` VALUES ('fefc3633d8404741a660d5a4a4546c45', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":\"-1\"}', '2019-07-01 12:19:45');
INSERT INTO `sys_log` VALUES ('ff10d2180eee424a8389c42f662ac349', 'admin', 'select', '系统登陆', '/crowdfounding-web/home/doLogin', NULL, '2019-07-02 05:47:25');
INSERT INTO `sys_log` VALUES ('ff28fb46433946ccbf96add3447bad8f', 'admin', '查询', '菜单管理', '/crowdfounding-web/system/menu/list', '{\"_r\":\"0.7379101230618963\",\"menuName\":[\"\"],\"type\":[\"\"]}', '2019-07-03 03:46:01');
INSERT INTO `sys_log` VALUES ('ff7e263645044f75980d843005f786ad', 'admin', '查询', '用户管理', '/crowdfounding-web/system/user/list', '{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"userState\":[\"\"],\"startTime\":[\"\"],\"endTime\":[\"\"]}', '2019-07-01 14:26:29');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `pid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级菜单ID',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '连接地址',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` decimal(10, 2) NULL DEFAULT NULL COMMENT '排序',
  `type` int(11) NULL DEFAULT NULL COMMENT '菜单类型',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `resource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称（菜单对应权限）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', '0', '', 'fa fa-cogs', 1.00, 1, '01', '');
INSERT INTO `sys_menu` VALUES (4, '菜单管理', '1', '/system/menu', 'fa fa-adjust', 3.00, 2, '0103', '');
INSERT INTO `sys_menu` VALUES (5, '角色管理', '1', '/system/role', 'fa fa-male', 2.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (12, '主页', '0', '', 'fa fa-user-o', 0.00, 1, NULL, '');
INSERT INTO `sys_menu` VALUES (14, '首页', '12', '/main/mainIndex', 'fa fa-television', 0.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (15, '用户管理', '1', '/system/user', 'fa fa-user', 1.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (16, '部门管理', '1', '/system/dept', 'fa fa-institution', 4.00, 2, NULL, '');
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
INSERT INTO `sys_menu` VALUES (35, '系统监控', '0', '', 'fa fa-cc', 4.10, 1, NULL, '');
INSERT INTO `sys_menu` VALUES (37, '服务监控', '35', '/system/monitor', 'fa fa-copyright', 1.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (38, '重置密码', '15', '', '', 4.00, 3, NULL, 'sys:user:resetPwd');
INSERT INTO `sys_menu` VALUES (39, '在线用户', '35', '/monitor/online', 'fa fa-user', 1.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (40, '强退', '39', '', '', NULL, 3, NULL, 'monitor:online:logout');
INSERT INTO `sys_menu` VALUES (41, '查看', '15', '', '', NULL, 3, NULL, 'sys:user:user');
INSERT INTO `sys_menu` VALUES (42, '查看', '5', '', '', NULL, 3, NULL, 'sys:role:role');
INSERT INTO `sys_menu` VALUES (43, '查看', '4', '', '', NULL, 3, NULL, 'sys:menu:menu');
INSERT INTO `sys_menu` VALUES (44, '查看', '16', '', '', NULL, 3, NULL, 'sys:dept:dept');
INSERT INTO `sys_menu` VALUES (45, '查看', '39', '', '', NULL, 3, NULL, 'monitor:online:online');
INSERT INTO `sys_menu` VALUES (46, '查看', '37', '', '', NULL, 3, NULL, 'monitor:system:driud');
INSERT INTO `sys_menu` VALUES (47, '日志操作', '1', '/system/log', '', 5.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (48, '查看', '47', '', '', 0.00, 3, NULL, 'sys:log:log');
INSERT INTO `sys_menu` VALUES (49, '删除', '47', '', '', NULL, 3, NULL, 'sys:log:remove');
INSERT INTO `sys_menu` VALUES (50, '字典管理', '22', '/commom/dict/type', 'fa fa-window-restore', 0.00, 2, NULL, '');
INSERT INTO `sys_menu` VALUES (52, '查看', '50', '', '', 0.00, 3, NULL, 'commom:dict:type');
INSERT INTO `sys_menu` VALUES (53, '新增', '50', '', '', 1.00, 3, NULL, 'commom:dict:add');
INSERT INTO `sys_menu` VALUES (54, '删除', '50', '', '', 2.00, 3, NULL, 'commom:dict:remove');
INSERT INTO `sys_menu` VALUES (55, '修改', '50', '', '', 3.00, 3, NULL, 'commom:dict:edit');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `roleName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `roleDesc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `roleState` int(2) NULL DEFAULT 1 COMMENT '状态,1-启用,-1禁用',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('7aedf9931d8a4ff3bc46c46e811a2304', '普通员工', '普通员工', 1, '2019-04-23 12:52:21');
INSERT INTO `sys_role` VALUES ('b1f9ce5543a049be9f169a3f5e6b72a8', '超级管理员', '超级管理员', 1, '2017-09-14 15:02:16');
INSERT INTO `sys_role` VALUES ('ca904d80931f4c368ac1c0919d16b6ae', '管理员', '管理员', 1, '2019-04-26 18:20:12');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `roleId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  `menuId` bigint(20) NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('00a9e274f6f94cf2bb840143652c8dd4', 'b1f9ce5543a049be9f169a3f5e6b72a8', 26);
INSERT INTO `sys_role_menu` VALUES ('00f63570ed2f4ec8a10b313934a1e60f', 'ca904d80931f4c368ac1c0919d16b6ae', 45);
INSERT INTO `sys_role_menu` VALUES ('03b23c6dd9ea455aa4aa92d4c8255766', 'ca904d80931f4c368ac1c0919d16b6ae', 37);
INSERT INTO `sys_role_menu` VALUES ('1034e0ab01a243cba31bb1b267c77684', '7aedf9931d8a4ff3bc46c46e811a2304', 22);
INSERT INTO `sys_role_menu` VALUES ('111940a242e64700917c32d76bcae07c', 'b1f9ce5543a049be9f169a3f5e6b72a8', 38);
INSERT INTO `sys_role_menu` VALUES ('116e701258e94233a596c9a2a308ff2e', 'ca904d80931f4c368ac1c0919d16b6ae', 54);
INSERT INTO `sys_role_menu` VALUES ('1288bdd32d2442b3944c244bb2f643d5', 'b1f9ce5543a049be9f169a3f5e6b72a8', 14);
INSERT INTO `sys_role_menu` VALUES ('164329bcd37d4e7599de76e0e9dfbae5', 'b1f9ce5543a049be9f169a3f5e6b72a8', 24);
INSERT INTO `sys_role_menu` VALUES ('1bacf46dd870434c8b6f8032cc788a92', 'b1f9ce5543a049be9f169a3f5e6b72a8', 46);
INSERT INTO `sys_role_menu` VALUES ('23a47ca58ced49aa92bc466f0bb69e68', 'b1f9ce5543a049be9f169a3f5e6b72a8', 16);
INSERT INTO `sys_role_menu` VALUES ('2788921df5e640b798adb5d15e6108b3', 'b1f9ce5543a049be9f169a3f5e6b72a8', 32);
INSERT INTO `sys_role_menu` VALUES ('2c499767b5044200805b4be2a2dec45e', 'b1f9ce5543a049be9f169a3f5e6b72a8', 12);
INSERT INTO `sys_role_menu` VALUES ('2d429fd24ec94861a6d4b918486fbd51', 'b1f9ce5543a049be9f169a3f5e6b72a8', 31);
INSERT INTO `sys_role_menu` VALUES ('3030f1571ad44fb9b9a64fee5163fd21', 'b1f9ce5543a049be9f169a3f5e6b72a8', 50);
INSERT INTO `sys_role_menu` VALUES ('3903ef10329c458ab4545187a26f1841', 'b1f9ce5543a049be9f169a3f5e6b72a8', 4);
INSERT INTO `sys_role_menu` VALUES ('4327ad910686489b81db1533b680d964', 'ca904d80931f4c368ac1c0919d16b6ae', 40);
INSERT INTO `sys_role_menu` VALUES ('44d66eeb9c25415789740dd349eae98e', 'b1f9ce5543a049be9f169a3f5e6b72a8', 22);
INSERT INTO `sys_role_menu` VALUES ('513ecccdf39940d38262986bcfa263d9', 'b1f9ce5543a049be9f169a3f5e6b72a8', 28);
INSERT INTO `sys_role_menu` VALUES ('513fbe96b63244f488ef5f71a5df08a6', 'ca904d80931f4c368ac1c0919d16b6ae', 22);
INSERT INTO `sys_role_menu` VALUES ('5208e0af5518426899844ff8370a9266', 'b1f9ce5543a049be9f169a3f5e6b72a8', 49);
INSERT INTO `sys_role_menu` VALUES ('54172cfa3fca4bf9a86efbc48bce2435', 'ca904d80931f4c368ac1c0919d16b6ae', 55);
INSERT INTO `sys_role_menu` VALUES ('57106622bdb944b2a41a50e3cdca4879', 'b1f9ce5543a049be9f169a3f5e6b72a8', 29);
INSERT INTO `sys_role_menu` VALUES ('58a4c561268f44eebeeaebbceab84573', 'b1f9ce5543a049be9f169a3f5e6b72a8', 52);
INSERT INTO `sys_role_menu` VALUES ('643d7950268d4e36a29e7e0fb65b8b01', 'ca904d80931f4c368ac1c0919d16b6ae', 50);
INSERT INTO `sys_role_menu` VALUES ('6a5bf507d5d54b97a3ee5784b77e6cda', 'b1f9ce5543a049be9f169a3f5e6b72a8', 44);
INSERT INTO `sys_role_menu` VALUES ('6c22880d6fdd480b95f01165ee55a2d1', 'ca904d80931f4c368ac1c0919d16b6ae', 35);
INSERT INTO `sys_role_menu` VALUES ('71aebda5f0a34d10933da9192aaefe80', 'b1f9ce5543a049be9f169a3f5e6b72a8', 42);
INSERT INTO `sys_role_menu` VALUES ('763c9333538240c7bc39e32ca45390a6', '7aedf9931d8a4ff3bc46c46e811a2304', 12);
INSERT INTO `sys_role_menu` VALUES ('7833d3d34cfa47b4a1bb031f6d90b325', 'ca904d80931f4c368ac1c0919d16b6ae', 39);
INSERT INTO `sys_role_menu` VALUES ('7855068b77ca45e6ab693d4e41f784ba', 'b1f9ce5543a049be9f169a3f5e6b72a8', 23);
INSERT INTO `sys_role_menu` VALUES ('876838d19f7b4a54835d72c3d33a3e00', 'b1f9ce5543a049be9f169a3f5e6b72a8', 48);
INSERT INTO `sys_role_menu` VALUES ('8b5e3ed211fc4d2f830523c5a6d8ee73', 'ca904d80931f4c368ac1c0919d16b6ae', 52);
INSERT INTO `sys_role_menu` VALUES ('8e62a5d879904a24b4c7381e96f7e675', 'ca904d80931f4c368ac1c0919d16b6ae', 53);
INSERT INTO `sys_role_menu` VALUES ('91ed827a4328453c907f7bac16167a73', '7aedf9931d8a4ff3bc46c46e811a2304', 50);
INSERT INTO `sys_role_menu` VALUES ('94982d03d7a546b7829f5a15ec8eb0d6', 'ca904d80931f4c368ac1c0919d16b6ae', 46);
INSERT INTO `sys_role_menu` VALUES ('9b76277b6c854842892d8f3614997d13', 'b1f9ce5543a049be9f169a3f5e6b72a8', 45);
INSERT INTO `sys_role_menu` VALUES ('a19f3bb5d18e44f08ca561eb63a5f2ca', 'b1f9ce5543a049be9f169a3f5e6b72a8', 54);
INSERT INTO `sys_role_menu` VALUES ('a3d456f29f6144bab809f1d91d7eb767', '7aedf9931d8a4ff3bc46c46e811a2304', 14);
INSERT INTO `sys_role_menu` VALUES ('ab86b5abadc740228891118b9957d8ba', 'ca904d80931f4c368ac1c0919d16b6ae', 14);
INSERT INTO `sys_role_menu` VALUES ('ac667280a25042f5b7875b6d51cc1af0', 'b1f9ce5543a049be9f169a3f5e6b72a8', 55);
INSERT INTO `sys_role_menu` VALUES ('b281a6e0a90145d3ae44ff31b264fcd9', 'b1f9ce5543a049be9f169a3f5e6b72a8', 39);
INSERT INTO `sys_role_menu` VALUES ('b86c84c58cc3450b9bfa772088d4a85f', 'b1f9ce5543a049be9f169a3f5e6b72a8', 37);
INSERT INTO `sys_role_menu` VALUES ('be59c01588494bc89e9abb14a4e351a4', 'b1f9ce5543a049be9f169a3f5e6b72a8', 35);
INSERT INTO `sys_role_menu` VALUES ('c49638fbf6d94006a6e369b3695adb66', 'b1f9ce5543a049be9f169a3f5e6b72a8', 25);
INSERT INTO `sys_role_menu` VALUES ('c60efa160f28487bbaf7ad7de211dd01', 'b1f9ce5543a049be9f169a3f5e6b72a8', 47);
INSERT INTO `sys_role_menu` VALUES ('cf37d55b206e4f4d9c66422e390637b5', '7aedf9931d8a4ff3bc46c46e811a2304', 52);
INSERT INTO `sys_role_menu` VALUES ('cffc0fe041254ef8a3fe7d0134ceb9fa', 'b1f9ce5543a049be9f169a3f5e6b72a8', 43);
INSERT INTO `sys_role_menu` VALUES ('d13eaf7d92864de9b9d9e382bc270453', 'b1f9ce5543a049be9f169a3f5e6b72a8', 5);
INSERT INTO `sys_role_menu` VALUES ('d6344a72954a4c6fa2a69157742aa126', 'b1f9ce5543a049be9f169a3f5e6b72a8', 40);
INSERT INTO `sys_role_menu` VALUES ('d6cd5d4a85ec4ab8b34d790b361d99e2', 'b1f9ce5543a049be9f169a3f5e6b72a8', 27);
INSERT INTO `sys_role_menu` VALUES ('d6df60ecf8e54b7fb1f7a153a227b906', 'b1f9ce5543a049be9f169a3f5e6b72a8', 41);
INSERT INTO `sys_role_menu` VALUES ('da493eb5fea94610a16a1c71da0794f4', 'b1f9ce5543a049be9f169a3f5e6b72a8', 34);
INSERT INTO `sys_role_menu` VALUES ('de68df8b60284f9cbaccf4647d1a7deb', 'b1f9ce5543a049be9f169a3f5e6b72a8', 33);
INSERT INTO `sys_role_menu` VALUES ('ea949086f436495a8257d2d0ee46d2c3', 'b1f9ce5543a049be9f169a3f5e6b72a8', 53);
INSERT INTO `sys_role_menu` VALUES ('eee31b293cc5462d946ddb9a21df1ca7', 'ca904d80931f4c368ac1c0919d16b6ae', 12);
INSERT INTO `sys_role_menu` VALUES ('f892552402af4926bcf9c9e6f4caa2e7', 'b1f9ce5543a049be9f169a3f5e6b72a8', 15);
INSERT INTO `sys_role_menu` VALUES ('fb98264dacb34496950806854b294371', 'b1f9ce5543a049be9f169a3f5e6b72a8', 1);
INSERT INTO `sys_role_menu` VALUES ('fbdffc6d3bec4b90991c149c2b1e8677', 'b1f9ce5543a049be9f169a3f5e6b72a8', 30);

-- ----------------------------
-- Table structure for sys_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting`  (
  `Id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `sysKey` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'KEY',
  `sysName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `sysValue` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `sysDesc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_setting
-- ----------------------------
INSERT INTO `sys_setting` VALUES ('1', 'systemName', '系统名称', 'AdminLTE-admin', 0, NULL);
INSERT INTO `sys_setting` VALUES ('2', 'systemSubName', '系统简称', 'AA', 1, NULL);
INSERT INTO `sys_setting` VALUES ('3', 'bottomCopyright', '许可说明', 'Copyright © 2017 米粒电商. All rights reserved.', 2, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `userState` int(2) NOT NULL DEFAULT 1 COMMENT '用户状态,1-启用,-1禁用',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `userDesc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `userImg` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'http://news.mydrivers.com/Img/20110518/04481549.png' COMMENT '头像',
  `deptId` bigint(20) NULL DEFAULT NULL COMMENT '部门主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('61eb24f91a1049afa78716d23c79e080', 'xixi', 'dc2e0e1ad4c8acc62f87cbbf7a0e05da', 1, '2019-06-20 03:27:21', NULL, 'http://news.mydrivers.com/Img/20110518/04481549.png', 6);
INSERT INTO `sys_user` VALUES ('8fa3880738f04299af1c6a063433ae71', 'ruo', '0ff7e732eef4f5081f85d115d9b57686', 1, '2019-07-01 14:23:52', NULL, 'http://news.mydrivers.com/Img/20110518/04481549.png', 1);
INSERT INTO `sys_user` VALUES ('c79ba431f9f74dfbae585b87b0cde933', 'admin', '038bdaf98f2037b31f1e75b5b4c9b26e', 1, '2017-09-14 15:02:17', NULL, 'http://news.mydrivers.com/Img/20110518/04481549.png', 4);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `Id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键',
  `roleId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('18cfabbca83747189fa9a2288103fea9', '61eb24f91a1049afa78716d23c79e080', 'ca904d80931f4c368ac1c0919d16b6ae');
INSERT INTO `sys_user_role` VALUES ('333386b571dc4a4b94f9019f757858ef', 'c79ba431f9f74dfbae585b87b0cde933', 'b1f9ce5543a049be9f169a3f5e6b72a8');
INSERT INTO `sys_user_role` VALUES ('3a2c99c755f34941a2bbf47ccaf80ea9', '8fa3880738f04299af1c6a063433ae71', '7aedf9931d8a4ff3bc46c46e811a2304');
INSERT INTO `sys_user_role` VALUES ('a87e6a4c155246439e0999e4152caef3', 'c79ba431f9f74dfbae585b87b0cde933', '7aedf9931d8a4ff3bc46c46e811a2304');
INSERT INTO `sys_user_role` VALUES ('b23d9535fccc4f3ca11559868e4b2504', '61eb24f91a1049afa78716d23c79e080', '7aedf9931d8a4ff3bc46c46e811a2304');
INSERT INTO `sys_user_role` VALUES ('b831aebf73b042d9add7d265ea16b020', 'c79ba431f9f74dfbae585b87b0cde933', 'ca904d80931f4c368ac1c0919d16b6ae');

SET FOREIGN_KEY_CHECKS = 1;
