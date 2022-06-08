package com.hhh.shop.common.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 密码加密的工具类
 */
public class PassworUtils {


    // 密码加密
    public static String encode(String pw) {
        return BCrypt.hashpw(pw, BCrypt.gensalt());
    }

    public static Boolean checkpw(String pw, String dbPw) {
        return BCrypt.checkpw(pw, dbPw);
    }

    public static void main(String[] args) {
        String pw = "admin";

        // 明文 --》加密算法+盐值(唯一)--》密文唯一  如何解决这个问题
        //
        // 加密
//        String gensalt = BCrypt.gensalt();
//        System.out.println("盐值：" + gensalt);
//
//        String hashpw = BCrypt.hashpw(pw, gensalt); // 盐值加密(唯一)
//        System.out.println("加密后的内容：" + hashpw);

        // 对比
        boolean checkpw = BCrypt.checkpw(pw, "$2a$10$fi7faiLG803811D5iaDf0uSKbl0hP9muDAVq53W7EkvAHjCl4nGtW"); // 密文中已经包含了盐值
        // 1、先从密码中获取盐值
        // 2、在使用盐值加密把用户的面密码进行加密
        // 3.然后把两个密文进行对比
        System.out.println(checkpw);
    }

}
