package com.utils.httputils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HttpUtils<ResponseObj> {

    public ResponseObj callREST(String url, Object requestObj, ResponseObj responseObj) throws Exception {
        String request = JSON.toJSONString(requestObj);
        String response = httpPostWithJSON(url, JSON.toJSONString(requestObj));
        return (ResponseObj) JSON.parseObject(response, responseObj.getClass());
    }

    public static String httpPostWithJSON(String url, String jsonStr) throws IOException, ClientProtocolException {
        return httpPostWithJSON(url, jsonStr, "utf-8");
    }

    public static String httpPostWithJSON(String url, String jsonStr, String charset) throws IOException, ClientProtocolException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        StringEntity entity = new StringEntity(jsonStr, charset);//解决中文乱码问题
        entity.setContentEncoding(charset);
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, charset);
        }
        return respContent;
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (!(ip == null||ip.equals("")) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (!(ip == null||ip.equals("")) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
