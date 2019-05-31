package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 投稿登録フォーム
 */
public class PostRegisterForm {
    @NotBlank
    @Size(max = 45)
    private String title;

    @NotBlank
    private String detail;

    /**
     * Getter & Setter
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
