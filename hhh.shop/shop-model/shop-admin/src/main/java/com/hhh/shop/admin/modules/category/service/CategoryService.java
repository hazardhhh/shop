package com.hhh.shop.admin.modules.category.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhh.shop.admin.common.utils.PageUtils;
import com.hhh.shop.admin.modules.category.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品类型
 *
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-29 01:32:46
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public List<CategoryEntity> getCategoryZtreeData();
}

