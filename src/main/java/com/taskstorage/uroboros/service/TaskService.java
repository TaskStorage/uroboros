package com.taskstorage.uroboros.service;

import com.taskstorage.uroboros.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> selectAll();
    Task selectById(Long id);
    void createTask(Task task);
    void updateTask(Task task);
    void deleteTask(Long id);
}
