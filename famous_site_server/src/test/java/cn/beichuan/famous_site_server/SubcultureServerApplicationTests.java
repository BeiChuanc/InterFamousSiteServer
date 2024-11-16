package cn.beichuan.famous_site_server;

import cn.beichuan.famous_site_server.mapper.UserMapper;
import cn.beichuan.famous_site_server.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FamousSiteServerApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test1() {
        System.out.println("---------  测试方法： Test1 -----------");
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
