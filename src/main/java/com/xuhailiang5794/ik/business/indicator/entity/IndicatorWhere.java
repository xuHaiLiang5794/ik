package com.xuhailiang5794.ik.business.indicator.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IndicatorWhere {
    private String id;

    /**
     * 条件值
     */
    @ApiModelProperty("条件值")
    private String value;

    /**
     * 条件表达式
     */
    @ApiModelProperty("条件表达式")
    private String exprId;

    /**
     * 排序权值
     */
    @ApiModelProperty("排序权值")
    private Integer priority;

    /**
     * 条件码值
     */
    @ApiModelProperty("条件码值")
    private Integer whereCode;

    /**
     * 指标函数ID
     */
    @ApiModelProperty("指标函数ID")
    private String libraryId;
}