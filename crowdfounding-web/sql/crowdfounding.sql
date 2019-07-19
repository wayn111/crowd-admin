-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: crowdfounding
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NOT NULL COMMENT '夫部门id',
  `deptName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `sort` decimal(10,2) DEFAULT NULL COMMENT '排序',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(32) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (1,0,'产品部',1.00,NULL,'产品部'),(2,0,'总销售部',2.00,NULL,'总销售部'),(3,0,'test部门',3.00,NULL,'test部门'),(4,1,'cpb',1.00,NULL,NULL),(6,3,'测试部门',1.00,NULL,NULL),(8,2,'销售部',1.00,NULL,NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
  `dictState` int(11) DEFAULT NULL COMMENT '1 启用  -1 禁用',
  `type` int(11) DEFAULT NULL COMMENT '1 字典类型  2 类型对应值',
  `sort` decimal(10,2) DEFAULT NULL COMMENT '排序（升序）',
  `dictType` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0' COMMENT '字典类型',
  `createBy` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateBy` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `delFlag` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记 0 存在 1 删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`name`) USING BTREE,
  KEY `sys_dict_del_flag` (`delFlag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` VALUES (123,'启用状态','state',1,1,1.00,NULL,'admin','2019-06-29 16:20:21','admin','2019-06-30 15:52:26','1 启用  -1 禁用','0'),(133,'启用','1',1,2,0.00,'state','admin','2019-06-30 15:41:45','admin','2019-06-30 15:48:35','启用','0'),(134,'禁用','-1',1,2,1.00,'state','admin','2019-06-30 15:48:17','admin','2019-06-30 15:48:32','禁用','0'),(135,'爱好','hobby',1,1,2.00,'null','admin','2019-06-30 15:49:16','admin','2019-07-17 02:30:39','swim 游泳 playball 打球。。。','0'),(136,'打球','playball',1,2,0.00,'hobby','admin','2019-06-30 15:49:43','admin','2019-06-30 15:50:18','','0'),(138,'菜单类型','menuType',1,1,0.10,'null','admin','2019-07-07 12:33:25','admin','2019-07-17 02:29:54','','0'),(139,'目录','1',1,2,NULL,'menuType','admin','2019-07-07 12:33:46','admin','2019-07-18 01:58:49','','0'),(140,'菜单','2',1,2,NULL,'menuType','admin','2019-07-07 12:33:54',NULL,NULL,'','0'),(141,'按钮','3',1,2,NULL,'menuType','admin','2019-07-07 12:33:58',NULL,NULL,'','0'),(142,'游泳','swim',1,2,1.00,'hobby','admin','2019-07-08 07:01:25','admin','2019-07-11 08:58:02','','0');
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_log` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户',
  `operState` int(2) DEFAULT NULL COMMENT '操作状态 1 正常 -1 失败',
  `operation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '日志',
  `moduleName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模块名称',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求路径',
  `requestParams` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '参数',
  `createTime` datetime DEFAULT NULL COMMENT '日志时间',
  `remarks` varchar(32) DEFAULT NULL COMMENT '备注',
  `errorMsg` varchar(32) DEFAULT NULL COMMENT '错误消息',
  `ip` varchar(32) DEFAULT NULL COMMENT '请求ip',
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '浏览器信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES ('0b0541a3c89744ff90d185e3fc8e1950','admin',1,'查询','部门管理','/crowdfounding-web/system/dept/list','{\"deptName\":[\"\"]}','2019-07-18 07:57:43',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('2efc51723f5d42559afc77b5b3eddce8','admin',1,'查询','用户管理','/crowdfounding-web/system/user/list','{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"phone\":[\"\"],\"email\":[\"\"],\"userState\":\"1\",\"startTime\":[\"\"],\"endTime\":[\"\"]}','2019-07-18 07:48:34',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('466f37f5b4dd4b2a8fa8f57366d455d1','admin',1,'查询','在线用户管理','/crowdfounding-web/monitor/online/list','{}','2019-07-18 08:37:14',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('4fd02b2c78ec476a9cbe0deb25d07336','admin',1,'查询','用户管理','/crowdfounding-web/system/user/list','{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"phone\":[\"\"],\"email\":[\"\"],\"userState\":\"1\",\"startTime\":[\"\"],\"endTime\":[\"\"]}','2019-07-18 08:36:37',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('6a486489868d4d588ee17ee29bb52b01','admin',1,'查询','角色管理','/crowdfounding-web/system/role/list','{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":\"1\",\"startTime\":[\"\"],\"endTime\":[\"\"]}','2019-07-18 07:48:40',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('728a444fd0474c669a34ddc25e99e3e7','admin',1,'查询','用户管理','/crowdfounding-web/system/user/list','{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"phone\":[\"\"],\"email\":[\"\"],\"userState\":\"1\",\"startTime\":[\"\"],\"endTime\":[\"\"]}','2019-07-18 08:32:43',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('7ca6a24a824d49b8842ed15d5f7194db','admin',1,'查询','部门管理','/crowdfounding-web/system/dept/list','{\"deptName\":[\"\"]}','2019-07-18 08:32:45',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('939113e56add4a0a81ad520bd1d585d6','admin',1,'查询','部门管理','/crowdfounding-web/system/dept/list','{\"deptName\":[\"\"]}','2019-07-18 07:59:29',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('999981e0881c4847b7a4de418d4e1cc7','admin',1,'查询','菜单管理','/crowdfounding-web/system/menu/list','{\"menuName\":[\"\"],\"type\":[\"\"]}','2019-07-18 08:32:44',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('a03f1370d9134dcd9ab7a03a380c0816','admin',1,'查询','用户管理','/crowdfounding-web/system/user/list','{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"phone\":[\"\"],\"email\":[\"\"],\"userState\":\"1\",\"startTime\":[\"\"],\"endTime\":[\"\"]}','2019-07-18 08:02:59',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('abe86bd5a3b84b3cb91ecef307d9194d','admin',1,'查询','角色管理','/crowdfounding-web/system/role/list','{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":\"1\",\"startTime\":[\"\"],\"endTime\":[\"\"]}','2019-07-18 08:37:58',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('b57d43ef7a684f4d89df04b0cb30cbb9','admin',1,'查询','菜单管理','/crowdfounding-web/system/menu/list','{\"menuName\":[\"\"],\"type\":[\"\"]}','2019-07-18 07:53:54',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('bc1ef376e66b4cb591df16b9d23deb44','admin',1,'查询','角色管理','/crowdfounding-web/system/role/list','{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":\"1\",\"startTime\":[\"\"],\"endTime\":[\"\"]}','2019-07-18 07:48:46',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('bef2300cb91b4534be6bb9c9c4971c4b','admin',1,'查询','用户管理','/crowdfounding-web/system/user/list','{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"deptId\":[\"\"],\"userName\":[\"\"],\"phone\":[\"\"],\"email\":[\"\"],\"userState\":\"1\",\"startTime\":[\"\"],\"endTime\":[\"\"]}','2019-07-18 07:52:01',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('d5eae79efb7144419048e538ebfe329c','admin',1,'更新','角色管理','/crowdfounding-web/system/role/editSave','{\"id\":\"7aedf9931d8a4ff3bc46c46e811a2304\",\"roleName\":\"普通员工\",\"roleState\":\"1\",\"roleStateSwicth\":\"on\",\"remarks\":[\"\"],\"menuIds\":\"14,52,12,22,50\"}','2019-07-18 07:48:44',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('e56db17936174f98b044f06223c525f1','admin',1,'查询','部门管理','/crowdfounding-web/system/dept/list','{\"deptName\":[\"\"]}','2019-07-18 07:57:23',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('f3a074af13184dd99cd3977631abb44a','admin',1,'查询','部门管理','/crowdfounding-web/system/dept/list','{\"deptName\":[\"\"]}','2019-07-18 08:37:01',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('f50e812df53c46868ba99a03df2d1e00','admin',1,'查询','角色管理','/crowdfounding-web/system/role/list','{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":\"1\",\"startTime\":[\"\"],\"endTime\":[\"\"]}','2019-07-18 07:48:44',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('fb166706f3fc46f3b7c35ed2de97f4ad','admin',1,'查询','角色管理','/crowdfounding-web/system/role/list','{\"sortName\":\"createTime\",\"sortOrder\":\"desc\",\"pageSize\":\"10\",\"pageNumber\":\"1\",\"roleName\":[\"\"],\"roleState\":\"1\",\"startTime\":[\"\"],\"endTime\":[\"\"]}','2019-07-18 08:32:43',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'),('ffb1d633fa3d45b3b953dc4658632abf','admin',1,'查询','部门管理','/crowdfounding-web/system/dept/list','{\"deptName\":[\"\"]}','2019-07-18 07:55:05',NULL,NULL,'0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `pid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级菜单ID',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '连接地址',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `sort` decimal(10,2) DEFAULT NULL COMMENT '排序',
  `type` int(11) DEFAULT NULL COMMENT '菜单类型',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '编码',
  `resource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源名称（菜单对应权限）',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(32) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理','0','','fa fa-cogs',1.00,1,'01','',NULL,NULL),(4,'菜单管理','1','/system/menu','fa fa-adjust',3.00,2,'0103','',NULL,NULL),(5,'角色管理','1','/system/role','fa fa-male',2.00,2,NULL,'',NULL,NULL),(12,'主页','0','','fa fa-user-o',0.00,1,NULL,'',NULL,NULL),(14,'首页','12','/main/mainIndex','fa fa-television',0.00,2,NULL,'',NULL,NULL),(15,'用户管理','1','/system/user','fa fa-user',1.00,2,NULL,'',NULL,NULL),(16,'部门管理','1','/system/dept','fa fa-institution',4.00,2,NULL,'',NULL,NULL),(22,'基础管理','0','','fa fa-id-card-o',3.00,1,NULL,'',NULL,NULL),(23,'新增','4','','',1.00,3,NULL,'sys:menu:add',NULL,NULL),(24,'编辑','4','','',2.00,3,NULL,'sys:menu:edit',NULL,NULL),(25,'删除','4','','',3.00,3,NULL,'sys:menu:remove',NULL,NULL),(26,'新增','15','','',1.00,3,NULL,'sys:user:add',NULL,NULL),(27,'编辑','15','','',2.00,3,NULL,'sys:user:edit',NULL,NULL),(28,'删除','15','','',3.00,3,NULL,'sys:user:remove',NULL,NULL),(29,'新增','5','','',1.00,3,NULL,'sys:role:add',NULL,NULL),(30,'删除','5','','',3.00,3,NULL,'sys:role:remove',NULL,NULL),(31,'新增','16','','',1.00,3,NULL,'sys:dept:add',NULL,NULL),(32,'编辑','16','','',2.00,3,NULL,'sys:dept:edit',NULL,NULL),(33,'删除','16','','',3.00,3,NULL,'sys:dept:remove',NULL,NULL),(34,'编辑','5','','',2.00,3,NULL,'sys:role:edit',NULL,NULL),(35,'系统监控','0','','fa fa-cc',4.10,1,NULL,'',NULL,NULL),(37,'数据监控','35','/system/monitor','fa fa-copyright',1.00,2,NULL,'',NULL,NULL),(38,'重置密码','15','','',4.00,3,NULL,'sys:user:resetPwd',NULL,NULL),(39,'在线用户','35','/monitor/online','fa fa-user',1.00,2,NULL,'',NULL,NULL),(40,'强退','39','','',1.00,3,NULL,'monitor:online:logout',NULL,NULL),(41,'查看','15','','',NULL,3,NULL,'sys:user:user',NULL,NULL),(42,'查看','5','','',NULL,3,NULL,'sys:role:role',NULL,NULL),(43,'查看','4','','',NULL,3,NULL,'sys:menu:menu',NULL,NULL),(44,'查看','16','','',NULL,3,NULL,'sys:dept:dept',NULL,NULL),(45,'查看','39','','',0.00,3,NULL,'monitor:online:online',NULL,NULL),(46,'查看','37','','',0.00,3,NULL,'monitor:system:driud',NULL,NULL),(47,'日志操作','1','/system/log','',5.00,2,NULL,'',NULL,NULL),(48,'查看','47','','',0.00,3,NULL,'sys:log:log',NULL,NULL),(49,'删除','47','','',NULL,3,NULL,'sys:log:remove',NULL,NULL),(50,'字典管理','22','/commom/dict/type','fa fa-window-restore',0.00,2,NULL,'',NULL,NULL),(52,'查看','50','','',0.00,3,NULL,'commom:dict:type',NULL,NULL),(53,'新增','50','','',1.00,3,NULL,'commom:dict:add',NULL,NULL),(54,'删除','50','','',2.00,3,NULL,'commom:dict:remove',NULL,NULL),(55,'编辑','50','','',3.00,3,NULL,'commom:dict:edit',NULL,NULL),(56,'系统工具','0','','fa fa-wrench',4.00,1,NULL,'',NULL,NULL),(57,'代码生成','56','/tool/gen','fa fa-meh-o',0.00,2,NULL,'',NULL,NULL),(58,'查看','57','','',1.00,3,NULL,'tool:gen:list',NULL,NULL),(61,'代码生成','57','','',2.00,3,NULL,'tool:gen:gen',NULL,NULL),(62,'系统监控','35','/monitor/system','fa fa-video-camera',2.00,2,NULL,'',NULL,NULL),(63,'查看','62','','',0.00,3,NULL,'monitor:system:system',NULL,NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `roleName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `roleState` int(2) DEFAULT '1' COMMENT '状态,1-启用,-1禁用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(32) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('7aedf9931d8a4ff3bc46c46e811a2304','普通员工',1,'2019-04-23 12:52:21',''),('b1f9ce5543a049be9f169a3f5e6b72a8','超级管理员',1,'2017-09-14 15:02:16',''),('c4e182e62aca4d48a6c9b1a897551d73','测试角色',1,'2019-07-07 12:07:15','测试角色'),('ca904d80931f4c368ac1c0919d16b6ae','管理员',1,'2019-04-26 18:20:12','管理员');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role_menu` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `roleId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  `menuId` bigint(20) NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES ('013cfba956304eab9b2a73eb49a5cc8b','ca904d80931f4c368ac1c0919d16b6ae',40),('01a89e8e6ad8454ab51bbacdbcfa4eef','b1f9ce5543a049be9f169a3f5e6b72a8',25),('02db560efb7e4842b5bc3b102a985e89','b1f9ce5543a049be9f169a3f5e6b72a8',23),('06a2f471aa0746748dd0466f64b92c6e','b1f9ce5543a049be9f169a3f5e6b72a8',45),('07609683a90743d2aa5ae368950cbfd9','b1f9ce5543a049be9f169a3f5e6b72a8',15),('08c583da1fe249e6a6e410b18417f489','b1f9ce5543a049be9f169a3f5e6b72a8',63),('0c9abd8891054657bc17f113ee7433ea','b1f9ce5543a049be9f169a3f5e6b72a8',54),('1f27099f8c02449cad68e9bef7bc7dd7','ca904d80931f4c368ac1c0919d16b6ae',37),('28ca30d2726f4be7b2b2d22aadbf8381','b1f9ce5543a049be9f169a3f5e6b72a8',27),('2c7a23a3855d4fefb9666dbb19a74a4a','b1f9ce5543a049be9f169a3f5e6b72a8',28),('2cbe97e11fca42209b42be22532930b6','c4e182e62aca4d48a6c9b1a897551d73',12),('2da7a7912564467eb72bff2d70d5296e','b1f9ce5543a049be9f169a3f5e6b72a8',5),('32073fdfa19a43279a993f10585a653a','b1f9ce5543a049be9f169a3f5e6b72a8',26),('3447e86b9aee4346876294e5a5f74493','b1f9ce5543a049be9f169a3f5e6b72a8',52),('36cfbf009bd9489c820c2f3f77d7a5a8','ca904d80931f4c368ac1c0919d16b6ae',14),('3cb5fe5f85254497a40e892963b3f74e','b1f9ce5543a049be9f169a3f5e6b72a8',24),('3f54450012a9415da2d513a8987ea7c1','b1f9ce5543a049be9f169a3f5e6b72a8',50),('47c047e58e2b480989660cae84643fa3','b1f9ce5543a049be9f169a3f5e6b72a8',34),('47e24e0fc23d4ce2af34fcc6d4431145','b1f9ce5543a049be9f169a3f5e6b72a8',43),('4878f018dc514159b9e52bc9cb14ca6d','b1f9ce5543a049be9f169a3f5e6b72a8',38),('56cc75913bc94e898af6451f9e24159a','b1f9ce5543a049be9f169a3f5e6b72a8',16),('5aa542b700f04ca09a6f8a48f3375d18','7aedf9931d8a4ff3bc46c46e811a2304',50),('5cb24bee7fee4e0ca4aaf318eac40ecb','b1f9ce5543a049be9f169a3f5e6b72a8',48),('60054baf86a94832973aba0b1ae3593a','b1f9ce5543a049be9f169a3f5e6b72a8',46),('64d8fccd55b544898889d3edd664c23d','b1f9ce5543a049be9f169a3f5e6b72a8',40),('66007914e6044f1ea8a6cd64407df585','b1f9ce5543a049be9f169a3f5e6b72a8',53),('67a54586995b486baebb871e58534776','b1f9ce5543a049be9f169a3f5e6b72a8',56),('6a32ba26ac604484b9b5d894486f0a58','ca904d80931f4c368ac1c0919d16b6ae',53),('6ad81626d5bb4052aa5759d827ed6a81','b1f9ce5543a049be9f169a3f5e6b72a8',30),('6d2a887d71824922960a5c14528c0deb','ca904d80931f4c368ac1c0919d16b6ae',50),('712f02be410d487fbd14cc27b828c385','b1f9ce5543a049be9f169a3f5e6b72a8',44),('726af44fbc4847c7906e61ce3c464dbd','b1f9ce5543a049be9f169a3f5e6b72a8',39),('79ce242bab694b56b5e1f570fc38607c','c4e182e62aca4d48a6c9b1a897551d73',57),('7c7a6c124fb549c9bdf0a190d649a419','ca904d80931f4c368ac1c0919d16b6ae',54),('7cfabdc86e2a47f3a64b940620055cfc','b1f9ce5543a049be9f169a3f5e6b72a8',61),('7ed0cf47b7414dbca1aa31d36e0164bf','c4e182e62aca4d48a6c9b1a897551d73',58),('850fcfc703c147888be299de0fbd6971','b1f9ce5543a049be9f169a3f5e6b72a8',35),('89ad4d71afad43b19dab71444e2c6a27','c4e182e62aca4d48a6c9b1a897551d73',14),('89e3436c5c0840c9b85f6f0d6216ad53','ca904d80931f4c368ac1c0919d16b6ae',45),('8c794b9144f74ba7bca626504a7b72ab','c4e182e62aca4d48a6c9b1a897551d73',56),('90a57739645a4aef84426f6e6b198deb','b1f9ce5543a049be9f169a3f5e6b72a8',42),('9718d9b77ec24ee494a8229e2a6e6a35','7aedf9931d8a4ff3bc46c46e811a2304',14),('9aa2e5acdd1e431fa298c601bae1b247','b1f9ce5543a049be9f169a3f5e6b72a8',55),('a08c52d477234306a133f92a054f1ead','b1f9ce5543a049be9f169a3f5e6b72a8',58),('a1a6546a3619452b9ae222fb31cad230','b1f9ce5543a049be9f169a3f5e6b72a8',49),('a2a6fc90e576403082abeaae47181413','7aedf9931d8a4ff3bc46c46e811a2304',52),('a5ed6c16c2a342c1ac97b6f887fc50cf','b1f9ce5543a049be9f169a3f5e6b72a8',29),('a810160af99a4a308cb8f4691c149125','7aedf9931d8a4ff3bc46c46e811a2304',12),('ac5ece97c55a4c7c9c345599db3ce0ef','b1f9ce5543a049be9f169a3f5e6b72a8',33),('b05b2754627a4ffb847dcfad555072db','b1f9ce5543a049be9f169a3f5e6b72a8',31),('b729412e1f0f4067822d3999ddd433b7','ca904d80931f4c368ac1c0919d16b6ae',52),('b7bb5378a1cf4810af8f737d6c335a19','7aedf9931d8a4ff3bc46c46e811a2304',22),('ba14d46725e045a589cd6a26f55295e3','ca904d80931f4c368ac1c0919d16b6ae',22),('bee3be87a25749ee8c0cb2695127ab6c','b1f9ce5543a049be9f169a3f5e6b72a8',62),('c05033fd47d04acb949174bab1b9374a','b1f9ce5543a049be9f169a3f5e6b72a8',4),('c3aee22cc7bd41c7b00474bc345ea666','b1f9ce5543a049be9f169a3f5e6b72a8',32),('c7ba9cfb43e248f6b6ef356c8b62e192','ca904d80931f4c368ac1c0919d16b6ae',12),('c80f99e3b9c0486487862019e1ec9b7b','ca904d80931f4c368ac1c0919d16b6ae',35),('ce42ed7e3571492a8d5bc36cd3258fab','b1f9ce5543a049be9f169a3f5e6b72a8',22),('d2600acc10884bab9616f40c38f057a4','ca904d80931f4c368ac1c0919d16b6ae',46),('d911e9a493e94b09ad25ee0bf04978e9','b1f9ce5543a049be9f169a3f5e6b72a8',47),('dae46873ec454ae3b1f96a7467907433','ca904d80931f4c368ac1c0919d16b6ae',55),('ddd1fc3c517f45f88eca1eaeb023d297','b1f9ce5543a049be9f169a3f5e6b72a8',1),('dea3a4c3a83f496cbad0e3926862dc9f','b1f9ce5543a049be9f169a3f5e6b72a8',57),('e1d50f49ddd84190aeeb7dcf6e60c2b3','ca904d80931f4c368ac1c0919d16b6ae',39),('e4b995e224eb44b591bd538e42cd4570','b1f9ce5543a049be9f169a3f5e6b72a8',37),('f4ae497d3ded4f4293a0c89e80ce21ec','b1f9ce5543a049be9f169a3f5e6b72a8',41),('f51a818e8f1845be8892615a4bc28f08','b1f9ce5543a049be9f169a3f5e6b72a8',14),('f9fa8f68322d4cdba1c897f02042d339','b1f9ce5543a049be9f169a3f5e6b72a8',12);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_setting`
--

DROP TABLE IF EXISTS `sys_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_setting` (
  `Id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `sysKey` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'KEY',
  `sysName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `sysValue` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '值',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `sysDesc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统设置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_setting`
--

LOCK TABLES `sys_setting` WRITE;
/*!40000 ALTER TABLE `sys_setting` DISABLE KEYS */;
INSERT INTO `sys_setting` VALUES ('1','systemName','系统名称','AdminLTE-admin',0,NULL),('2','systemSubName','系统简称','AA',1,NULL),('3','bottomCopyright','许可说明','Copyright © 2017 米粒电商. All rights reserved.',2,NULL);
/*!40000 ALTER TABLE `sys_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `userState` int(2) NOT NULL DEFAULT '1' COMMENT '用户状态,1-启用,-1禁用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `userImg` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'http://news.mydrivers.com/Img/20110518/04481549.png' COMMENT '头像',
  `deptId` bigint(20) DEFAULT NULL COMMENT '部门主键',
  `remarks` varchar(32) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('61eb24f91a1049afa78716d23c79e080','xixi','dc2e0e1ad4c8acc62f87cbbf7a0e05da',NULL,NULL,1,'2019-06-20 03:27:21','http://news.mydrivers.com/Img/20110518/04481549.png',6,'width: \'10%\',普通员工'),('c79ba431f9f74dfbae585b87b0cde933','admin','038bdaf98f2037b31f1e75b5b4c9b26e','','',1,'2017-09-14 15:02:17','http://news.mydrivers.com/Img/20110518/04481549.png',2,'超级管理员'),('e4f9291a3e9b4a3d9baf2b3e67245b98','ddd','ccba00ea77381b70945691f54e56b53e','13617159811','1669738430@qq.com',1,'2019-07-07 08:02:24','http://news.mydrivers.com/Img/20110518/04481549.png',4,'dd');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user_role` (
  `Id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键',
  `roleId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES ('3fb98d9ec7824759967b15bd6f9e1d9a','61eb24f91a1049afa78716d23c79e080','c4e182e62aca4d48a6c9b1a897551d73'),('65c5956e19ca4d90948a4ec8f6c3fb0c','61eb24f91a1049afa78716d23c79e080','7aedf9931d8a4ff3bc46c46e811a2304'),('d97e2d499b214539b053577a34645f3d','c79ba431f9f74dfbae585b87b0cde933','b1f9ce5543a049be9f169a3f5e6b72a8'),('ef73e884b7544e858d44a634bd88c2ed','e4f9291a3e9b4a3d9baf2b3e67245b98','c4e182e62aca4d48a6c9b1a897551d73');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-18 16:41:41
