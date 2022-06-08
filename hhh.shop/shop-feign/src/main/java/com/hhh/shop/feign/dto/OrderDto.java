package com.hhh.shop.feign.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {

    private String id;
    private Integer uid;
    private BigDecimal totalPrice;
    /**
     * 1：未支付
     * 2：已支付
     * 3：已取消
     * 4:已超时
     * 5:未发货
     * 6:已发货
     * 7:确认收货 --》待评价
     */
    private Integer status;
    /**
     *
     */
    private Integer comment;
    /**
     *
     */
    private Integer addressId;
    /**
     *
     */
    private String remark;
    /**
     *
     */
    private String payType;
    /**
     *
     */
    private String payNum;
}
