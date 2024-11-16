package cn.beichuan.famous_site_server.controller;

import cn.beichuan.famous_site_server.models.Post;
import cn.beichuan.famous_site_server.service.PostService;
import cn.beichuan.famous_site_server.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    /// 获取所有帖子
    @GetMapping("/all")
    public R<List<Post>> allList() {
        List<Post> posts = postService.list();
        return R.success(posts, "帖子 -- 获取所有帖子");
    }

    /// 单个帖子
    @GetMapping("/{id}")
    public R<Post> getById(@PathVariable("id") String id) {
        Post post = postService.getById(id);
        return R.success(post, "帖子 -- 获取单个帖子");
    }
}
