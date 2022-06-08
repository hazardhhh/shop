package com.hhh.shop.feign.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarItemDto {

    private Integer num; // 商品的数量
    private ProductDto product; // 商品信息
    private BigDecimal subPrice; // 商品的价格*商品数量
}
