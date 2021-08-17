package com.example.codefellowship;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private Date createdAt;

    @ManyToOne
    private ApplicationUser user;

    public Post(){

    }

    public Post(String body, Date createdAt, ApplicationUser user) {
        this.body = body;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Post(String body, Date createdAt) {
        this.body = body;
        this.createdAt = createdAt;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

//    public void setUser(ApplicationUser user) {
//        this.user = user;
//    }
//
//    public ApplicationUser getUser() {
//        return user;
//    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
