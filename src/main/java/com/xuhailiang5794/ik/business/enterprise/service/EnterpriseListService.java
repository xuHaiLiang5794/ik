package com.xuhailiang5794.ik.business.enterprise.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuhailiang5794.ik.business.enterprise.entity.EnterpriseList;
import com.xuhailiang5794.ik.business.enterprise.mapper.EnterpriseListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class EnterpriseListService {
    @Autowired
    private EnterpriseListMapper mapper;

    @Transactional
    public void save(List<EnterpriseList> enterpriseLists) {
        if (enterpriseLists != null && !enterpriseLists.isEmpty()) {
            for (EnterpriseList enterprise : enterpriseLists) {
                if (mapper.selectByPrimaryKey(enterprise.getCode()) == null) {
                    mapper.insert(enterprise);
                } else {
                    mapper.updateByPrimaryKey(enterprise);
                }
            }
        }
    }

    public EnterpriseList get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public Page list(int page, int limit, Map<String, Object> params) {
        PageHelper.startPage(page, limit);
        Page<EnterpriseList> pageData = (Page<EnterpriseList>) mapper.selectByCondition(params);
        return pageData;
    }

}