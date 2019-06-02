package com.example.demo.form;

import com.example.demo.validator.Password;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class UserUpdatePasswordForm implements Serializable {

    /**
     * フィールド
     */
    @NotBlank
    private String currentPassword;

    @NotBlank
    @Password
    private String newPassword;

    @NotBlank
    private String confirmPassword;

    /**
     * パスワード精査
     */
    @AssertTrue(message = "パスワードが一致しません。")
    public boolean isPasswordValid() {
        return Objects.nonNull(newPassword) && newPassword.equals(confirmPassword);
    }

    @AssertTrue(message = "パスワードが変更されていません。")
    public boolean isNewPasswordValid() {
        return Objects.nonNull(currentPassword) && !currentPassword.equals(newPassword);
    }

    /**
     * Getter & Setter
     */
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
