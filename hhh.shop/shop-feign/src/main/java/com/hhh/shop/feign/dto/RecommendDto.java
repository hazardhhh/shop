package com.hhh.shop.feign.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

// dto中的字段可以不合数据库保存一致，看业务需要
@Data
public class RecommendDto implements Serializable {

    // 为什么值留四个字段呢？因为对于shop-home来讲值关注这四个
    private Integer id;
    private String name;
    private String checkUrl;
    private String pngUrl;
}
