package com.hhh.shop.admin;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class ShopAdminApplicationTests {

  String AK = "PB2D8Ea0vOYRJjJlckTRL_JS9yDgRBNNVMEnflPD";
  String SK = "QqbFqUWVeXeSGt9b5P6pIKHCbt2teL73jlfBV0Hn";
  String bucket = "hhh-shop-fileserver"; // 空间名称
  String host = "http://rchs72zvu.hn-bkt.clouddn.com/";

  Auth auth = null;

  @Test
  void contextLoads() {

    // 1.连接文件服务器
    auth = Auth.create(AK, SK);

    // 2.获取token
    String token = getUpToken0();

    // 3.创建上传管理器
    UploadManager uploadManager = new UploadManager(new Configuration());

    // 4.上传文件
    File file = new File("C:\\Users\\H\\Desktop\\text.jpg");
    try {
      Response response = uploadManager.put(file, "text.jpg", token);
      if (response.isOK()) {
        StringMap stringMap = response.jsonToMap();
        Object hash = stringMap.get("key");
        System.out.println("filePath:" + host + hash);
      }
    } catch (QiniuException e) {
      e.printStackTrace();
    }
  }

  private String getUpToken0() {
    return auth.uploadToken(bucket);
  }
}
