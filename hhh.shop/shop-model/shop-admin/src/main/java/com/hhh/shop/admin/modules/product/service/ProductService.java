package com.hhh.shop.admin.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhh.shop.admin.common.utils.PageUtils;
import com.hhh.shop.admin.modules.product.entity.ProductEntity;
import com.hhh.shop.admin.modules.product.entity.ProductPicEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品管理
 *
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-30 11:01:29
 */
public interface ProductService extends IService<ProductEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveProduct(ProductEntity product);

    List<ProductEntity> productList(Integer num);

    List<ProductPicEntity> findProductPicListByPid(Integer pid);

    List<ProductEntity> all();

    ProductEntity getByIdInfo(Integer id);
}

