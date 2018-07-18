package com.xuhailiang5794.ik.support.generator.business.source;

import com.xuhailiang5794.ik.utils.OutputUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import static com.xuhailiang5794.ik.support.generator.business.source.GeneratedFile.Constants.*;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/18
 */
public class GeneratedControllerFile extends GeneratedFile {
    private static final String currentPackage = CONTROLLER_PACKAGE;

    public GeneratedControllerFile(String basePackage, String entityPackage, String entityName, File moduleDir) {
        super(basePackage, currentPackage, entityPackage, entityName, moduleDir);
    }

    @Override
    public String getFormattedContent() {
        StringBuilder sb = new StringBuilder();
        Class clazz;
        try {
            clazz = Class.forName(getDomainPackage() + OutputUtils.packageSeparator() + getDomainObjectName());
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("要生成的domain对象不存在");
        }
        appendPackage(sb);
        OutputUtils.newLine(sb);

        int startIndex = sb.length();
        String flagStr = "importStrings";
        sb.append(flagStr);
        int endIndex = sb.length();
        OutputUtils.newLine(sb);

        sb.append("@Api(description = \"\")");
        OutputUtils.newLine(sb);
        sb.append("@RestController");
        OutputUtils.newLine(sb);
        sb.append("@RequestMapping(\"");
        sb.append(getModuleDir().getName());
        sb.append("/");
        sb.append(domainObjectName.toLowerCase());
        sb.append("\")");
        OutputUtils.newLine(sb);
        sb.append("@Slf4j");
        OutputUtils.newLine(sb);

        sb.append(Modifier.toString(clazz.getModifiers()));
        sb.append(" class ");
        sb.append(getFileName());
        sb.append(" {");
        OutputUtils.newLine(sb);

        appendService(sb);
        appendMethod(sb);

        Field[] fields = clazz.getDeclaredFields();
        List<String> importStrings = getImportStrings(fields.length);
        initImportStrings(importStrings);

        OutputUtils.newLine(sb);
        sb.append("}");

        StringBuilder importStringsSb = new StringBuilder();
        for (String importString : importStrings) {
            importStringsSb.append(importString);
            OutputUtils.newLine(importStringsSb);
        }
        sb.replace(startIndex, endIndex, importStringsSb.toString());
        return sb.toString();
    }

    private void appendMethod(StringBuilder sb) {
        appendSaveMethod(sb);
        appendDeleteMethod(sb);
        appendGetMethod(sb);
        appendListMethod(sb);
    }

    private void appendListMethod(StringBuilder sb) {
        OutputUtils.newLine(sb);
        sb.append("    @ApiOperation(value = \"list\", notes = \"根据条件分页获取数据\", response = ");
        sb.append(domainObjectName);
        sb.append(VO_NAME_SUFFIX);
        sb.append(".class)");
        OutputUtils.newLine(sb);
        sb.append("    @GetMapping(\"list\")");
        OutputUtils.newLine(sb);
        sb.append("    public Object list(@RequestParam(defaultValue = \"1\") int page, @RequestParam(defaultValue = \"20\") int limit, ");
        sb.append(domainObjectName);
        sb.append(VO_NAME_SUFFIX);
        sb.append(" vo)");
        OutputUtils.newLine(sb);
        sb.append("            throws InvocationTargetException, IllegalAccessException {");
        OutputUtils.newLine(sb);
        sb.append("        return service.list(1, 10, BeanUtils.beanToMap(vo));");
        OutputUtils.newLine(sb);
        sb.append("    }");
        OutputUtils.newLine(sb);
    }

    private void appendGetMethod(StringBuilder sb) {
        OutputUtils.newLine(sb);
        sb.append("    @ApiOperation(value = \"get\", notes = \"根据主键获取数据\", response = ");
        sb.append(domainObjectName);
        sb.append(VO_NAME_SUFFIX);
        sb.append(".class)");
        OutputUtils.newLine(sb);
        sb.append("    @GetMapping(\"get\")");
        OutputUtils.newLine(sb);
        sb.append("    public ");
        sb.append(domainObjectName);
        sb.append(" get(" + getPrimaryKeyClass().getSimpleName() + " id) {");
        OutputUtils.newLine(sb);
        sb.append("        ");
        sb.append("return service.get(id);");
        OutputUtils.newLine(sb);
        sb.append("    }");
        OutputUtils.newLine(sb);
    }

    private void appendDeleteMethod(StringBuilder sb) {
        OutputUtils.newLine(sb);
        sb.append("    @ApiOperation(value = \"delete\", notes = \"根据主键删除,逻辑删除\", response = ");
        sb.append(domainObjectName);
        sb.append(VO_NAME_SUFFIX);
        sb.append(".class)");
        OutputUtils.newLine(sb);
        sb.append("    @DeleteMapping(\"delete\")");
        OutputUtils.newLine(sb);
        sb.append("    public Object delete(" + getPrimaryKeyClass().getSimpleName() + " id) {");
        OutputUtils.newLine(sb);
        sb.append("        ");
        sb.append("return service.delete(id);");
        OutputUtils.newLine(sb);
        sb.append("    }");
        OutputUtils.newLine(sb);
    }

    private void appendSaveMethod(StringBuilder sb) {
        OutputUtils.newLine(sb);
        sb.append("    @ApiOperation(value = \"save\", notes = \"保存\", response = ");
        sb.append(domainObjectName);
        sb.append(VO_NAME_SUFFIX);
        sb.append(".class)");
        OutputUtils.newLine(sb);
        sb.append("    @PostMapping(\"save\")");
        OutputUtils.newLine(sb);
        sb.append("    public Object save(");
        sb.append(domainObjectName);
        sb.append(VO_NAME_SUFFIX);
        sb.append(" vo) {");
        OutputUtils.newLine(sb);
        sb.append("        log.info(\"数据对象 {}\", vo);");
        OutputUtils.newLine(sb);
        sb.append("        service.save(vo);");
        OutputUtils.newLine(sb);
        sb.append("        return vo;");
        OutputUtils.newLine(sb);
        sb.append("    }");
        OutputUtils.newLine(sb);
    }

    private void appendService(StringBuilder sb) {
        sb.append("    @Autowired");
        OutputUtils.newLine(sb);
        sb.append("    private ");
        sb.append(domainObjectName);
        sb.append(SERVICE_NAME_SUFFIX);
        sb.append(" service;");
        OutputUtils.newLine(sb);
    }

    private void initImportStrings(List<String> importStrings) {
        importStrings.add("import " + basePackage + OutputUtils.packageSeparator() + domainPackage + OutputUtils.packageSeparator() + getDomainObjectName() + ";");
        importStrings.add("import " + basePackage + OutputUtils.packageSeparator() + SERVICE_PACKAGE + OutputUtils.packageSeparator() + getDomainObjectName() + SERVICE_NAME_SUFFIX + ";");
        importStrings.add("import " + basePackage + OutputUtils.packageSeparator() + VO_PACKAGE + OutputUtils.packageSeparator() + getDomainObjectName() + VO_NAME_SUFFIX + ";");
        importStrings.add("import com.xuhailiang5794.ik.utils.BeanUtils;");
        importStrings.add("import io.swagger.annotations.Api;");
        importStrings.add("import io.swagger.annotations.ApiOperation;");
        importStrings.add("import lombok.extern.slf4j.Slf4j;");
        importStrings.add("import org.springframework.beans.factory.annotation.Autowired;");
        importStrings.add("import org.springframework.web.bind.annotation.*;");
        importStrings.add("import java.lang.reflect.InvocationTargetException;");


        Class clazz = getPrimaryKeyClass();
        if (!clazz.getPackage().getName().equals("java.lang")) {
            importStrings.add("import " + clazz.getName() + ";");
        }
    }

    private void appendPackage(StringBuilder sb) {
        sb.append("package ");
        sb.append(getCurrentPackage());
        sb.append(";");
        OutputUtils.newLine(sb);
    }

    @Override
    public String getFileName() {
        return getDomainObjectName() + CONTROLLER_NAME_SUFFIX;
    }

    @Override
    protected String getNameSuffix() {
        return CONTROLLER_NAME_SUFFIX;
    }
}
