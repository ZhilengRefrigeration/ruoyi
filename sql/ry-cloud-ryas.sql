/*
 Navicat Premium Data Transfer

 Source Server         : MySQL (CIT1)
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           :
 Source Schema         : ry-cloud

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 22/02/2024 11:11:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for WMS_M_ITEM_INFO
-- ----------------------------
DROP TABLE IF EXISTS `WMS_M_ITEM_INFO`;
CREATE TABLE `WMS_M_ITEM_INFO`  (
  `DEPT_ID` int NOT NULL DEFAULT 100 COMMENT '从属部门ID',
  `ITEM_CD` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '物品代码',
  `ITEM_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '物品名称',
  `ENABLE_FLG` int NOT NULL DEFAULT 1 COMMENT '启用标志',
  `DEFAULT_STG_BIN_CD` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '默认库位号',
  `ITEM_TYPE_CD` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '物品类型代码',
  `STD_UNIT_CD` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标准单位代码',
  `PKG_UNIT_CD` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '包装单位代码',
  `GOODS_CLS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '物品区分',
  `LOT_NO_MGMT_CLS` int NOT NULL DEFAULT 0 COMMENT '批号管理区分(0:不管理, 1:管理)',
  `MANUFACTURER` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生产商',
  `SUPPLIER` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '供应商',
  `SPEC_1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '规格1',
  `SPEC_2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '规格2',
  `SPEC_3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '规格3',
  `SPEC_4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '规格4',
  `SPEC_5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '规格5',
  `CLS_1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分类1',
  `CLS_2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分类2',
  `CLS_3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分类3',
  `CLS_4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分类4',
  `CLS_5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分类5',
  `DELIVERY_PERIOD` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '供货周期',
  `PREP_LEAD_TIME` decimal(18, 6) NULL DEFAULT NULL COMMENT '筹备提前期',
  `INSTOCK_LEAD_TIME` decimal(18, 6) NULL DEFAULT NULL COMMENT '入库提前期',
  `RESTING_PERIOD` decimal(18, 6) NULL DEFAULT NULL COMMENT '放置期',
  `OUTSTOCK_LEAD_TIME` decimal(18, 6) NULL DEFAULT NULL COMMENT '出库提前期',
  `SAFETY_STOCK` decimal(18, 6) NOT NULL DEFAULT 0.000000 COMMENT '安全库存量',
  `MAX_INV_QTY` decimal(18, 6) NOT NULL DEFAULT 0.000000 COMMENT '最大库存量',
  `PURCH_LIMIT_QTY` decimal(18, 6) NOT NULL DEFAULT 0.000000 COMMENT '发起购买的阈值',
  `OUTSTOCK_REQ_MIN_QTY` decimal(18, 6) NOT NULL DEFAULT 0.000000 COMMENT '出库申请最小数量',
  `OUTSTOCK_UNIT_CLS` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '出库单位区分',
  `NET_WEIGHT_PER_UNIT` decimal(18, 6) NULL DEFAULT NULL COMMENT '单位净重',
  `OWN_VOL_M3` decimal(18, 6) NULL DEFAULT NULL COMMENT '自身体积(M3)',
  `SIZE_D` decimal(18, 6) NULL DEFAULT NULL COMMENT '尺寸(长)',
  `SIZE_W` decimal(18, 6) NULL DEFAULT NULL COMMENT '尺寸(宽)',
  `SIZE_H` decimal(18, 6) NULL DEFAULT NULL COMMENT '尺寸(高)',
  `PKG_RQMT_DESC` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '包装要求描述',
  `STACKING_RQMT_DESC` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '码放要求描述',
  `STG_RQMT_DESC` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '储存要求描述',
  `PICTURE_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片ID',
  `PICTURE_URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片URL',
  `REMARK_1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注1',
  `REMARK_2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注2',
  `REMARK_3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注3',
  `REMARK_4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注4',
  `REMARK_5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注5',
  `UPDATE_COUNT` int NOT NULL DEFAULT 0 COMMENT '更新次数',
  `DELETE_FLAG` int NOT NULL DEFAULT 0 COMMENT '删除标志',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ITEM_CD`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '物品基础信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of WMS_M_ITEM_INFO
-- ----------------------------
INSERT INTO `WMS_M_ITEM_INFO` VALUES (100, 'G00001', '测试产品1', 1, NULL, 'ITYPE00003', 'UNIT00001', 'UNIT00003', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.000000, 0.000000, 0.000000, 0.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '20240221172017A002', 'http://127.0.0.1:9300/statics/2024/02/21/20240221172017A002.jpg', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-21 17:15:47', '1', '2024-02-21 17:20:17', NULL);

-- ----------------------------
-- Table structure for WMS_M_ITEM_TYPE
-- ----------------------------
DROP TABLE IF EXISTS `WMS_M_ITEM_TYPE`;
CREATE TABLE `WMS_M_ITEM_TYPE`  (
  `DEPT_ID` int NOT NULL DEFAULT 100 COMMENT '从属部门ID',
  `ITEM_TYPE_CD` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '物品类型编码',
  `ITEM_TYPE_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '物品类型名称',
  `REMARK_1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注1',
  `REMARK_2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注2',
  `REMARK_3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注3',
  `REMARK_4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注4',
  `REMARK_5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注5',
  `UPDATE_COUNT` int NOT NULL DEFAULT 0 COMMENT '更新次数',
  `DELETE_FLAG` int NOT NULL DEFAULT 0 COMMENT '更新次数',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ITEM_TYPE_CD`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '物品类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of WMS_M_ITEM_TYPE
-- ----------------------------
INSERT INTO `WMS_M_ITEM_TYPE` VALUES (100, 'ITYPE00001', '原材料', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-20 15:27:52', '1', '2024-02-20 15:27:52', NULL);
INSERT INTO `WMS_M_ITEM_TYPE` VALUES (100, 'ITYPE00002', '半成品', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-20 15:28:05', '1', '2024-02-20 15:28:05', NULL);
INSERT INTO `WMS_M_ITEM_TYPE` VALUES (100, 'ITYPE00003', '成品', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-20 15:28:10', '1', '2024-02-20 15:28:10', NULL);

-- ----------------------------
-- Table structure for WMS_M_UNIT_INFO
-- ----------------------------
DROP TABLE IF EXISTS `WMS_M_UNIT_INFO`;
CREATE TABLE `WMS_M_UNIT_INFO`  (
  `DEPT_ID` int NOT NULL DEFAULT 100 COMMENT '从属部门ID',
  `UNIT_CODE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '单位代码',
  `UNIT_NAME` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '单位名称',
  `REMARK_1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注1',
  `REMARK_2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注2',
  `REMARK_3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注3',
  `REMARK_4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注4',
  `REMARK_5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注5',
  `UPDATE_COUNT` int NOT NULL DEFAULT 0 COMMENT '更新次数',
  `DELETE_FLAG` int NOT NULL DEFAULT 0 COMMENT '删除标志',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`UNIT_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '单位基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of WMS_M_UNIT_INFO
-- ----------------------------
INSERT INTO `WMS_M_UNIT_INFO` VALUES (100, 'UNIT00001', '个', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-19 13:37:19', '1', '2024-02-19 13:37:19', NULL);
INSERT INTO `WMS_M_UNIT_INFO` VALUES (100, 'UNIT00002', '包', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-19 13:37:27', '1', '2024-02-19 13:37:27', NULL);
INSERT INTO `WMS_M_UNIT_INFO` VALUES (100, 'UNIT00003', '托', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-19 13:37:33', '1', '2024-02-19 13:37:33', NULL);
INSERT INTO `WMS_M_UNIT_INFO` VALUES (100, 'UNIT00004', '台', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-19 13:37:37', '1', '2024-02-19 13:37:37', NULL);
INSERT INTO `WMS_M_UNIT_INFO` VALUES (100, 'UNIT00005', '辆', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-19 13:37:43', '1', '2024-02-19 13:37:43', NULL);
INSERT INTO `WMS_M_UNIT_INFO` VALUES (100, 'UNIT00006', 'PCS', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-19 13:37:48', '1', '2024-02-19 13:37:48', NULL);
INSERT INTO `WMS_M_UNIT_INFO` VALUES (100, 'UNIT00007', '把', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-19 13:38:11', '1', '2024-02-19 13:38:11', NULL);
INSERT INTO `WMS_M_UNIT_INFO` VALUES (100, 'UNIT00008', '根', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-19 13:38:30', '1', '2024-02-19 13:38:30', NULL);
INSERT INTO `WMS_M_UNIT_INFO` VALUES (100, 'UNIT00009', '颗', NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-19 13:38:45', '1', '2024-02-19 13:38:45', NULL);

-- ----------------------------
-- Table structure for WMS_M_WAREHOUSE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `WMS_M_WAREHOUSE_INFO`;
CREATE TABLE `WMS_M_WAREHOUSE_INFO`  (
  `DEPT_ID` int NOT NULL DEFAULT 100 COMMENT '从属部门ID',
  `WHS_CD` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '仓库代码',
  `WHS_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '仓库名称',
  `WHS_TYPE_CD` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '仓库类型代码',
  `ABBR` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '缩写',
  `ADDR_1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址1',
  `ADDR_2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址2',
  `ADDR_3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址3',
  `PHONE_NO` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
  `ZIP_CD` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮编',
  `EMAIL` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系邮箱',
  `FAX_NO` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '传真号',
  `RESP_PERSON` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '负责人',
  `AREA` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '区域',
  `RENTAL_FEE` decimal(18, 6) NULL DEFAULT NULL COMMENT '租赁费用',
  `STORING_FEE` decimal(18, 6) NULL DEFAULT NULL COMMENT '存储费用',
  `REMARK_1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `REMARK_2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `REMARK_3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `REMARK_4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `REMARK_5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `UPDATE_COUNT` int NOT NULL DEFAULT 0 COMMENT '更新次数',
  `DELETE_FLAG` int NOT NULL DEFAULT 0 COMMENT '删除标志',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`WHS_CD`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '仓库基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of WMS_M_WAREHOUSE_INFO
-- ----------------------------
INSERT INTO `WMS_M_WAREHOUSE_INFO` VALUES (100, 'WHS001', '仓库1', 'OWN', 'WHS1', 'Flat 25, 12/F apartment and floor no. Acacia Building', '', NULL, '08524785441', '200001', '', NULL, '张三', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '1', '2024-02-19 13:42:34', '1', '2024-02-19 13:42:34', NULL);

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `tpl_web_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '前端模板类型（element-ui模版 element-plus模版）',
  `tpl_backend_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '后端模板类型（MyBaitsDynamicSQL模板，常规模板）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '代码生成业务表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (9, 'WMS_M_UNIT_INFO', '单位基础信息表', NULL, NULL, 'UnitInfo', 'crud', 'element-plus', 'mybatis-dynamic', 'com.ruoyi.wms', 'wms', 'UnitInfo', '单位信息管理', 'ryas', '0', '/', '{\"parentMenuId\":\"2000\"}', 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:26', NULL);
INSERT INTO `gen_table` VALUES (12, 'WMS_M_WAREHOUSE_INFO', '仓库基础信息表', NULL, NULL, 'WarehouseInfo', 'crud', 'element-plus', 'mybatis-dynamic', 'com.ruoyi.wms', 'wms', 'WarehouseInfo', '仓库基础信息', 'ryas', '0', '/', '{\"parentMenuId\":2000}', 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04', NULL);
INSERT INTO `gen_table` VALUES (13, 'sys_file', '文件存储记录表', NULL, NULL, 'SysFile', 'crud', 'element-plus', 'mybatis-dynamic', 'com.ruoyi.file', 'file', 'FileRecord', '文件存储记录', 'ryas', '0', '/', '{\"parentMenuId\":\"1\"}', 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:14', NULL);
INSERT INTO `gen_table` VALUES (14, 'sys_seq_rule', '序列号生成规则表', NULL, NULL, 'SysSeqRule', 'crud', 'element-plus', 'mybatis-dynamic', 'com.ruoyi.system', 'system', 'SeqRule', '序列号生成规则', 'ryas', '0', '/', '{\"parentMenuId\":1}', 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26', NULL);
INSERT INTO `gen_table` VALUES (15, 'WMS_M_ITEM_TYPE', '物品类型表', NULL, NULL, 'ItemType', 'crud', 'element-plus', 'mybatis-dynamic', 'com.ruoyi.wms', 'wms', 'ItemType', '物品类型', 'ryas', '0', '/', '{\"parentMenuId\":2000}', 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11', NULL);
INSERT INTO `gen_table` VALUES (16, 'WMS_M_ITEM_INFO', '物品基础信息表', NULL, NULL, 'ItemInfo', 'crud', 'element-plus', 'normal', 'com.ruoyi.wms', 'wms', 'ItemInfo', '物品基础信息', 'ryas', '0', '/', '{\"parentMenuId\":\"2000\"}', 'admin', '2024-02-20 08:25:28', '', '2024-02-21 02:00:27', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典类型',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 328 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '代码生成业务表字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (132, 9, 'DEPT_ID', '从属部门ID', 'int', 'Long', 'deptId', '0', '0', '0', '0', '0', '0', '0', 'EQ', 'input', '', 1, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:26');
INSERT INTO `gen_table_column` VALUES (133, 9, 'UNIT_CODE', '单位代码', 'varchar(25)', 'String', 'unitCode', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 2, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:26');
INSERT INTO `gen_table_column` VALUES (134, 9, 'UNIT_NAME', '单位名称', 'varchar(10)', 'String', 'unitName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:26');
INSERT INTO `gen_table_column` VALUES (135, 9, 'REMARK_1', '备注1', 'varchar(100)', 'String', 'remark1', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:26');
INSERT INTO `gen_table_column` VALUES (136, 9, 'REMARK_2', '备注2', 'varchar(100)', 'String', 'remark2', '0', '0', NULL, '0', '0', '0', '0', 'EQ', 'input', '', 5, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:26');
INSERT INTO `gen_table_column` VALUES (137, 9, 'REMARK_3', '备注3', 'varchar(100)', 'String', 'remark3', '0', '0', NULL, '0', '0', '0', '0', 'EQ', 'input', '', 6, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:27');
INSERT INTO `gen_table_column` VALUES (138, 9, 'REMARK_4', '备注4', 'varchar(100)', 'String', 'remark4', '0', '0', NULL, '0', '0', '0', '0', 'EQ', 'input', '', 7, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:27');
INSERT INTO `gen_table_column` VALUES (139, 9, 'REMARK_5', '备注5', 'varchar(100)', 'String', 'remark5', '0', '0', NULL, '0', '0', '0', '0', 'EQ', 'input', '', 8, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:27');
INSERT INTO `gen_table_column` VALUES (140, 9, 'UPDATE_COUNT', '更新次数', 'int', 'Long', 'updateCount', '0', '0', '0', '0', '0', '0', '0', 'EQ', 'input', '', 9, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:27');
INSERT INTO `gen_table_column` VALUES (141, 9, 'DELETE_FLAG', '删除标志', 'int', 'Long', 'deleteFlag', '0', '0', '0', '0', '0', '0', '0', 'EQ', 'input', '', 10, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:27');
INSERT INTO `gen_table_column` VALUES (142, 9, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '0', NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:27');
INSERT INTO `gen_table_column` VALUES (143, 9, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '0', NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:27');
INSERT INTO `gen_table_column` VALUES (144, 9, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '0', '0', NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:27');
INSERT INTO `gen_table_column` VALUES (145, 9, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '0', '0', NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:27');
INSERT INTO `gen_table_column` VALUES (146, 9, 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '0', '0', '0', NULL, 'EQ', 'textarea', '', 15, 'admin', '2024-02-18 07:22:15', '', '2024-02-18 08:07:27');
INSERT INTO `gen_table_column` VALUES (198, 12, 'DEPT_ID', '从属部门ID', 'int', 'Long', 'deptId', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (199, 12, 'WHS_CD', '仓库代码', 'varchar(25)', 'String', 'whsCd', '1', '0', NULL, '1', NULL, NULL, '1', 'EQ', 'input', '', 2, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (200, 12, 'WHS_NAME', '仓库名称', 'varchar(100)', 'String', 'whsName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (201, 12, 'WHS_TYPE_CD', '仓库类型代码', 'varchar(25)', 'String', 'whsTypeCd', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 4, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (202, 12, 'ABBR', '缩写', 'varchar(100)', 'String', 'abbr', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 5, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (203, 12, 'ADDR_1', '地址1', 'varchar(255)', 'String', 'addr1', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 6, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (204, 12, 'ADDR_2', '地址2', 'varchar(255)', 'String', 'addr2', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 7, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (205, 12, 'ADDR_3', '地址3', 'varchar(255)', 'String', 'addr3', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 8, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (206, 12, 'PHONE_NO', '联系电话', 'varchar(100)', 'String', 'phoneNo', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 9, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (207, 12, 'ZIP_CD', '邮编', 'varchar(25)', 'String', 'zipCd', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 10, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (208, 12, 'EMAIL', '联系邮箱', 'varchar(100)', 'String', 'email', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 11, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (209, 12, 'FAX_NO', '传真号', 'varchar(100)', 'String', 'faxNo', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 12, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (210, 12, 'RESP_PERSON', '负责人', 'varchar(10)', 'String', 'respPerson', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 13, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (211, 12, 'AREA', '区域', 'varchar(100)', 'String', 'area', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 14, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (212, 12, 'RENTAL_FEE', '租赁费用', 'decimal(18,6)', 'BigDecimal', 'rentalFee', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 15, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (213, 12, 'STORING_FEE', '存储费用', 'decimal(18,6)', 'BigDecimal', 'storingFee', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 16, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (214, 12, 'REMARK_1', NULL, 'varchar(100)', 'String', 'remark1', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 17, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (215, 12, 'REMARK_2', NULL, 'varchar(100)', 'String', 'remark2', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 18, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (216, 12, 'REMARK_3', NULL, 'varchar(100)', 'String', 'remark3', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 19, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:04');
INSERT INTO `gen_table_column` VALUES (217, 12, 'REMARK_4', NULL, 'varchar(100)', 'String', 'remark4', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 20, 'admin', '2024-02-18 08:02:20', '', '2024-02-18 08:07:05');
INSERT INTO `gen_table_column` VALUES (218, 12, 'REMARK_5', NULL, 'varchar(100)', 'String', 'remark5', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 21, 'admin', '2024-02-18 08:02:21', '', '2024-02-18 08:07:05');
INSERT INTO `gen_table_column` VALUES (219, 12, 'UPDATE_COUNT', '更新次数', 'int', 'Long', 'updateCount', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 22, 'admin', '2024-02-18 08:02:21', '', '2024-02-18 08:07:05');
INSERT INTO `gen_table_column` VALUES (220, 12, 'DELETE_FLAG', '删除标志', 'int', 'Long', 'deleteFlag', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 23, 'admin', '2024-02-18 08:02:21', '', '2024-02-18 08:07:05');
INSERT INTO `gen_table_column` VALUES (221, 12, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 24, 'admin', '2024-02-18 08:02:21', '', '2024-02-18 08:07:05');
INSERT INTO `gen_table_column` VALUES (222, 12, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 25, 'admin', '2024-02-18 08:02:21', '', '2024-02-18 08:07:05');
INSERT INTO `gen_table_column` VALUES (223, 12, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 26, 'admin', '2024-02-18 08:02:21', '', '2024-02-18 08:07:05');
INSERT INTO `gen_table_column` VALUES (224, 12, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 27, 'admin', '2024-02-18 08:02:21', '', '2024-02-18 08:07:05');
INSERT INTO `gen_table_column` VALUES (225, 12, 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'textarea', '', 28, 'admin', '2024-02-18 08:02:21', '', '2024-02-18 08:07:05');
INSERT INTO `gen_table_column` VALUES (226, 13, 'file_id', '文件ID', 'varchar(50)', 'String', 'fileId', '1', '0', NULL, '0', NULL, '1', '1', 'EQ', 'input', '', 1, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:14');
INSERT INTO `gen_table_column` VALUES (227, 13, 'saved_name', '保存的文件名称', 'varchar(255)', 'String', 'savedName', '0', '0', '0', '0', '0', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:14');
INSERT INTO `gen_table_column` VALUES (228, 13, 'original_name', '原始文件名称', 'varchar(255)', 'String', 'originalName', '0', '0', '0', '0', '0', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:14');
INSERT INTO `gen_table_column` VALUES (229, 13, 'file_path', '文件路径', 'varchar(500)', 'String', 'filePath', '0', '0', '0', '0', '0', '1', '0', 'EQ', 'textarea', '', 4, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:14');
INSERT INTO `gen_table_column` VALUES (230, 13, 'extension', '文件后缀', 'varchar(50)', 'String', 'extension', '0', '0', '0', '0', '0', '1', '0', 'EQ', 'input', '', 5, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:15');
INSERT INTO `gen_table_column` VALUES (231, 13, 'storage_type', '存储方式', 'varchar(50)', 'String', 'storageType', '0', '0', '0', '0', '0', '1', '0', 'EQ', 'select', '', 6, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:15');
INSERT INTO `gen_table_column` VALUES (232, 13, 'request_url', '获取文件的URL', 'varchar(255)', 'String', 'requestUrl', '0', '0', '0', '0', '0', '1', '0', 'EQ', 'input', '', 7, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:15');
INSERT INTO `gen_table_column` VALUES (233, 13, 'file_size', '文件大小(Byte)', 'bigint', 'Long', 'fileSize', '0', '0', '0', '0', '0', '1', '0', 'EQ', 'input', '', 8, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:15');
INSERT INTO `gen_table_column` VALUES (234, 13, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, NULL, NULL, '1', NULL, 'EQ', 'input', '', 9, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:15');
INSERT INTO `gen_table_column` VALUES (235, 13, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, NULL, NULL, '1', NULL, 'EQ', 'datetime', '', 10, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:15');
INSERT INTO `gen_table_column` VALUES (236, 13, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, NULL, NULL, '1', NULL, 'EQ', 'input', '', 11, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:15');
INSERT INTO `gen_table_column` VALUES (237, 13, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, NULL, NULL, '1', NULL, 'EQ', 'datetime', '', 12, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:15');
INSERT INTO `gen_table_column` VALUES (238, 13, 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'textarea', '', 13, 'admin', '2024-02-19 10:01:22', '', '2024-02-19 10:08:15');
INSERT INTO `gen_table_column` VALUES (239, 14, 'rule_id', '规则ID', 'bigint', 'Long', 'ruleId', '1', '0', NULL, '0', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (240, 14, 'seq_dist_cd', '序列号识别码', 'varchar(30)', 'String', 'seqDistCd', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (241, 14, 'rule_name', '规则名称', 'varchar(50)', 'String', 'ruleName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (242, 14, 'prefix', '前缀', 'varchar(50)', 'String', 'prefix', '0', '0', '1', '1', '1', '1', '0', 'EQ', 'input', '', 4, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (243, 14, 'separator1', '分隔符1', 'varchar(50)', 'String', 'separator1', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 5, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (244, 14, 'date_format', '日期格式', 'varchar(30)', 'String', 'dateFormat', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 6, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (245, 14, 'min_digits', '序列号数字部分的最小位数，不足补0', 'int', 'Long', 'minDigits', '0', '0', '1', '1', '1', '1', '0', 'EQ', 'input', '', 7, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (246, 14, 'separator2', '分隔符2', 'varchar(50)', 'String', 'separator2', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 8, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (247, 14, 'generator_name', '生成器名称(或类全名)，自定义的生成器可忽略前面的规则自行生成', 'varchar(255)', 'String', 'generatorName', '0', '0', NULL, '1', '1', '1', '0', 'LIKE', 'input', '', 9, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (248, 14, 'enable_flag', '是否启用', 'int', 'Long', 'enableFlag', '0', '0', '1', '1', '1', '1', '0', 'EQ', 'input', '', 10, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (249, 14, 'remark_1', '备注1', 'varchar(100)', 'String', 'remark1', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (250, 14, 'remark_2', '备注2', 'varchar(100)', 'String', 'remark2', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (251, 14, 'remark_3', '备注3', 'varchar(100)', 'String', 'remark3', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (252, 14, 'remark_4', '备注4', 'varchar(100)', 'String', 'remark4', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2024-02-19 10:01:22', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (253, 14, 'remark_5', '备注5', 'varchar(100)', 'String', 'remark5', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2024-02-19 10:01:23', '', '2024-02-20 02:07:26');
INSERT INTO `gen_table_column` VALUES (254, 14, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 16, 'admin', '2024-02-19 10:01:23', '', '2024-02-20 02:07:27');
INSERT INTO `gen_table_column` VALUES (255, 14, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 17, 'admin', '2024-02-19 10:01:23', '', '2024-02-20 02:07:27');
INSERT INTO `gen_table_column` VALUES (256, 14, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 18, 'admin', '2024-02-19 10:01:23', '', '2024-02-20 02:07:27');
INSERT INTO `gen_table_column` VALUES (257, 14, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 19, 'admin', '2024-02-19 10:01:23', '', '2024-02-20 02:07:27');
INSERT INTO `gen_table_column` VALUES (258, 14, 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'textarea', '', 20, 'admin', '2024-02-19 10:01:23', '', '2024-02-20 02:07:27');
INSERT INTO `gen_table_column` VALUES (259, 15, 'DEPT_ID', '从属部门ID', 'int', 'Long', 'deptId', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (260, 15, 'ITEM_TYPE_CD', '物品类型编码', 'varchar(100)', 'String', 'itemTypeCd', '1', '0', NULL, '0', NULL, NULL, NULL, 'EQ', 'input', '', 2, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (261, 15, 'ITEM_TYPE_NAME', '物品类型名称', 'varchar(100)', 'String', 'itemTypeName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (262, 15, 'REMARK_1', '备注1', 'varchar(100)', 'String', 'remark1', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (263, 15, 'REMARK_2', '备注2', 'varchar(100)', 'String', 'remark2', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (264, 15, 'REMARK_3', '备注3', 'varchar(100)', 'String', 'remark3', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (265, 15, 'REMARK_4', '备注4', 'varchar(100)', 'String', 'remark4', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (266, 15, 'REMARK_5', '备注5', 'varchar(100)', 'String', 'remark5', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (267, 15, 'UPDATE_COUNT', '更新次数', 'int', 'Long', 'updateCount', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (268, 15, 'DELETE_FLAG', '更新次数', 'int', 'Long', 'deleteFlag', '0', '0', '1', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (269, 15, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (270, 15, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (271, 15, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (272, 15, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (273, 15, 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'textarea', '', 15, 'admin', '2024-02-20 07:11:35', '', '2024-02-20 07:13:11');
INSERT INTO `gen_table_column` VALUES (274, 16, 'DEPT_ID', '从属部门ID', 'int', 'Long', 'deptId', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (275, 16, 'ITEM_CD', '物品代码', 'varchar(50)', 'String', 'itemCd', '1', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (276, 16, 'ITEM_NAME', '物品名称', 'varchar(100)', 'String', 'itemName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (277, 16, 'SUPPLIER', '供应商', 'varchar(100)', 'String', 'supplier', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 4, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (278, 16, 'PREP_LEAD_TIME', '筹备提前期', 'decimal(18,6)', 'BigDecimal', 'prepLeadTime', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 5, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (279, 16, 'INSTOCK_LEAD_TIME', '入库提前期', 'decimal(18,6)', 'BigDecimal', 'instockLeadTime', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 6, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (280, 16, 'RESTING_PERIOD', '放置期', 'decimal(18,6)', 'BigDecimal', 'restingPeriod', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 7, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (281, 16, 'OUTSTOCK_LEAD_TIME', '出库提前期', 'decimal(18,6)', 'BigDecimal', 'outstockLeadTime', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 8, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (282, 16, 'SPEC_1', '规格1', 'varchar(100)', 'String', 'spec1', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 9, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (283, 16, 'SPEC_2', '规格2', 'varchar(100)', 'String', 'spec2', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 10, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (284, 16, 'SPEC_3', '规格3', 'varchar(100)', 'String', 'spec3', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 11, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (285, 16, 'SPEC_4', '规格4', 'varchar(100)', 'String', 'spec4', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 12, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (286, 16, 'SPEC_5', '规格5', 'varchar(100)', 'String', 'spec5', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 13, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (287, 16, 'CLS_1', '分类1', 'varchar(100)', 'String', 'cls1', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 14, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (288, 16, 'CLS_2', '分类2', 'varchar(100)', 'String', 'cls2', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 15, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (289, 16, 'CLS_3', '分类3', 'varchar(100)', 'String', 'cls3', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 16, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (290, 16, 'CLS_4', '分类4', 'varchar(100)', 'String', 'cls4', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 17, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (291, 16, 'CLS_5', '分类5', 'varchar(100)', 'String', 'cls5', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 18, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (292, 16, 'UPDATE_COUNT', '更新次数', 'int', 'Long', 'updateCount', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 19, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (293, 16, 'DELETE_FLAG', '删除标志', 'int', 'Long', 'deleteFlag', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 20, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (294, 16, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 21, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (295, 16, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 22, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (296, 16, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 23, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (297, 16, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 24, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (298, 16, 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'textarea', '', 25, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:27');
INSERT INTO `gen_table_column` VALUES (299, 16, 'REMARK_1', '备注1', 'varchar(100)', 'String', 'remark1', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 26, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (300, 16, 'REMARK_2', '备注2', 'varchar(100)', 'String', 'remark2', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 27, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (301, 16, 'REMARK_3', '备注3', 'varchar(100)', 'String', 'remark3', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 28, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (302, 16, 'REMARK_4', '备注4', 'varchar(100)', 'String', 'remark4', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 29, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (303, 16, 'REMARK_5', '备注5', 'varchar(100)', 'String', 'remark5', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 30, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (304, 16, 'DELIVERY_PERIOD', '供货周期', 'varchar(50)', 'String', 'deliveryPeriod', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 31, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (305, 16, 'DEFAULT_STG_BIN_CD', '默认库位号', 'varchar(25)', 'String', 'defaultStgBinCd', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 32, 'admin', '2024-02-20 08:25:29', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (306, 16, 'ENABLE_FLG', '启用标志', 'int', 'Long', 'enableFlg', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 33, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (307, 16, 'SAFETY_STOCK', '安全库存量', 'decimal(18,6)', 'BigDecimal', 'safetyStock', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 34, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (308, 16, 'MAX_INV_QTY', '最大库存量', 'decimal(18,6)', 'BigDecimal', 'maxInvQty', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 35, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (309, 16, 'PURCH_LIMIT_QTY', '购买阈值', 'decimal(18,6)', 'BigDecimal', 'purchLimitQty', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 36, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (310, 16, 'GOODS_CLS', '物品区分', 'varchar(10)', 'String', 'goodsCls', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 37, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (311, 16, 'LOT_NO_MGMT_CLS', '批号管理区分(0:不管理, 1:管理)', 'int', 'Long', 'lotNoMgmtCls', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 38, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (312, 16, 'ITEM_TYPE_CD', '物品类型代码', 'varchar(30)', 'String', 'itemTypeCd', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 39, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (313, 16, 'STD_UNIT_CD', '标准单位代码', 'varchar(30)', 'String', 'stdUnitCd', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 40, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (314, 16, 'PKG_UNIT_CD', '包装单位代码', 'varchar(30)', 'String', 'pkgUnitCd', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 41, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (315, 16, 'OUTSTOCK_REQ_MIN_QTY', '出库申请最小数量', 'decimal(18,6)', 'BigDecimal', 'outstockReqMinQty', '0', '0', '0', '1', '1', '0', '0', 'EQ', 'input', '', 42, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (316, 16, 'OUTSTOCK_UNIT_CLS', '出库单位区分', 'varchar(30)', 'String', 'outstockUnitCls', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 43, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (317, 16, 'NET_WEIGHT_PER_UNIT', '单位净重', 'decimal(18,6)', 'BigDecimal', 'netWeightPerUnit', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 44, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (318, 16, 'OWN_VOL_M3', '自身体积(M3)', 'decimal(18,6)', 'BigDecimal', 'ownVolM3', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 45, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (319, 16, 'SIZE_D', '尺寸(长)', 'decimal(18,6)', 'BigDecimal', 'sizeD', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 46, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (320, 16, 'SIZE_W', '尺寸(宽)', 'decimal(18,6)', 'BigDecimal', 'sizeW', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 47, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (321, 16, 'SIZE_H', '尺寸(高)', 'decimal(18,6)', 'BigDecimal', 'sizeH', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 48, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (322, 16, 'PKG_RQMT_DESC', '包装要求描述', 'varchar(255)', 'String', 'pkgRqmtDesc', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 49, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (323, 16, 'STACKING_RQMT_DESC', '码放要求描述', 'varchar(255)', 'String', 'stackingRqmtDesc', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 50, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (324, 16, 'STG_RQMT_DESC', '储存要求描述', 'varchar(255)', 'String', 'stgRqmtDesc', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 51, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (325, 16, 'MANUFACTURER', '生产商', 'varchar(100)', 'String', 'manufacturer', '0', '0', NULL, '1', '1', '1', '0', 'EQ', 'input', '', 52, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (326, 16, 'PICTURE_ID', '图片ID', 'varchar(50)', 'String', 'pictureId', '0', '0', NULL, '1', '1', '0', '0', 'EQ', 'input', '', 53, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');
INSERT INTO `gen_table_column` VALUES (327, 16, 'PICTURE_URL', '图片URL', 'varchar(255)', 'String', 'pictureUrl', '0', '0', NULL, '0', '0', '0', '0', 'EQ', 'input', '', 54, 'admin', '2024-02-20 08:25:30', '', '2024-02-21 02:00:28');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2024-01-30 05:05:41', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2024-01-30 05:05:41', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2024-01-30 05:05:41', '', NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2024-01-30 05:05:41', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2024-01-30 05:05:41', '', NULL, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', 'RYAS科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-01-30 05:05:40', 'admin', '2024-01-31 06:50:32');
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-01-30 05:05:40', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-01-30 05:05:40', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-01-30 05:05:40', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-01-30 05:05:40', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-01-30 05:05:40', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-01-30 05:05:40', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-01-30 05:05:40', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-01-30 05:05:40', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-01-30 05:05:40', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (100, 1, '启用', '1', 'sys_enable_flag', NULL, 'primary', 'N', '0', '1', '2024-02-21 03:02:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (101, 2, '禁用', '0', 'sys_enable_flag', NULL, 'danger', 'N', '0', '1', '2024-02-21 03:03:08', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2024-01-30 05:05:41', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100, '启用标志', 'sys_enable_flag', '0', '1', '2024-02-21 03:02:06', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `file_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文件ID',
  `saved_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '保存的文件名称',
  `original_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '原始文件名称',
  `file_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文件路径',
  `extension` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文件后缀',
  `storage_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '存储方式',
  `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '获取文件的URL',
  `file_size` bigint NOT NULL COMMENT '文件大小(Byte)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '文件存储记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `custom_job_class` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '自定义QuartzJob类',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '测试任务（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', NULL, 'admin', '2024-01-30 05:05:41', 'admin', '2024-01-31 09:19:48', '');
INSERT INTO `sys_job` VALUES (2, '测试任务（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', NULL, 'admin', '2024-01-30 05:05:41', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '测试任务（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', NULL, 'admin', '2024-01-30 05:05:41', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_data_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_data_log`;
CREATE TABLE `sys_job_data_log`  (
  `log_id` bigint NOT NULL COMMENT '任务数据日志ID',
  `start_time` datetime NULL DEFAULT NULL COMMENT '执行开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '执行结束时间',
  `message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '日志内容',
  `log_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '日志类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '状态（0正常执行 1异常）',
  `job_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '任务执行类',
  `job_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '任务执行方法名',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '定时任务数据日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_data_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录IP地址',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '提示信息',
  `access_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  INDEX `idx_sys_logininfor_s`(`status` ASC) USING BTREE,
  INDEX `idx_sys_logininfor_lt`(`access_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 151 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路由参数',
  `is_frame` int NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2043 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 100, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2024-01-30 05:05:40', 'admin', '2024-02-02 09:13:20', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 200, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2024-01-30 05:05:40', 'admin', '2024-02-02 09:13:26', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 300, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2024-01-30 05:05:40', 'admin', '2024-02-02 09:13:31', '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2024-01-30 05:05:40', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2024-01-30 05:05:40', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2024-01-30 05:05:40', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2024-01-30 05:05:40', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2024-01-30 05:05:40', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2024-01-30 05:05:40', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2024-01-30 05:05:40', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2024-01-30 05:05:40', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2024-01-30 05:05:40', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2024-01-30 05:05:40', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2024-01-30 05:05:40', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, 'Sentinel控制台', 2, 3, 'http://localhost:8718', '', '', 0, 0, 'C', '0', '0', 'monitor:sentinel:list', 'sentinel', 'admin', '2024-01-30 05:05:40', '', NULL, '流量控制菜单');
INSERT INTO `sys_menu` VALUES (112, 'Nacos控制台', 2, 4, 'http://localhost:8848/nacos', '', '', 0, 0, 'C', '0', '0', 'monitor:nacos:list', 'nacos', 'admin', '2024-01-30 05:05:40', '', NULL, '服务治理菜单');
INSERT INTO `sys_menu` VALUES (113, 'Admin控制台', 2, 5, 'http://localhost:9100/login', '', '', 0, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2024-01-30 05:05:40', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2024-01-30 05:05:40', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2024-01-30 05:05:40', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, 'http://localhost:8080/swagger-ui/index.html', '', '', 0, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2024-01-30 05:05:40', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'system/operlog/index', '', 1, 0, 'C', '0', '0', 'system:operlog:list', 'form', 'admin', '2024-01-30 05:05:40', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'system/logininfor/index', '', 1, 0, 'C', '0', '0', 'system:logininfor:list', 'logininfor', 'admin', '2024-01-30 05:05:40', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:operlog:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:operlog:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '日志导出', 500, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:operlog:export', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:export', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '账户解锁', 501, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:unlock', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 6, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 115, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 115, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 115, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 115, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2000, '基础信息', 0, 20, 'masterData', NULL, NULL, 1, 0, 'M', '0', '0', '', 'dict', 'admin', '2024-02-02 09:13:06', 'admin', '2024-02-02 09:13:43', '');
INSERT INTO `sys_menu` VALUES (2007, '单位信息管理', 2000, 1, 'UnitInfo', 'wms/UnitInfo/index', NULL, 1, 0, 'C', '0', '0', 'wms:UnitInfo:list', 'tree-table', 'admin', '2024-02-05 08:26:48', '1', '2024-02-20 08:36:41', '单位信息管理菜单');
INSERT INTO `sys_menu` VALUES (2008, '单位信息管理查询', 2007, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:UnitInfo:query', '#', 'admin', '2024-02-05 08:26:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2009, '单位信息管理新增', 2007, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:UnitInfo:add', '#', 'admin', '2024-02-05 08:26:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2010, '单位信息管理修改', 2007, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:UnitInfo:edit', '#', 'admin', '2024-02-05 08:26:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2011, '单位信息管理删除', 2007, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:UnitInfo:remove', '#', 'admin', '2024-02-05 08:26:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2012, '单位信息管理导出', 2007, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:UnitInfo:export', '#', 'admin', '2024-02-05 08:26:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2013, '物品类型', 2000, 2, 'ItemType', 'wms/ItemType/index', NULL, 1, 0, 'C', '0', '0', 'wms:ItemType:list', 'tree', 'admin', '2024-02-20 07:21:07', '1', '2024-02-20 07:22:45', '物品类型菜单');
INSERT INTO `sys_menu` VALUES (2014, '物品类型查询', 2013, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:ItemType:query', '#', 'admin', '2024-02-20 07:21:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2015, '物品类型新增', 2013, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:ItemType:add', '#', 'admin', '2024-02-20 07:21:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2016, '物品类型修改', 2013, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:ItemType:edit', '#', 'admin', '2024-02-20 07:21:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2017, '物品类型删除', 2013, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:ItemType:remove', '#', 'admin', '2024-02-20 07:21:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2018, '物品类型导出', 2013, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:ItemType:export', '#', 'admin', '2024-02-20 07:21:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2019, '仓库基础信息', 2000, 3, 'WarehouseInfo', 'wms/WarehouseInfo/index', NULL, 1, 0, 'C', '0', '0', 'wms:WarehouseInfo:list', 'warehouse', 'admin', '2024-02-18 08:34:10', '1', '2024-02-20 09:19:41', '仓库基础信息菜单');
INSERT INTO `sys_menu` VALUES (2020, '仓库基础信息查询', 2019, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:WarehouseInfo:query', '#', 'admin', '2024-02-18 08:34:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2021, '仓库基础信息新增', 2019, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:WarehouseInfo:add', '#', 'admin', '2024-02-18 08:34:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2022, '仓库基础信息修改', 2019, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:WarehouseInfo:edit', '#', 'admin', '2024-02-18 08:34:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2023, '仓库基础信息删除', 2019, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:WarehouseInfo:remove', '#', 'admin', '2024-02-18 08:34:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2024, '仓库基础信息导出', 2019, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:WarehouseInfo:export', '#', 'admin', '2024-02-18 08:34:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2025, '文件存储记录', 1, 10, 'FileRecord', 'file/FileRecord/index', NULL, 1, 0, 'C', '0', '0', 'file:FileRecord:list', 'documentation', 'admin', '2024-02-19 10:19:23', '1', '2024-02-19 10:25:26', '文件存储记录菜单');
INSERT INTO `sys_menu` VALUES (2026, '文件存储记录查询', 2025, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'file:FileRecord:query', '#', 'admin', '2024-02-19 10:19:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2027, '文件存储记录新增', 2025, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'file:FileRecord:add', '#', 'admin', '2024-02-19 10:19:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2028, '文件存储记录修改', 2025, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'file:FileRecord:edit', '#', 'admin', '2024-02-19 10:19:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2029, '文件存储记录删除', 2025, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'file:FileRecord:remove', '#', 'admin', '2024-02-19 10:19:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2030, '文件存储记录导出', 2025, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'file:FileRecord:export', '#', 'admin', '2024-02-19 10:19:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2031, '序列号生成规则', 1, 11, 'SeqRule', 'system/SeqRule/index', NULL, 1, 0, 'C', '0', '0', 'system:SeqRule:list', 'number', 'admin', '2024-02-20 02:05:15', '1', '2024-02-20 02:08:58', '序列号生成规则菜单');
INSERT INTO `sys_menu` VALUES (2032, '序列号生成规则查询', 2031, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:SeqRule:query', '#', 'admin', '2024-02-20 02:05:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2033, '序列号生成规则新增', 2031, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:SeqRule:add', '#', 'admin', '2024-02-20 02:05:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2034, '序列号生成规则修改', 2031, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:SeqRule:edit', '#', 'admin', '2024-02-20 02:05:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2035, '序列号生成规则删除', 2031, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:SeqRule:remove', '#', 'admin', '2024-02-20 02:05:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2036, '序列号生成规则导出', 2031, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:SeqRule:export', '#', 'admin', '2024-02-20 02:05:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2037, '物品基础信息', 2000, 4, 'ItemInfo', 'wms/ItemInfo/index', NULL, 1, 0, 'C', '0', '0', 'wms:ItemInfo:list', 'cubes', 'admin', '2024-02-20 08:34:17', '1', '2024-02-20 09:21:44', '物品基础信息菜单');
INSERT INTO `sys_menu` VALUES (2038, '物品基础信息查询', 2037, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:ItemInfo:query', '#', 'admin', '2024-02-20 08:34:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2039, '物品基础信息新增', 2037, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:ItemInfo:add', '#', 'admin', '2024-02-20 08:34:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2040, '物品基础信息修改', 2037, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:ItemInfo:edit', '#', 'admin', '2024-02-20 08:34:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2041, '物品基础信息删除', 2037, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:ItemInfo:remove', '#', 'admin', '2024-02-20 08:34:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2042, '物品基础信息导出', 2037, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'wms:ItemInfo:export', '#', 'admin', '2024-02-20 08:34:17', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2024-01-30 05:05:41', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2024-01-30 05:05:41', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '返回参数',
  `status` int NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint NULL DEFAULT 0 COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  INDEX `idx_sys_oper_log_bt`(`business_type` ASC) USING BTREE,
  INDEX `idx_sys_oper_log_s`(`status` ASC) USING BTREE,
  INDEX `idx_sys_oper_log_ot`(`oper_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 267 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2024-01-30 05:05:40', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2024-01-30 05:05:40', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2024-01-30 05:05:40', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2024-01-30 05:05:40', 'admin', '2024-01-31 06:48:15', '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);

-- ----------------------------
-- Table structure for sys_seq_result
-- ----------------------------
DROP TABLE IF EXISTS `sys_seq_result`;
CREATE TABLE `sys_seq_result`  (
  `seq_id` bigint(20) UNSIGNED ZEROFILL NOT NULL COMMENT '序列号ID',
  `seq_dist_cd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '序列号识别码',
  `prefix` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '前缀',
  `separator1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分隔符1',
  `date_val` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '日期值',
  `separator2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分隔符2',
  `seq_no` int NOT NULL COMMENT '当前序列号',
  `remark_1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注1',
  `remark_2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注2',
  `remark_3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注3',
  `remark_4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注4',
  `remark_5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注5',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`seq_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '序列号生成记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_seq_result
-- ----------------------------
INSERT INTO `sys_seq_result` VALUES (00000006458374766593, 'UNIT', 'UNIT', NULL, NULL, NULL, 9, NULL, NULL, NULL, NULL, NULL, '1', '2024-02-19 13:37:19', '1', '2024-02-19 13:38:44', NULL);
INSERT INTO `sys_seq_result` VALUES (00000006458406061570, 'GT', 'GT', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, '1', '2024-02-19 13:41:24', '1', '2024-02-19 13:41:39', NULL);
INSERT INTO `sys_seq_result` VALUES (00000006458414990338, 'WHS', 'WH', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '1', '2024-02-19 13:42:34', '1', '2024-02-19 13:42:34', NULL);
INSERT INTO `sys_seq_result` VALUES (00000006470282975873, 'ITYPE', 'ITYPE', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, '1', '2024-02-20 15:27:52', '1', '2024-02-20 15:28:10', NULL);

-- ----------------------------
-- Table structure for sys_seq_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_seq_rule`;
CREATE TABLE `sys_seq_rule`  (
  `rule_id` bigint NOT NULL COMMENT '规则ID',
  `seq_dist_cd` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '序列号识别码',
  `rule_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '规则名称',
  `prefix` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '前缀',
  `separator1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分隔符1',
  `date_format` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '日期格式',
  `min_digits` int NOT NULL COMMENT '序列号数字部分的最小位数，不足补0',
  `separator2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分隔符2',
  `generator_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成器名称(或类全名)，自定义的生成器可忽略前面的规则自行生成',
  `enable_flag` int NOT NULL DEFAULT 1 COMMENT '是否启用',
  `remark_1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注1',
  `remark_2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注2',
  `remark_3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注3',
  `remark_4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注4',
  `remark_5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注5',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`rule_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '序列号生成规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_seq_rule
-- ----------------------------
INSERT INTO `sys_seq_rule` VALUES (1, 'UNIT', '单位代码生成规则', 'UNIT', NULL, NULL, 5, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '1', '2024-02-19 11:38:35', '1', '2024-02-20 10:48:55', NULL);
INSERT INTO `sys_seq_rule` VALUES (2, 'ITYPE', '物品类型代码生成规则', 'ITYPE', NULL, NULL, 5, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '1', '2024-02-19 13:33:21', '1', '2024-02-19 13:33:24', NULL);
INSERT INTO `sys_seq_rule` VALUES (3, 'WHS', '仓库代码生成规则', 'WHS', NULL, NULL, 3, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '1', '2024-02-19 13:34:28', '1', '2024-02-19 13:34:30', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '管理员', '00', 'admin@ryas.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2024-01-30 05:05:40', 'admin', '2024-01-30 05:05:40', '', '2024-01-31 06:49:46', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@ryas.com', '15666661234', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2024-01-30 05:05:40', 'admin', '2024-01-30 05:05:40', 'admin', '2024-01-31 06:50:04', '测试员');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
