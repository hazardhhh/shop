package com.hhh.shop.feign.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer type1;
    private Integer type2;
    private List<ProductPicDto> productPicList;
}
