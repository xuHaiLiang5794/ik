package com.xuhailiang5794.ik.business.enterprise.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StockExchange {
    /**
     * 代码
     */
    @ApiModelProperty("代码")
    private Integer code;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
}