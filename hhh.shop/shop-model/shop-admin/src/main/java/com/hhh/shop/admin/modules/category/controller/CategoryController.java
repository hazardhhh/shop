package com.hhh.shop.admin.modules.category.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hhh.shop.admin.modules.category.entity.CategoryEntity;
import com.hhh.shop.admin.modules.category.service.CategoryService;
import com.hhh.shop.admin.common.utils.PageUtils;
import com.hhh.shop.admin.common.utils.R;



/**
 * 商品类型
 *
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-29 01:32:46
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public List<CategoryEntity> all() {
        return categoryService.list();
    }

    @GetMapping("/getZtreeData")
    public R getZtreeData() {
        return R.ok().put("data", categoryService.getCategoryZtreeData());
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("category:category:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("category:category:info")
    public R info(@PathVariable("id") Integer id){
		CategoryEntity category = categoryService.getById(id);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("category:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("category:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("category:category:delete")
    public R delete(@RequestBody Integer[] ids){
		categoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
