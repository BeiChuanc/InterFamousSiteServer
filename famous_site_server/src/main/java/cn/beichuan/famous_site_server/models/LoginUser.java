package cn.beichuan.famous_site_server.models;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/// 登录用户类
@Data
@TableName("user")
public class LoginUser {
    private String id;
    /// 用户名
    private String username;
    /// 邮箱
    private String email;
    /// 手机号
    private String phone;
    /// 密码
    private String password;
}
