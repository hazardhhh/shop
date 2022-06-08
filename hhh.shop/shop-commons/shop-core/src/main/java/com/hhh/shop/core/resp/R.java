package com.hhh.shop.core.resp;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * R
 *
 * @author HHH
 * @version 1.0.0
 * @date 2022/5/27 3:19
 */
public class R extends HashMap {

    public boolean isOk() {
        Integer code = (Integer) this.get("code");
        if (code == 0) {
            return true;
        }
        return false;
    }

    public static R ok() {
        R r = new R();
        r.put("code", 0); //200代表成功
        return r;
    }

    public static R ok(String msg) {
        R ok = ok();
        ok.put("msg", msg);
        return ok;
    }

    public static R error() {
        R r = new R();
        r.put("code", 500);
        return r;
    }

    public static R error(String msg, Integer code) {
        R error = error();
        error.put("msg", msg);
        error.put("code", code);
        return error;
    }

    public static R error(String msg) {
        R error = error();
        error.put("msg", msg);
        return error;
    }

    public static void out(HttpServletResponse response, R r) {
        try {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(r));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public R put(Object key, Object value) {
        super.put(key, value);
        return this;
    }

    // 根据传入的布尔值来判断
    public static R write(Boolean flag) {
        if (flag) {
            return ok();
        }
        return error();
    }

}
