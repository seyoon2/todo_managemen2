package com.example.demo.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 投稿リポジトリ
 */
public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {
}
