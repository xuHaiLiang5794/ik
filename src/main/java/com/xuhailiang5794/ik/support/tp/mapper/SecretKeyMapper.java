package com.xuhailiang5794.ik.support.tp.mapper;

import com.xuhailiang5794.ik.support.tp.entity.SecretKey;

public interface SecretKeyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tp_secret_key
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tp_secret_key
     *
     * @mbg.generated
     */
    int insert(SecretKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tp_secret_key
     *
     * @mbg.generated
     */
    int insertSelective(SecretKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tp_secret_key
     *
     * @mbg.generated
     */
    SecretKey selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tp_secret_key
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SecretKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tp_secret_key
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SecretKey record);
}