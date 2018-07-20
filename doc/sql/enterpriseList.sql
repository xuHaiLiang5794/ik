-- ----------------------------
-- Table structure for t_stock_exchange
-- ----------------------------
CREATE TABLE `t_stock_exchange` (
  `CODE` int(11) NOT NULL COMMENT '代码',
  `NAME` varchar(20) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='股票交易所';

-- ----------------------------
-- Records
-- ----------------------------
INSERT INTO `t_stock_exchange` VALUES ('1', '上海证券交易所');
INSERT INTO `t_stock_exchange` VALUES ('2', '深圳证券交易所');

-- ----------------------------
-- Table structure for t_enterprise_list
-- ----------------------------
CREATE TABLE `t_enterprise_list` (
  `CODE` varchar(20) NOT NULL COMMENT '股票代码（1）',
  `EXCHANGE_CODE` int(11) NOT NULL COMMENT '股票交易所（0）',
  `NAME` varchar(20) DEFAULT NULL COMMENT '名称（2）',
  `LATEST_PRICE` double DEFAULT NULL COMMENT '最新价（元）（3）',
  `CHANGE_AMT` double DEFAULT NULL COMMENT '涨跌额（元）（4）',
  `CHG` double DEFAULT NULL COMMENT '涨跌幅（%）（5）',
  `VOLUME` bigint(20) DEFAULT NULL COMMENT '成交量（手）（6）',
  `TURNOVER` bigint(20) DEFAULT NULL COMMENT '成交额（元）（7）',
  `AMPLITUDE` double DEFAULT NULL COMMENT '振幅（%）（8）',
  `HIGH` double DEFAULT NULL COMMENT '最高（元）（9）',
  `LOW` double DEFAULT NULL COMMENT '最低（元）（10）',
  `OPEN` double DEFAULT NULL COMMENT '今开（元）（11）',
  `PREV_CLOSE` double DEFAULT NULL COMMENT '昨收（元）（12）',
  `RATIO` double DEFAULT NULL COMMENT '量比（14）',
  `TURNOVER_RATE` double DEFAULT NULL COMMENT '换手率（%）（15）',
  `P_E_RATIO` double DEFAULT NULL COMMENT '市盈率（动态）（16）',
  `P_B_RATIO` double DEFAULT NULL COMMENT '市净率（17）',
  `LISTING_DATE` varchar(10) DEFAULT NULL COMMENT '上市日期（23）',
  `CLOSING_PRICE` varchar(20) DEFAULT NULL COMMENT '收盘时间（24）',
  `DATA_TIME` timestamp NULL DEFAULT NULL COMMENT '数据时间',
  PRIMARY KEY (`CODE`),
  KEY `R_30` (`EXCHANGE_CODE`),
  CONSTRAINT `t_enterprise_list_ibfk_1` FOREIGN KEY (`EXCHANGE_CODE`) REFERENCES `t_stock_exchange` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='沪深A股企业清单';

-- ----------------------------
-- Records
-- ----------------------------
