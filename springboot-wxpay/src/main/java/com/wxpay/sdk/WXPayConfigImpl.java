package com.wxpay.sdk;

import com.wxpay.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhangGang on 2018/11/23.
 */
@Slf4j
@Component
public class WXPayConfigImpl extends WXPayConfig {
    @Value("${wxapp.appId}")
    public String appID;
    @Value("${wxpay.payMch}")
    public String payMch;
    @Value("${wxapp.appKey}")
    public String appKey;
    @Value("${wxpay.payKey}")
    public String payKey;
    @Value("${domain_wxPay.isSendBox}")
    private boolean isSendBox;


    @Override
    public String getAppID() {
        return appID;
    }

    @Override
    public String getMchID() {
        return payMch;
    }

    @Override
    public String getKey() {
        if (isSendBox) {
            return getSendBoxKey();
        }
        return payKey;
    }
    private String getSendBoxKey() {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("mch_id", getMchID());
            map.put("nonce_str", WXPayUtil.generateNonceStr());
            map.put("sign", WXPayUtil.generateSignature(map, payKey));
            String xml = WXPayUtil.mapToXml(map);
            xml = HttpUtils.httpPostWithJSON("https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey", xml);
            map = WXPayUtil.xmlToMap(xml);
            if (map.containsKey("return_code") && map.containsKey("sandbox_signkey")) {
                return map.get("sandbox_signkey");
            } else {
                log.error(xml);
            }
        } catch (Exception e) {
            log.error("SendBoxGetKey", e);
        }
        return null;
    }
    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public IWXPayDomain getWXPayDomain() { // 这个方法需要这样实现, 否则无法正常初始化WXPay
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }


}
