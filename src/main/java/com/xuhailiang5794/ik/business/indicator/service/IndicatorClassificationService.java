package com.xuhailiang5794.ik.business.indicator.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuhailiang5794.ik.business.indicator.entity.IndicatorClassification;
import com.xuhailiang5794.ik.business.indicator.mapper.IndicatorClassificationMapper;
import com.xuhailiang5794.ik.business.indicator.vo.IndicatorClassificationVO;
import com.xuhailiang5794.ik.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/16 14:14
 */
@Service
@Transactional(readOnly = true)
public class IndicatorClassificationService {
    @Autowired
    private IndicatorClassificationMapper mapper;

    @Transactional
    public int save(IndicatorClassificationVO vo) {
        IndicatorClassification record = new IndicatorClassification();
        BeanUtils.copyProperties(vo, record);
        BeanUtils.setDataTimeOfBean(record);
        return mapper.insertSelective(record);
    }

    public IndicatorClassification get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public Page list(int page, int limit, Map<String, Object> params) {
        PageHelper.startPage(page, limit);
        Page<IndicatorClassification> pageData = (Page<IndicatorClassification>) mapper.selectByCon(params);
        return pageData;
    }
}
