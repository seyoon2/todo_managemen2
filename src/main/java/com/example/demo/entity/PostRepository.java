package com.example.demo.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 投稿リポジトリ
 */
public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {
    /**
     * セッションユーザーIDに紐づく投稿を検索する。
     *
     * @param id ユーザーID（プライマリキー）
     * @return Post[] 投稿
     */
    @Query(value = "SELECT * FROM posts WHERE poster_id = :id", nativeQuery = true)
    List<Post> findByAccountId(@Param("id") int id);
}
