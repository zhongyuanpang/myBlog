package com.pzy.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //拦截admin下的所有
                .addPathPatterns("/admin/**")
                //设置不被拦截的
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
