package com.xuhailiang5794.ik.support.generator.business.source;

import com.xuhailiang5794.ik.utils.OutputUtils;

import java.io.File;
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
public class GeneratedServiceFile extends GeneratedFile {
    private static final String currentPackage = SERVICE_PACKAGE;

    public GeneratedServiceFile(String basePackage, String entityPackage, String entityName, File moduleDir) {
        super(basePackage, currentPackage, entityPackage, entityName, moduleDir);
    }

    @Override
    protected void appendContent(Class clazz, StringBuilder sb, List<String> importStrings) {
        sb.append("@Service");
        OutputUtils.newLine(sb);
        sb.append("@Transactional(readOnly = true)");
        OutputUtils.newLine(sb);

        sb.append(Modifier.toString(clazz.getModifiers()));
        sb.append(" class ");
        sb.append(getFileName());
        sb.append(" {");
        OutputUtils.newLine(sb);

        appendMapper(sb);
        appendMethod(sb);

        initImportStrings(importStrings);

        OutputUtils.newLine(sb);
        sb.append("}");
    }

    private void appendMethod(StringBuilder sb) {
        appendSaveMethod(sb);
        appendDeleteMethod(sb);
        appendGetMethod(sb);
        appendListMethod(sb);
    }

    private void appendListMethod(StringBuilder sb) {
        OutputUtils.newLine(sb);
        sb.append("    public Page list(int page, int limit, Map<String, Object> params) {");
        OutputUtils.newLine(sb);
        sb.append("        PageHelper.startPage(page, limit);");
        OutputUtils.newLine(sb);
        sb.append("        Page<");
        sb.append(domainObjectName);
        sb.append("> pageData = (Page<");
        sb.append(domainObjectName);
        sb.append(">) mapper.selectByCondition(params);");
        OutputUtils.newLine(sb);
        sb.append("        return pageData;");
        OutputUtils.newLine(sb);
        sb.append("    }");
        OutputUtils.newLine(sb);
    }

    private void appendGetMethod(StringBuilder sb) {
        OutputUtils.newLine(sb);
        sb.append("    public ");
        sb.append(domainObjectName);
        sb.append(" get(" + getPrimaryKeyClass().getSimpleName() + " id) {");
        OutputUtils.newLine(sb);
        sb.append("        ");
        sb.append("return mapper.selectByPrimaryKey(id);");
        OutputUtils.newLine(sb);
        sb.append("    }");
        OutputUtils.newLine(sb);
    }

    private void appendDeleteMethod(StringBuilder sb) {
        OutputUtils.newLine(sb);
        sb.append("    @Transactional");
        OutputUtils.newLine(sb);
        sb.append("    public int delete(" + getPrimaryKeyClass().getSimpleName() + " id) {");
        OutputUtils.newLine(sb);
        sb.append("        ");
        sb.append("return mapper.deleteByPrimaryKey(id);");
        OutputUtils.newLine(sb);
        sb.append("    }");
        OutputUtils.newLine(sb);
    }

    private void appendSaveMethod(StringBuilder sb) {
        OutputUtils.newLine(sb);
        sb.append("    @Transactional");
        OutputUtils.newLine(sb);
        sb.append("    public int save(");
        sb.append(domainObjectName);
        sb.append("VO vo) {");
        OutputUtils.newLine(sb);
        sb.append("        ");
        sb.append(domainObjectName);
        sb.append(" record = new ");
        sb.append(domainObjectName);
        sb.append("();");
        OutputUtils.newLine(sb);
        sb.append("        BeanUtils.copyProperties(vo, record);");
        OutputUtils.newLine(sb);
        sb.append("        BeanUtils.setDataTimeOfBean(record);");
        OutputUtils.newLine(sb);
        sb.append("        return mapper.insertSelective(record);");
        OutputUtils.newLine(sb);
        sb.append("    }");
        OutputUtils.newLine(sb);
    }

    private void appendMapper(StringBuilder sb) {
        sb.append("    @Autowired");
        OutputUtils.newLine(sb);
        sb.append("    private ");
        sb.append(domainObjectName);
        sb.append("Mapper mapper;");
        OutputUtils.newLine(sb);
    }

    private void initImportStrings(List<String> importStrings) {
        importStrings.add("import com.github.pagehelper.Page;");
        importStrings.add("import com.github.pagehelper.PageHelper;");
        importStrings.add("import com.xuhailiang5794.ik.utils.BeanUtils;");
        importStrings.add("import org.springframework.beans.factory.annotation.Autowired;");
        importStrings.add("import org.springframework.stereotype.Service;");
        importStrings.add("import org.springframework.transaction.annotation.Transactional;");
        importStrings.add("import java.util.Map;");

        importStrings.add("import " + getDomainPackage() + OutputUtils.packageSeparator() + getDomainObjectName() + ";");
        importStrings.add("import " + basePackage + OutputUtils.packageSeparator() + MAPPER_PACKAGE + OutputUtils.packageSeparator() + getDomainObjectName() + "Mapper;");
        importStrings.add("import " + basePackage + OutputUtils.packageSeparator() + VO_PACKAGE + OutputUtils.packageSeparator() + getDomainObjectName() + "VO;");

        Class clazz = getPrimaryKeyClass();
        if (!clazz.getPackage().getName().equals("java.lang")) {
            importStrings.add("import " + clazz.getName() + ";");
        }
    }

    @Override
    protected String getNameSuffix() {
        return SERVICE_NAME_SUFFIX;
    }
}
