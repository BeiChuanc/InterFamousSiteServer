package cn.beichuan.famous_site_server.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {
  /**
   * 上传文件
   * 
   * @param file 文件
   * @return 文件访问URL
   */
  String uploadFile(MultipartFile file);

  /**
   * 删除文件
   * 
   * @param objectName 对象名称（文件路径）
   */
  void deleteFile(String objectName);

  /**
   * 获取文件访问URL
   * 
   * @param objectName 对象名称（文件路径）
   * @return 文件访问URL
   */
  String getFileUrl(String objectName);
}
