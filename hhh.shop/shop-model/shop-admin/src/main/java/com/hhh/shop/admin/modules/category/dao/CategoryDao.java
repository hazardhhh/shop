package com.hhh.shop.admin.modules.category.dao;

import com.hhh.shop.admin.modules.category.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品类型
 * 
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-29 01:32:46
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
    public List<CategoryEntity> getCategoryZtreeData();
}
