package cn.beichuan.famous_site_server.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.beichuan.famous_site_server.mapper.LoginMapper;
import cn.beichuan.famous_site_server.models.LoginUser;
import cn.beichuan.famous_site_server.service.LoginService;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, LoginUser> implements LoginService {

    @Override
    public Map<String, String> login(String username, String password) {
        // 查找用户
        LambdaQueryChainWrapper<LoginUser> query = this.query().lambda();
        query.select(LoginUser::getId, LoginUser::getUsername, LoginUser::getPassword, LoginUser::getEmail,
                LoginUser::getPhone);
        query.eq(LoginUser::getUsername, username).eq(LoginUser::getPassword, password);
        LoginUser loginUser = baseMapper.selectOne(query.getWrapper());
        if (loginUser == null) {
            return null;
        }
        // 授权信息
        StpUtil.login(loginUser.getId());
        String token = StpUtil.getTokenValue();
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
