package com.taskstorage.uroboros.repository;

import com.taskstorage.uroboros.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    @Override
    public List<Task> selectAll() {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("Test","Task"));
        return tasks;
    }

    @Override
    public Task selectById(Long id) {
        return null;
    }

    @Override
    public void createTask(Task task) {

    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void deleteTask(Long id) {

    }
}
