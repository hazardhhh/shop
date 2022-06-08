package com.hhh.shop.feign.api;

import com.hhh.shop.feign.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// 该接口中只放商品的服务
@FeignClient(value = "shop-admin", contextId = "product")
public interface ProductService {

    @GetMapping("/product/productList/{num}")
    public List<ProductDto> productList(@PathVariable("num") Integer num);

    @GetMapping("/product/productByCategoryId/{id}/{type}")
    public List<ProductDto> productByCategoryId(@PathVariable("id") Integer id, @PathVariable("type") Integer type);

    @GetMapping("/product/getById/{id}")
    ProductDto getById(@PathVariable("id") Integer id);
}