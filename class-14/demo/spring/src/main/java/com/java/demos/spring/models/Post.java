package com.java.demos.spring.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table(name = "blog_post") // changes the table name at the database level
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // represents the id of the author at the database level
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    private String content;

    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    // default constructor
    // AKA
    // no args constructor
    public Post() {
    }

    public Post(AppUser appUser, String content) {
        this.appUser = appUser;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public AppUser getUser() {
        return appUser;
    }

    public String getContent() {
        return content;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
