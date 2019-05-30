package com.example.demo.form;

import com.example.demo.validator.HalfAlphameric;
import com.example.demo.validator.Password;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * ユーザー登録フォーム
 */
public class UserRegisterForm implements Serializable {
    @NotBlank
    @Size(min = 3, max = 20, message = "error.size.min.max")
    @HalfAlphameric
    private String accountId;

    @NotBlank
    @Password
    private String password;

    @NotBlank
    private String confirmPassword;

    @AssertTrue(message = "パスワードが一致しません。")
    public boolean isPasswordValid() {
        return Objects.nonNull(password) && password.equals(confirmPassword);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
