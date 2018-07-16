package com.xuhailiang5794.ik.business.indicator.entity;

import lombok.Data;

@Data
public class ExpressionCode {
    private String id;

    /**
     * 码值
    */
    private Integer code;

    /**
     * 描述
    */
    private String remark;
}