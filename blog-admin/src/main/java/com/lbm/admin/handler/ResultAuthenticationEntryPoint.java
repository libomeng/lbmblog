package com.lbm.admin.handler;

import com.alibaba.fastjson.JSON;
import com.lbm.common.Result;
import com.lbm.common.uitl.WebUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class ResultAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
      authException.printStackTrace();
        Result result = Result.fail(-999, "登录失败，请重新登录");
        String jsonString = JSON.toJSONString(result);
        WebUtils.renderString(response, jsonString);
    }
}
