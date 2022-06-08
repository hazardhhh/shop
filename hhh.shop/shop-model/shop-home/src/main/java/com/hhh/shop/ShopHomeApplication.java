package com.hhh.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.hhh.shop.feign.api")
public class ShopHomeApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShopHomeApplication.class, args);
  }
}
