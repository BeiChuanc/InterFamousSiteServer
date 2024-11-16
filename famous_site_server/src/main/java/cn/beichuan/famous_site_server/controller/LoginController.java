package cn.beichuan.famous_site_server.controller;

import cn.beichuan.famous_site_server.models.LoginUser;
import cn.beichuan.famous_site_server.service.LoginService;
import cn.beichuan.famous_site_server.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/// 登录控制层
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /// 用户登录
    @PostMapping()
    public R<Map<String, String>> login(@RequestBody LoginUser loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return R.error(400, "用户名或密码不能为空");
        }

        Map<String, String> map = loginService.login(username, password);
        if (map == null || map.isEmpty()) {
            return R.error(401, "用户名或密码错误");
        }
        return R.success(map, "登录成功！");
    }
}
