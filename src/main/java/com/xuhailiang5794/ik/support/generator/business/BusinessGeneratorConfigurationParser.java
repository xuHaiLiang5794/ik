package com.xuhailiang5794.ik.support.generator.business;

import com.xuhailiang5794.ik.support.generator.business.elements.GeneratorConfiguration;
import com.xuhailiang5794.ik.support.generator.business.elements.JavaMvc;
import com.xuhailiang5794.ik.support.generator.business.elements.JavaMvcGenerator;
import com.xuhailiang5794.ik.support.generator.business.elements.Module;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

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
public class BusinessGeneratorConfigurationParser {
    public GeneratorConfiguration parseConfiguration(Element rootNode) {
        GeneratorConfiguration configuration = new GeneratorConfiguration();
        try {
            parseConfigurationProperty(configuration, rootNode);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        NodeList nodeList = rootNode.getElementsByTagName("module");

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node childNode = nodeList.item(i);
            parseModule(configuration, childNode);
        }

        return configuration;
    }

    private void parseModule(GeneratorConfiguration configuration, Node node) {
        log.info("解析module元素 {}", node);
        NodeList nodeList = node.getChildNodes();
        Module module = new Module();
        setProperty(module, node.getAttributes());
        configuration.addModule(module);
        for (int i = 0, size = nodeList.getLength(); i < size; i++) {
            Node childNode = nodeList.item(i);
            initModule(module, childNode);
        }
    }

    private void initModule(Module module, Node node) {
        switch (node.getNodeName()) {
            case "javaMvcGenerator":
                parseJavaMvcGenerator(module, node);
                break;
            case "javaMvc":
                parseJavaMvc(module, node);
                break;
        }
    }

    private void parseJavaMvc(Module module, Node node) {
        log.info("解析javaMvc元素 {}", node);
        JavaMvc mvc = new JavaMvc();
        setProperty(mvc, node.getAttributes());
        module.addJavaMvc(mvc);
    }

    private void parseJavaMvcGenerator(Module module, Node node) {
        log.info("解析javaMvcGenerator元素 {}", node);
        JavaMvcGenerator mvcGenerator = new JavaMvcGenerator();
        setProperty(mvcGenerator, node.getAttributes());
        module.setMvcGenerator(mvcGenerator);
    }

    private void parseConfigurationProperty(GeneratorConfiguration configuration, Element node)
            throws InvocationTargetException, IllegalAccessException {
        log.info("解析root Element属性");
        setProperty(configuration, node.getAttributes());
    }

    private void setProperty(Object obj, NamedNodeMap nodeMap) {
        Class clazz = obj.getClass();
        for (int i = 0, size = nodeMap.getLength(); i < size; i++) {
            Node node = nodeMap.item(i);
            setProperty(clazz, obj, node);
        }
    }

    private void setProperty(Class clazz, Object obj, Node node) {
        try {
            Field field = clazz.getDeclaredField(node.getNodeName());
            field.setAccessible(true);
            Object value = getValue(field, node);
            field.set(obj, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object getValue(Field field, Node node) {
        String value = node.getNodeValue();
        if (field.getType() == boolean.class
                || field.getType() == Boolean.class) {
            return isTrue(value);
        }
        return value;
    }

    private boolean isTrue(String value) {
        return "true".equalsIgnoreCase(value);
    }
}
