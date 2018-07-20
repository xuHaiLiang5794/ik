package com.xuhailiang5794.ik.support.generator.business.source;

import com.xuhailiang5794.ik.utils.OutputUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.xuhailiang5794.ik.support.generator.business.source.GeneratedFile.Constants.FILE_ENCODING;
import static com.xuhailiang5794.ik.support.generator.business.source.GeneratedFile.Constants.FIND_PRIMARY_KEY_METHOD;
import static com.xuhailiang5794.ik.support.generator.business.source.GeneratedFile.Constants.MAPPER_PACKAGE;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/18
 */
@Getter(AccessLevel.PROTECTED)
@AllArgsConstructor
public abstract class GeneratedFile {
    /**
     * 上层包名
     */
    protected String basePackage;
    /**
     * 当前Class应属子包名
     */
    protected String currentPackage;
    /**
     * 当前Class对应的实体类所在子包名
     */
    protected String domainPackage;
    /**
     * 当前Class对应的实体类的类名
     */
    protected String domainObjectName;
    /**
     * 文件保存的module目录
     */
    private File moduleDir;

    public void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(getCurrentFile(), false);
        OutputStreamWriter osw;
        osw = new OutputStreamWriter(fos, FILE_ENCODING);

        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(getFormattedContent());
        bw.close();
    }

    /**
     * 生成java代码
     *
     * @return
     */
//    public abstract String getFormattedContent();
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
        List<String> importStrings = getImportStrings(10);

        appendContent(clazz, sb, importStrings);

        StringBuilder importStringsSb = new StringBuilder();
        importStrings = importDistinct(importStrings);
        for (String importString : importStrings) {
            importStringsSb.append(importString);
            OutputUtils.newLine(importStringsSb);
        }
        sb.replace(startIndex, endIndex, importStringsSb.toString());
        return sb.toString();
    }
    protected abstract void appendContent(Class clazz, StringBuilder sb, List<String> importStrings);
    private void appendPackage(StringBuilder sb) {
        sb.append("package ");
        sb.append(getCurrentPackage());
        sb.append(";");
        OutputUtils.newLine(sb);
    }

    /**
     * 获取生成的java文件名
     *
     * @return
     */
    public String getFileName() {
        return getDomainObjectName() + getNameSuffix();
    }

    /**
     * 获取当前要生成的Class所属的包名
     *
     * @return
     */
    public String getCurrentPackage() {
        return getPackage(currentPackage);
    }

    /**
     * 获取当前要生成的Class对应的实体类的包名
     *
     * @return
     */
    protected String getDomainPackage() {
        return getPackage(domainPackage);
    }

    private String getPackage(String subPackage) {
        StringBuilder sb = new StringBuilder();
        sb.append(basePackage);
        sb.append(OutputUtils.packageSeparator());
        sb.append(subPackage);
        return sb.toString();
    }

    /**
     * 获取当前要生成的Class文件
     *
     * @return
     */
    private File getCurrentFile() {
        StringBuilder subFilePath = new StringBuilder();
        subFilePath.append(currentPackage);
        subFilePath.append(File.separator);
        subFilePath.append(domainObjectName);
        subFilePath.append(getNameSuffix());
        subFilePath.append(Constants.SUFFIX);
        File currentFile = new File(moduleDir, subFilePath.toString());

        /**
         * 如果上层目录不存在，则创建
         */
        if (!currentFile.getParentFile().exists()) {
            currentFile.getParentFile().mkdirs();
        }
        return currentFile;
    }

    protected abstract String getNameSuffix();

    protected List<String> getImportStrings(int size) {
        List<String> importStrings = new ArrayList<>(size);
        return importStrings;
    }

    protected String getType(String typeName, List<String> importStrings) {
        int index = typeName.lastIndexOf(".");
        if (!"java.lang".equals(typeName.substring(0, index))) {
            importStrings.add("import " + typeName + ";");
        }
        typeName = typeName.substring(index + 1);
        return typeName;
    }

    protected List<String> importDistinct(List<String> importStrings) {
        return importStrings.stream().distinct().collect(Collectors.toList());
    }

    protected interface Constants {
        String SUFFIX = ".java";
        String FILE_ENCODING = "UTF-8";
        String MAPPER_PACKAGE = "mapper";
        String SERVICE_PACKAGE = "service";
        String CONTROLLER_PACKAGE = "controller";
        String SERVICE_NAME_SUFFIX = "Service";
        String CONTROLLER_NAME_SUFFIX = "Controller";
        String VO_PACKAGE = "vo";
        String VO_NAME_SUFFIX = "VO";

        String FIND_PRIMARY_KEY_METHOD = "deleteByPrimaryKey";
    }

    protected Class getPrimaryKeyClass() {
        String mapperClass = basePackage + OutputUtils.packageSeparator() + MAPPER_PACKAGE + OutputUtils.packageSeparator() + getDomainObjectName() + "Mapper";
        try {
            Class clazz = Class.forName(mapperClass);
            Method[] methods = clazz.getDeclaredMethods();
            Method method = null;
            if (ArrayUtils.isNotEmpty(methods)) {
                for (Method m : methods) {
                    if (FIND_PRIMARY_KEY_METHOD.equals(m.getName())) {
                        method = m;
                        break;
                    }
                }
            }
            if (method != null) {
                Class[] classes = method.getParameterTypes();
                if (ArrayUtils.isNotEmpty(classes)) {
                    return classes[0];
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
