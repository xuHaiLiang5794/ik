package com.xuhailiang5794.ik.support.generator.business.elements;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/17
 */
@Data
public class GeneratorConfiguration {
    /**
     * 根包名
     */
    private String basePackage;
    /**
     * entity包名，比如entity、domain
     */
    private String entitySubPackage;

    private List<Module> modules;

    public void addModule(Module module) {
        if (modules == null) {
            modules = new ArrayList<>();
        }
        modules.add(module);
    }
}
