package cn.beichuan.famous_site_server.service.impl;

import cn.beichuan.famous_site_server.mapper.UserMapper;
import cn.beichuan.famous_site_server.models.User;
import cn.beichuan.famous_site_server.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/// 用户服务层
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
