package com.taskstorage.uroboros.controller;

import com.taskstorage.uroboros.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String tasks(Model model) {
        model.addAttribute("tasklist",taskRepository.selectAll());
        return "tasks";
    }
}
