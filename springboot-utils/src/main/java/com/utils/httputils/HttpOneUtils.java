package com.utils.httputils;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * http请求工具
 */
@Slf4j
public final class HttpOneUtils {

    /**
     * 发送JSON到服务器
     *
     * @param url    服务器地址
     * @param data   JSON数据
     * @param header 头属性实体
     * @return 返回结果
     */
    public static String postJsonString(String url, String data, Header header, RequestConfig requestConfig) {
        CloseableHttpResponse response = null;
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringEntity requestEntity = new StringEntity(data, ContentType.create("application/json", "utf-8"));
        HttpPost postMethod = new HttpPost(url);
        postMethod.setConfig(requestConfig);
        postMethod.setEntity(requestEntity);
        if (header != null) {
            postMethod.setHeader(header);
        }
        try {
            response = httpClient.execute(postMethod);
            HttpEntity entity = response.getEntity();
            Charset charset = ContentType.getOrDefault(entity).getCharset();
            result = EntityUtils.toString(entity, charset);

        } catch (Exception e) {
            log.error(e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.toString(), e);
            }
        }
        return result;
    }

    /**
     * 发送JSON到服务器
     *
     * @param url    服务器地址
     * @param data   JSON数据
     * @param header 头属性实体
     * @return 返回结果
     */
    public static String postJsonString(String url, String data, Header header) {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置超时时间
        /*setConnectTimeout：设置连接超时时间，单位毫秒。
        setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
        setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。*/
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(30000)
                .build();
        StringEntity requestEntity = new StringEntity(data, ContentType.create("application/json", "utf-8"));
        HttpPost postMethod = new HttpPost(url);
        postMethod.setConfig(requestConfig);
        postMethod.addHeader("Content-Type", "application/json;charset=UTF-8");
        postMethod.setEntity(requestEntity);
        if (header != null) {
            postMethod.setHeader(header);
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(postMethod);
            HttpEntity entity = response.getEntity();
            Charset charset = ContentType.getOrDefault(entity).getCharset();
            result = EntityUtils.toString(entity, charset);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.toString(), e);
            }
        }
        return result;
    }

    /**
     * 发送JSON到服务器
     *
     * @param url 服务器地址
     * @return 返回结果
     */
    public static String postJsonString(String url, Map<String, Object> parameters) {
        CloseableHttpResponse response = null;
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置超时时间
        /*setConnectTimeout：设置连接超时时间，单位毫秒。
        setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
        setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。*/
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(7000)
                .build();

        String data = JSON.toJSONString(parameters);
        String encodeParameters = null;
        try {
            encodeParameters = URLEncoder.encode(data, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[http请求，参数编码失败]");
        }
        StringEntity requestEntity = new StringEntity(encodeParameters, ContentType.create("application/json", "GBK"));
        HttpPost postMethod = new HttpPost(url);
        postMethod.setConfig(requestConfig);
        postMethod.addHeader("Content-Type", "application/json;charset=GBK");
        postMethod.setEntity(requestEntity);
        try {
            response = httpClient.execute(postMethod);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.toString(), e);
            }
        }
        return result;
    }

    /**
     * 发送POST请求
     *
     * @param url        目的地址
     * @param parameters 请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendPostBody(String url, Map<String, String> parameters) {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            params = JSON.toJSONString(parameters);
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            httpConn.setRequestProperty("Charset", "UTF-8");
            httpConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.toString(), e);

            }
        }
        return result;
    }


    public static String postFileMultiPart(String url, Map<String, ContentBody> reqParam, Header header) throws Exception {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpPost httppost = new HttpPost(url);
            //setConnectTimeout：设置连接超时时间，单位毫秒。setConnectionRequestTimeout：
            // 设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
            // setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(15000).build();
            httppost.setConfig(defaultRequestConfig);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            for (Map.Entry<String, ContentBody> param : reqParam.entrySet()) {
                multipartEntityBuilder.addPart(param.getKey(), param.getValue());
            }
            if (header != null) {
                httppost.setHeader(header);
            }
            HttpEntity reqEntity = multipartEntityBuilder.build();
            httppost.setEntity(reqEntity);
            // 执行post请求.
            response = httpclient.execute(httppost);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, Charset.forName("UTF-8"));
            }
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.toString(), e);
            }
        }
        return null;
    }

    /**
     * 发送GET请求
     *
     * @param url 请求url
     * @return JSON或者字符串
     * @throws Exception
     */
    public static String sendGet(String url, List<Header> headerList, RequestConfig requestConfig, String defaultCharset) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            //创建HttpClient对象
            client = HttpClients.createDefault();
            //创建URIBuilder
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setConfig(requestConfig);
            if (headerList != null && headerList.size() > 0) {
                for (Header header : headerList) {
                    httpGet.setHeader(header);
                }
            }
            //设置请求头部编码
//            httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=GBK"));
            //设置返回编码
//            httpGet.setHeader(new BasicHeader("Accept", "*/*"));
            //请求服务
            response = client.execute(httpGet);
            //获取响应吗
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, defaultCharset);
                return result;
            } else {
                log.error("HttpClientService-line: {}, errorMsg{}", 97, "GET请求失败！");
            }
        } catch (Exception e) {
            log.error("HttpClientService-line: {}, Exception: {}", 100, e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "GBK"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "GBK"));
            result.append("&");
        }
        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }
}
