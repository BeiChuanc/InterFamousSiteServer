package cn.beichuan.famous_site_server.controller;

import cn.beichuan.famous_site_server.service.MinioService;
import cn.beichuan.famous_site_server.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/minio")
public class MinioController {

  @Autowired
  private MinioService minioService;

  /**
   * 上传文件
   */
  @PostMapping("/upload")
  public R<String> upload(@RequestParam("file") MultipartFile file) {
    try {
      String url = minioService.uploadFile(file);
      return R.success(url);
    } catch (Exception e) {
      return R.error("文件上传失败：" + e.getMessage());
    }
  }

  /**
   * 删除文件
   */
  @DeleteMapping("/{objectName}")
  public R<Void> delete(@PathVariable String objectName) {
    try {
      minioService.deleteFile(objectName);
      return R.success("文件删除成功！");
    } catch (Exception e) {
      return R.error("文件删除失败：" + e.getMessage());
    }
  }

  /**
   * 获取文件访问URL
   */
  @GetMapping("/{objectName}")
  public R<String> getUrl(@PathVariable String objectName) {
    try {
      String url = minioService.getFileUrl(objectName);
      return R.success(url);
    } catch (Exception e) {
      return R.error("获取文件访问路径失败：" + e.getMessage());
    }
  }
}
