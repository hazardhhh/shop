package com.hhh.shop.feign.api;

import com.hhh.shop.feign.api.impl.RecommentdServiceImpl;
import com.hhh.shop.feign.dto.CategoryDto;
import com.hhh.shop.feign.dto.RecommendDto;
import com.hhh.shop.feign.dto.SysUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


// shop-admin
@FeignClient(
        value = "shop-admin", // 服务名称
//        path = "",  // namespace
        fallback = RecommentdServiceImpl.class // 降级实现
)
public interface AdminService {

    //    @GetMapping("/recommend/getHomeRecommendList")
    @GetMapping("/recommend/getHomeRecommendList") // 自动添加接口上的path
    public List<RecommendDto> getHomeRecommendList();


    @GetMapping("/sys/user/byName/{name}")
    public SysUserDto byName(@PathVariable("name") String name);

    @GetMapping("/category/all")
    public List<CategoryDto> getAllCategory();
}
