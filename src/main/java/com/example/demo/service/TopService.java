package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * トップサービス
 */
@Service
@Transactional
public class TopService {

    /**
     * ユーザーリポジトリ
     */
    private final UserRepository userRepository;

    @Autowired
    public TopService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * アカウント最新データ取得処理。
     *
     * @param id アカウントの主キー
     * @return アカウント
     */
    public User getAccountById(int id) {
        // 取れないことは考慮しない。
        return userRepository.findById(id).get();
    }
}
