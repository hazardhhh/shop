package com.hhh.shop.home.controller;

import com.hhh.shop.core.resp.R;

import com.hhh.shop.feign.api.AdminService;
import com.hhh.shop.feign.dto.RecommendDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 这个接口给首页商品推荐提供服务
 *
 * @author HHH
 * @version 1.0.0
 * @date 2022/5/27 2:53
 */
// 这个接口给首页的商品推荐提供服务
@RestController
@RequestMapping("/recommend")
public class RecommendController {

  @Autowired
  private AdminService recomendService;

  @GetMapping("/test")
  public R test() { // emp Hiredate,
    return R.ok().put("data", new Date());
  }

  @GetMapping("/getCurrentDayRecommend")
  public R getCurrentDayRecommend() {
    // 调用shop-admin的接口(controller)获取数据
    List<RecommendDto> homeRecommendList = recomendService.getHomeRecommendList();
    return R.ok().put("data",homeRecommendList);
  }
}
