package com.hhh.shop.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Slf4j
public class FeignHeadParamInter implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            // 获取token
            String token = requestAttributes.getRequest().getHeader("token");
            if (!ObjectUtils.isEmpty(token)) {
                log.debug("feign调用自动添加token");
                template.header("token", token);
            }
        }
    }
}
