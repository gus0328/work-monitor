package com.iccm.common.config;

import com.iccm.common.interceptor.LoginInterceptor;
import com.iccm.common.interceptor.UploadInterceptor;
import com.iccm.common.properties.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by wangfan on 2019-01-04 下午 3:40.
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private UploadInterceptor uploadInterceptor;

    @Autowired
    private SystemProperties systemProperties;

    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }

    /**
     * 设置拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").
                excludePathPatterns("/login/*").
                excludePathPatterns("/captcha/captchaImage").
                excludePathPatterns("/upload/**").
                excludePathPatterns("/avator/**").excludePathPatterns("/js/**").excludePathPatterns("/save_error_logger");
        registry.addInterceptor(uploadInterceptor).addPathPatterns("/upload/**").excludePathPatterns("/upload/test").excludePathPatterns("/upload/query");
    }

    /**
     * webSocket
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avator/**").addResourceLocations("file:"+systemProperties.getAvatorPath()+"/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }
}