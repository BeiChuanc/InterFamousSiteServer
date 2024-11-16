package cn.beichuan.famous_site_server.service.impl;

import cn.beichuan.famous_site_server.mapper.PostMapper;
import cn.beichuan.famous_site_server.models.Post;
import cn.beichuan.famous_site_server.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
}
