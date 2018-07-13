package com.xuhailiang5794.ik.support.tp.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Partner {
    private String id;

    /**
     * 合作方名称
    */
    private String partnerName;

    /**
     * 合作开始时间
    */
    private Date partnerBeginTime;

    /**
     * 合作到期时间
    */
    private Date partnerEndTime;

    /**
     * 数据时间
    */
    private Date dataTime;

    /**
     * 备注
    */
    private String remark;
}