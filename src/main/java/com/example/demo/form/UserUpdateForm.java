package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserUpdateForm implements Serializable {
    @NotBlank
    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
