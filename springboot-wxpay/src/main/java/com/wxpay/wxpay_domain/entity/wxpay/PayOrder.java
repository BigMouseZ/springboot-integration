package com.wxpay.wxpay_domain.entity.wxpay;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * 微信支付，微信支付商户统一下单实体类
 */
@Data
@Builder
public class PayOrder {
	//微信分配的公众账号ID（配置填写）
	private String appid; 
	//微信支付分配的商户号（配置填写）
	private String mch_id;
	//随机字符串，不长于32位（配置填写）
	private String nonce_str ;
	//签名（配置填写）
	private String sign;
	//商品简单描述 不长于128位（接口调用必填）
	//规则：https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_2
	@NonNull
	private String body;
	//商品详细 不长于6000位,商品详细列表，使用Json格式，传输签名前请务必使用CDATA标签将JSON文本串保护起来。可以不填
	//private String detail;
	//商户订单号 32个字符内 建议根据当前系统时间加随机序列来生成订单号（接口调用必填）
	@NonNull
	private String out_trade_no;
	//订单总金额，单位为分（接口调用必填）
	private String total_fee;
	 //APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP （接口调用必填）
	 @NonNull
	private String spbill_create_ip;
	//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。（配置填写）
	private String notify_url;
	//交易类型 取值如下：JSAPI，NATIVE，APP （配置填写）
	private String trade_type;
	//指定支付方式  no_credit--指定不能使用信用卡支付，可以不填
	//private String limit_pay;
	//用户标识，trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取 （接口调用必填）
	@NonNull
	private String openid;
}
