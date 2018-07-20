package com.xuhailiang5794.ik.business.enterprise.mapper;

import com.xuhailiang5794.ik.business.enterprise.entity.StockExchange;
import java.util.List;
import java.util.Map;

public interface StockExchangeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_exchange
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_exchange
     *
     * @mbg.generated
     */
    int insert(StockExchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_exchange
     *
     * @mbg.generated
     */
    int insertSelective(StockExchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_exchange
     *
     * @mbg.generated
     */
    StockExchange selectByPrimaryKey(Integer code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_exchange
     *
     * @mbg.generated
     */
    List<StockExchange> selectByCondition(Map<String, Object> params);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_exchange
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StockExchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_exchange
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StockExchange record);
}