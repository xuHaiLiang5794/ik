package com.xuhailiang5794.ik.test;

import com.xuhailiang5794.ik.support.tp.mapper.SecretKeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/13 18:48
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private SecretKeyMapper secretKeyMapper;

    @GetMapping
    public Object test() {
        return secretKeyMapper.selectByPrimaryKey("1");
    }
}
