package com.xuhailiang5794.ik.business.indicator.controller;

import com.xuhailiang5794.ik.business.indicator.entity.IndicatorClassification;
import com.xuhailiang5794.ik.business.indicator.service.IndicatorClassificationService;
import com.xuhailiang5794.ik.business.indicator.vo.IndicatorClassificationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/16 14:07
 */
@Api(description = "指标类表维护")
@RestController
@RequestMapping("indicator/classification")
@Slf4j
public class IndicatorClassificationController {
    @Autowired
    private IndicatorClassificationService service;

    @ApiOperation(value = "save", notes = "通过指标ID获取指标对象", response = Object.class)
    @PostMapping("save")
    public Object save(IndicatorClassificationVO vo) {
        log.info("添加指标类 {}", vo);
        service.save(vo);
        return vo;
    }

    @ApiOperation(value = "get", notes = "通过指标ID获取指标对象", response = Object.class)
    @GetMapping("get")
    public Object get(String id) {
        IndicatorClassification classification = service.get(id);
        return classification;
    }

    @ApiOperation(value = "list", notes = "分页获取数据", response = Object.class)
    @GetMapping("list")
    public Object list() {
        return service.list(1, 10, null);
    }
}
