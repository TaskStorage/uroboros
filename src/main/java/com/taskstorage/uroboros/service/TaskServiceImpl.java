package com.taskstorage.uroboros.service;

import com.taskstorage.uroboros.model.Task;
import com.taskstorage.uroboros.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional
    public List<Task> selectAll() {
        return taskRepository.selectAll();
    }

    @Override
    @Transactional
    public Task selectById(Long id) {
        return taskRepository.selectById(id);
    }

    @Override
    @Transactional
    public void createTask(Task task) {
        taskRepository.createTask(task);
    }

    @Override
    @Transactional
    public void updateTask(Task task) {
        taskRepository.updateTask(task);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }
}
