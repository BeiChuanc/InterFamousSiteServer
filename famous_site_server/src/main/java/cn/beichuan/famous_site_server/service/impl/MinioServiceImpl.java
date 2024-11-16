package cn.beichuan.famous_site_server.service.impl;

import cn.beichuan.famous_site_server.service.MinioService;
import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MinioServiceImpl implements MinioService {

  @Autowired
  private MinioClient minioClient;

  @Value("${minio.bucketName}")
  private String bucketName;

  @Value("${minio.endpoint}")
  private String endpoint;

  @Override
  public String uploadFile(MultipartFile file) {
    try {
      // 生成文件路径
      LocalDate now = LocalDate.now();
      String datePath = String.format("/%d/%02d/%02d",
          now.getYear(),
          now.getMonthValue(),
          now.getDayOfMonth());

      // 获取文件名和扩展名
      String originalFilename = file.getOriginalFilename();
      String extension = "";
      int lastDotIndex = originalFilename.lastIndexOf(".");
      if (lastDotIndex > 0) {
        extension = originalFilename.substring(lastDotIndex);
      }

      // 生成唯一文件名
      String objectName = datePath + "/" + UUID.randomUUID() + extension;

      // 上传文件
      PutObjectArgs putObjectArgs = PutObjectArgs.builder()
          .bucket(bucketName)
          .object(objectName)
          .stream(file.getInputStream(), file.getSize(), -1)
          .contentType(file.getContentType())
          .build();

      minioClient.putObject(putObjectArgs);

      // 返回文件访问路径
      return endpoint + "/" + bucketName + "/" + objectName;
    } catch (Exception e) {
      log.error("文件上传失败", e);
      throw new RuntimeException("文件上传失败");
    }
  }

  @Override
  public void deleteFile(String objectName) {
    try {
      minioClient.removeObject(
          RemoveObjectArgs.builder()
              .bucket(bucketName)
              .object(objectName)
              .build());
    } catch (Exception e) {
      log.error("文件删除失败", e);
      throw new RuntimeException("文件删除失败");
    }
  }

  @Override
  public String getFileUrl(String objectName) {
    try {
      return minioClient.getPresignedObjectUrl(
          GetPresignedObjectUrlArgs.builder()
              .method(Method.GET)
              .bucket(bucketName)
              .object(objectName)
              .expiry(7, TimeUnit.DAYS)
              .build());
    } catch (Exception e) {
      log.error("获取文件访问路径失败", e);
      throw new RuntimeException("获取文件访问路径失败");
    }
  }
}
