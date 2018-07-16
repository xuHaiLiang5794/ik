package com.xuhailiang5794.ik.business.indicator.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class IndicatorClassification {
    private String id;

    /**
     * 指标类（包含包名）
    */
    private String clazz;

    /**
     * 描述
    */
    private String remark;

    /**
     * 数据时间
    */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataTime;
}