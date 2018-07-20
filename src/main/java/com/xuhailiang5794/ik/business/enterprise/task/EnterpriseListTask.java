package com.xuhailiang5794.ik.business.enterprise.task;

import com.xuhailiang5794.ik.business.enterprise.entity.EnterpriseList;
import com.xuhailiang5794.ik.business.enterprise.query.EnterpriseListQuery;
import com.xuhailiang5794.ik.business.enterprise.service.EnterpriseListService;
import com.xuhailiang5794.ik.support.thread.TaskThread;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * <pre>
 * 查询沪深A股企业清单
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/20
 */
@AllArgsConstructor
public class EnterpriseListTask extends TaskThread {
    /**
     * 当前查询页
     */
    private int page;
    private List<EnterpriseList> data;
    private EnterpriseListService service;

    @Override
    protected void handle() {
        if (data == null) {
            EnterpriseListQuery enterpriseListQuery = new EnterpriseListQuery();
            enterpriseListQuery.query(page);
            try {
                List<EnterpriseList> enterpriseLists = enterpriseListQuery.getEnterpriseList();
                data = enterpriseLists;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
        service.save(data);
    }
}
