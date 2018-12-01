package com.wxpay.wxpay_domain.server;

import com.wxpay.sdk.WXPay;
import com.wxpay.sdk.WXPayConfigImpl;
import com.wxpay.sdk.WXPayUtil;
import com.wxpay.wxpay_domain.entity.wxpay.PayOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.wxpay.sdk.WXPayConstants.SUCCESS;

@Slf4j
@Component
public class Domain_WxPay implements IDomain_WxPay {
    @Value("${domain_wxPay.isSendBox}")
    private boolean isSendBox;
    @Value("${domain_wxPay.notifyUrl}")
    private String notifyUrl;
    @Autowired
    WXPayConfigImpl wxPayConfig;

    /**
     * 作用：统一下单<br>
     * 场景：公共号支付、扫码支付、APP支付
     * @throws Exception
     */
    public Map<String, String> unifiedOrder(PayOrder payOrder) throws Exception {
        WXPay wxpay = new WXPay(wxPayConfig, notifyUrl,true, isSendBox);
        payOrder.setTrade_type("JSAPI");
        Map<String, String> payOrderMap = WXPayUtil.objectToMap(payOrder);
        log.debug("\n"+ WXPayUtil.mapToXml(payOrderMap));
        log.info("发起微信支付下单接口, request={}", payOrderMap);
        Map<String, String> response = wxpay.unifiedOrder(payOrderMap);
        log.info("微信支付下单成功, 返回值 response={}", response);
       // response.put("out_trade_no", payOrder.getOut_trade_no());
        String returnCode = response.get("return_code");
        if (!SUCCESS.equals(returnCode)) {
            return null;
        }
        String resultCode = response.get("result_code");
        if (!SUCCESS.equals(resultCode)) {
            return null;
        }
        String prepay_id = response.get("prepay_id");
        if (prepay_id == null) {
            return null;
        }
        // ******************************************
        //  前端调起微信支付必要参数
        // ******************************************
        String packages = "prepay_id=" + prepay_id;
        Map<String, String> wxPayMap = new HashMap<>();
        wxPayMap.put("appId", wxPayConfig.getAppID());
        wxPayMap.put("timeStamp", String.valueOf(WXPayUtil.getCurrentTimestamp()));
        wxPayMap.put("nonceStr", WXPayUtil.generateNonceStr());
        wxPayMap.put("package", packages);
        wxPayMap.put("signType", "MD5");
        // 加密串中包括 appId timeStamp nonceStr package signType 5个参数, 通过sdk WXPayUtil类加密, 注意, 此处使用  MD5加密  方式
        String paySign = WXPayUtil.generateSignature(wxPayMap, wxPayConfig.getKey());
        // ******************************************
        //  返回给前端调起微信支付的必要参数
        // ******************************************
        Map<String, String> result = new HashMap<>();
        result.put("paySign", paySign);
        result.putAll(wxPayMap);
        return result;
    }
    /**
     * 作用：查询订单<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付
     * @param //transaction_id 微信的订单号，优先使用
     * @param out_trade_no 商户订单号
     *  微信订单查询，优先使用微信订单号
     */
    public Map<String, String> orderQuery(String out_trade_no,boolean transaction) throws Exception {

        Map<String, String> result = new HashMap<>();
        WXPay wxpay = new WXPay(wxPayConfig, notifyUrl,true, isSendBox);
        Map<String, String> queryOrderMap = new HashMap<>();
        if(transaction){
            queryOrderMap.put("transaction_id",out_trade_no);
        }else {
            queryOrderMap.put("out_trade_no",out_trade_no);
        }
        log.info("发起微信订单查询接口, request={}", queryOrderMap);
        Map<String, String> response = wxpay.orderQuery(queryOrderMap);
        log.info("微信订单查询成功, 返回值 response={}", response);
        String returnCode = response.get("return_code");
        if (!SUCCESS.equals(returnCode)) {
            return null;
        }
        String resultCode = response.get("result_code");
        if (!SUCCESS.equals(resultCode)) {
            return null;
        }
        return result;
    }

}
