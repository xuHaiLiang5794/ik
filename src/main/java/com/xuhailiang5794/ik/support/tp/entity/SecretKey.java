package com.xuhailiang5794.ik.support.tp.entity;

import lombok.Data;

@Data
public class SecretKey {
    private String id;

    /**
     * 密钥键
    */
    private String secretKey;

    /**
     * 密钥值
    */
    private String value;

    /**
     * 备注
    */
    private String remark;

    /**
     * 合作方ID
    */
    private String partnerId;
}