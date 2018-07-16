package com.xuhailiang5794.ik.business.indicator.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class IndicatorLibrary {
    private String id;

    /**
     * 函数名称
    */
    private String funName;

    /**
     * 函数表达式
    */
    private String funExpr;

    /**
     * 数据时间
    */
    private LocalDateTime dataTime;

    /**
     * 指标类型ID
    */
    private String clazzId;
}