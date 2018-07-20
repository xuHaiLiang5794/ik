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
public class Module {
    private String targetProject;
    private boolean create = true;
    private JavaMvcGenerator mvcGenerator;
    private List<JavaMvc> javaMvc;

    private String basePackage;

    public void addJavaMvc(JavaMvc mvc) {
        if (javaMvc == null) {
            javaMvc = new ArrayList<>();
        }
        javaMvc.add(mvc);
    }
}
