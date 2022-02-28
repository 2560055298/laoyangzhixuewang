-- MySQL dump 10.13  Distrib 5.7.37, for Linux (x86_64)
--
-- Host: localhost    Database: laoyang
-- ------------------------------------------------------
-- Server version	5.7.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `edu_chapter`
--

DROP TABLE IF EXISTS `edu_chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_chapter` (
  `id` char(19) NOT NULL COMMENT '章节ID',
  `course_id` char(19) NOT NULL COMMENT '课程ID',
  `title` varchar(50) NOT NULL COMMENT '章节名称',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '显示排序',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_chapter`
--

LOCK TABLES `edu_chapter` WRITE;
/*!40000 ALTER TABLE `edu_chapter` DISABLE KEYS */;
INSERT INTO `edu_chapter` VALUES ('1497021310486728706','1497020997272883201','第一章：初始C语言',1,'2022-02-25 09:31:08','2022-02-25 09:31:08'),('1497021349506338818','1497020997272883201','第二章：运算和数据类型',2,'2022-02-25 09:31:18','2022-02-25 09:31:18'),('1497021412886466562','1497020997272883201','第三章：分支结构程序',3,'2022-02-25 09:31:33','2022-02-25 09:31:33'),('1497021452321312769','1497020997272883201','第四章：程序的循环控制',4,'2022-02-25 09:31:42','2022-02-25 09:31:42'),('1497021491177345025','1497020997272883201','第五章：数组',5,'2022-02-25 09:31:51','2022-02-25 09:31:51'),('1497021520503918593','1497020997272883201','第六章：函数',6,'2022-02-25 09:31:58','2022-02-25 09:32:15'),('1497021624610738177','1497020997272883201','第七章：基本数据类型',7,'2022-02-25 09:32:23','2022-02-25 09:32:23'),('1497021672929120257','1497020997272883201','第八章：字符串',8,'2022-02-25 09:32:35','2022-02-25 09:32:35'),('1497021714477895681','1497020997272883201','第九章：指针',9,'2022-02-25 09:32:45','2022-02-25 09:32:45'),('1497021757540814850','1497020997272883201','第十章：结构体',10,'2022-02-25 09:32:55','2022-02-25 09:32:55'),('1497021793481805826','1497020997272883201','第十一章：文件处理',11,'2022-02-25 09:33:04','2022-02-25 09:33:04'),('1497024714604826625','1497024448673370114','第一章：变量和基本类型',1,'2022-02-25 09:44:40','2022-02-25 09:44:40'),('1497024774809866242','1497024448673370114','第二章：字符串、向量、数组',2,'2022-02-25 09:44:54','2022-02-25 09:44:54'),('1497024838332600321','1497024448673370114','第三章：表达式',3,'2022-02-25 09:45:10','2022-02-25 09:45:10'),('1497024911154106370','1497024448673370114','第四章：语句、函数',4,'2022-02-25 09:45:27','2022-02-25 09:45:27'),('1497024949536182274','1497024448673370114','第五章：类',5,'2022-02-25 09:45:36','2022-02-25 09:45:36'),('1497024987213615106','1497024448673370114','第六章：IO库',6,'2022-02-25 09:45:45','2022-02-25 09:45:45'),('1497025058663583745','1497024448673370114','第七章：容器',7,'2022-02-25 09:46:02','2022-02-25 09:46:02'),('1497025170324344833','1497024448673370114','第八章：重载、类型转换',8,'2022-02-25 09:46:29','2022-02-25 09:46:29'),('1497025227022946306','1497024448673370114','第九章：TTL标准模板库',9,'2022-02-25 09:46:42','2022-02-25 09:46:42'),('1497030758982766593','1497030255863418882','第一章：Java概述',1,'2022-02-25 10:08:41','2022-02-25 10:08:41'),('1497030803069095938','1497030255863418882','第二章：变量、运算符',2,'2022-02-25 10:08:52','2022-02-25 10:08:52'),('1497030862535938049','1497030255863418882','第三章：程序控制结构',0,'2022-02-25 10:09:06','2022-02-25 10:09:06'),('1497030939249758210','1497030255863418882','第四章：数组、排序、查找',4,'2022-02-25 10:09:24','2022-02-25 10:09:24'),('1497030985676509186','1497030255863418882','第五章：面向对象',5,'2022-02-25 10:09:35','2022-02-25 10:09:35'),('1497031034309464065','1497030255863418882','第六章：枚举、注解',6,'2022-02-25 10:09:47','2022-02-25 10:09:47'),('1497031072418910209','1497030255863418882','第七章：异常',7,'2022-02-25 10:09:56','2022-02-25 10:09:56'),('1497031129591468033','1497030255863418882','第八章：集合',8,'2022-02-25 10:10:09','2022-02-25 10:10:09'),('1497031157785579521','1497030255863418882','第九章：泛型',9,'2022-02-25 10:10:16','2022-02-25 10:10:16'),('1497031196540948481','1497030255863418882','第十章：多线程',10,'2022-02-25 10:10:25','2022-02-25 10:10:25'),('1497031228526710785','1497030255863418882','第十一章：IO流',11,'2022-02-25 10:10:33','2022-02-25 10:10:33'),('1497031296042422273','1497030255863418882','第十二章：网络编程',12,'2022-02-25 10:10:49','2022-02-25 10:10:49'),('1497031341873582081','1497030255863418882','第十三章：反射',13,'2022-02-25 10:11:00','2022-02-25 10:11:00'),('1497033584702148609','1497032903526207490','第一章：数据结构绪论',1,'2022-02-25 10:19:55','2022-02-25 10:19:55'),('1497033631443472385','1497032903526207490','第二章：算法',2,'2022-02-25 10:20:06','2022-02-25 10:20:06'),('1497033661193670658','1497032903526207490','第三章：线性表',3,'2022-02-25 10:20:13','2022-02-25 10:20:13'),('1497033697784778753','1497032903526207490','第四章：栈与队列',4,'2022-02-25 10:20:22','2022-02-25 10:20:22'),('1497033721679728641','1497032903526207490','第五章：串',5,'2022-02-25 10:20:27','2022-02-25 10:20:27'),('1497033745000058881','1497032903526207490','第六章：树',6,'2022-02-25 10:20:33','2022-02-25 10:20:33'),('1497033775069024257','1497032903526207490','第七章：图',7,'2022-02-25 10:20:40','2022-02-25 10:20:40'),('1497033801480556545','1497032903526207490','第八章：查找',8,'2022-02-25 10:20:46','2022-02-25 10:20:46'),('1497033828361850881','1497032903526207490','第九章：排序',9,'2022-02-25 10:20:53','2022-02-25 10:20:53'),('1497034971771064321','1497034731961733121','第一章：引论',1,'2022-02-25 10:25:26','2022-02-25 10:25:26'),('1497035020534042626','1497034731961733121','第二章：进程与线程',2,'2022-02-25 10:25:37','2022-02-25 10:25:37'),('1497035063445966849','1497034731961733121','第三章：存储管理',3,'2022-02-25 10:25:47','2022-02-25 10:25:47'),('1497035090578919426','1497034731961733121','第四章：文件系统',4,'2022-02-25 10:25:54','2022-02-25 10:25:54'),('1497035154311368706','1497034731961733121','第五章：输入 / 输出',5,'2022-02-25 10:26:09','2022-02-25 10:26:09'),('1497035179745628161','1497034731961733121','第六章：死锁',6,'2022-02-25 10:26:15','2022-02-25 10:26:15'),('1497035237157261313','1497034731961733121','第七章：多媒体操作系统',7,'2022-02-25 10:26:29','2022-02-25 10:26:29'),('1497035281415557122','1497034731961733121','第八章：多处理机系统',8,'2022-02-25 10:26:39','2022-02-25 10:26:39'),('1497035327238328321','1497034731961733121','第九章：安全',9,'2022-02-25 10:26:50','2022-02-25 10:26:50'),('1497035451775602689','1497034731961733121','第十章：研究Linux、windows',10,'2022-02-25 10:27:20','2022-02-25 10:27:20'),('1497035528783024129','1497034731961733121','第十一章：网络安全',11,'2022-02-25 10:27:38','2022-02-25 10:27:38'),('1497037259608707073','1497036801875922945','第一章：计算机系统概论',1,'2022-02-25 10:34:31','2022-02-25 10:34:31'),('1497037300268290050','1497036801875922945','第二章：运算方法和运算器',2,'2022-02-25 10:34:41','2022-02-25 10:34:41'),('1497037737008582657','1497036801875922945','第三章：多层次的存储器',3,'2022-02-25 10:36:25','2022-02-25 10:36:25'),('1497037788648853506','1497036801875922945','指令系统',4,'2022-02-25 10:36:37','2022-02-25 10:36:37'),('1497037840993767426','1497036801875922945','第五章：中央处理器',5,'2022-02-25 10:36:50','2022-02-25 10:36:50'),('1497037896048201730','1497036801875922945','第六章：总线系统',6,'2022-02-25 10:37:03','2022-02-25 10:37:03'),('1497037995444817922','1497036801875922945','第七章：外存与 I/O 设备',7,'2022-02-25 10:37:26','2022-02-25 10:37:26'),('1497038059336650753','1497036801875922945','第八章：输入输出系统',8,'2022-02-25 10:37:42','2022-02-25 10:37:42'),('1497038148130066434','1497036801875922945','第九章：并行组织与结构',9,'2022-02-25 10:38:03','2022-02-25 10:38:03'),('1497039356576157697','1497038980930097154','第一章：绪论',1,'2022-02-25 10:42:51','2022-02-25 10:42:51'),('1497039412968574978','1497038980930097154','第二章：关系数据库',2,'2022-02-25 10:43:04','2022-02-25 10:43:04'),('1497039499211853826','1497038980930097154','第三章：SQL语句',3,'2022-02-25 10:43:25','2022-02-25 10:43:25'),('1497039528597147650','1497038980930097154','第四章：数据库安全',4,'2022-02-25 10:43:32','2022-02-25 10:43:32'),('1497039587602616322','1497038980930097154','第五章：存储过程、触发器、数据库完整型',5,'2022-02-25 10:43:46','2022-02-25 10:43:46'),('1497039635451236354','1497038980930097154','第六章：关系数据库设计理论',6,'2022-02-25 10:43:57','2022-02-25 10:43:57'),('1497039668691095553','1497038980930097154','第七章：数据库设计',7,'2022-02-25 10:44:05','2022-02-25 10:44:05'),('1497039707794591746','1497038980930097154','第八章：索引',8,'2022-02-25 10:44:15','2022-02-25 10:44:15'),('1497039735799959554','1497038980930097154','第九章：数据库保护',9,'2022-02-25 10:44:21','2022-02-25 10:44:21'),('1497039775259971586','1497038980930097154','第十章：数据库新技术',10,'2022-02-25 10:44:31','2022-02-25 10:44:31'),('1497040879590854657','1497040613755867138','第一章：数据库入门',1,'2022-02-25 10:48:54','2022-02-25 10:48:54'),('1497040937887485954','1497040613755867138','第二章：数据库和表的基本操作',2,'2022-02-25 10:49:08','2022-02-25 10:50:06'),('1497041064232505345','1497040613755867138','第三章：添加、更新、删除表数据',3,'2022-02-25 10:49:38','2022-02-25 10:49:38'),('1497041148655456257','1497040613755867138','第四章：单表查询',4,'2022-02-25 10:49:58','2022-02-25 10:49:58'),('1497041281006718977','1497040613755867138','第五章：多表操作',5,'2022-02-25 10:50:30','2022-02-25 10:50:30'),('1497041341257895938','1497040613755867138','第六章：事务与存储过程',6,'2022-02-25 10:50:44','2022-02-25 10:50:44'),('1497041376464883714','1497040613755867138','第七章：视图',7,'2022-02-25 10:50:53','2022-02-25 10:50:53'),('1497041409968984066','1497040613755867138','第八章：数据库管理',8,'2022-02-25 10:51:00','2022-02-25 10:51:00'),('1497042728167428098','1497042409194803202','第一章：Linux操作系统概述',1,'2022-02-25 10:56:15','2022-02-25 10:56:15'),('1497042792659046402','1497042409194803202','第二章：Linux的基本配置与使用',2,'2022-02-25 10:56:30','2022-02-25 10:56:30'),('1497042858400567297','1497042409194803202','第三章：文件系统与文件目录操作',3,'2022-02-25 10:56:46','2022-02-25 10:56:46'),('1497042896040251394','1497042409194803202','第四章：用户管理',4,'2022-02-25 10:56:55','2022-02-25 10:56:55'),('1497042928135065601','1497042409194803202','第五章：进程管理',5,'2022-02-25 10:57:02','2022-02-25 10:57:02'),('1497042965086883841','1497042409194803202','第六章：Shell编程',6,'2022-02-25 10:57:11','2022-02-25 10:57:11'),('1497043008816697345','1497042409194803202','第七章：网络配置',7,'2022-02-25 10:57:22','2022-02-25 10:57:22'),('1497043083223650305','1497042409194803202','第一节：我是如何学习linux的？',1,'2022-02-25 10:57:39','2022-02-25 10:57:39'),('1497045209752555522','1497045084519026690','第一章：HTML概述',1,'2022-02-25 11:06:06','2022-02-25 11:06:06'),('1497045249346785282','1497045084519026690','第二章：页面背景与颜色控制',2,'2022-02-25 11:06:16','2022-02-25 11:06:16'),('1497045277549285378','1497045084519026690','第三章：文本格式',3,'2022-02-25 11:06:23','2022-02-25 11:06:23'),('1497045310290022402','1497045084519026690','第四章：列表格式',4,'2022-02-25 11:06:30','2022-02-25 11:06:30'),('1497045333589381122','1497045084519026690','第五章：使用图像',5,'2022-02-25 11:06:36','2022-02-25 11:06:36'),('1497045360063827969','1497045084519026690','第六章：使用超链接',6,'2022-02-25 11:06:42','2022-02-25 11:06:42'),('1497045391168786434','1497045084519026690','第七章：使用表格',7,'2022-02-25 11:06:50','2022-02-25 11:06:50'),('1497045420801544194','1497045084519026690','第八章：使用框架',8,'2022-02-25 11:06:57','2022-02-25 11:06:57');
/*!40000 ALTER TABLE `edu_chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_comment`
--

DROP TABLE IF EXISTS `edu_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_comment` (
  `id` char(19) NOT NULL COMMENT '讲师ID',
  `course_id` varchar(19) NOT NULL DEFAULT '' COMMENT '课程id',
  `teacher_id` char(19) NOT NULL DEFAULT '' COMMENT '讲师id',
  `member_id` varchar(19) NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_teacher_id` (`teacher_id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_comment`
--

LOCK TABLES `edu_comment` WRITE;
/*!40000 ALTER TABLE `edu_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `edu_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_course`
--

DROP TABLE IF EXISTS `edu_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_course` (
  `id` char(19) NOT NULL COMMENT '课程ID',
  `teacher_id` char(19) NOT NULL DEFAULT '-1' COMMENT '课程讲师ID',
  `subject_id` char(19) NOT NULL DEFAULT '-1' COMMENT '课程专业ID',
  `subject_parent_id` char(19) NOT NULL DEFAULT '-1' COMMENT '课程专业父级ID',
  `title` varchar(50) NOT NULL COMMENT '课程标题',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '总课时',
  `cover` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '课程封面图片路径',
  `buy_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '销售数量',
  `view_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '浏览数量',
  `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `status` varchar(10) NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
  `is_deleted` tinyint(3) DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`,`price`),
  KEY `idx_title` (`title`),
  KEY `idx_subject_id` (`subject_id`),
  KEY `idx_teacher_id` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_course`
--

LOCK TABLES `edu_course` WRITE;
/*!40000 ALTER TABLE `edu_course` DISABLE KEYS */;
INSERT INTO `edu_course` VALUES ('1497020997272883201','1496833406225379330','1497020375647670274','1497020375584755715','C语言',0.10,128,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/25/f89c125cc081400bbdb0999fc4297391c.jpg',0,50,1,'Normal',0,'2022-02-25 09:29:54','2022-02-27 06:36:05'),('1497024448673370114','1496833406225379330','1497020375647670275','1497020375584755715','C++',0.00,64,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/25/8666058d98c4484fbf77dda8884a9720Top-Uses-Of-C.jpg',0,40,1,'Normal',0,'2022-02-25 09:43:37','2022-02-25 16:43:44'),('1497030255863418882','1496854071129174018','1497020375714779137','1497020375584755715','Java',0.10,128,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/25/90ece56cbc5f435ca05209e240231aa5java.jpg',0,30,1,'Normal',0,'2022-02-25 10:06:41','2022-02-25 16:44:19'),('1497032903526207490','1496854071129174018','1497020375387625425','1497020375387623425','数据结构',0.00,128,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/25/c31fb9c8755e4d438164eaecc3eb7fc4shujujiegou2.jpg',0,100,1,'Normal',0,'2022-02-01 10:17:12','2022-02-25 16:44:29'),('1497034731961733121','1496851858797391874','1497020375450537986','1497020375387623425','操作系统',0.00,128,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/25/1bc93ac9cb244caf984e3f56eebe100ccaozuoxitong.jpg',0,80,1,'Normal',0,'2022-02-25 10:24:28','2022-02-25 16:45:34'),('1497036801875922945','1496851858797391874','1497020375517646849','1497020375387623425','计算机组成原理',0.00,128,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/25/3adf88f687594c32ad4240c853673414jizu.jpg',0,70,1,'Draft',0,'2022-02-25 10:32:42','2022-02-25 10:32:42'),('1497038980930097154','1496856403292221442','1497020375584755714','1497020375387623425','数据库',0.00,64,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/25/6d8ce72a5c5a4af49111d5cef5ca6539db.jpg',0,60,1,'Normal',0,'2022-02-25 10:41:21','2022-02-26 18:18:24'),('1497040613755867138','1496856403292221442','1497020375781888002','1497020375714779138','MySQL',0.00,128,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/25/a56f1814d9054260a5b72e42b181d54cmysql.jpg',0,0,1,'Normal',0,'2022-02-25 10:47:51','2022-02-25 16:45:48'),('1497042409194803202','1496833406225379330','1497020375781888003','1497020375714779138','Linux',0.00,64,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/25/7475e1d360cf46578e97c4cc9f28f1eclinux.jpg',0,0,1,'Draft',0,'2022-02-25 10:54:59','2022-02-25 10:54:59'),('1497045084519026690','1496834502289289218','1497020375848996866','1497020375781888004','HTML',0.00,64,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/25/6444d69eccb64026b91d6c440b3c4670Snipaste_2022-02-25_11-03-47.jpg',0,0,1,'Draft',0,'2022-02-25 11:05:37','2022-02-25 11:05:37');
/*!40000 ALTER TABLE `edu_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_course_collect`
--

DROP TABLE IF EXISTS `edu_course_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_course_collect` (
  `id` char(19) NOT NULL COMMENT '收藏ID',
  `course_id` char(19) NOT NULL COMMENT '课程讲师ID',
  `member_id` char(19) NOT NULL DEFAULT '' COMMENT '课程专业ID',
  `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_course_collect`
--

LOCK TABLES `edu_course_collect` WRITE;
/*!40000 ALTER TABLE `edu_course_collect` DISABLE KEYS */;
/*!40000 ALTER TABLE `edu_course_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_course_description`
--

DROP TABLE IF EXISTS `edu_course_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_course_description` (
  `id` char(19) NOT NULL COMMENT '课程ID',
  `description` text COMMENT '课程简介',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程简介';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_course_description`
--

LOCK TABLES `edu_course_description` WRITE;
/*!40000 ALTER TABLE `edu_course_description` DISABLE KEYS */;
INSERT INTO `edu_course_description` VALUES ('1497020997272883201','<p>C语言是一门面向过程的、抽象化的通用程序设计语言，广泛应用于底层开发。<br />C语言能以简易的方式编译、处理低级存储器。<br />C语言是仅产生少量的机器语言以及不需要任何运行环境支持便能运行的高效率程序设计语言。<br />尽管C语言提供了许多低级处理的功能，但仍然保持着跨平台的特性，以一个标准规格写出的C语言程序可在包括类似嵌入式处理器以及超级计算机等作业平台的许多计算机平台上进行编译。</p>','2022-02-25 09:29:54','2022-02-27 06:36:05'),('1497024448673370114','<p>C++是一种计算机高级程序设计语言，由C语言扩展升级而产生，最早于1979年由本贾尼&middot;斯特劳斯特卢普在AT&amp;T贝尔工作室研发。<br />C++既可以进行C语言的过程化程序设计，又可以进行以抽象数据类型为特点的基于对象的程序设计，还可以进行以继承和多态为特点的面向对象的程序设计。<br />C++擅长面向对象程序设计的同时，还可以进行基于过程的程序设计。<br />C++拥有计算机运行的实用性特征，同时还致力于提高大规模程序的编程质量与程序设计语言的问题描述能力。</p>','2022-02-25 09:43:37','2022-02-25 16:43:44'),('1497030255863418882','','2022-02-25 10:06:41','2022-02-25 16:44:12'),('1497032903526207490','<p>数据结构是计算机存储、组织数据的方式。<br />数据结构是指相互之间存在一种或多种特定关系的数据元素的集合。<br />通常情况下，精心选择的数据结构可以带来更高的运行或者存储效率。<br />数据结构往往同高效的检索算法和索引技术有关。</p>','2022-02-25 10:17:12','2022-02-25 16:44:25'),('1497034731961733121','<p>操作系统（operating system，简称OS）是管理计算机硬件与软件资源的计算机程序。<br />操作系统需要处理如管理与配置内存、决定系统资源供需的优先次序、控制输入设备与输出设备、操作网络与管理文件系统等基本事务。<br />操作系统也提供一个让用户与系统交互的操作界面。</p>','2022-02-25 10:24:28','2022-02-25 16:45:30'),('1497036801875922945','<p>计算机组成原理这门课程主要以单机系统为对象。</p>\n<p>介绍计算机系统的硬件组成，其核心是建立一个计算机系统的整机概念。<br />学习完这门课程将会对计算机的硬件组织方式有一个清晰的了解，能够明白计算机各部分硬件的功能，以及各部分硬件怎样协调工作。</p>','2022-02-25 10:32:42','2022-02-25 10:32:42'),('1497038980930097154','<p>数据库是结构化信息或数据（一般以电子形式存储在计算机系统中）的有组织的集合，通常由数据库管理系统 (DBMS) 来控制。<br />在现实中，数据、DBMS 及关联应用一起被称为数据库系统，通常简称为数据库。<br />为了提高数据处理和查询效率，当今最常见的数据库通常以行和列的形式将数据存储在一系列的表中，支持用户便捷地访问、管理、修改、更新、控制和组织数据。<br />另外，大多数数据库都使用结构化查询语言 (SQL) 来编写和查询数据。</p>','2022-02-25 10:41:21','2022-02-26 18:18:18'),('1497040613755867138','<p>MySQL是一个关系型数据库管理系统，由瑞典MySQL AB 公司开发，属于 Oracle 旗下产品。</p>\n<p>MySQL 是最流行的关系型数据库管理系统之一，在 WEB 应用方面，MySQL是最好的 RDBMS (Relational Database Management System，关系数据库管理系统) 应用软件之一。MySQL是一种关系型数据库管理系统，关系数据库将数据保存在不同的表中，而不是将所有数据放在一个大仓库内，这样就增加了速度并提高了灵活性。<br />MySQL所使用的 SQL 语言是用于访问数据库的最常用标准化语言。</p>\n<p>MySQL 软件采用了双授权政策，分为社区版和商业版，由于其体积小、速度快、总体拥有成本低，尤其是开放源码这一特点，一般中小型网站的开发都选择 MySQL 作为网站数据库。</p>','2022-02-25 10:47:51','2022-02-25 16:45:43'),('1497042409194803202','<p>Linux，全称GNU/Linux，是一种免费使用和自由传播的类UNIX操作系统，其内核由林纳斯&middot;本纳第克特&middot;托瓦兹于1991年10月5日首次发布，它主要受到Minix和Unix思想的启发，是一个基于POSIX的多用户、多任务、支持多线程和多CPU的操作系统。<br />它能运行主要的Unix工具软件、应用程序和网络协议。<br />它支持32位和64位硬件。<br />Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。<br />Linux有上百种不同的发行版，如基于社区开发的debian、archlinux，和基于商业开发的Red Hat Enterprise Linux、SUSE、Oracle Linux等。</p>','2022-02-25 10:54:59','2022-02-25 10:54:59'),('1497045084519026690','<p>HTML的全称为超文本标记语言，是一种标记语言。它包括一系列标签．通过这些标签可以将网络上的文档格式统一，使分散的Internet资源连接为一个逻辑整体。<br />HTML文本是由HTML命令组成的描述性文本，HTML命令可以说明文字，图形、动画、声音、表格、链接等。<br />超文本是一种组织信息的方式，它通过超级链接方法将文本中的文字、图表与其他信息媒体相关联。<br />这些相互关联的信息媒体可能在同一文本中，也可能是其他文件，或是地理位置相距遥远的某台计算机上的文件。<br />这种组织信息方式将分布在不同位置的信息资源用随机方式进行连接，为人们查找，检索信息提供方便。 [1]</p>','2022-02-25 11:05:37','2022-02-25 11:05:37');
/*!40000 ALTER TABLE `edu_course_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_role`
--

DROP TABLE IF EXISTS `edu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_role` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT '角色id',
  `user_id` char(19) NOT NULL COMMENT '用户id',
  `role_name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
  `des` varchar(20) DEFAULT '' COMMENT '角色描述',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_role`
--

LOCK TABLES `edu_role` WRITE;
/*!40000 ALTER TABLE `edu_role` DISABLE KEYS */;
INSERT INTO `edu_role` VALUES ('1','1111','admin','超级管理员','2019-11-11 13:09:32','2019-11-18 10:27:18'),('2','1112','admin','超级管理员','2022-02-23 10:08:11','2022-02-26 10:08:15');
/*!40000 ALTER TABLE `edu_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_subject`
--

DROP TABLE IF EXISTS `edu_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_subject` (
  `id` char(19) NOT NULL COMMENT '课程类别ID',
  `title` varchar(10) NOT NULL COMMENT '类别名称',
  `parent_id` char(19) NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程科目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_subject`
--

LOCK TABLES `edu_subject` WRITE;
/*!40000 ALTER TABLE `edu_subject` DISABLE KEYS */;
INSERT INTO `edu_subject` VALUES ('1497020375387623425','计算机基础','0',0,'2022-02-25 09:27:25','2022-02-25 09:27:25'),('1497020375387625425','数据结构','1497020375387623425',0,'2022-02-25 10:15:03','2022-02-25 10:15:06'),('1497020375450537986','操作系统','1497020375387623425',0,'2022-02-25 09:27:25','2022-02-25 09:27:25'),('1497020375517646849','计算机组成原理','1497020375387623425',0,'2022-02-25 09:27:25','2022-02-25 09:27:25'),('1497020375584755714','数据库','1497020375387623425',0,'2022-02-25 09:27:25','2022-02-25 09:27:25'),('1497020375584755715','后端开发','0',0,'2022-02-25 09:27:25','2022-02-25 09:27:25'),('1497020375647670274','C','1497020375584755715',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375647670275','C++','1497020375584755715',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375714779137','Java','1497020375584755715',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375714779138','数据库','0',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375781888002','MySQL','1497020375714779138',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375781888003','Linux','1497020375714779138',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375781888004','前端开发','0',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375848996866','HTML','1497020375781888004',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375848996867','CSS','1497020375781888004',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375916105730','JavaScript','1497020375781888004',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375916105731','开发工具','0',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375983214593','Git','1497020375916105731',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375983214594','自学编程经验','0',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020375983214595','大学如何提前了解编程','1497020375983214594',0,'2022-02-25 09:27:26','2022-02-25 09:27:26'),('1497020376046129153','大学如何确定技术方向','1497020375983214594',0,'2022-02-25 09:27:26','2022-02-25 09:27:26');
/*!40000 ALTER TABLE `edu_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_teacher`
--

DROP TABLE IF EXISTS `edu_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_teacher` (
  `id` char(19) NOT NULL COMMENT '讲师ID',
  `name` varchar(20) NOT NULL COMMENT '讲师姓名',
  `intro` varchar(500) NOT NULL DEFAULT '' COMMENT '讲师简介',
  `career` varchar(500) DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
  `level` int(10) unsigned NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
  `avatar` varchar(255) DEFAULT NULL COMMENT '讲师头像',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='讲师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_teacher`
--

LOCK TABLES `edu_teacher` WRITE;
/*!40000 ALTER TABLE `edu_teacher` DISABLE KEYS */;
INSERT INTO `edu_teacher` VALUES ('1496828255745896450','牛老师','是一名自学编程，有着很多失败经验的CS专业应届毕业生。','无',2,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/78ac2d0ad066465096aed62605633a74file.png',1,0,'2022-02-24 20:44:01','2022-02-24 20:44:30'),('1496829687396069377','虎老师','对待自己有一颗如虎的心，对待学生却丝毫不马虎。','无',2,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/3b15c0a14cbb47999e69cda23b72041ffile.png',2,0,'2022-02-24 20:49:42','2022-02-24 20:49:42'),('1496830731756793858','兔老师','敏捷如兔，讲课干净利落，通俗易通。','无',2,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/b497ed004e5949d3b1b38d59ec2ac2d0file.png',1,0,'2022-02-24 20:53:51','2022-02-24 20:53:51'),('1496831670735630338','龙老师','老师生龙活虎， 上得课堂，下得厨房,。','无',2,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/0d2740e859b74b16a3d91b017862beeefile.png',1,0,'2022-02-24 20:57:35','2022-02-24 20:57:35'),('1496833406225379330','羊老师','出得起羊相， 亮得出羊毛， 养得起羊圈， 教得出扬名。','无',1,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/7d80c0364ad94ad1a738269e6b402bf7file.png',1,0,'2022-02-24 21:04:29','2022-02-24 21:04:29'),('1496834502289289218','马老师','千里马常有， 马老师不常有, 马到成功，非老马不可。','无',2,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/6feb2d774ea9480088e2bfd03e29883ffile.png',1,0,'2022-02-24 21:08:50','2022-02-24 21:08:50'),('1496835721476993026','猴老师','有猴子的灵敏聪明， 也有猴子的调皮可爱，猴老师上课更是幽默风趣，不枉猴哥之称。','无',2,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/2221bd9c85ac4773b87d9f99fa3f584cfile.png',1,0,'2022-02-24 21:13:41','2022-02-24 21:13:41'),('1496840408095813633','蛇老师','蛇老师有着如蛇一样敏锐， 讲课总是能抓住学生们的心，让学生们学习来如鱼得水。','无',2,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/edfae44541234e649b4ab1bc95204c5dfile.png',1,0,'2022-02-24 21:32:18','2022-02-24 22:11:52'),('1496851858797391874','狗老师','有着狗宝宝的忠诚和温和， 同学们都喜欢的老师。','无',1,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/545e6f1c34c84b0f94ca0cb335184175file.png',1,0,'2022-02-24 22:17:48','2022-02-24 22:17:48'),('1496854071129174018','猪老师','有着猪宝宝的憨厚老实，对待学生体贴且耐性。','无',2,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/191fff3af3954d70a783bc74c15cd087file.png',1,0,'2022-02-24 22:26:35','2022-02-24 22:26:35'),('1496856403292221442','鸡老师','闻鸡起舞的鸡老师，兢兢业业为了学生们，是学生们公认的好老师','无',1,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/19de5e78a860407d9785bb0b0569d09ffile.png',1,0,'2022-02-24 22:35:51','2022-02-24 22:42:24'),('1496859044260900865','鼠老师','鼠老师是鼠害专家，各种bug都逃不过鼠老师秒杀。','无',2,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/24/0d31aba9c3144ebab93ca9e32f053263file.png',1,0,'2022-02-24 22:46:21','2022-02-24 22:46:21'),('1497727506311077890','123','12312','321312',1,NULL,12312,1,'2022-02-26 18:17:19','2022-02-26 18:17:19'),('1497911628165865474','312312','312312','312312',1,NULL,31231231,1,'2022-02-27 06:28:57','2022-02-27 06:28:57'),('1497939254901268482','12312','312312','12312',2,NULL,3123,1,'2022-02-27 08:18:43','2022-02-27 08:18:43');
/*!40000 ALTER TABLE `edu_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_user`
--

DROP TABLE IF EXISTS `edu_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_user` (
  `id` char(19) NOT NULL COMMENT '用户ID',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '用户密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif' COMMENT '用户头像',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_user`
--

LOCK TABLES `edu_user` WRITE;
/*!40000 ALTER TABLE `edu_user` DISABLE KEYS */;
INSERT INTO `edu_user` VALUES ('1111','admin','123456','老洋','https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/02/20/d610bde53d8dbd7e761a310ed38e2ace_1.jpg',0,'2019-11-01 10:39:47','2019-11-01 10:39:47'),('1112','test','123456','test','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0,'2019-11-01 16:36:07','2019-11-01 16:40:08');
/*!40000 ALTER TABLE `edu_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_video`
--

DROP TABLE IF EXISTS `edu_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_video` (
  `id` char(19) NOT NULL COMMENT '视频ID',
  `course_id` char(19) NOT NULL COMMENT '课程ID',
  `chapter_id` char(19) NOT NULL COMMENT '章节ID',
  `title` varchar(50) NOT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) DEFAULT NULL COMMENT '原始文件名称',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `play_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '播放次数',
  `is_free` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT '0' COMMENT '视频时长（秒）',
  `status` varchar(20) NOT NULL DEFAULT 'Empty' COMMENT 'Empty未上传 Transcoding转码中  Normal正常',
  `size` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '视频源文件大小（字节）',
  `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_chapter_id` (`chapter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程视频';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_video`
--

LOCK TABLES `edu_video` WRITE;
/*!40000 ALTER TABLE `edu_video` DISABLE KEYS */;
INSERT INTO `edu_video` VALUES ('1497038273548144642','1497036801875922945','1497037259608707073','第一节：我的学习方式','','',1,0,1,0,'Empty',0,1,'2022-02-25 10:38:33','2022-02-25 10:38:33'),('1497104758622650370','1497020997272883201','1497021310486728706','第一小节：我的c语言之旅途','082df8f0592a4f5f90a94be68c728f91','video.mp4',1,0,1,0,'Empty',0,1,'2022-02-25 15:02:44','2022-02-25 16:21:48'),('1497125960137367554','1497020997272883201','1497021310486728706','第二小节：我的c语言征途','e39642026d7b4317922f6299999174a6','video.mp4',1,0,0,0,'Empty',0,1,'2022-02-25 16:26:59','2022-02-25 16:26:59');
/*!40000 ALTER TABLE `edu_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistics_daily`
--

DROP TABLE IF EXISTS `statistics_daily`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statistics_daily` (
  `id` char(19) NOT NULL COMMENT '主键',
  `date_calculated` varchar(20) NOT NULL COMMENT '统计日期',
  `register_num` int(11) NOT NULL DEFAULT '0' COMMENT '注册人数',
  `login_num` int(11) NOT NULL DEFAULT '0' COMMENT '登录人数',
  `video_view_num` int(11) NOT NULL DEFAULT '0' COMMENT '每日播放视频数',
  `course_num` int(11) NOT NULL DEFAULT '0' COMMENT '每日新增课程数',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `statistics_day` (`date_calculated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站统计日数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistics_daily`
--

LOCK TABLES `statistics_daily` WRITE;
/*!40000 ALTER TABLE `statistics_daily` DISABLE KEYS */;
INSERT INTO `statistics_daily` VALUES ('1497372145645277185','2022-02-25',0,112,139,193,'2022-02-26 08:45:14','2022-02-26 08:45:14'),('1497617460327669761','2022-02-26',0,171,153,178,'2022-02-26 11:00:02','2022-02-26 11:00:02');
/*!40000 ALTER TABLE `statistics_daily` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `id` char(19) NOT NULL DEFAULT '',
  `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
  `course_id` varchar(19) NOT NULL DEFAULT '' COMMENT '课程id',
  `course_title` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `course_cover` varchar(255) DEFAULT NULL COMMENT '课程封面',
  `teacher_name` varchar(20) DEFAULT NULL COMMENT '讲师名称',
  `member_id` varchar(19) NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '会员手机',
  `total_fee` decimal(10,2) DEFAULT '0.01' COMMENT '订单金额（分）',
  `pay_type` tinyint(3) DEFAULT NULL COMMENT '支付类型（1：微信 2：支付宝）',
  `status` tinyint(3) DEFAULT NULL COMMENT '订单状态（0：未支付 1：已支付）',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_order_no` (`order_no`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_pay_log`
--

DROP TABLE IF EXISTS `t_pay_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_pay_log` (
  `id` char(19) NOT NULL DEFAULT '',
  `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
  `pay_time` datetime DEFAULT NULL COMMENT '支付完成时间',
  `total_fee` decimal(10,2) DEFAULT '0.01' COMMENT '支付金额（分）',
  `transaction_id` varchar(30) DEFAULT NULL COMMENT '交易流水号',
  `trade_state` char(20) DEFAULT NULL COMMENT '交易状态',
  `pay_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付类型（1：微信 2：支付宝）',
  `attr` text COMMENT '其他属性',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_pay_log`
--

LOCK TABLES `t_pay_log` WRITE;
/*!40000 ALTER TABLE `t_pay_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_pay_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ucenter_member`
--

DROP TABLE IF EXISTS `ucenter_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ucenter_member` (
  `id` char(19) CHARACTER SET utf8mb4 NOT NULL COMMENT '会员id',
  `openid` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '微信openid',
  `mobile` varchar(11) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(2) unsigned DEFAULT NULL COMMENT '性别 1 女，2 男',
  `age` tinyint(3) unsigned DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户头像',
  `sign` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户签名',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='会员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ucenter_member`
--

LOCK TABLES `ucenter_member` WRITE;
/*!40000 ALTER TABLE `ucenter_member` DISABLE KEYS */;
INSERT INTO `ucenter_member` VALUES ('1496790659367985153',NULL,'13047638263','e10adc3949ba59abbe56e057f20f883e','laoyang',NULL,NULL,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/01/12/c319a3da734b4de8b829291800b4a806tuzi.jpg',NULL,0,0,'2022-02-24 18:14:37','2022-02-24 18:14:37'),('1497839930724888577',NULL,'15575222885','e10adc3949ba59abbe56e057f20f883e','yangzi',NULL,NULL,'https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/01/12/c319a3da734b4de8b829291800b4a806tuzi.jpg',NULL,0,0,'2022-02-27 01:44:03','2022-02-27 01:44:03');
/*!40000 ALTER TABLE `ucenter_member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-27 22:41:30
