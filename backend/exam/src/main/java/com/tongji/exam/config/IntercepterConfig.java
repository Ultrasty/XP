/***********************************************************
 * @Description : 拦截器配置

 ***********************************************************/
package com.tongji.exam.config;

import com.tongji.exam.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截user下的api
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/**").addPathPatterns("/exam/**");
    }

}
