package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostRepository;
import com.example.demo.entity.User;
import com.example.demo.form.PostRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.demo.service.PostSpecifications.detailContains;
import static com.example.demo.service.PostSpecifications.tittleContains;


/**
 * 投稿検索サービス
 */
@Service
@Transactional
public class PostSearchService {

    /**
     * 投稿リポジトリ
     */
    private final PostRepository postRepository;

    /**
     * HTTPセッション
     */
    private final HttpSession session;

    /**
     * セッションキー(ログインユーザのアカウント)
     */
    private static final String SESSION_FORM_ID = "account";

    @Autowired
    public PostSearchService(PostRepository postRepository, HttpSession session) {
        this.postRepository = postRepository;
        this.session = session;
    }

    /**
     * 検索条件から投稿を検索する
     *
     * @param form
     * @return 検索結果
     */
    @Transactional(readOnly = true)
    public List<Post> findPost(PostRegisterForm form) {
        return postRepository.findAll(
                Specification
                        .where(tittleContains(form.getTitle()))
                        .and(detailContains(form.getDetail()))
        );
    }

    @Transactional(readOnly = true)
    public List<Post> findPostById(int id) {
        return postRepository.findByAccountId(id);
    }

    /**
     * セッションユーザー取得処理。
     *
     * @return アカウント
     */
    public User getSesionUser() {
        return (User) session.getAttribute(SESSION_FORM_ID);
    }
}
