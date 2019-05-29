package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * アカウントIDに紐づくアカウントを検索する。
     *
     * @param accountId アカウントID
     * @return アカウント
     */
    // TODO: Todo_managementでは、delete_flagはどうなっているか確認
    @Query(value = "SELECT * FROM users WHERE accountId = :accountId", nativeQuery = true)
    User findByAccountId(@Param("accountId") String accountId);

    /**
     * アカウントIDに紐づくアカウントの件数を取得する。
     *
     * @param accountId アカウントID
     * @return 件数
     */
    @Query(value = "SELECT COUNT(*) FROM users WHERE accountId = :accountId", nativeQuery = true)
    int countByAccountId(@Param("accountId") String accountId);

    /**
     * 全アカウントを検索する。
     *
     * @return 全アカウントのリスト
     */
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findAllAccount();
}
