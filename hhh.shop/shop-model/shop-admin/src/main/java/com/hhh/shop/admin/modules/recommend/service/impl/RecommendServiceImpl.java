package com.hhh.shop.admin.modules.recommend.service.impl;

import com.hhh.shop.admin.modules.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhh.shop.admin.common.utils.PageUtils;
import com.hhh.shop.admin.common.utils.Query;

import com.hhh.shop.admin.modules.recommend.dao.RecommendDao;
import com.hhh.shop.admin.modules.recommend.entity.RecommendEntity;
import com.hhh.shop.admin.modules.recommend.service.RecommendService;
import org.springframework.util.ObjectUtils;


@Service("recommendService")
public class RecommendServiceImpl extends ServiceImpl<RecommendDao, RecommendEntity> implements RecommendService {

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RecommendEntity> page = this.page(
                new Query<RecommendEntity>().getPage(params),
                new QueryWrapper<RecommendEntity>()
        );

        return new PageUtils(page);
    }

    private String limitKey = "shop:home:recommend:limit";

    @Override
    public List<RecommendEntity> getHomeRecommendList() {
        Integer limit = 4; // 图片最大显示的数量，这是一个默认值,后台人员可以修改。一般会把这个参数配置到数据字典里面

        // 从数据字典中查询limit
        String value = sysConfigService.getValue(limitKey);
        if(!ObjectUtils.isEmpty(value)){
            limit = Integer.parseInt(value);
        }

        // 1、先查询当天展示的数据
        List<RecommendEntity> list = baseMapper.getCurrentDayList(limit);

        // 2.如果当前有显示的数据，直接返回
        if (list != null && list.size() > 0) {
            return list;
        }

        // 3.如果当天没有显示的数据，就显示最新数据(以当天为节点)
        list = baseMapper.getLatestList(limit);
        return list;
    }

}