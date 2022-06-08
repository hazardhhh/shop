package com.hhh.shop.gateway.interceprot;

import com.hhh.shop.gateway.filter.AuthenticationFilter;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// 网关webflux，不是webmvc
@Component
@Slf4j
public class FeignHeadParamInterceprot implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        log.debug("【网关】feign在调用的时候会先进入这个方法，把请求头添加进去,然后在发送请求");

        // 在webmvc中可以这样写，但是gateway用的webflux，这样写不行
//        ServletRequestAttributes req2 = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        String token = req2.getRequest().getHeader("token");

        // 从ThreadLocal中获取请求头
        String token = AuthenticationFilter.getToken();
        if (!ObjectUtils.isEmpty(token)) {
            template.header("token", token);
        }
    }
}
