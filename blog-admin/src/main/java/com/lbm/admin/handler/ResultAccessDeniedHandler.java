package com.lbm.admin.handler;

import com.alibaba.fastjson.JSON;
import com.lbm.common.Result;
import com.lbm.common.uitl.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SpringSecurity授权过程异常处理
 */
@Component
public class ResultAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result result = Result.fail(-999, "权限不足");
        String jsonString = JSON.toJSONString(result);
        WebUtils.renderString(response, jsonString);
    }
}
