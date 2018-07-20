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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='指标分类表';

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
  CONSTRAINT `t_ind_indicator_where_ibfk_1` FOREIGN KEY (`where_code`) REFERENCES `t_ind_where_code` (`code`),
  CONSTRAINT `t_ind_indicator_where_ibfk_2` FOREIGN KEY (`expr_id`) REFERENCES `t_ind_expression` (`ID`),
  CONSTRAINT `t_ind_indicator_where_ibfk_3` FOREIGN KEY (`library_id`) REFERENCES `t_ind_indicator_library` (`ID`)
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
-- Records
-- ----------------------------
INSERT INTO `t_ind_indicator_classification` VALUES ('14df355688d011e8810500ac687129bf', '10', 'r10000', '2018-07-16 16:13:10.000000');
INSERT INTO `t_ind_indicator_classification` VALUES ('256341c588cf11e8810500ac687129bf', '10', 'r10000', '2018-07-16 16:06:29.000000');
INSERT INTO `t_ind_indicator_classification` VALUES ('fc471c1688ce11e8810500ac687129bf', '10', 'r10000', '2018-07-16 16:05:21.000000');
