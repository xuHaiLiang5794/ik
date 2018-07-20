package com.xuhailiang5794.ik.business.enterprise.query;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xuhailiang5794.ik.business.enterprise.entity.EnterpriseList;
import com.xuhailiang5794.ik.support.annotation.Index;
import com.xuhailiang5794.ik.utils.BeanUtils;
import com.xuhailiang5794.ik.utils.HttpUtils;
import com.xuhailiang5794.ik.utils.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/20
 */
public class EnterpriseListQuery {
    private JSONObject object;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        JSONObject object = new EnterpriseListQuery().query(1);
    }

    public List<EnterpriseList> getEnterpriseList() throws IllegalAccessException, InstantiationException {
        JSONArray array = object.getJSONArray("data");
        List<EnterpriseList> enterpriseLists = null;
        if (array != null && !array.isEmpty()) {
            enterpriseLists = new ArrayList<>(array.size());
            Class clazz = EnterpriseList.class;
            Field[] fields = clazz.getDeclaredFields();
            Field.setAccessible(fields, true);
            for (Object o : array) {
                Object obj = clazz.newInstance();
                String[] arr = o.toString().split(",");
                for (Field field : fields) {
                    Annotation annotation = field.getAnnotation(Index.class);
                    if (annotation != null) {
                        BeanUtils.setFieldValue(obj, field, arr[((Index) annotation).index()]);
                    }
                }
                BeanUtils.setDataTimeOfBean(obj);
                enterpriseLists.add((EnterpriseList) obj);
            }
        }
        return enterpriseLists;
    }

    public JSONObject query(Integer currPage) {
        List<NameValuePair> valuePairs = new ArrayList<>();
        valuePairs.add(new BasicNameValuePair("cb", "jQuery1124006683771404178729_1532055283823"));
        valuePairs.add(new BasicNameValuePair("type", "CT"));
        valuePairs.add(new BasicNameValuePair("token", "4f1862fc3b5e77c150a2b985b12db0fd"));
        valuePairs.add(new BasicNameValuePair("js", "(%7Bdata%3A%5B(x)%5D%2CrecordsTotal%3A(tot)%2CrecordsFiltered%3A(tot)%7D)"));
        valuePairs.add(new BasicNameValuePair("cmd", "C._A"));
        valuePairs.add(new BasicNameValuePair("sty", "FCOIATC"));
        valuePairs.add(new BasicNameValuePair("st", "(ChangePercent)"));
        valuePairs.add(new BasicNameValuePair("sr", "-1"));
        valuePairs.add(new BasicNameValuePair("p", String.valueOf(currPage)));
        valuePairs.add(new BasicNameValuePair("ps", "20"));
        valuePairs.add(new BasicNameValuePair("_", "1532055283830"));
        String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx";
        String result = HttpUtils.get(url, valuePairs);
        result = result.replace("jQuery1124006683771404178729_1532055283823(", "");
        result = StringUtils.unEscape(result);
        result = result.substring(0, result.length() - 1);
        JSONObject dataJsonObject = JSONObject.parseObject(result);
        Integer recordsTotal = dataJsonObject.getInteger("recordsTotal");
        Double pages = Math.ceil(recordsTotal == null ? 0 : recordsTotal / 1.0 / 20);
        dataJsonObject.put("pages", pages.intValue());
        object = dataJsonObject;
        return dataJsonObject;
    }
}
