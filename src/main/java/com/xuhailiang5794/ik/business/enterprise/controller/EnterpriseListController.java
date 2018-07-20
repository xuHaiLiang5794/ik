package com.xuhailiang5794.ik.business.enterprise.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuhailiang5794.ik.business.enterprise.entity.EnterpriseList;
import com.xuhailiang5794.ik.business.enterprise.query.EnterpriseListQuery;
import com.xuhailiang5794.ik.business.enterprise.service.EnterpriseListService;
import com.xuhailiang5794.ik.business.enterprise.task.EnterpriseListTask;
import com.xuhailiang5794.ik.business.enterprise.vo.EnterpriseListVO;
import com.xuhailiang5794.ik.support.thread.RequestThreadPool;
import com.xuhailiang5794.ik.utils.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@Api(description = "")
@RestController
@RequestMapping("enterprise/enterpriselist")
@Slf4j
public class EnterpriseListController {
    @Autowired
    private EnterpriseListService service;
    @Autowired
    private RequestThreadPool threadPool;

    @ApiOperation(value = "get", notes = "根据主键获取数据", response = EnterpriseListVO.class)
    @GetMapping("get")
    public EnterpriseList get(String id) {
        return service.get(id);
    }

    @ApiOperation(value = "list", notes = "根据条件分页获取数据", response = EnterpriseListVO.class)
    @GetMapping("list")
    public Object list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int limit, EnterpriseListVO vo)
            throws InvocationTargetException, IllegalAccessException {
        return service.list(page, limit, BeanUtils.beanToMap(vo));
    }

    @ApiOperation(value = "updateEnterprises", notes = "更新沪深A股企业名单", response = EnterpriseListVO.class)
    @GetMapping("updateEnterprises")
    public Object list() {
        EnterpriseListQuery query = new EnterpriseListQuery();
        JSONObject dataJsonObject = query.query(1);
        Integer pages = dataJsonObject.getInteger("pages");
        try {
            threadPool.execute(new EnterpriseListTask(1, query.getEnterpriseList(), service));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (pages != null && pages > 1) {
            while (pages > 1) {
                threadPool.execute(new EnterpriseListTask(pages--, null, service));
            }
        }
        return "后台更新中";
    }

}