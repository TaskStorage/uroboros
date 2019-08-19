package com.taskstorage.uroboros.controller;

import com.taskstorage.uroboros.model.Task;
import com.taskstorage.uroboros.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TaskService taskService;

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        List<Task> tasks = taskService.selectAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
    //TODO "@AuthenticationPrincipal User user" - author
    //TODO File attach
    @PostMapping("/createTask")
    public String create(Model model, Task task) {
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/deleteTask/{id}")
    public String delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String getTask(Model model, @PathVariable Long id) {

        List<Task> tasks = taskService.selectAll();
        Task currentTask = taskService.selectById(id);

        model.addAttribute("tasks", tasks);
        model.addAttribute("currentTask", currentTask);
        return "tasks";
    }

    @PostMapping("/tasks/edit/{id}")
    public String updateTask(
            @PathVariable Long id,
            @RequestParam("description") String description,
            @RequestParam("content") String content) throws IOException {

        Task currentTask = taskService.selectById(id);
            if(!StringUtils.isEmpty(description)) {
                currentTask.setDescription(description);
            }
            if(!StringUtils.isEmpty(content)) {
                currentTask.setContent(content);
            }
            taskService.updateTask(currentTask);

        return "redirect:/tasks/";
    }
}



