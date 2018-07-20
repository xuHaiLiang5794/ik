package com.xuhailiang5794.ik.utils;

import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/16
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static void setFieldValue(Object obj, Field field, Object value) throws IllegalAccessException {
        if (value == null
                || "".equalsIgnoreCase(value.toString())
                || "-".equalsIgnoreCase(value.toString())
                || "--".equalsIgnoreCase(value.toString())) {
            return;
        }
        Class clazz = field.getType();
        if (clazz == int.class
                || clazz == Integer.class) {
            value = Integer.valueOf(value.toString());
        } else if (clazz == double.class
                || clazz == Double.class) {
            value = Double.valueOf(value.toString());
        } else if (value.toString().equalsIgnoreCase("true")
                || value.toString().equalsIgnoreCase("false")) {
            value = value.toString().equalsIgnoreCase("true");
        } else if (clazz == long.class || clazz == Long.class) {
            value = Long.valueOf(value.toString());
        }
        field.set(obj, value);
    }

    /**
     * 给bean设置数据时间
     *
     * @param obj
     */
    public static void setDataTimeOfBean(Object obj) {
        Assert.notNull(obj, "obj must not be null");
        Class clazz = obj.getClass();
        try {
            Field field = clazz.getDeclaredField("dataTime");
            field.setAccessible(true);
            Class fieldType = field.getType();
            Object data = getCurrentDate(fieldType);
            field.set(obj, data);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static Object getCurrentDate(Class fieldType) {
        Object data = null;
        /**
         * 这里把mybatis generator里面的日期和时间类都if一遍，
         * 按照使用频率排列if，减少if次数
         */
        if (fieldType == LocalDateTime.class) {
            data = LocalDateTime.now();
        } else if (fieldType == Date.class) {
            data = new Date();
        } else if (fieldType == LocalDate.class) {
            data = LocalDate.now();
        } else if (fieldType == OffsetDateTime.class) {
            data = OffsetDateTime.now();
        } else if (fieldType == OffsetTime.class) {
            data = OffsetTime.now();
        }
        return data;
    }

    /**
     * 把java bean的属性和值都取出来放到Map中返回
     *
     * @return
     */
    public static Map<String, Object> beanToMap(Object obj)
            throws InvocationTargetException, IllegalAccessException {
        Assert.notNull(obj, "obj must not be null");
        PropertyDescriptor[] targetPds = getPropertyDescriptors(obj.getClass());
        return getMap(obj, targetPds);
    }

    private static Map<String, Object> getMap(Object obj, PropertyDescriptor[] targetPds)
            throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> data = new HashMap<>(targetPds.length);
        for (PropertyDescriptor targetPd : targetPds) {
            Method method = targetPd.getReadMethod();
            method.setAccessible(true);
            data.put(targetPd.getName(), method.invoke(obj));
        }
        return data;
    }

}
