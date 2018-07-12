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
public class ChineseCommentGenerator extends DefaultCommentGenerator {

    /**
     * 给属性添加文档注释
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        addFieldComment(field, introspectedColumn);
    }

    /**
     * 给属性添加文档注释
     * @param field
     * @param introspectedColumn
     */
    private void addFieldComment(Field field, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        if (StringUtils.isNotBlank(remarks)) {
            addJavaDocLine(field, remarks);
        }
    }

    /**
     * 给属性添加文档注释
     * @param field
     * @param remarks
     */
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
    }
}
