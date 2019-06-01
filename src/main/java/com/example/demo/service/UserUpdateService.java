package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;
import com.example.demo.form.UserUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * ユーザー更新サービス。
 */
@Service
public class UserUpdateService {
    /**
     * ユーザーリポジトリ
     */
    private final UserRepository userRepository;
    /**
     * パスワードエンコーダー
     */
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserUpdateService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * ユーザー最新データ取得処理。
     *
     * @param id ID
     * @return ユーザーエンティティ
     */
    @Transactional(readOnly = true)
    public User getAccountById(int id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElseThrow(() -> new RuntimeException("account is not found"));
    }

    /**
     * ユーザー情報の更新有無精査。
     *
     * @param userUpdateForm 精査対象のアカウント
     * @param targetUser     更新対象のアカウント
     * @return true:更新なし false:更新あり
     */

    public boolean isNoChange(UserUpdateForm userUpdateForm, User targetUser) {
        return userUpdateForm.getAccountId().equals(targetUser.getAccountId());
    }

    /**
     * ユーザーIDの重複精査。
     *
     * @param accountId 精査対象のアカウントID
     * @return true:存在 false:未存在
     */
    @Transactional(readOnly = true)
    public boolean isExistsAccountId(String accountId) {
        // アカウントIDの重複精査
        int result = userRepository.countByAccountId(accountId);
        return result != 0;
    }

    /**
     * 更新処理。
     *
     * @param user 更新対象のアカウント
     */
    @Transactional
    public void updateAccountById(User user) {
        userRepository.save(user);
    }

    /**
     * 現在のパスワード一致精査。
     *
     * @param id                 アカウントの主キー
     * @param rawCurrentPassword 入力された現在のパスワード
     * @return 精査結果
     */
    public boolean validCurrentPassword(int id, String rawCurrentPassword) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("account is not found"));
        return passwordEncoder.matches(rawCurrentPassword, user.getPassword());
    }

    /**
     * パスワード更新処理。
     *
     * @param id             アカウントの主キー
     * @param rawNewPassword 入力された新しいパスワード
     */
    public void updatePassword(int id, String rawNewPassword) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("account is not found"));
        user.setPassword(passwordEncoder.encode(rawNewPassword));
        userRepository.save(user);
    }

}
