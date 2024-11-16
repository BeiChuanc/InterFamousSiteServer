package cn.beichuan.famous_site_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.beichuan.famous_site_server.mapper")
public class FamousSiteServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamousSiteServerApplication.class, args);
    }

}
