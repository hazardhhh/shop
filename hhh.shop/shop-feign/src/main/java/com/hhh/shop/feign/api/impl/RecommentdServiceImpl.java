package com.hhh.shop.feign.api.impl;

import com.hhh.shop.feign.api.AdminService;
import com.hhh.shop.feign.dto.CategoryDto;
import com.hhh.shop.feign.dto.RecommendDto;
import com.hhh.shop.feign.dto.SysUserDto;

import java.util.List;

// 本地降级实现
public class RecommentdServiceImpl implements AdminService {
    @Override
    public List<RecommendDto> getHomeRecommendList() {
        return null;
    }

    @Override
    public SysUserDto byName(String name) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return null;
    }
}
