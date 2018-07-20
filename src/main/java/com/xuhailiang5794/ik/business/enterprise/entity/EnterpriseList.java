package com.xuhailiang5794.ik.business.enterprise.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xuhailiang5794.ik.support.annotation.Index;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EnterpriseList {
    /**
     * 股票代码（1）
     */
    @ApiModelProperty("股票代码（1）")
    @Index(index = 1)
    private String code;

    /**
     * 股票交易所（0）
     */
    @ApiModelProperty("股票交易所（0）")
    @Index(index = 0)
    private Integer exchangeCode;

    /**
     * 名称（2）
     */
    @ApiModelProperty("名称（2）")
    @Index(index = 2)
    private String name;

    /**
     * 最新价（元）（3）
     */
    @ApiModelProperty("最新价（元）（3）")
    @Index(index = 3)
    private Double latestPrice;

    /**
     * 涨跌额（元）（4）
     */
    @ApiModelProperty("涨跌额（元）（4）")
    @Index(index = 4)
    private Double changeAmt;

    /**
     * 涨跌幅（%）（5）
     */
    @ApiModelProperty("涨跌幅（%）（5）")
    @Index(index = 5)
    private Double chg;

    /**
     * 成交量（手）（6）
     */
    @ApiModelProperty("成交量（手）（6）")
    @Index(index = 6)
    private Long volume;

    /**
     * 成交额（元）（7）
     */
    @ApiModelProperty("成交额（元）（7）")
    @Index(index = 7)
    private Long turnover;

    /**
     * 振幅（%）（8）
     */
    @ApiModelProperty("振幅（%）（8）")
    @Index(index = 8)
    private Double amplitude;

    /**
     * 最高（元）（9）
     */
    @ApiModelProperty("最高（元）（9）")
    @Index(index = 9)
    private Double high;

    /**
     * 最低（元）（10）
     */
    @ApiModelProperty("最低（元）（10）")
    @Index(index = 10)
    private Double low;

    /**
     * 今开（元）（11）
     */
    @ApiModelProperty("今开（元）（11）")
    @Index(index = 11)
    private Double open;

    /**
     * 昨收（元）（12）
     */
    @ApiModelProperty("昨收（元）（12）")
    @Index(index = 12)
    private Double prevClose;

    /**
     * 量比（14）
     */
    @ApiModelProperty("量比（14）")
    @Index(index = 14)
    private Double ratio;

    /**
     * 换手率（%）（15）
     */
    @ApiModelProperty("换手率（%）（15）")
    @Index(index = 15)
    private Double turnoverRate;

    /**
     * 市盈率（动态）（16）
     */
    @ApiModelProperty("市盈率（动态）（16）")
    @Index(index = 16)
    private Double pERatio;

    /**
     * 市净率（17）
     */
    @ApiModelProperty("市净率（17）")
    @Index(index = 17)
    private Double pBRatio;

    /**
     * 上市日期（23）
     */
    @ApiModelProperty("上市日期（23）")
    @Index(index = 23)
    private String listingDate;

    /**
     * 收盘时间（24）
     */
    @ApiModelProperty("收盘时间（24）")
    @Index(index = 24)
    private String closingPrice;

    /**
     * 数据时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("数据时间")
    private LocalDateTime dataTime;

    private StockExchange stockExchange;
}