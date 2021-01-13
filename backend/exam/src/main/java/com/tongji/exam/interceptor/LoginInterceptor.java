package com.tongji.exam.interceptor;

import com.google.gson.Gson;
import com.tongji.exam.utils.JwtUtils;
import com.tongji.exam.vo.JsonData;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 拦截器，用于请求鉴权
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${interceptors.auth-ignore-uris}")
    private String authIgnoreUris;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器:");
        String uri = request.getRequestURI();
        System.out.println(uri);
        System.out.println("无需拦截的接口路径：" + authIgnoreUris);
        String[] authIgnoreUriArr = authIgnoreUris.split(",");
        // 登录和注册相关接口不需要进行token拦截和校验，直接返回true
        for (String authIgnoreUri : authIgnoreUriArr) {
            if (authIgnoreUri.equals(uri)) {
                return true;
            }
        }
        //和前端适配Access-Token属性，前端会在登陆后的每个接口请求头加Access-Token属性
        String token = request.getHeader("Access-Token");
        if (token == null) {
            token = request.getParameter("token");
        }
        if (token != null) {
            // 请求中是携带参数的
            Claims claims = JwtUtils.checkJWT(token);
            if (claims == null) {
                // 返回null说明用户篡改了token，导致校验失败
                sendJsonMessage(response, JsonData.buildError("token无效，请重新登录"));
                return false;
            }

            String id = (String) claims.get("id");

            String username = (String) claims.get("username");
            request.setAttribute("user_id", id);
            request.setAttribute("username", username);
            return true;
        }
        sendJsonMessage(response, JsonData.buildError("token为null,请先登录！"));
        return false;
    }

    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception {
        Gson g = new Gson();
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(g.toJson(obj));
        writer.close();
        response.flushBuffer();
    }
}
