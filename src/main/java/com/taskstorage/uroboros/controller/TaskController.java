package com.taskstorage.uroboros.controller;

import com.taskstorage.uroboros.model.Task;
import com.taskstorage.uroboros.model.User;
import com.taskstorage.uroboros.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public String listTasks(@RequestParam(required = false, defaultValue = "") String searchTag, Model model) {

        List<Task> tasks;

        if (searchTag != null && !searchTag.isEmpty()) {
            tasks = taskRepository.findByDescriptionContainingOrContentContaining(searchTag);
        } else {
            tasks = taskRepository.selectAll();
        }

        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    //TODO File attach
    @PostMapping("/createTask")
    public String create(@AuthenticationPrincipal User user, Model model, Task task) {
        task.setAuthor(user);
        taskRepository.createTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/deleteTask/{id}")
    public String delete(@PathVariable Long id) {
        taskRepository.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String getTask(Model model, @PathVariable Long id) {

        List<Task> tasks = taskRepository.selectAll();
        Task currentTask = taskRepository.selectById(id);

        model.addAttribute("tasks", tasks);
        model.addAttribute("currentTask", currentTask);
        return "tasks";
    }

    @PostMapping("/tasks/edit/{id}")
    public String updateTask(
            @PathVariable Long id,
            @RequestParam("description") String description,
            @RequestParam("content") String content) throws IOException {

        Task currentTask = taskRepository.selectById(id);
            if(!StringUtils.isEmpty(description)) {
                currentTask.setDescription(description);
            }
            if(!StringUtils.isEmpty(content)) {
                currentTask.setContent(content);
            }
            taskRepository.updateTask(currentTask);

        return "redirect:/tasks/";
    }
}



