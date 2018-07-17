package com.xuhailiang5794.ik.business.indicator.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Expression {
    private String id;

    /**
     * 表达式代码
     */
    @ApiModelProperty("表达式代码")
    private String exprCode;
}