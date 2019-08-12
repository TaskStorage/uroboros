package com.taskstorage.uroboros.model;

import java.util.Objects;

public class Task {

    private Long id;
    private String description;
    private String content;

    public Task() {
    }

    public Task(String description, String content) {
        this.id = null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(description, task.description) &&
                Objects.equals(content, task.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, content);
    }
}
