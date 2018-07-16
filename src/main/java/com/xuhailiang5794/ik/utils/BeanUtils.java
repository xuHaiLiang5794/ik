package com.xuhailiang5794.ik.utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Date;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/16 14:59
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 给bean设置数据时间
     * @param obj
     */
    public static void setDataTimeOfBean(Object obj) {
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
}
