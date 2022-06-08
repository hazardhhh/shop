package com.hhh.shop.feign.api;

import com.hhh.shop.feign.dto.StockDto;
import com.hhh.shop.feign.dto.StockResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "shop-admin", contextId = "shop-stock")
public interface StockService {

    @PostMapping("/stock/lockStock")
    public StockResult lockStock(List<StockDto> list);

    @PostMapping("/stock/unlockStock")
    void unlockStock(List<StockDto> stockDtos);
}
