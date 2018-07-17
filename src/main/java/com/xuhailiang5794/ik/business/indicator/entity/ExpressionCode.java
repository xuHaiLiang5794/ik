package com.xuhailiang5794.ik.business.indicator.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ExpressionCode {
    private String id;

    /**
     * 码值
     */
    @ApiModelProperty("码值")
    private Integer code;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String remark;
}