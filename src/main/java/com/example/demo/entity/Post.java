package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "issue_person_id")
    private int issuePersonId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getIssuePersonId() {
        return issuePersonId;
    }

    public void setIssuePersonId(int issuePersonId) {
        this.issuePersonId = issuePersonId;
    }
}
