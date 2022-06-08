package com.hhh.shop.admin.modules.recommend.dao;

import com.hhh.shop.admin.modules.recommend.entity.RecommendEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 首页推荐表
 * 
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-25 15:25:41
 */
@Mapper
public interface RecommendDao extends BaseMapper<RecommendEntity> {
    List<RecommendEntity> getCurrentDayList(Integer limit);

    List<RecommendEntity> getLatestList(Integer limit);
}
