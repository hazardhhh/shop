package com.hhh.shop.feign.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysUserDto {

    private Long userId;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String mobile;
    private Integer status;
    private List<Long> roleIdList;
    private Long createUserId;
    private Date createTime;
}
