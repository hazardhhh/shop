package com.hhh.shop.admin.modules.recommend.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hhh.shop.admin.common.utils.PageUtils;
import com.hhh.shop.admin.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hhh.shop.admin.modules.recommend.entity.RecommendEntity;
import com.hhh.shop.admin.modules.recommend.service.RecommendService;

/**
 * 首页推荐表
 *
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-25 15:25:41
 */
@RestController
@RequestMapping("recommend")
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    // 提供一个接口返回首页轮播图数据
    @GetMapping("/getHomeRecommendList")
    public List<RecommendEntity> getHomeRecommendList() {
        return recommendService.getHomeRecommendList();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("recommend:recommend:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = recommendService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("recommend:recommend:info")
    public R info(@PathVariable("id") Integer id){
		RecommendEntity recommend = recommendService.getById(id);

        return R.ok().put("recommend", recommend);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("recommend:recommend:save")
    public R save(@RequestBody RecommendEntity recommend){
        recommend.setCreateTime(new Date()); //创建时间
		recommendService.save(recommend);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("recommend:recommend:update")
    public R update(@RequestBody RecommendEntity recommend){
		recommendService.updateById(recommend);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("recommend:recommend:delete")
    public R delete(@RequestBody Integer[] ids){
		recommendService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
