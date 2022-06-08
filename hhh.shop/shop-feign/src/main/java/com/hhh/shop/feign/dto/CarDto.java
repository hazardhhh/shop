package com.hhh.shop.feign.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CarDto {

    private String userId; // 用户Id
    private BigDecimal price; // 购车的总价
    private List<CarItemDto> carItem; // 购车中的商品

    // 返回购车的总价
    public BigDecimal getCarPrice() {
        BigDecimal sum = new BigDecimal(0.0);
        for (CarItemDto carItemDto : carItem) {
            BigDecimal price = carItemDto.getProduct().getPrice();
            Integer num = carItemDto.getNum();
            sum = price.add(price.multiply(BigDecimal.valueOf(num)));
        }
        return sum;
    }
}
