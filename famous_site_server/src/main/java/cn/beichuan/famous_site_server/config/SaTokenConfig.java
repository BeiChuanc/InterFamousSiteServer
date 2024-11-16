package cn.beichuan.famous_site_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;

/// SaToken配置类
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

  // 注册拦截器
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
    registry.addInterceptor(new SaInterceptor(_ -> StpUtil.checkLogin()))
        .addPathPatterns("/**")
        .excludePathPatterns("/api/v1/login");
  }
}
