package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostListService {

    /**
     * 投稿リポジトリ
     */
    private final PostRepository postRepository;

    @Autowired
    public PostListService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public List<Post> getPostList(User user) {
        return postRepository.findByAccountId(user.getId());
    }
}
