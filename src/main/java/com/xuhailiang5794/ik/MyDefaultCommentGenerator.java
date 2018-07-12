package com.xuhailiang5794.ik;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/12 17:39
 */
public class MyDefaultCommentGenerator extends DefaultCommentGenerator {
    /**
     *
     */
    private int d;

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        addFieldComment(field, introspectedColumn);
    }

    private void addFieldComment(Field field, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        if (StringUtils.isNotBlank(remarks)) {
            addJavaDocLine(field, remarks);
        }
    }

    private void addJavaDocLine(Field field, String remarks) {
        field.addJavaDocLine("/**");
        field.addJavaDocLine(
                new StringBuilder()
                        .append(" * ")
                        .append(remarks)
                        .toString());
        field.addJavaDocLine("*/");
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        super.addSetterComment(method, introspectedTable, introspectedColumn);
    }
}
