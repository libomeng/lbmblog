package com.lbm.api.common.cache;

import com.alibaba.fastjson.JSON;
import com.lbm.api.service.RedisService;
import com.lbm.api.vo.Result;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Component
@Slf4j
@Aspect
public class CacheAspect {

    @Autowired
    RedisTemplate jsonRedisTemplate;

    @Autowired
    RedisService redisService;

    @Pointcut("@annotation(com.lbm.api.common.cache.Cache)")
//    @Pointcut("execution(public * com.lbm.api.controller.*.*(..))")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point)  {

        Signature signature = point.getSignature();
       MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        Object[] parameters = point.getArgs();
        String param ="";
        for (Object parameter : parameters) {
            param += JSON.toJSONString(parameter);
        }
        if(!StringUtil.isNullOrEmpty(param)){
             param = DigestUtils.md5Hex(param);
        }
        try {
            Method method = methodSignature.getMethod();
//
            Type resultType = method.getAnnotatedReturnType().getType();
        Cache annotation = method.getAnnotation(Cache.class);
        long expire = annotation.expire();
        String name = annotation.name();
        String key = className+"."+methodName+"."+name;
//        String value = (String) jsonRedisTemplate.opsForValue().get(key);
            Object entity = redisService.getEntity(key, param);
            if(entity != null){
            log.info("{}.{}使用缓存", className,methodName);
            return entity;
        }

        //原生方法执行
        Object proceed = point.proceed();
//            String result = JSON.toJSONString(proceed);
//            jsonRedisTemplate.opsForValue().set(key,result,expire, TimeUnit.MILLISECONDS);
            redisService.setEntity(key,param,proceed);
        log.info("{}.{}结果写入缓存",className,methodName);
        return proceed;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return Result.fail(-999, "系统错误");
    }

}
