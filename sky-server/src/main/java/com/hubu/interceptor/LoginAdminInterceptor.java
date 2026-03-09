package com.hubu.interceptor;/*
 * @Auther:long
 * @Date:2025/11/6
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.constant.MessageConstant;
import com.hubu.constant.SessionAttributeConstant;
import com.hubu.context.BaseContext;
import com.hubu.exception.SessionInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.logging.Handler;

/**
 * 登录拦截
 */
@Component
@Slf4j
public class LoginAdminInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!((handler instanceof HandlerMethod) )){
            //访问静态资源放行
            return true;
        }
        log.info("拦截资源路径{}",request.getRequestURI());
        //1. 获取session
        HttpSession session = request.getSession(false);
        //2. session不存在或session信息不存在 不放行
        if (session==null || session.getAttribute(SessionAttributeConstant.EMP_ID) == null){
            throw new SessionInvalidException(MessageConstant.SESSION_INVALID);
        }
        //3. session存在 保存信息到threadlocal
        Long id = (Long)session.getAttribute(SessionAttributeConstant.EMP_ID);
        BaseContext.setCurrentId(id);
        return true;
    }
}
