package com.java.demos.spring.models.dto;

public class PostDTO {
    private String user;
    private String content;

    public PostDTO() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
