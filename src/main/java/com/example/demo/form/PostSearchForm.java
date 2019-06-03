package com.example.demo.form;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 投稿検索フォーム
 */
public class PostSearchForm implements Serializable {
    @Size(max = 45, message = "{error.size.max}")
    private String title;

    @Size(max = 100, message = "{error.size.max}")
    private String detail;

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
