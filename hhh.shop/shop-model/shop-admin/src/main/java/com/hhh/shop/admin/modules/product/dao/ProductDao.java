package com.hhh.shop.admin.modules.product.dao;

import com.hhh.shop.admin.modules.product.entity.ProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhh.shop.admin.modules.product.entity.ProductPicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品管理
 * 
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-30 11:01:29
 */
@Mapper
public interface ProductDao extends BaseMapper<ProductEntity> {
    void insertProductPicture(@Param("pid") Integer id, @Param("list") List<ProductPicEntity> pictureList);

    List<ProductPicEntity> findProductPicListByPid(Integer pid);

    List<ProductEntity> productList(@Param("start") Integer start, @Param("size") Integer size);
}
