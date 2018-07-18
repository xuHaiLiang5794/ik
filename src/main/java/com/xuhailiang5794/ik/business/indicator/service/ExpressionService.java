package com.xuhailiang5794.ik.business.indicator.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuhailiang5794.ik.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import com.xuhailiang5794.ik.business.indicator.entity.Expression;
import com.xuhailiang5794.ik.business.indicator.mapper.ExpressionMapper;
import com.xuhailiang5794.ik.business.indicator.vo.ExpressionVO;

@Service
@Transactional(readOnly = true)
public class ExpressionService {
    @Autowired
    private ExpressionMapper mapper;

    @Transactional
    public int save(ExpressionVO vo) {
        Expression record = new Expression();
        BeanUtils.copyProperties(vo, record);
        BeanUtils.setDataTimeOfBean(record);
        return mapper.insertSelective(record);
    }

    @Transactional
    public int delete(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public Expression get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public Page list(int page, int limit, Map<String, Object> params) {
        PageHelper.startPage(page, limit);
        Page<Expression> pageData = (Page<Expression>) mapper.selectByCondition(params);
        return pageData;
    }

}