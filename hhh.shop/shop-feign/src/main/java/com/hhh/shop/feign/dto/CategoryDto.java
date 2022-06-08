package com.hhh.shop.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Integer id;
    private String name;
    private Integer pid;
    private String icon;
    private Integer orderNum;
}
