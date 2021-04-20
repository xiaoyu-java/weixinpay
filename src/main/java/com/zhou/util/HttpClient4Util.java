package com.zhou.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2020-09-29.
 * <p>
 * Author: zhukang
 * <p>
 * Description: http请求工具类
 */
public final class HttpClient4Util {
    /**
     * 注释内容
     */
    private static Logger log = LoggerFactory.getLogger(HttpClient4Util.class);

    /**
     * 连接超时时间
     */
    public static final int CONNECT_TIMEOUT = 10000;

    /**
     * 读取超时时间
     */
    public static final int SO_TIMEOUT = 30000;

    private HttpClient4Util() {
    }

    /**
     * <HttpClient直接连接接口> <功能详细描述>
     *
     * @param url      接口URL
     * @param params   NameValuePair参数
     * @param encoding 编码
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getResponse4PostByMap(String url, Map<String, Object> params, String encoding) {
        return post(url, params, encoding);
    }

    /**
     * <HttpClient直接连接接口> <功能详细描述>
     *
     * @param url      接口URL
     * @param params   NameValuePair参数
     * @param encoding 编码
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getResponse4PostByString(String url, String params, String encoding) {
        return post(url, params, encoding);
    }

    /**
     * <HttpClient直接连接接口> <功能详细描述>
     *
     * @param url
     * @param encoding
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static String getResponse4GetAsString(String url, String encoding) {
        return get(url, encoding);
    }

    /**
     * HttpClient直接连接接口，直接返回数据
     *
     * @param url 接口URL
     * @param params NameValuePair参数
     * @param encoding 编码
     * @return
     * @throws Exception
     */
    private static String post(String url, Map<String, Object> params, String encoding) {
        log.info("执行Http Post请求,地址: " + url + ",参数: " + params);

        String response = null;
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);

            List<NameValuePair> nvps = new ArrayList<>(params.size());
            if (!params.isEmpty()) {
                Set<Entry<String, Object>> entrySet = params.entrySet();
                for (Entry<String, Object> entry : entrySet) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
            }

            // 请求方式为key=value的方式
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.addHeader("Accept-Language", "zh-cn");
            // 及时释放连接，不缓存连接(防止close_wait)
            httpPost.addHeader("Connection", "close");

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SO_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
            httpPost.setConfig(requestConfig);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                response = EntityUtils.toString(httpEntity, encoding).replaceAll("\r\n", "");
                EntityUtils.consume(httpEntity);
            }

            httpPost.abort();
        } catch (Exception e) {
            log.error("执行Http Post请求失败! Exception: " + e.getMessage());
        } finally {
            if(null != httpClient){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("Http Post执行后响应内容： " + response);
        return response;
    }

    /**
     * HttpClient直接连接接口，直接返回数据，
     *
     * @param url 接口URL
     * @param params xml字符串参数
     * @param encoding 编码
     * @return
     * @throws Exception
     */
    private static String post(String url, String params, String encoding) {
        log.info("执行Http Post请求,地址: " + url + ",参数: " + params);

        String response = null;
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SO_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
            httpPost.setConfig(requestConfig);

            StringEntity postEntity = new StringEntity(params, "UTF-8");

            //httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            // httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Content-Type", "text/xml");
            // 及时释放连接，不缓存连接(防止close_wait)
            httpPost.addHeader("Connection", "close");
            httpPost.setEntity(postEntity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                response = EntityUtils.toString(httpEntity, encoding).replaceAll("\r\n", "");
                EntityUtils.consume(httpEntity);
            }

            httpPost.abort();
        } catch (Exception e) {
            log.error("执行Http Post请求失败! Exception: " + e.getMessage());
        } finally {
            if(null != httpClient){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("Http Post执行后响应内容： " + response);
        return response;
    }


    /**
     * HttpClient直接连接接口，直接返回数据
     *
     * @param url 接口URL
     * @param encoding 编码
     * @return
     * @throws Exception
     */
    private static String get(String url, String encoding) {
        log.info("执行Http get请求,地址: " + url );

        String response = null;
        HttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SO_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
            httpGet.setConfig(requestConfig);

            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpGet.addHeader("Accept-Language", "zh-cn");
            // 及时释放连接，不缓存连接(防止close_wait)
            httpGet.addHeader("Connection", "close");


            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                response = EntityUtils.toString(httpEntity, encoding).replaceAll("\r\n", "");
                EntityUtils.consume(httpEntity);
            }

            httpGet.abort();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("执行Http GET请求失败! Exception: " + e.getMessage());
        }
        log.info("Http GET执行后响应内容： " + response);
        return response;
    }

}
