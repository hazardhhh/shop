package com.hhh.shop.feign.api;

import com.hhh.shop.core.resp.R;
import com.hhh.shop.feign.dto.CarDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "shop-car", contextId = "shop-car")
public interface CarService {

    @GetMapping("/car/getUserCar2")
    public CarDto getUserCar2();

    @PostMapping("/car/deleteUserCar")
    void deleteUserCar(List<Integer> selectProductIdList);
}
