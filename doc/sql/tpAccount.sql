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
-- Records
-- ----------------------------

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
