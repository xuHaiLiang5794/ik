package com.xuhailiang5794.ik.business.indicator.controller;

import com.xuhailiang5794.ik.business.indicator.entity.Expression;
import com.xuhailiang5794.ik.business.indicator.service.ExpressionService;
import com.xuhailiang5794.ik.business.indicator.vo.ExpressionVO;
import com.xuhailiang5794.ik.utils.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.InvocationTargetException;

@Api(description = "")
@RestController
@RequestMapping("indicator/expression")
@Slf4j
public class ExpressionController {
    @Autowired
    private ExpressionService service;

    @ApiOperation(value = "save", notes = "保存", response = ExpressionVO.class)
    @PostMapping("save")
    public Object save(ExpressionVO vo) {
        log.info("数据对象 {}", vo);
        service.save(vo);
        return vo;
    }

    @ApiOperation(value = "delete", notes = "根据主键删除,逻辑删除", response = ExpressionVO.class)
    @DeleteMapping("delete")
    public Object delete(String id) {
        return service.delete(id);
    }

    @ApiOperation(value = "get", notes = "根据主键获取数据", response = ExpressionVO.class)
    @GetMapping("get")
    public Expression get(String id) {
        return service.get(id);
    }

    @ApiOperation(value = "list", notes = "根据条件分页获取数据", response = ExpressionVO.class)
    @GetMapping("list")
    public Object list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int limit, ExpressionVO vo)
            throws InvocationTargetException, IllegalAccessException {
        return service.list(1, 10, BeanUtils.beanToMap(vo));
    }

}