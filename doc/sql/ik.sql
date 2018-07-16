/*
MySQL Data Transfer
Source Host: localhost
Source Database: ik
Target Host: localhost
Target Database: ik
Date: 2018/7/16 17:51:49
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_ind_expression
-- ----------------------------
CREATE TABLE `t_ind_expression` (
  `ID` varchar(32) NOT NULL,
  `expr_code` varchar(32) NOT NULL COMMENT '表达式代码',
  PRIMARY KEY (`ID`),
  KEY `R_26` (`expr_code`),
  CONSTRAINT `t_ind_expression_ibfk_1` FOREIGN KEY (`expr_code`) REFERENCES `t_ind_expression_code` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ind_expression_code
-- ----------------------------
CREATE TABLE `t_ind_expression_code` (
  `ID` varchar(32) NOT NULL,
  `code` int(11) DEFAULT NULL COMMENT '码值',
  `remark` varchar(20) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ind_indicator_classification
-- ----------------------------
CREATE TABLE `t_ind_indicator_classification` (
  `ID` varchar(32) NOT NULL,
  `clazz` varchar(50) DEFAULT NULL COMMENT '指标类（包含包名）',
  `remark` varchar(30) DEFAULT NULL COMMENT '描述',
  `data_time` timestamp(6) NULL DEFAULT NULL COMMENT '数据时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ind_indicator_library
-- ----------------------------
CREATE TABLE `t_ind_indicator_library` (
  `ID` varchar(32) NOT NULL,
  `fun_name` varchar(30) DEFAULT NULL COMMENT '函数名称',
  `fun_expr` varchar(30) DEFAULT NULL COMMENT '函数表达式',
  `data_time` timestamp(6) NULL DEFAULT NULL COMMENT '数据时间',
  `clazz_id` varchar(32) NOT NULL COMMENT '指标类型ID',
  PRIMARY KEY (`ID`),
  KEY `R_27` (`clazz_id`),
  CONSTRAINT `t_ind_indicator_library_ibfk_1` FOREIGN KEY (`clazz_id`) REFERENCES `t_ind_indicator_classification` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ind_indicator_where
-- ----------------------------
CREATE TABLE `t_ind_indicator_where` (
  `ID` varchar(32) NOT NULL,
  `value` varchar(20) DEFAULT NULL COMMENT '条件值',
  `expr_id` varchar(32) NOT NULL COMMENT '条件表达式',
  `priority` int(11) DEFAULT NULL COMMENT '排序权值',
  `where_code` int(11) NOT NULL COMMENT '条件码值',
  `library_id` varchar(32) NOT NULL COMMENT '指标函数ID',
  PRIMARY KEY (`ID`),
  KEY `R_22` (`where_code`),
  KEY `R_25` (`expr_id`),
  KEY `R_29` (`library_id`),
  CONSTRAINT `t_ind_indicator_where_ibfk_3` FOREIGN KEY (`library_id`) REFERENCES `t_ind_indicator_library` (`ID`),
  CONSTRAINT `t_ind_indicator_where_ibfk_1` FOREIGN KEY (`where_code`) REFERENCES `t_ind_where_code` (`code`),
  CONSTRAINT `t_ind_indicator_where_ibfk_2` FOREIGN KEY (`expr_id`) REFERENCES `t_ind_expression` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ind_where_code
-- ----------------------------
CREATE TABLE `t_ind_where_code` (
  `code` int(11) NOT NULL,
  `remark` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_tp_partner
-- ----------------------------
CREATE TABLE `t_tp_partner` (
  `ID` varchar(20) NOT NULL,
  `partner_name` varchar(20) DEFAULT NULL COMMENT '合作方名称',
  `partner_begin_time` date DEFAULT NULL COMMENT '合作开始时间',
  `partner_end_time` date DEFAULT NULL COMMENT '合作到期时间',
  `data_time` date DEFAULT NULL COMMENT '数据时间',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_tp_secret_key
-- ----------------------------
CREATE TABLE `t_tp_secret_key` (
  `secret_key` varchar(20) DEFAULT NULL COMMENT '密钥键',
  `value` varchar(20) DEFAULT NULL COMMENT '密钥值',
  `ID` char(18) NOT NULL,
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  `partner_id` varchar(20) DEFAULT NULL COMMENT '合作方ID',
  PRIMARY KEY (`ID`),
  KEY `R_19` (`partner_id`),
  CONSTRAINT `t_tp_secret_key_ibfk_1` FOREIGN KEY (`partner_id`) REFERENCES `t_tp_partner` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_ind_indicator_classification` VALUES ('14df355688d011e8810500ac687129bf', '10', 'r10000', '2018-07-16 16:13:10.000000');
INSERT INTO `t_ind_indicator_classification` VALUES ('256341c588cf11e8810500ac687129bf', '10', 'r10000', '2018-07-16 16:06:29.000000');
INSERT INTO `t_ind_indicator_classification` VALUES ('fc471c1688ce11e8810500ac687129bf', '10', 'r10000', '2018-07-16 16:05:21.000000');
INSERT INTO `t_tp_secret_key` VALUES (null, null, '1', null, null);
