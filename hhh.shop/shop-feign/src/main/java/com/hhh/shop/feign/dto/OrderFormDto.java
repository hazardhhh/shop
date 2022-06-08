package com.hhh.shop.feign.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderFormDto {

    private Integer addressId; // 收货地址

    private List<Integer> prodctIdList; // 给我选购车中商品的id

    private String remark; // 备注

    private Integer userId;

    private CarDto carDto; // 用户购物车

    private String orderId;
}
