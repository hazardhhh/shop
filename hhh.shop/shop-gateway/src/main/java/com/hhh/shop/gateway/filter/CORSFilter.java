package com.hhh.shop.gateway.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

// 在网关中用一个Filter来统一的解决跨域的问题
@Component
public class CORSFilter {

    @Bean
    public CorsWebFilter corsFilter() {
        System.out.println("CORSFilter.corsFilter");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //在生产环境上最好指定域名，以免产生跨域安全问题
        corsConfiguration.addAllowedOrigin("*"); // 允许那些域访问
        corsConfiguration.addAllowedHeader("*"); // 访问时运行请求头中携带那些参数
        corsConfiguration.addAllowedMethod("*"); // 运行那些请求方式访问
        corsConfiguration.setAllowCredentials(true); // 时是否允许浏览器读取response的内容
        source.registerCorsConfiguration("/**", corsConfiguration); // 跨域对那些请求地址有效
        return new CorsWebFilter(source);
    }

}
