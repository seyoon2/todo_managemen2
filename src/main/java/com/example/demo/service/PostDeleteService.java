package com.example.demo.service;

import com.example.demo.entity.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 投稿削除機能
 */
@Service
@Transactional
public class PostDeleteService {

    /**
     * 投稿リポジトリ
     */
    private PostRepository postRepository;

    @Autowired
    public PostDeleteService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 削除処理
     *
     * @param id
     */
    public void delete(int id) {
        postRepository.deleteById(id);
    }

    /**
     * プライマリキーによる存在チェック
     *
     * @param id
     * @return true: 存在 false: 非存在
     */
    public boolean isExistsById(int id) {
        return postRepository.existsById(id);
    }
}
