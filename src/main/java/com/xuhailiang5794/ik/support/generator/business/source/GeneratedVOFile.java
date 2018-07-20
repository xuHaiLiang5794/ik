package com.xuhailiang5794.ik.support.generator.business.source;

import com.xuhailiang5794.ik.utils.OutputUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import static com.xuhailiang5794.ik.support.generator.business.source.GeneratedFile.Constants.VO_NAME_SUFFIX;
import static com.xuhailiang5794.ik.support.generator.business.source.GeneratedFile.Constants.VO_PACKAGE;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/18
 */
public class GeneratedVOFile extends GeneratedFile {
    private static final String currentPackage = VO_PACKAGE;

    public GeneratedVOFile(String basePackage, String entityPackage, String entityName, File moduleDir) {
        super(basePackage, currentPackage, entityPackage, entityName, moduleDir);
    }

    @Override
    protected void appendContent(Class clazz, StringBuilder sb, List<String> importStrings) {
        sb.append("@Data");
        OutputUtils.newLine(sb);

        sb.append(Modifier.toString(clazz.getModifiers()));
        sb.append(" class ");
        sb.append(getFileName());
        sb.append(" {");
        OutputUtils.newLine(sb);

        Field[] fields = clazz.getDeclaredFields();
        importStrings.add("import lombok.Data;");

        appendFields(fields, sb, importStrings);

        OutputUtils.newLine(sb);
        sb.append("}");
    }

    private void appendFields(Field[] fields, StringBuilder sb, List<String> importStrings) {
        for (Field field : fields) {
            OutputUtils.newLine(sb);
            appendAnnotation(sb, field, importStrings);

            sb.append("    ");
            sb.append(Modifier.toString(field.getModifiers()));
            sb.append(" ");
            sb.append(getType(field.getType().getName(), importStrings));
            sb.append(" ");
            sb.append(field.getName());
            sb.append(";");
            OutputUtils.newLine(sb);
        }
    }

    private void appendAnnotation(StringBuilder sb, Field field, List<String> importStrings) {
        Annotation[] annotations = field.getAnnotations();
        if (ArrayUtils.isNotEmpty(annotations)) {
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().getTypeName()
                        .startsWith("io.swagger.annotations")) {
                    importStrings.add("import " + annotation.annotationType().getTypeName() + ";");
                    String annotationStr = proSwaggerAnnotation(annotation);
                    sb.append("    @");
                    sb.append(annotationStr);
                    OutputUtils.newLine(sb);
                }
            }
        }
    }

    private String proSwaggerAnnotation(Annotation annotation) {
        String annotationStr = annotation.toString();
        annotationStr = annotationStr.replace("=,", "=\"\",")
                .replace("value=", "value=\"")
                .replace(", required=", "\", required=")
                .replace("example=)", "example=\"\")")
                .substring(annotationStr.lastIndexOf(".") + 1)
                .replace("reference=\"\", ", "")
                .replace("access=\"\", ", "")
                .replace("allowableValues=\"\", ", "")
                .replace("notes=\"\", ", "")
                .replace("hidden=false, ", "")
                .replace("dataType=\"\", ", "")
                .replace("name=\"\", ", "")
                .replace("readOnly=false, ", "")
                .replace("position=0, ", "")
                .replace("value=\"\", ", "")
                .replace("required=false, ", "")
                .replace(", example=\"\"", "");
        return annotationStr;
    }

    @Override
    protected String getNameSuffix() {
        return VO_NAME_SUFFIX;
    }
}
