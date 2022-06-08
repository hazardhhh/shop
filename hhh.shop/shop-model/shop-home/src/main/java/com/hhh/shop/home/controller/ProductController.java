package com.hhh.shop.home.controller;

import com.hhh.shop.core.resp.R;
import com.hhh.shop.feign.api.ProductService;
import com.hhh.shop.feign.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private RedissonClient redissonClient;

    @GetMapping("/findProductList/{num}")
    public R findProductList(@PathVariable Integer num) {
        return R.ok().put("data", productService.productList(num));
    }

    @GetMapping("/productByCategoryId/{id}/{type}")
    public R productByCategoryId(@PathVariable Integer id, @PathVariable Integer type) {
        return R.ok().put("data", productService.productByCategoryId(id, type));
    }

    private String prodctInfoKey = "shop:product:info:%s";

    /**
     * 锁的是代码吗？锁线程/对象
     * 使用Redis做缓存服务器，减少数据库压力。
     * 1、先查询缓存，缓存没有再去数据库中查询
     * 2、需要解决缓存穿透: 数据库中没有，缓存中也没有
     * a)使用空值或者默认值
     * 3、需要解决缓存击穿：缓存中没有，数据库有，并发的情况下
     * a)锁去解决
     *
     * @param id
     * @return
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable Integer id) {
        return R.ok().put("data", productService.getById(id));
    }

}
