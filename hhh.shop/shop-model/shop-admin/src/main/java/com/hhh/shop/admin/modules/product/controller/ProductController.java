package com.hhh.shop.admin.modules.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hhh.shop.admin.modules.product.entity.ProductEntity;
import com.hhh.shop.admin.modules.product.service.ProductService;
import com.hhh.shop.admin.common.utils.PageUtils;
import com.hhh.shop.admin.common.utils.R;

/**
 * 商品管理
 *
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-30 11:01:29
 */
@RestController
@RequestMapping("/product")
public class ProductController {
  @Autowired private ProductService productService;

  @GetMapping("/productByCategoryId/{id}/{type}")
  public List<ProductEntity> productByCategoryId(
      @PathVariable Integer id, @PathVariable Integer type) {

    // 1.创建MP查询条件
    QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<ProductEntity>();

    // 2.设置查询条件
    if (id != null && id != 0) { // 类型id为0是没有这个真实数据的，是为了测试
      queryWrapper.eq("type1", id);
      queryWrapper.or();
      queryWrapper.eq("type2", id); // where type1 = ? or type2 = ?
    }

    // 设置排序规则
    if (type == 1) { // 按照销量排序，我们暂时没有销量这字段就按照id排序
      queryWrapper.orderByDesc("id");
    } else if (type == 2) { // // 按照价格升序
      queryWrapper.orderByAsc("price");
    } else if (type == 3) { // 按照价格降序
      queryWrapper.orderByDesc("price");
    } else if (type == 4) { // 按照评论排序

    }

    // 3.查询商品信息
    List<ProductEntity> list = productService.list(queryWrapper);

    // 4.查询每个商品的图片
    for (ProductEntity productEntity : list) {
      productEntity.setProductPicList(
          productService.findProductPicListByPid(productEntity.getId()));
    }
    return list;
  }

  /**
   * 提供一个接口让shop-hone来调用
   *
   * @param num 页面
   * @return
   */
  @GetMapping("/productList/{num}")
  public List<ProductEntity> productList(@PathVariable Integer num) {
    return productService.productList(num);
  }

  /** 列表 */
  @RequestMapping("/list")
  @RequiresPermissions("product:product:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = productService.queryPage(params);

    return R.ok().put("page", page);
  }

  /** 信息 */
  @RequestMapping("/info/{id}")
  //    @RequiresPermissions("product:product:info")
  public R info(@PathVariable("id") Integer id) {
    ProductEntity product = productService.getById(id);

    return R.ok().put("data", productService.getById(id));
  }

  /** 保存 */
  @RequestMapping("/save")
  @RequiresPermissions("product:product:save")
  public R save(@RequestBody ProductEntity product) {
    // 1.保存MySQL
    productService.saveProduct(product); // MP会自动实现主键回填


    return R.ok();
  }

  /** 修改 */
  @RequestMapping("/update")
  @RequiresPermissions("product:product:update")
  public R update(@RequestBody ProductEntity product) {
    productService.updateById(product);

    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  @RequiresPermissions("product:product:delete")
  public R delete(@RequestBody Integer[] ids) {
    productService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }
}
