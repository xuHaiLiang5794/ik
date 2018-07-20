package com.xuhailiang5794.ik.business.enterprise.vo;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

@Data
public class EnterpriseListVO {

    @ApiModelProperty(value="股票代码（1）")
    private String code;

    @ApiModelProperty(value="股票交易所（0）")
    private Integer exchangeCode;

    @ApiModelProperty(value="名称（2）")
    private String name;

    @ApiModelProperty(value="最新价（元）（3）")
    private Double latestPrice;

    @ApiModelProperty(value="涨跌额（元）（4）")
    private Double changeAmt;

    @ApiModelProperty(value="涨跌幅（%）（5）")
    private Double chg;

    @ApiModelProperty(value="成交量（手）（6）")
    private Long volume;

    @ApiModelProperty(value="成交额（元）（7）")
    private Long turnover;

    @ApiModelProperty(value="振幅（%）（8）")
    private Double amplitude;

    @ApiModelProperty(value="最高（元）（9）")
    private Double high;

    @ApiModelProperty(value="最低（元）（10）")
    private Double low;

    @ApiModelProperty(value="今开（元）（11）")
    private Double open;

    @ApiModelProperty(value="昨收（元）（12）")
    private Double prevClose;

    @ApiModelProperty(value="量比（14）")
    private Double ratio;

    @ApiModelProperty(value="换手率（%）（15）")
    private Double turnoverRate;

    @ApiModelProperty(value="市盈率（动态）（16）")
    private Double pERatio;

    @ApiModelProperty(value="市净率（17）")
    private Double pBRatio;

    @ApiModelProperty(value="上市日期（23）")
    private String listingDate;

    @ApiModelProperty(value="收盘时间（24）")
    private String closingPrice;

    @ApiModelProperty(value="数据时间")
    private LocalDateTime dataTime;

}