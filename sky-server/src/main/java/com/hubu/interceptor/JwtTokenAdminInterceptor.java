package com.hubu.interceptor;/*
 * @Auther:long
 * @Date:2025/9/25
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.constant.JwtClaimsConstant;
import com.hubu.context.BaseContext;
import com.hubu.properties.JwtProperties;
import com.hubu.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    private static Set<String> EXCLUDED_URLS = new HashSet<>();
    static {
        EXCLUDED_URLS.add("/admin/employee/login");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器，URI={}", request.getRequestURI());
        // 如果不是 Controller 方法，直接放行（例如静态资源）
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String requestURI = request.getRequestURI();
        if(EXCLUDED_URLS.contains(requestURI)){
            log.warn("请求路径{}在白名单中直接放行",requestURI);
            return true;
        }
        //拦截jwt
        // 1.获取token
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        // 2.验证token
        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(),token);
            Long id = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            BaseContext.setCurrentId(id);
            return true;
        }
        //捕获异常
        catch (Exception e){
            log.warn("token验证失败");
            //不通过 响应401状态码
            response.setStatus(401);
            return false;
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 请求结束时清理，避免内存泄漏 & 脏数据
        BaseContext.removeCurrentId();
        log.debug("清理 ThreadLocal: userId 已移除");
    }
}
