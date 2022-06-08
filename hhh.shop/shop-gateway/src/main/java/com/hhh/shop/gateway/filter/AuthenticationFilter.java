package com.hhh.shop.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.hhh.shop.core.resp.R;
import com.hhh.shop.feign.api.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// 在网关中做拦截所有请求，调用shop-auth完成token认证
@Component
@Slf4j
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private static ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();

    public static String getToken() {
        return tokenThreadLocal.get();
    }

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.debug("【网关】拦截所有请求,调用认证中心进行token认证");

        // 1.获取token
        String token = exchange.getRequest().getHeaders().getFirst("token");
        log.debug("【网关】token:{}", token);

        // 2.把token放再ThreadLocal中
        tokenThreadLocal.set(token);

        // feign调用认证中心,把uri传递过去
        R r = authenticationService.token(exchange.getRequest().getURI().getPath());

        log.debug("【网关】登录认证的结果:" + r);
        if (r.isOk()) {
            return chain.filter(exchange); // token验证通过放行
        }
        // 验证不通过直接从网关响应给用户
        return errorInfo(exchange,r);
    }

    // 响应错误信息,webflux中的响应方式
    public static Mono<Void> errorInfo(ServerWebExchange exchange, R r) {
        return Mono.defer(() -> {
            // 把对象转成JSON
            byte[] bytes = bytes = JSON.toJSONBytes(r);
            // 获取响应对象
            ServerHttpResponse response = exchange.getResponse();
            // 响应响应类型
            response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            // 设置HTTP响应码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(buffer));
        });
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
