package com.example.demo.form;

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
    private String accountId;
    //    @HalfAlphameric TODO: 自作アノテーションがどこにあるか探す

    @NotBlank
    private String password;
    //    @Password TODO: 自作アノテーションがどこにあるか探す

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
