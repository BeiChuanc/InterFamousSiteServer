package cn.beichuan.famous_site_server.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.beichuan.famous_site_server.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理器
 */
@Slf4j
@ResponseBody
@ControllerAdvice(annotations = { RestController.class })
public class GlobalExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public R<String> exceptionHandler(NotLoginException e) {
        log.error(e.getMessage());
        return R.error(401, "无效token");
    }

    @ExceptionHandler(Exception.class)
    public R<String> exceptionHandler(Exception e) {
        log.error("未处理错误: {}", e.getMessage(), e);
        return R.error(0, "未处理错误: " + e.getMessage());
    }
}
