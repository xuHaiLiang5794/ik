package com.xuhailiang5794.ik.support.generator.business.elements;

import lombok.Data;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/17
 */
@Data
public class JavaMvc {
    private String domainObjectName;
    private boolean enableSave;
    private boolean enableDelete;
    private boolean enableUpdate;
    private boolean enableSelectByPrimaryKey;
    private boolean enableSelectByCondition;
}
