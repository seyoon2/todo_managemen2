package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostRepository;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * 投稿登録サービス。
 */
@Service
@Transactional
public class PostRegisterService {

    /**
     * 投稿リポジトリ
     */
    private final PostRepository postRepository;
    /**
     * ユーザーリポジトリ
     */
    private final UserRepository userRepository;

    @Autowired
    public PostRegisterService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * 投稿登録処理。
     */
    public void register(Post post) {
        postRepository.save(post);
    }

    /**
     * 全ユーザーを検索する。
     *
     * @return 全アカウントのリスト
     */
    public List<User> findAllAccount() {
        return userRepository.findAllAccount();
    }

    /**
     * ユーザーの主キー検索。
     *
     * @param id ID
     * @return ユーザーエンティティ
     */
    public Optional<User> findAccountById(int id) {
        return userRepository.findById(id);
    }

}