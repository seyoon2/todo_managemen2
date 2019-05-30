package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザー登録サービス。
 */
@Service
public class UserRegisterService {

    /**
     * ユーザーリポジトリ
     */
    private final UserRepository userRepository;
    /**
     * パスワードエンコーダー
     */
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 登録処理。
     *
     * @param user        登録対象のユーザー
     * @param rawPassword 暗号化前のパスワード
     */
    @Transactional
    public void register(User user, String rawPassword) {
        // パスワードの暗号化
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    /**
     * アカウントIDの重複精査。
     *
     * @param accountId 精査対象のアカウントID
     * @return true:未存在 false:存在
     */
    @Transactional(readOnly = true)
    public boolean isExistsAccountId(String accountId) {
        int result = userRepository.countByAccountId(accountId);
        return result != 0;
    }
}
