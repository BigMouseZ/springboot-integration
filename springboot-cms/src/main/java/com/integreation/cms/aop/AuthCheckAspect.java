package com.integreation.cms.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.integreation.cms.entity.cms.cmsentity.AuthorityEntity;
import com.integreation.cms.entity.cms.cmsentity.CmsRedisKey;
import com.integreation.cms.entity.response.RequestVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.utils.SpringContextUtils;
import com.integreation.cms.utils.redis.redisutils.RedisItemUtil;
import com.integreation.cms.utils.redis.redisutils.RedisMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by ZhangGang on 2019/7/18.
 */
@Slf4j
@Aspect
@Component
public class AuthCheckAspect {
    @Autowired
    private RedisMapUtil redisMapUtil;
    @Autowired
    private RedisItemUtil redisItemUtil;
    @Autowired
    private SpringContextUtils springContextUtils;

    @Pointcut(value = "@annotation(com.integreation.cms.aop.AuthCheck)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AuthCheck annotation = method.getAnnotation(AuthCheck.class);
        boolean checkSessionid = annotation.checkSessionid();
        boolean checkAuth = annotation.checkAuth();
        boolean datacheck = annotation.datacheck();
        boolean saveLog = annotation.saveLog();
        Object[] arguments = joinPoint.getArgs();
        RequestVo requestVo = JSONObject.parseObject(JSON.toJSONString(arguments[0]), new TypeReference<RequestVo>() {
        });
        // 登录校验
        if (checkSessionid) {
            if (requestVo == null) {
                log.error("参数格式错误：{}", JSON.toJSONString(arguments[0]));
                responseVo.setCode(ResponseVo.ResponseErrCode.LoginFail);
                responseVo.setMessage("参数格式错误");
                return responseVo;
            }
            if (requestVo.getSessionId() == null) {
                log.error("登录状态验证-参数无效没有sessionid");
                responseVo.setCode(ResponseVo.ResponseErrCode.LoginFail);
                responseVo.setMessage("登录状态验证-参数无效没有sessionid");
                return responseVo;
            }
            boolean login = redisItemUtil.exists(CmsRedisKey.USER_LOGIN_INFO_TABLE + ":" + requestVo.getSessionId());
            if (!login) {
                //没有登录
                log.info("没有通过登录效验，或已过期");
                responseVo.setCode(ResponseVo.ResponseErrCode.LoginFail);
                responseVo.setMessage("没有通过登录效验，或已过期");
                return responseVo;
            } else {
                //登录效验通过
//                log.info("通过登录效验");
                //刷新时效
//                redisItemUtil.expire(CmsRedisKey.USER_LOGIN_INFO_TABLE + ":" + requestVo.getSessionId(), 7200);
            }
        }
        //权限校验
        if (checkAuth) {
            if (requestVo.getSessionId() == null) {
                log.error("权限验证-参数无效没有sessionId");
                responseVo.setMessage("权限验证-参数无效没有sessionId");
                return responseVo;
            } else {
                //进行全新校验
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                String url = request.getRequestURI();
                String reurl = url.substring(1);
                AuthorityEntity userAuthority = redisMapUtil.getByKey(requestVo.getSessionId(), reurl);
                if (userAuthority == null) {
                    log.info("接口{}权限验证，验证失败",url);
                    responseVo.setMessage("权限验证，验证失败");
                    return responseVo;
                } else {
                    log.debug("接口{}权限验证，验证通过",url);
                }
            }
        }
        //数据校验
        if (datacheck) {
            try {
                Object target = springContextUtils.getBean("check." + joinPoint.getTarget().getClass().getSimpleName());
                log.info("方法名：{}", method.getName());
                Method get = ReflectionUtils.findMethod(target.getClass(), method.getName(), RequestVo.class);
                String checkre = (String) ReflectionUtils.invokeMethod(get, target, requestVo);
                if (checkre != null) {
                    log.debug("数据校验不通过！");
                    responseVo.setMessage(checkre);
                    return responseVo;
                } else {
                }
            } catch (Exception e) {
                log.error("数据校验异常：", e);
            }
        }
        //权限校验
        if (saveLog) {
          //TODO 日志记录
        }
        try {
            //执行主程序
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("执行异常：", e);
            responseVo.setCode(ResponseVo.ResponseErrCode.ServerErr);
            responseVo.setMessage("执行异常");
            return responseVo;
        }
    }
}
