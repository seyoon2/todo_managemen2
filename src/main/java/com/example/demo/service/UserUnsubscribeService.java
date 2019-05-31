package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 退会サービス。
 */
@Service
@Transactional
public class UserUnsubscribeService {

    /** アカウントリポジトリ */
    private final UserRepository userRepository;

    @Autowired
    public UserUnsubscribeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 退会処理。
     *
     * @param user 退会対象のアカウント
     */
    // TODO 動作未確認
    public void delete(User user) {
        user.setDeleteFlag(true);
        userRepository.save(user);
    }
}
