package com.xuhailiang5794.ik.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/19
 */
@Slf4j
public class HttpUtils {

    private static CloseableHttpClient httpclient = HttpClients.custom().build();

    public static String get(String url) {
        return get(url, null);
    }

    public static String get(String url, List<NameValuePair> nvps) {
        String result = null;
        if (nvps != null && nvps.size() > 0) {
            String param = URLEncodedUtils.format(nvps, "utf-8");
            url = url + "?" + param;
        }
        HttpGet httpget = new HttpGet(url);
        log.info("HttpUtils.get:{}", url);
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
        CloseableHttpResponse httpResult = null;
        try {
            httpResult = httpclient.execute(httpget);
            HttpEntity entity = httpResult.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
        } catch (Exception e) {
        } finally {
            try {
                if (httpResult != null) {
                    httpResult.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String post(String url, List<NameValuePair> nvps) {
        log.info("HttpUtils.post:{}", url);
        String result = null;
        CloseableHttpResponse httpResult = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            httpResult = httpclient.execute(httpPost);

            HttpEntity entity = httpResult.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
            log.info(result);
            EntityUtils.consume(entity);
        } catch (Exception e) {
        } finally {
            try {
                if (httpResult != null) {
                    httpResult.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static String download(String url, String filepath) {
        try {
            System.out.println(url);
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = client.execute(httpget);

            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            File file = new File(filepath);
            file.getParentFile().mkdirs();
            FileOutputStream fileout = new FileOutputStream(file);
            /**
             * 根据实际运行效果 设置缓冲区大小
             */
            byte[] buffer = new byte[1024];
            int ch = 0;
            while ((ch = is.read(buffer)) != -1) {
                fileout.write(buffer, 0, ch);
            }
            is.close();
            fileout.flush();
            fileout.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
