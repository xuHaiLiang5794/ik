package com.xuhailiang5794.ik.business.indicator.mapper;

import com.xuhailiang5794.ik.business.indicator.entity.ExpressionCode;
import java.util.List;
import java.util.Map;

public interface ExpressionCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_expression_code
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_expression_code
     *
     * @mbg.generated
     */
    int insert(ExpressionCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_expression_code
     *
     * @mbg.generated
     */
    int insertSelective(ExpressionCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_expression_code
     *
     * @mbg.generated
     */
    ExpressionCode selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_expression_code
     *
     * @mbg.generated
     */
    List<ExpressionCode> selectByCondition(Map<String, Object> params);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_expression_code
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ExpressionCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ind_expression_code
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ExpressionCode record);
}