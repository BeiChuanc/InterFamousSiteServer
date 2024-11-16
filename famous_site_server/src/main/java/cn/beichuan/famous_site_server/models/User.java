package cn.beichuan.famous_site_server.models;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/// 用户实体类
@Data
@TableName("user")
public class User {
    private String id;
    /// 名字
    private String name;
    /// 头像地址
    private String avatar;
    /// 简介
    private String introduction;
    /// 邮箱
    private String email;
    /// 手机号
    private String phone;
    /// 创建时间
    private String createTime;
    /// 更新时间
    private String updateTime;
}
