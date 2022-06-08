package com.hhh.shop.auth.sso;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.hhh.shop.auth.vo.SysLoginVo;
import com.hhh.shop.common.utils.PassworUtils;
import com.hhh.shop.core.resp.R;
import com.hhh.shop.feign.api.AdminService;
import com.hhh.shop.feign.dto.SysUserDto;
import com.sun.prism.impl.shape.ShapeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

// 运营人员的登录，注销
@RestController
@RequestMapping("/sso/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录
     */
    // http://ip:80/system/sso/admin/login
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody SysLoginVo vo) throws IOException {

        // 1.对比验证码
//		boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
//		if(!captcha){
//			return R.error("验证码不正确");
//		}

        // 2、根据用户名查询用户的信息
        SysUserDto user = adminService.byName(vo.getUsername());

        if (user == null) {
            return R.error("用户名不存在");
        }

        // 3、密码的比对
        if (!PassworUtils.checkpw(vo.getPassword(), user.getPassword())) {
            return R.error("账号或密码不正确");
        }

        // 4、账号锁定
        if (user.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }

        // 5、生成token，使用sa-token
        // 这个登录是是浏览器访问的，所以可以使用Cookie
        SaLoginModel model = new SaLoginModel();
        model.setTimeout(60 * 60 * 24 * 3); // 设置超时时间为7天
        StpUtil.login(user.getUserId(), model); // 登录(1创建一个token，保存redis中，写到Cookie里面)

        return R.ok();
    }
}
