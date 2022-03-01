package com.lbm.api.handler;

import com.alibaba.fastjson.JSON;
import com.lbm.api.service.GuestUserService;
import com.lbm.api.service.SysUserService;
import com.lbm.api.util.UserThreadLocal;
import com.lbm.api.vo.ErrorCode;
import com.lbm.api.vo.LoginVo;
import com.lbm.api.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    GuestUserService guestUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("Token");
        String userId = request.getHeader("user_id");
        log.info("=================request start===========================");
        log.info("request uri:{}", request.getRequestURI());
        log.info("request IP:{}", request.getRemoteAddr());
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");
        /**
         * token为空，验证不通过
         */
        if (StringUtils.isEmpty(token) || "null".equals(token) || "undefined".equals(token)) {
            return false;
        }
        return true;
        //TODO 将用户加入线程池
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
