package com.taskstorage.uroboros.model;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String description;
    private String content;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User author;

    public Task() {
    }

//    public Task(String description, String content, User author) {
//        this.description = description;
//        this.content = content;
//        this.author = author;
//    }
//    public Task(Long id, String description, String content, User author) {
//        this.id = id;
//        this.description = description;
//        this.content = content;
//        this.author = author;
//    }

    public Task(String description, String content, User author) {
        this.description = description;
        this.content = content;
    }
    public Task(Long id, String description, String content, User author) {
        this.id = id;
        this.description = description;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public User getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
//    }
}
