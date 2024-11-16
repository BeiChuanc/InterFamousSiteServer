package cn.beichuan.famous_site_server.models;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/// 帖子信息
@Data
@TableName("post")
public class Post {
    private String id;
    /// 用户ID
    private String uid;
    /// 标题
    private String title;
    /// 内容
    private String content;
    /// 媒体json，包括视频、图片
    private String medias;
    /// 创建时间
    private Date createTime;
    /// 更新时间
    private Date updateTime;
}
