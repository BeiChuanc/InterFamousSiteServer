package cn.beichuan.famous_site_server.service;

import cn.beichuan.famous_site_server.models.LoginUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/// 登录服务层
public interface LoginService extends IService<LoginUser> {
    /// 用户登录
    Map<String, String> login(String username, String password);
}
