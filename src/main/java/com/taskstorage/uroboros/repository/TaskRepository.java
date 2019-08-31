package com.taskstorage.uroboros.repository;

import com.taskstorage.uroboros.model.Task;
import com.taskstorage.uroboros.model.User;

import java.util.List;

public interface TaskRepository {
    List<Task> selectAll();
    List<Task> selectByUser(User user);
    Task selectById(Long id);
    void createTask(Task task);
    void updateTask(Task task);
    void deleteTask(Long id);

    List<Task> findByDescriptionContainingOrContentContaining(String searchTag);

    List<Task> findByDescriptionContainingAndAuthorOrContentContainingAndAuthor(String searchTag, User user);
}
