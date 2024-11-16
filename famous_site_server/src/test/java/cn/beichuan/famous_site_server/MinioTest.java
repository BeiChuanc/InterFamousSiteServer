package cn.beichuan.famous_site_server;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MinioTest {

  @Autowired
  private MinioClient minioClient;

  @Value("${minio.bucketName}")
  private String bucketName;

  @Value("${minio.endpoint}")
  private String endpoint;

  @Test
  public void testUpload() {
    // 在测试开始时设置控制台编码
    System.setProperty("file.encoding", "UTF-8");
    try {
      // 这里填写您本地文件的路径
      String filePath = "B:\\1图片和视频\\one-hand.jpg";

      // 生成文件路径
      LocalDate now = LocalDate.now();
      String datePath = String.format("/%d/%02d/%02d",
          now.getYear(),
          now.getMonthValue(),
          now.getDayOfMonth());

      // 获取原始文件名和扩展名
      File file = new File(filePath);
      String originalFilename = file.getName();
      String extension = "";
      int lastDotIndex = originalFilename.lastIndexOf(".");
      if (lastDotIndex > 0) {
        extension = originalFilename.substring(lastDotIndex);
      }

      // 使用UUID生成唯一文件名
      String uniqueFileName = UUID.randomUUID().toString() + extension;

      // 组合完整的对象名称
      String objectName = datePath + "/" + uniqueFileName;

      FileInputStream fis = new FileInputStream(filePath);

      // 根据文件扩展名设置contentType
      String contentType = getContentType(extension);

      PutObjectArgs putObjectArgs = PutObjectArgs.builder()
          .bucket(bucketName)
          .object(objectName)
          .stream(fis, fis.available(), -1)
          .contentType(contentType)
          .build();

      minioClient.putObject(putObjectArgs);

      // 获取文件的访问URL（有效期7天）
      String url = minioClient.getPresignedObjectUrl(
          GetPresignedObjectUrlArgs.builder()
              .method(Method.GET)
              .bucket(bucketName)
              .object(objectName)
              .expiry(7, TimeUnit.DAYS)
              .build());

      System.out.println("文件上传成功！");
      System.out.println("对象名称: " + objectName);
      System.out.println("文件访问URL: " + url);

      String publicUrl = endpoint + "/" + bucketName + "/" + objectName;
      System.out.println("公共访问URL: " + publicUrl);

    } catch (Exception e) {
      System.err.println("上传失败：" + e.getMessage());
      e.printStackTrace();
    }
  }

  // 根据文件扩展名获取对应的ContentType
  private String getContentType(String extension) {
    switch (extension.toLowerCase()) {
      case ".jpg":
      case ".jpeg":
        return "image/jpeg";
      case ".png":
        return "image/png";
      case ".gif":
        return "image/gif";
      case ".mp4":
        return "video/mp4";
      case ".mp3":
        return "audio/mpeg";
      default:
        return "application/octet-stream";
    }
  }
}
