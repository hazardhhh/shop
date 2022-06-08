package com.hhh.shop.feign.dto;

import lombok.Data;

@Data
public class StockDto {

    private Integer id; // 商品id

    private Integer num; // 数量

    private Integer wid; // 仓库id

    private String sku; // 规则参数
}
