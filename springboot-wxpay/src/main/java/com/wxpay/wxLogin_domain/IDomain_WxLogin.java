package com.wxpay.wxLogin_domain;


import com.wxpay.err.BusinessException;

/**
 * Created by Chenzq on 2018/3/1.
 */
public interface IDomain_WxLogin {

    /**
     * 登陆
     * @param code 登陆兑换码
     * @return sessionID
     */
    String login(String code) throws Exception;;
    /**
     * 获取openid
     * @param sessionId 自定义登录态
     * @return openid
     */
    String getOpenId(String sessionId) throws BusinessException;;
    /**
     * 获取Unionid
     * @param sessionId 自定义登录态
     * @return Unionid
     */
    String getUnionid(String sessionId) throws BusinessException;;
}
