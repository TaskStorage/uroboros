package com.taskstorage.uroboros.repository;

import com.taskstorage.uroboros.model.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> selectAll();
    Task selectById(Long id);
    void createTask(Task task);
    void updateTask(Task task);
    void deleteTask(Long id);

    List<Task> findByDescriptionContainingOrContentContaining(String searchTag);
}
