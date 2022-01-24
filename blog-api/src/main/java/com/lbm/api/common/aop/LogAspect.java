package com.lbm.api.common.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Slf4j
@Component
public class LogAspect {
//    @Pointcut("@annotation(com.lbm.api.common.aop.LogAnnotation)")
    @Pointcut("execution(public * com.lbm.api.controller.*.*(..))")
    public void logPointCut() {
    }
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Long beginTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        Long time = System.currentTimeMillis()-beginTime;
        recordLog(time,joinPoint,proceed);
        return proceed;
    }

    private void recordLog(Long time, ProceedingJoinPoint joinPoint,Object proceed) {
        //获取类名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        Method method = signature.getMethod();
        String methodName = method.getName();

        //获取参数名
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        log.info("==={}===:{}.{}执行",df.format(new Date()),className,methodName);
        log.info("使用参数:{}",params);
        log.info("返回值:{}",JSON.toJSONString(proceed));
        log.info("耗时:{}ms",time);
        log.info("===end===");


    }

}
