package com.hhh.shop.home.controller;

import com.alibaba.fastjson.JSON;
import com.hhh.shop.core.resp.R;
import com.hhh.shop.feign.api.AdminService;
import com.hhh.shop.feign.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private AdminService adminService;

    // 显示商品类型
    // 把一个总的集合拆分成多个小集合(8个)，把这些小集合在放在一个list
    @GetMapping("/list")
    public R list() {

        // 1.获取所有的商品类型数据
        List<CategoryDto> list = adminService.getAllCategory();

        // 2.计算总屏数量
        Integer max = list.size() % 8 == 0 ? list.size() / 8 : (list.size() / 8) + 1;

        int index = 0; // 用来记录拆分成多个集合的索引

        int in = 8;// 每屏显示8个
        List<Map<String, List<CategoryDto>>> resultList = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            List<CategoryDto> indexList = new ArrayList<>();
            for (int j = index; j < list.size(); j++) {
                index = j;
                if (index <= (in-1)) {
                    indexList.add(list.get(j));
                } else {
//                    in *= 2;
                    in += 8;
                    break;
                }
            }
            Map<String, List<CategoryDto>> map = new HashMap<>();
            map.put("categoryList", indexList);
            resultList.add(map);
        }
        return R.ok().put("data", resultList);
    }

    public static void main(String[] args) {


        System.out.println(17 / 8);

        // 1.第一个屏展示的数据
        List<CategoryDto> list1 = new ArrayList<>();
        list1.add(new CategoryDto(1, "1", 1, "1", 1));
        list1.add(new CategoryDto(2, "2", 2, "2", 2));
        list1.add(new CategoryDto(3, "3", 3, "3", 3));

        // 2.第一个屏展示的数据
        List<CategoryDto> list2 = new ArrayList<>();
        list2.add(new CategoryDto(4, "4", 4, "4", 4));
        list2.add(new CategoryDto(5, "5", 5, "5", 5));
        list2.add(new CategoryDto(6, "6", 6, "6", 6));


        Map<String, List<CategoryDto>> m1 = new HashMap<>();
        m1.put("categoryList", list1);

        Map<String, List<CategoryDto>> m2 = new HashMap<>();
        m2.put("categoryList", list2);


        // 3.把list1和list2放在一个集合中
        List<Map<String, List<CategoryDto>>> list3 = new ArrayList<>();

        list3.add(m1);
        list3.add(m2);


        String string = JSON.toJSONString(list3);
        System.out.println(string);

    }
}
