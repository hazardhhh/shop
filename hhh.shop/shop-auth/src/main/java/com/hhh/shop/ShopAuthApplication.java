package com.hhh.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.hhh.shop.feign.api")
public class ShopAuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShopAuthApplication.class, args);
  }
}
