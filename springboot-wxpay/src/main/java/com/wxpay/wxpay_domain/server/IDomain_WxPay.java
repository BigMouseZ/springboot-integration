package com.wxpay.wxpay_domain.server;


import com.wxpay.wxpay_domain.entity.wxpay.PayOrder;

import java.util.Map;

/**
 * 微信支付领域
 */
public interface IDomain_WxPay {

    Map<String, String> unifiedOrder(PayOrder payOrder) throws Exception;
    Map<String, String> orderQuery(String out_trade_no, boolean transaction)throws Exception;
}
