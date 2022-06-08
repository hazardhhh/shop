package com.hhh.shop.feign.api;

import com.hhh.shop.core.resp.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shop-auth")
public interface AuthenticationService {

    @GetMapping("/auth/token")
    public R token(@RequestParam("uri") String uri);
}
