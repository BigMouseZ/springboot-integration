package com.wxpay.wxLogin_domain;

import com.alibaba.fastjson.JSON;
import com.utils.redisutils.RedisItemUtil;
import com.wxpay.err.BusinessException;
import com.wxpay.util.HttpUtils;
import com.wxpay.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Chenzq on 2018/3/6.
 */
@Component
@Configuration
public class Domain_WxLogin implements IDomain_WxLogin {

    @Value("${wxapp.login.url}")
    private String loginUrl;
    @Value("${wxapp.sessionTtl}")
    private long ttl = 3600;
    @Value("${wxapp.appId}")
    private String appId;
    @Value("${wxapp.appKey}")
    private String appSecret;
    @Autowired
    RedisItemUtil redisItemUtil;
    /**
     * 登陆
     * https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html#wxloginobject
     * https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
     * @param code 登陆兑换码
     * @return
     */
    @Override
    public String login(String code) throws Exception {
        String url = String.format(loginUrl, appId, appSecret, code);
        String json = HttpUtils.httpPostWithJSON(url, "");
        LoginResponseTo loginResponseTo = JSON.parseObject(json, LoginResponseTo.class);
        if (loginResponseTo.getOpenid() == null) {
            System.out.println("微信登陆失败");
            System.out.println("失败描述："+loginResponseTo.getErrmsg());
            throw new BusinessException("微信登陆失败",BusinessException.BusinessExceptionCode.RemoteServerException, "微信登陆失败");
        }
        String sessionId = creatSessionId();
        saveLoginResponse(sessionId, loginResponseTo, ttl);
        return sessionId;
    }

    @Override
    public String getOpenId(String sessionId) throws BusinessException {
        LoginResponseTo info = loadLoginInfo(sessionId);
        if (info != null)
            return info.getOpenid();
        throw new BusinessException(BusinessException.BusinessExceptionCode.SessionNotFound, "获取openid失败，找不到对应session");
    }

    @Override
    public String getUnionid(String sessionId) throws BusinessException {
        LoginResponseTo info = loadLoginInfo(sessionId);
        if (info != null)
            return info.getUnionid();
        throw new BusinessException(BusinessException.BusinessExceptionCode.SessionNotFound, "获取unionid失败，找不到对应session");
    }

    /**
     * 存储登陆信息
     * @param loginResponseTo
     * @param ttl 过期时间
     */
    private void saveLoginResponse(String sessionId,LoginResponseTo loginResponseTo, long ttl) {
        redisItemUtil.setByKey("session_"+sessionId,JSON.toJSONString(loginResponseTo));
        redisItemUtil.expire("session_"+sessionId,ttl);

    }

    private LoginResponseTo loadLoginInfo(String sessionId) {
        return JSON.parseObject(redisItemUtil.getByKey("session_" + sessionId), LoginResponseTo.class);
    }

    private String creatSessionId() {
        return UUIDUtil.newOne();
    }

    public static class LoginResponseTo {
        private String openid;
        private String session_key;
        private String unionid;
        private String errcode;
        private String errmsg;

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getSession_key() {
            return session_key;
        }

        public void setSession_key(String session_key) {
            this.session_key = session_key;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public String getErrcode() {
            return errcode;
        }

        public void setErrcode(String errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }
}
