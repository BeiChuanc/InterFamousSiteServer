package cn.beichuan.famous_site_server.models.vo;

import cn.beichuan.famous_site_server.models.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;

/// 帖子视图对象
@EqualsAndHashCode(callSuper = true)
@Data
public class PostVo extends Post {
    /// 点赞数
    private Integer likeCount;
    /// 评论数
    private Integer commentCount;
}
