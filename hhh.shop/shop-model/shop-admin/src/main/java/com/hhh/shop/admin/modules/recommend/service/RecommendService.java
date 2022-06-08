package com.hhh.shop.admin.modules.recommend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhh.shop.admin.common.utils.PageUtils;
import com.hhh.shop.admin.modules.recommend.entity.RecommendEntity;

import java.util.List;
import java.util.Map;

/**
 * 首页推荐表
 *
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-25 15:25:41
 */
public interface RecommendService extends IService<RecommendEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RecommendEntity> getHomeRecommendList();
}

