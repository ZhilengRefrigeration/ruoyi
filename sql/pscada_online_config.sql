-- MySQL dump 10.16  Distrib 10.1.37-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 192.168.8.201    Database: pscada_online_config
-- ------------------------------------------------------
-- Server version	10.3.24-MariaDB-log

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
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin DEFAULT NULL COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO `config_info` VALUES (1,'application-dev.yml','DEFAULT_GROUP','spring:\n  main:\n    allow-bean-definition-overriding: true\n\n#请求处理的超时时间\nribbon:\n  ReadTimeout: 10000\n  ConnectTimeout: 10000\n\n# feign 配置\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n','57470c6d167154919418fa150863b7fb','2019-11-29 16:31:20','2020-09-01 09:14:30',NULL,'0:0:0:0:0:0:0:1','','','通用配置','null','null','yaml','null'),(2,'ruoyi-gateway-dev.yml','DEFAULT_GROUP','spring:\r\n  redis:\r\n    host: 192.168.8.201\r\n    port: 6379\r\n    password: \r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          lowerCaseServiceId: true\r\n          enabled: true\r\n      routes:\r\n        # 认证中心\r\n        - id: ruoyi-auth\r\n          uri: lb://ruoyi-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n          filters:\r\n            # 验证码处理\r\n            - CacheRequestFilter\r\n            - ValidateCodeFilter\r\n            - StripPrefix=1\r\n        # 代码生成\r\n        - id: ruoyi-gen\r\n          uri: lb://ruoyi-gen\r\n          predicates:\r\n            - Path=/code/**\r\n          filters:\r\n            - StripPrefix=1\r\n        # 定时任务\r\n        - id: ruoyi-job\r\n          uri: lb://ruoyi-job\r\n          predicates:\r\n            - Path=/schedule/**\r\n          filters:\r\n            - StripPrefix=1\r\n        # 系统模块\r\n        - id: ruoyi-system\r\n          uri: lb://ruoyi-system\r\n          predicates:\r\n            - Path=/system/**\r\n          filters:\r\n            - StripPrefix=1\r\n        # 文件服务\r\n        - id: ruoyi-file\r\n          uri: lb://ruoyi-file\r\n          predicates:\r\n            - Path=/file/**\r\n          filters:\r\n            - StripPrefix=1\r\n\r\n# 不校验白名单\r\nignore:\r\n  whites:\r\n    - /auth/logout\r\n    - /auth/login\r\n    - /*/v2/api-docs\r\n    - /csrf\r\n','ef4a58daf989827334b3aac1c9d68392','2020-05-14 14:17:55','2020-11-18 17:53:23',NULL,'0:0:0:0:0:0:0:1','','','网关模块','null','null','yaml','null'),(3,'ruoyi-auth-dev.yml','DEFAULT_GROUP','spring: \n  datasource:\n    driver-class-name: org.mariadb.jdbc.Driver\n    url: jdbc:mysql://192.168.8.201:3306/pscada_online?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: gridnt123456\n  redis:\n    host: 192.168.8.201\n    port: 6379\n    password: \n','882ded47786e758dff156023ca718017','2020-11-20 00:00:00','2020-12-18 03:34:08',NULL,'192.168.8.13','','','认证中心','null','null','yaml','null'),(4,'ruoyi-monitor-dev.yml','DEFAULT_GROUP','# Spring\r\nspring: \r\n  security:\r\n    user:\r\n      name: ruoyi\r\n      password: 123456\r\n  boot:\r\n    admin:\r\n      ui:\r\n        title: 若依服务状态监控\r\n','8e49d78998a7780d780305aeefe4fb1b','2020-11-20 00:00:00','2020-11-20 00:00:00',NULL,'0:0:0:0:0:0:0:1','','','监控中心','null','null','yaml','null'),(5,'ruoyi-system-dev.yml','DEFAULT_GROUP','# Spring\nspring: \n  redis:\n    host: 192.168.8.201\n    port: 6379\n    password: \n  datasource:\n    driver-class-name: org.mariadb.jdbc.Driver\n    url: jdbc:mysql://192.168.8.201:3306/pscada_online?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: gridnt123456\n\n# Mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.ruoyi.system\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger 配置\nswagger:\n  title: 系统模块接口文档\n  license: Powered By ruoyi\n  licenseUrl: https://ruoyi.vip\n  authorization:\n    name: RuoYi OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: 客户端授权范围\n    token-url-list:\n      - http://192.168.8.201:8080/auth/oauth/token\n','72f72a060d87479031feb421c3802a63','2020-11-20 00:00:00','2020-12-18 03:34:27',NULL,'192.168.8.13','','','系统模块','null','null','yaml','null'),(6,'ruoyi-gen-dev.yml','DEFAULT_GROUP','# Spring\nspring: \n  redis:\n    host: 192.168.8.201\n    port: 6379\n    password: \n  datasource: \n    driver-class-name: org.mariadb.jdbc.Driver\n    url: jdbc:mysql://192.168.8.201:3306/pscada_online?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: gridnt123456\n\n# Mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.ruoyi.gen.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger 配置\nswagger:\n  title: 代码生成接口文档\n  license: Powered By ruoyi\n  licenseUrl: https://ruoyi.vip\n  authorization:\n    name: RuoYi OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: 客户端授权范围\n    token-url-list:\n      - http://192.168.8.201:8080/auth/oauth/token\n\n# 代码生成\ngen: \n  # 作者\n  author: ruoyi\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.ruoyi.system\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n','ba080f94354e89e684991b2251560288','2020-11-20 00:00:00','2020-12-18 03:32:50',NULL,'192.168.8.13','','','代码生成','null','null','yaml','null'),(7,'ruoyi-job-dev.yml','DEFAULT_GROUP','# Spring\nspring: \n  redis:\n    host: 192.168.8.201\n    port: 6379\n    password: \n  datasource:\n    driver-class-name: org.mariadb.jdbc.Driver\n    url: jdbc:mysql://192.168.8.201:3306/pscada_online?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: gridnt123456\n\n# Mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.ruoyi.job.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger 配置\nswagger:\n  title: 定时任务接口文档\n  license: Powered By ruoyi\n  licenseUrl: https://ruoyi.vip\n  authorization:\n    name: RuoYi OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: 客户端授权范围\n    token-url-list:\n      - http://192.168.8.201:8080/auth/oauth/token\n','e9f884d297bd09f8b9026b950a59ed1a','2020-11-20 00:00:00','2020-12-18 03:42:04',NULL,'192.168.8.13','','','定时任务','null','null','yaml','null'),(8,'ruoyi-file-dev.yml','DEFAULT_GROUP','## 本地文件上传    \nfile:\n    domain: http://192.168.8.201:9300\n    path: /opt/pscada-online-uploadPath\n    prefix: /statics\n\n# FastDFS配置\nfdfs:\n  domain: http://192.168.8.201\n  soTimeout: 3000\n  connectTimeout: 2000\n  trackerList: 192.168.8.201:22122','237fa92caa520ed03a5150c58faf81b0','2020-11-20 00:00:00','2020-12-18 04:21:21',NULL,'192.168.8.13','','','文件服务','null','null','yaml','null'),(9,'sentinel-ruoyi-gateway','DEFAULT_GROUP','[\r\n    {\r\n        \"resource\": \"ruoyi-auth\",\r\n        \"count\": 500,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"ruoyi-system\",\r\n        \"count\": 1000,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"ruoyi-gen\",\r\n        \"count\": 200,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"ruoyi-job\",\r\n        \"count\": 300,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    }\r\n]','9f3a3069261598f74220bc47958ec252','2020-11-20 00:00:00','2020-11-20 00:00:00',NULL,'0:0:0:0:0:0:0:1','','','限流策略','null','null','json','null');
/*!40000 ALTER TABLE `config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_aggr`
--

DROP TABLE IF EXISTS `config_info_aggr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_aggr`
--

LOCK TABLES `config_info_aggr` WRITE;
/*!40000 ALTER TABLE `config_info_aggr` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_aggr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_beta`
--

DROP TABLE IF EXISTS `config_info_beta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin DEFAULT NULL COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_beta`
--

LOCK TABLES `config_info_beta` WRITE;
/*!40000 ALTER TABLE `config_info_beta` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_beta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_tag`
--

DROP TABLE IF EXISTS `config_info_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin DEFAULT NULL COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_tag`
--

LOCK TABLES `config_info_tag` WRITE;
/*!40000 ALTER TABLE `config_info_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
/*!40000 ALTER TABLE `config_tags_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_tags_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
/*!40000 ALTER TABLE `group_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT current_timestamp(),
  `gmt_modified` datetime NOT NULL DEFAULT current_timestamp(),
  `src_user` text COLLATE utf8_bin DEFAULT NULL,
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
INSERT INTO `his_config_info` VALUES (6,1,'ruoyi-gen-dev.yml','DEFAULT_GROUP','','# Spring\r\nspring: \r\n  redis:\r\n    host: 192.168.8.201\r\n    port: 6379\r\n    password: \r\n  datasource: \r\n    driver-class-name: org.mariadb.jdbc.Driver\r\n    url: jdbc:mysql://192.168.8.201:3306/pscada_online?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: gridnt123456\r\n\r\n# Mybatis配置\r\nmybatis:\r\n    # 搜索指定包别名\r\n    typeAliasesPackage: com.ruoyi.gen.domain\r\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\r\n    mapperLocations: classpath:mapper/**/*.xml\r\n\r\n# swagger 配置\r\nswagger:\r\n  title: 代码生成接口文档\r\n  license: Powered By ruoyi\r\n  licenseUrl: https://ruoyi.vip\r\n  authorization:\r\n    name: RuoYi OAuth\r\n    auth-regex: ^.*$\r\n    authorization-scope-list:\r\n      - scope: server\r\n        description: 客户端授权范围\r\n    token-url-list:\r\n      - http://192.168.8.201:8080/auth/oauth/token\r\n\r\n# 代码生成\r\ngen: \r\n  # 作者\r\n  author: ruoyi\r\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\r\n  packageName: com.ruoyi.system\r\n  # 自动去除表前缀，默认是false\r\n  autoRemovePre: false\r\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\r\n  tablePrefix: sys_\r\n','888139d720c7f480b256f573ac003aa4','2020-12-18 11:32:50','2020-12-18 03:32:50',NULL,'192.168.8.13','U',''),(3,2,'ruoyi-auth-dev.yml','DEFAULT_GROUP','','spring: \r\n  datasource:\r\n    driver-class-name: org.mariadb.jdbc.Driver\r\n    url: jdbc:mysql://192.168.8.201:3306/pscada_online?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: gridnt123456\r\n  redis:\r\n    host: 192.168.8.201\r\n    port: 6379\r\n    password: \r\n','688f353a2800cce3fb8d0fdad1c31efd','2020-12-18 11:34:08','2020-12-18 03:34:08',NULL,'192.168.8.13','U',''),(5,3,'ruoyi-system-dev.yml','DEFAULT_GROUP','','# Spring\r\nspring: \r\n  redis:\r\n    host: 192.168.8.201\r\n    port: 6379\r\n    password: \r\n  datasource:\r\n    driver-class-name: org.mariadb.jdbc.Driver\r\n    url: jdbc:mysql://192.168.8.201:3306/pscada_online?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: gridnt123456\r\n\r\n# Mybatis配置\r\nmybatis:\r\n    # 搜索指定包别名\r\n    typeAliasesPackage: com.ruoyi.system\r\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\r\n    mapperLocations: classpath:mapper/**/*.xml\r\n\r\n# swagger 配置\r\nswagger:\r\n  title: 系统模块接口文档\r\n  license: Powered By ruoyi\r\n  licenseUrl: https://ruoyi.vip\r\n  authorization:\r\n    name: RuoYi OAuth\r\n    auth-regex: ^.*$\r\n    authorization-scope-list:\r\n      - scope: server\r\n        description: 客户端授权范围\r\n    token-url-list:\r\n      - http://192.168.8.201:8080/auth/oauth/token\r\n','470f9a259ef1d0d56bd9cd6fe952c1ea','2020-12-18 11:34:27','2020-12-18 03:34:27',NULL,'192.168.8.13','U',''),(7,4,'ruoyi-job-dev.yml','DEFAULT_GROUP','','# Spring\r\nspring: \r\n  redis:\r\n    host: 192.168.8.201\r\n    port: 6379\r\n    password: \r\n  datasource:\r\n    driver-class-name: org.mariadb.jdbc.Driver\r\n    url: jdbc:mysql://192.168.8.201:3306/pscada_online?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: gridnt123456\r\n\r\n# Mybatis配置\r\nmybatis:\r\n    # 搜索指定包别名\r\n    typeAliasesPackage: com.ruoyi.job.domain\r\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\r\n    mapperLocations: classpath:mapper/**/*.xml\r\n\r\n# swagger 配置\r\nswagger:\r\n  title: 定时任务接口文档\r\n  license: Powered By ruoyi\r\n  licenseUrl: https://ruoyi.vip\r\n  authorization:\r\n    name: RuoYi OAuth\r\n    auth-regex: ^.*$\r\n    authorization-scope-list:\r\n      - scope: server\r\n        description: 客户端授权范围\r\n    token-url-list:\r\n      - http://192.168.8.201:8080/auth/oauth/token\r\n','754f7d0ff1910d123d6dcaf0df546bb3','2020-12-18 11:35:06','2020-12-18 03:35:06',NULL,'192.168.8.13','U',''),(7,5,'ruoyi-job-dev.yml','DEFAULT_GROUP','','# Spring\nspring: \n  redis:\n    host: 192.168.8.201\n    port: 6379\n    password: \n  datasource:\n    driver-class-name: org.mariadb.jdbc.Driver\n    url: jdbc:mysql://192.168.8.201:3306/pscada_online?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: gridnt123456\n\n# Mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.ruoyi.job.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger 配置\nswagger:\n  title: 定时任务接口文档\n  license: Powered By ruoyi\n  licenseUrl: https://ruoyi.vip\n  authorization:\n    name: RuoYi OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: 客户端授权范围\n    token-url-list:\n      - http://192.168.8.201:8080/auth/oauth/token\n','e9f884d297bd09f8b9026b950a59ed1a','2020-12-18 11:42:04','2020-12-18 03:42:04',NULL,'192.168.8.13','U',''),(8,6,'ruoyi-file-dev.yml','DEFAULT_GROUP','','## 本地文件上传    \r\nfile:\r\n    domain: http://127.0.0.1:9300\r\n    path: D:/ruoyi/uploadPath\r\n    prefix: /statics\r\n\r\n# FastDFS配置\r\nfdfs:\r\n  domain: http://127.0.0.1\r\n  soTimeout: 3000\r\n  connectTimeout: 2000\r\n  trackerList: 127.0.0.1:22122','e6aae59c07b40c652044268052841566','2020-12-18 11:43:03','2020-12-18 03:43:03',NULL,'192.168.8.13','U',''),(8,7,'ruoyi-file-dev.yml','DEFAULT_GROUP','','## 本地文件上传    \nfile:\n    domain: http://192.168.8.201:9300\n    path: /opt/pscada-online/uploadPath\n    prefix: /statics\n\n# FastDFS配置\nfdfs:\n  domain: http://192.168.8.201\n  soTimeout: 3000\n  connectTimeout: 2000\n  trackerList: 192.168.8.201:22122','a6e43932631a4b440e00a0b04f2806ba','2020-12-18 11:43:12','2020-12-18 03:43:12',NULL,'192.168.8.13','U',''),(8,8,'ruoyi-file-dev.yml','DEFAULT_GROUP','','## 本地文件上传    \nfile:\n    domain: http://192.168.8.201:9300\n    path: /opt/pscada-online/uploadPath\n    prefix: /statics\n\n# FastDFS配置\nfdfs:\n  domain: http://192.168.8.201\n  soTimeout: 3000\n  connectTimeout: 2000\n  trackerList: 192.168.8.201:22122','a6e43932631a4b440e00a0b04f2806ba','2020-12-18 12:21:21','2020-12-18 04:21:21',NULL,'192.168.8.13','U','');
/*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(512) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('nacos','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
/*!40000 ALTER TABLE `tenant_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('nacos','$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'pscada_online_config'
--

--
-- Dumping routines for database 'pscada_online_config'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-18 13:29:34
