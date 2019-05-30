package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * アカウントIDに紐づくユーザーを検索する。
     *
     * @param accountId アカウントID
     * @return ユーザー
     */
    @Query(value = "SELECT * FROM users WHERE account_id = :account_id", nativeQuery = true)
    User findByAccountId(@Param("account_id") String accountId);

    /**
     * アカウントIDに紐づくユーザーの件数を取得する。
     *
     * @param accountId アカウントID
     * @return 件数
     */
    @Query(value = "SELECT COUNT(*) FROM users WHERE account_id = :account_id", nativeQuery = true)
    int countByAccountId(@Param("account_id") String accountId);

    /**
     * 全アカウントを検索する。
     *
     * @return 全アカウントのリスト
     */
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findAllAccount();
}
