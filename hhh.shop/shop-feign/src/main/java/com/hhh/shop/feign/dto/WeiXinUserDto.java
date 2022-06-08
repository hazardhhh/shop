package com.hhh.shop.feign.dto;

import lombok.Data;

@Data
public class WeiXinUserDto  {
	private Integer id;
	private String username;
	private String password;
	private String phone;
	private String headUrl;
	private String email;
}
