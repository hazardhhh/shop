package com.hhh.shop.admin.common.controller;

import com.hhh.shop.admin.common.utils.R;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * FileUploadController
 *
 * @author HHH
 * @version 1.0.0
 * @date 2022/5/26 13:56
 */
@RestController
@RequestMapping("/upload")
@Slf4j
public class FileUploadController {

//      @Autowired
//      private UploadManager uploadManager;
//
//      @Autowired
//      private Auth auth;

  String AK = "PB2D8Ea0vOYRJjJlckTRL_JS9yDgRBNNVMEnflPD";
  String SK = "QqbFqUWVeXeSGt9b5P6pIKHCbt2teL73jlfBV0Hn";
  String bucket = "hhh-shop-fileserver"; // 空间名称
  String host = "http://rchs72zvu.hn-bkt.clouddn.com/";

  Auth auth = null;

  @PostMapping("/file")
  public R file(MultipartFile file) {
    // 1.连接文件服务器
    auth = Auth.create(AK, SK);

    // 2.获取token
    String token = auth.uploadToken(bucket); // token一般都会有过期时间

    // 3.创建上传管理器
    UploadManager uploadManager = new UploadManager(new Configuration());

    // 2.需要上传的文件
    try {
      Response response = uploadManager.put(file.getBytes(), file.getOriginalFilename(), token);
      if (response.isOK()) {
        StringMap stringMap = response.jsonToMap();
        Object hash = stringMap.get("key"); // 文件名称
        String filePath = host + hash;
        return R.ok().put("path", filePath);
      } else {
        log.error("上传文件失败:{}", response.getInfo());
      }
    } catch (QiniuException e) {
      log.error("上传文件出现异常:", e);
    } catch (IOException e) {
      log.error("上传文件出现异常:", e);
    }
    return R.error();
  }
}
