package com.hhh.shop.feign.dto;

import lombok.Data;

@Data
public class StockResult {

    private Boolean isOk;
    private Object data;
}
