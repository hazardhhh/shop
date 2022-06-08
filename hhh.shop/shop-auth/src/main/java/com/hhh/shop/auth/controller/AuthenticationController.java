package com.hhh.shop.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.hhh.shop.auth.config.AuthProperties;
import com.hhh.shop.core.resp.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthProperties authProperties;

    // 创建一个匹配对象
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private RedisTemplate redisTemplate;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // shp:user:uptoken:2022-04-18:10
    private String updateTokenKeyTemplate = "shop:user:uptoken:%s:%s";

    // 网关来调用--》校验token接口
    @GetMapping("/token")
    public R token(@RequestHeader(name = "token", defaultValue = "") String token,
                   String uri) {
        log.debug("【认证中心】校验token");
        log.debug("【认证中心】token:{}", token);
        log.debug("【认证中心】 uri:{}", uri);

        // 1.判断当前url是否需要token的校验
        if (isExcloudResoruce(uri)) {
            return R.ok(); // 该url不需要token校验就可以访问
        }

        // 2.校验token，判断是否登录
        if (StpUtil.isLogin()) { // 校验token ok token还没有过期
            renewTokenTimeout(); // 给token续期
            return R.ok();
        }
        return R.error("token已过期", 503);
    }


    // 每次都需跟新token的过期时间太频繁了，每天更新一次就可以了
    private void renewTokenTimeout() {

        // 1.获取当前的时间
        String data = sdf.format(new Date());

        // 2.获取当前用户Id
        String userId = (String) StpUtil.getLoginId();

        // 3.获取key
        // key=shop:user:uptoken:2022-04-18:10 -->10这个用户当天已经更新过了
        String key = String.format(updateTokenKeyTemplate, data, userId);

        // 4.查询当天是否已经更新过key了
        Object value = redisTemplate.opsForValue().get(key);
        if (ObjectUtils.isEmpty(value)) {
            log.debug("更新token的过期时间");
            // value为空代表当天还没有更新，更新token过期时间
            StpUtil.renewTimeout(60 * 60 * 24 * 3);//给token设置过期时间为3天
            // 给redis保存一下，当前用户，当日已经更新了token
            redisTemplate.opsForValue().set(key, "1", 1, TimeUnit.DAYS);
        }

    }

    private boolean isExcloudResoruce(String requestURI) {
        // 获取到忽略的资源,这里会有多个
        List<String> excloudPathList = authProperties.getExcloudPath();

        // 判断当前uri是否和配置的忽略资源匹配
        for (String pattern : excloudPathList) {
            if (antPathMatcher.match(pattern, requestURI)) {
                return true; // 只要有一个匹配到就返回true
            }
        }
        return false;
    }

    public static void main(String[] args) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String uri1 = "/system/admin/10/20//xx";  // 正在访问的地址
//        String uri2 = "/system/admin/10/20";  // 正在访问的地址
        // 第一个参数：要忽略的地址
        // /*和/**(多层)
        boolean match = antPathMatcher.match("/system/admin/**", uri1);
        System.out.println(match);
    }
}
