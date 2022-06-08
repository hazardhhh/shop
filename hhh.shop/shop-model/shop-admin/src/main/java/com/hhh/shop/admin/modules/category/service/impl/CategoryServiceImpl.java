package com.hhh.shop.admin.modules.category.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhh.shop.admin.common.utils.PageUtils;
import com.hhh.shop.admin.common.utils.Query;

import com.hhh.shop.admin.modules.category.dao.CategoryDao;
import com.hhh.shop.admin.modules.category.entity.CategoryEntity;
import com.hhh.shop.admin.modules.category.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> getCategoryZtreeData() {
        return baseMapper.getCategoryZtreeData();
    }
}