package com.xuhailiang5794.ik.business.indicator.mapper;

import com.xuhailiang5794.ik.business.indicator.entity.IndicatorClassification;

import java.util.List;
import java.util.Map;

public interface IndicatorClassificationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_indicator_classification
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_indicator_classification
     *
     * @mbg.generated
     */
    int insert(IndicatorClassification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_indicator_classification
     *
     * @mbg.generated
     */
    int insertSelective(IndicatorClassification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_indicator_classification
     *
     * @mbg.generated
     */
    IndicatorClassification selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_indicator_classification
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(IndicatorClassification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_indicator_classification
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(IndicatorClassification record);

    List<IndicatorClassification> selectByCon(Map<String, Object> params);
}