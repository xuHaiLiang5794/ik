package com.xuhailiang5794.ik.support.generator.business;

import com.xuhailiang5794.ik.support.generator.business.elements.GeneratorConfiguration;
import com.xuhailiang5794.ik.support.generator.business.elements.JavaMvc;
import com.xuhailiang5794.ik.support.generator.business.elements.JavaMvcGenerator;
import com.xuhailiang5794.ik.support.generator.business.elements.Module;
import com.xuhailiang5794.ik.support.generator.business.source.VoSource;
import com.xuhailiang5794.ik.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
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
    private static final String DEFAULT_ENTITY = "entity";
    public static final String VO = "vo";
    public static final String VO_SUFFIX = "VO";
    private static final String SUFFIX = ".java";
    private static final String FILE_ENCODING = "utf-8";
    public static void main(String[] args)
            throws IOException, ParserConfigurationException, SAXException {
        GeneratorConfiguration configuration = parseBusinessGeneratorConfiguration(Object.class.getResource("/generator/generatorBusinessConfig.xml"));
        log.info("configuration {}", configuration);
        List<Module> modules = configuration.getModules();
        String basePackage = configuration.getBasePackage();
        String entitySubPackage = configuration.getEntitySubPackage();
        basePackage = StringUtils.trimToEmpty(basePackage);
        basePackage = basePackage.replace(".", File.separator);
        entitySubPackage = StringUtils.isEmpty(entitySubPackage) ? DEFAULT_ENTITY : entitySubPackage;
        for (Module module : modules) {
            File moduleDir = createModuleDir(module, basePackage);
            StringBuilder sb = new StringBuilder();
            sb.append(configuration.getBasePackage());
            sb.append(".");
            sb.append(module.getMvcGenerator().getTargetPackage());
            module.setBasePackage(sb.toString());
            writeGeneratedVOFile(moduleDir, entitySubPackage, module);
        }
    }

    public static void writeGeneratedVOFile(File baseDir, String entitySubPackage, Module module) throws IOException {
        List<JavaMvc> javaMvcList = module.getJavaMvc();
        File voDir = new File(baseDir, VO);
        for (JavaMvc mvc : javaMvcList) {
            File javaFile = new File(voDir, mvc.getDomainObjectName() + VO_SUFFIX + SUFFIX);
            writeFile(javaFile, new VoSource().getFormattedContent(module.getBasePackage(), entitySubPackage, VO, entitySubPackage, mvc), FILE_ENCODING);
        }
    }

    private static void writeFile(File file, String content, String fileEncoding) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;
        if (fileEncoding == null) {
            osw = new OutputStreamWriter(fos);
        } else {
            osw = new OutputStreamWriter(fos, fileEncoding);
        }

        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(content);
        bw.close();
    }

    /**
     * 获取模块File对象，如果该路径不存在则创建
     * @param module
     * @param basePackage
     * @return
     * @throws FileNotFoundException
     */
    private static File createModuleDir(Module module, String basePackage) throws FileNotFoundException {
        String targetProject = module.getTargetProject();
        String targetPackage = module.getMvcGenerator().getTargetPackage();

        File dir = new File(targetProject);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(basePackage);
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
            Document document = null;

            document = builder.parse(configFile);
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
