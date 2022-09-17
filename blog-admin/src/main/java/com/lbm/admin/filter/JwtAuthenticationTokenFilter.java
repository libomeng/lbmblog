package com.lbm.admin.filter;


import com.lbm.admin.entity.dos.SysUserDetails;
import com.lbm.admin.exception.AuthorizeException;
import com.lbm.admin.exception.CertificationException;
import com.lbm.admin.service.RedisService;
import com.lbm.common.config.RedisKeyConfig;
import com.lbm.common.uitl.JWTUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Data
@AllArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        //JWT解析token
        String userId;
        try {
            userId = JWTUtils.checkToken(token);
        } catch (Exception e) {
            throw new CertificationException("token非法");
        }
        //获取redis中的用户信息
        SysUserDetails sysUserDetails = redisService.getSysUserDetails(RedisKeyConfig.USER_DETAILS + userId);
        if (Objects.isNull(sysUserDetails)) {
            throw new CertificationException("用户未登录");
        }
        //封装用户权限信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUserDetails, null, sysUserDetails.getAuthorities());
        //将用户信息存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
