package com.hhh.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.hhh.shop.gateway.filter")
@EnableFeignClients(basePackages = "com.hhh.shop.feign.api")
public class ShopGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShopGatewayApplication.class, args);
  }
}
