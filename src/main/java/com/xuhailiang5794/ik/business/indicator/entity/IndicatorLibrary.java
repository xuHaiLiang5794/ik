package com.xuhailiang5794.ik.business.indicator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class IndicatorLibrary {
    private String id;

    /**
     * 函数名称
     */
    @ApiModelProperty("函数名称")
    private String funName;

    /**
     * 函数表达式
     */
    @ApiModelProperty("函数表达式")
    private String funExpr;

    /**
     * 数据时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("数据时间")
    private LocalDateTime dataTime;

    /**
     * 指标类型ID
     */
    @ApiModelProperty("指标类型ID")
    private String clazzId;
}