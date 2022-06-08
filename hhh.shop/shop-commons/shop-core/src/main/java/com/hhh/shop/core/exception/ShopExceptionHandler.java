package com.hhh.shop.core.exception;

import com.hhh.shop.core.resp.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ShopExceptionHandler {

    // 系统异常
    @ExceptionHandler(Exception.class)
    public R systemException(Exception e) {
        log.error("系统异常", e); // 异常信心都用error来输出
        return R.error("系统正在维护，请联系管理员。");
    }

    // 业务异常
//    @ExceptionHandler(ShopException.class)
//    public R shopException(ShopException e) {
//        log.error("系统异常", e); // 异常信心都用error来输出
//        return R.error(e.getMsg());
//    }
}
