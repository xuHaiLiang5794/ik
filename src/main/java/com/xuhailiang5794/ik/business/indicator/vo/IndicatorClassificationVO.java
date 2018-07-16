package com.xuhailiang5794.ik.business.indicator.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/16 14:36
 */
@Data
public class IndicatorClassificationVO {
    @ApiModelProperty("指标ID")
    private String id;

    @ApiModelProperty("指标类（包含包名）")
    private String clazz;

    @ApiModelProperty("描述")
    private String remark;
}
