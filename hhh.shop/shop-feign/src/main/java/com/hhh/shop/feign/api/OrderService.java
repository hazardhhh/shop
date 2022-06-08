package com.hhh.shop.feign.api;

import com.hhh.shop.core.resp.R;
import com.hhh.shop.feign.dto.OrderDto;
import com.hhh.shop.feign.dto.OrderFormDto;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "shop-admin", contextId = "order-server")
public interface OrderService {

    @RequestMapping("/order/save")
    public OrderDto save(OrderFormDto order);

    @GetMapping("/order/getOrderById/{id}")
    OrderDto getOrderById(@PathVariable("id") String orderId);

    @GetMapping("/order/updateOrderStatus/{id}/{status}")
    void updateOrderStatus(@PathVariable("id") String id, @PathVariable("status") Integer status);
}
