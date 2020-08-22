package com.utils.httputils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Project: expressway
 * @Author: fengzy
 * @Description:
 * @Date:Create：in 2020/6/18 22:47
 * @Modified By：fengzy
 */
public class HttpRequestPassUtil {
    public static byte[] downImage(String url){
        if(url != null){
            CloseableHttpClient httpclient = HttpClients.createDefault();
            try{
                RequestConfig config = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(2000).build();//设置请求和传输超时时间

                HttpGet httpget = new HttpGet(url);
                httpget.setConfig(config);
                HttpResponse response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();
                InputStream in = entity.getContent();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int l = -1;
                byte[] tmp = new byte[1024];
                while ((l = in.read(tmp)) != -1) {
                    out.write(tmp, 0, l);
                }
                // 关闭低层流。
                in.close();
//		        httpclient.close();
                return out.toByteArray();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
