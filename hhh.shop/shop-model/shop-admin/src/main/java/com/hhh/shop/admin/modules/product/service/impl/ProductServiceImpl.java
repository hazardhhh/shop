package com.hhh.shop.admin.modules.product.service.impl;

import com.hhh.shop.admin.common.utils.PageUtils;
import com.hhh.shop.admin.common.utils.Query;
import com.hhh.shop.admin.modules.product.entity.ProductPicEntity;
import com.hhh.shop.admin.modules.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhh.shop.admin.modules.product.dao.ProductDao;
import com.hhh.shop.admin.modules.product.entity.ProductEntity;
import com.hhh.shop.admin.modules.product.service.ProductService;
import org.springframework.util.ObjectUtils;


@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProductEntity> implements ProductService {

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        // 1.查询商品信息
        IPage<ProductEntity> page = this.page(
                new Query<ProductEntity>().getPage(params),
                new QueryWrapper<ProductEntity>()
        );

        // 2.查询每个商品的图片信息
        List<ProductEntity> records = page.getRecords();
        for (ProductEntity productEntity : records) {
            productEntity.setProductPicList(this.findProductPicListByPid(productEntity.getId()));
        }
        return new PageUtils(page);
    }


    // 这里要有事务
    @Override
    public void saveProduct(ProductEntity product) {

        // 保存商品信息
        baseMapper.insert(product); // 一条数据

        // 保存图片信息，多条数据
        baseMapper.insertProductPicture(product.getId(), product.getProductPicList());
    }

    @Override
    public List<ProductEntity> productList(Integer num) {

        String value = sysConfigService.getValue("shop:home:product:limit");
        Integer size = 4;
        if (!ObjectUtils.isEmpty(value)) {
            size = Integer.parseInt(value); // 从数据字典中读取
        }
        Integer start = ((num) - 1) * size;

        // 1.查询商品信息
        List<ProductEntity> productEntityList = baseMapper.productList(start, size);

        // 2.查询每个商品的图片
        for (ProductEntity productEntity : productEntityList) {
            productEntity.setProductPicList(findProductPicListByPid(productEntity.getId()));
        }
        return productEntityList;
    }

    @Override
    public List<ProductPicEntity> findProductPicListByPid(Integer pid) {
        return baseMapper.findProductPicListByPid(pid);
    }

    @Override
    public List<ProductEntity> all() {

        // 3.查询商品信息
        List<ProductEntity> list = baseMapper.selectList(null);

        // 4.查询每个商品的图片
        for (ProductEntity productEntity : list) {
            productEntity.setProductPicList(findProductPicListByPid(productEntity.getId()));
        }
        return list;
    }

    @Override
    public ProductEntity getByIdInfo(Integer id) {

        // 1.查询商品的信息
        ProductEntity productEntity = baseMapper.selectById(id);
        // 2.查询商品图片
        productEntity.setProductPicList(baseMapper.findProductPicListByPid(id));
        return productEntity;
    }

}