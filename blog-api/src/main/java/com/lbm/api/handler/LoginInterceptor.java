package com.lbm.api.handler;

import com.alibaba.fastjson.JSON;
import com.lbm.api.service.SysUserService;
import com.lbm.api.util.UserThreadLocal;
import com.lbm.api.vo.ErrorCode;
import com.lbm.api.vo.LoginVo;
import com.lbm.api.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    SysUserService sysUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("Token");
        log.info("=================request start===========================");
        log.info("request uri:{}",request.getRequestURI());
        log.info("request IP:{}", request.getRemoteAddr());
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");
        if (token == null) {
            response.setContentType("application/json;charset=utf-8");
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.getWriter().write(JSON.toJSONString(result));
            return false;
        }
        Result result = sysUserService.currentUser(token);
        if (!result.getSuccess()){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(result));
            return false;
        }
        LoginVo loginVo = (LoginVo)result.getData();
        UserThreadLocal.put(loginVo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
