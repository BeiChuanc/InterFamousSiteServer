package cn.beichuan.famous_site_server.controller;

import cn.beichuan.famous_site_server.models.User;
import cn.beichuan.famous_site_server.service.UserService;
import cn.beichuan.famous_site_server.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public R<List<User>> allList() {
        List<User> users = userService.list();

        return R.success(users, "成功！");
    }
}
