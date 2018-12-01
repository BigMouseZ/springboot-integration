package com.wxpay.wxlogin_domain;


import com.wxpay.err.BusinessException;

/**
 * Created by Chenzq on 2018/3/1.
 */
public interface IDomain_WxLogin {

    /**
     * 登陆
     *
     * @param code          登陆兑换码
     * @param encryptedData 敏感信息
     * @param iv            解密偏移向量
     * @return sessionID
     */
    String login(String code, String encryptedData, String iv) throws Exception;

    ;

    /**
     * 获取openid
     *
     * @param sessionId 自定义登录态
     * @return openid
     */
    String getOpenId(String sessionId) throws BusinessException;

    /**
     * 获取Unionid
     *
     * @param sessionId 自定义登录态
     * @return Unionid
     */
    String getUnionid(String sessionId) throws BusinessException;

    ;
}
