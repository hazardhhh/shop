package com.hhh.shop.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component // 实例化当前bean放到IOC柔容器中
@ConfigurationProperties(prefix = "shop.auth") //属性读取yml中shop.auth.[属性名称]
public class AuthProperties {

    private List<String> excloudPath; // 不需要token认证的资源
}
