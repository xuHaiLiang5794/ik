package com.xuhailiang5794.ik.support.generator.business;

import com.xuhailiang5794.ik.support.generator.business.elements.GeneratorConfiguration;
import com.xuhailiang5794.ik.support.generator.business.elements.JavaMvc;
import com.xuhailiang5794.ik.support.generator.business.elements.Module;
import com.xuhailiang5794.ik.support.generator.business.source.GeneratedControllerFile;
import com.xuhailiang5794.ik.support.generator.business.source.GeneratedServiceFile;
import com.xuhailiang5794.ik.support.generator.business.source.GeneratedVOFile;
import com.xuhailiang5794.ik.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
@Slf4j
public class BusinessGenerator {

    public static void main(String[] args)
            throws IOException, ParserConfigurationException, SAXException {
        GeneratorConfiguration configuration = parseBusinessGeneratorConfiguration(Object.class.getResource("/generator/generatorBusinessConfig.xml"));
        log.info("configuration {}", configuration);
        List<Module> modules = configuration.getModules();
        String basePackage = configuration.getBasePackage();
        basePackage = StringUtils.trimToEmpty(basePackage);
        basePackage = basePackage.replace(".", File.separator);
        for (Module module : modules) {
            File moduleDir = createModuleDir(module, basePackage);
            StringBuilder sb = new StringBuilder();
            sb.append(configuration.getBasePackage());
            sb.append(".");
            sb.append(module.getMvcGenerator().getTargetPackage());
            module.setBasePackage(sb.toString());
            writeGeneratedVOFile(moduleDir, configuration, module);
        }
    }

    public static void writeGeneratedVOFile(File moduleDir, GeneratorConfiguration configuration, Module module) throws IOException {
        List<JavaMvc> javaMvcList = module.getJavaMvc();
        String basePackage = module.getBasePackage();
        String entityPackage = configuration.getEntitySubPackage();
        for (JavaMvc mvc : javaMvcList) {
            new GeneratedVOFile(
                    basePackage, entityPackage, mvc.getDomainObjectName(), moduleDir)
                    .writeFile();
            new GeneratedServiceFile(
                    basePackage, entityPackage, mvc.getDomainObjectName(), moduleDir)
                    .writeFile();
            new GeneratedControllerFile(
                    basePackage, entityPackage, mvc.getDomainObjectName(), moduleDir)
                    .writeFile();
        }
    }

    /**
     * 获取模块File对象，如果该路径不存在则创建
     *
     * @param module
     * @param subDirPath
     * @return
     * @throws FileNotFoundException
     */
    private static File createModuleDir(Module module, String subDirPath) throws FileNotFoundException {
        String targetProject = module.getTargetProject();
        String targetPackage = module.getMvcGenerator().getTargetPackage();

        File dir = new File(targetProject);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(subDirPath);
        sb.append(File.separator);
        sb.append(StringUtils.trimToEmpty(targetPackage));

        File moduleDir = new File(dir, sb.toString());
        log.info("moduleDir exist {}", moduleDir.exists());
        if (!moduleDir.exists()) {
            moduleDir.mkdirs();
        }
        return moduleDir;
    }

    public static GeneratorConfiguration parseBusinessGeneratorConfiguration(URL url)
            throws IOException, ParserConfigurationException, SAXException {
        return parseBusinessGeneratorConfiguration(url.getPath());
    }

    public static GeneratorConfiguration parseBusinessGeneratorConfiguration(String configFile)
            throws IOException, ParserConfigurationException, SAXException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(configFile);
            Element rootNode = document.getDocumentElement();
            GeneratorConfiguration config = parseBusinessGeneratorConfiguration(rootNode);
            return config;
        } catch (ParserConfigurationException e) {
            throw e;
        }
    }

    private static GeneratorConfiguration parseBusinessGeneratorConfiguration(Element rootNode) {
        return new BusinessGeneratorConfigurationParser().parseConfiguration(rootNode);
    }
}
