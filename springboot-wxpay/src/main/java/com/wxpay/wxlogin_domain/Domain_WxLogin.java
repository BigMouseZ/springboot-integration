package com.wxpay.wxlogin_domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.wxpay.err.BusinessException;
import com.wxpay.util.HttpUtils;
import com.wxpay.util.UUIDUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by Chenzq on 2018/3/6.
 */
@Slf4j
@Component
@Configuration
public class Domain_WxLogin implements IDomain_WxLogin {

    @Value("${wxapp.login.url}")
    private String loginUrl;
    @Value("${wxapp.sessionTtl}")
    private long ttl;
    @Value("${wxapp.appId}")
    private String appId;
    @Value("${wxapp.appKey}")
    private String appSecret;
    @Autowired
    RedisTemplate<String,String> mRedisTemplate;
    /**
     * 登陆
     * https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html#wxloginobject
     * https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
     * @param code 登陆兑换码
     * @return
     */
    @Override
    public String login(String code,String encryptedData, String iv) throws Exception {
        String url = String.format(loginUrl, appId, appSecret, code);
        String json = HttpUtils.httpPostWithJSON(url, "");
        LoginResponseTo loginResponseTo = JSON.parseObject(json, LoginResponseTo.class);
        if (loginResponseTo.getOpenid() == null) {
            log.info("微信登陆失败");
            log.info("失败描述："+loginResponseTo.getErrmsg());
            throw new BusinessException("微信登陆失败",BusinessException.BusinessExceptionCode.RemoteServerException, "微信登陆失败");
        }
        JSONObject userInfo = getUserInfo( encryptedData,loginResponseTo.getSession_key(),iv);
        if(userInfo!=null){
            String unionId = userInfo.get("unionId").toString();
            loginResponseTo.setUnionid(unionId);
        }
        log.info("微信登录成功！");
        String sessionId = creatSessionId();
        saveLoginResponse(sessionId, loginResponseTo, ttl);
        return sessionId;
    }
    private static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            byte[] resultByte = decrypt(dataByte,keyByte,ivByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    private static byte[] decrypt(byte[] encryptedData, byte[] sessionkey, byte[] iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(sessionkey, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(encryptedData);
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
        mRedisTemplate.opsForValue().set("session_"+sessionId,JSON.toJSONString(loginResponseTo));
        mRedisTemplate.expire("session_"+sessionId,ttl, TimeUnit.SECONDS);

    }

    private LoginResponseTo loadLoginInfo(String sessionId) {
        return JSON.parseObject(mRedisTemplate.opsForValue().get("session_" + sessionId), LoginResponseTo.class);
    }

    private String creatSessionId() {
        return UUIDUtil.newOne();
    }

    @Data
    public static class LoginResponseTo {
        private String openid;
        private String session_key;
        private String unionid;
        private String errcode;
        private String errmsg;
    }
}
