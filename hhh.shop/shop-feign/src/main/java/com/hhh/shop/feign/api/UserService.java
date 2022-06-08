package com.hhh.shop.feign.api;

import com.hhh.shop.feign.dto.WeiXinUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(value = "shop-admin", contextId = "user", path = "/user")
@FeignClient(
        name = "shop-admin",
        contextId = "user",
        path = "/user"
//        url = "http://localhost:8081"
//        configuration = XxxxFeign.classs,
        ,primary = true
        ) // 这样写是给注解中的value属性赋值
public interface UserService {
    @RequestMapping("/register")
    public WeiXinUserDto register(@RequestBody WeiXinUserDto user);

    @GetMapping("/findUserByPhone/{phone}")
    WeiXinUserDto findUserByPhone(@PathVariable("phone") String phone);

    @GetMapping("/getUserInfo/{userId}")
    WeiXinUserDto getUserInfo(@PathVariable Integer userId);
}
