package com.xuhailiang5794.ik.support.generator.business.source;

import com.xuhailiang5794.ik.support.generator.business.BusinessGenerator;
import com.xuhailiang5794.ik.support.generator.business.elements.JavaMvc;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 生成VO文件代码内容
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/17
 */
public class VoSource {
    public static void newLine(StringBuilder sb) {
        sb.append("\n");
    }

    public String getFormattedContent(String basePackage, String entityPackage, String voPackage, String subPath, JavaMvc mvc) {
        StringBuilder sb = new StringBuilder();
        Class clazz;
        try {
            clazz = Class.forName(basePackage + "." + entityPackage + "." + mvc.getDomainObjectName());
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("要生成的domain对象不存在");
        }
        sb.append("package ");
        sb.append(basePackage + "." + voPackage);
        sb.append(";");
        newLine(sb);
        sb.append(Modifier.toString(clazz.getModifiers()));
        sb.append(" class ");
        sb.append(clazz.getSimpleName());
        sb.append(BusinessGenerator.VO_SUFFIX);
        sb.append(" {");
        newLine(sb);
        Field[] fields = clazz.getDeclaredFields();
        List<String> importStrings = new ArrayList<>(fields.length);
        for (Field field : fields) {
            newLine(sb);
            Annotation[] annotations = field.getAnnotations();
            if (ArrayUtils.isNotEmpty(annotations)) {
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().getTypeName().startsWith("io.swagger.annotations")) {
                        //@io.swagger.annotations.ApiModelProperty(reference=, access=, allowableValues=, notes=, hidden=false, dataType=, name=, readOnly=false, position=0, value=表达式代码, required=false, example=)
                        String annotationStr = annotation.toString();
                        annotationStr = annotationStr.replace("=,", "=\"\",");
                        annotationStr = annotationStr.replace("value=", "value=\"");
                        annotationStr = annotationStr.replace(", required=", "\", required=");
                        annotationStr = annotationStr.replace("example=)", "example=\"\")");
                        sb.append("    ");
                        sb.append(annotationStr);
                        newLine(sb);
                    }
                }
            }
            sb.append("    ");
            sb.append(Modifier.toString(field.getModifiers()));
            sb.append(" ");
            sb.append(field.getType().getName());
            sb.append(" ");
            sb.append(field.getName());
            sb.append(";");
        }

        sb.append("}");
        return sb.toString();
    }
}
