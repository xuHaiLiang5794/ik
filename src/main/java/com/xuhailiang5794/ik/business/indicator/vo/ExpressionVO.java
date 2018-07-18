package com.xuhailiang5794.ik.business.indicator.vo;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class ExpressionVO {

    private String id;

    @ApiModelProperty(value="表达式代码")
    private String exprCode;

}