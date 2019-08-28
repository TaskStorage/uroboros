package com.taskstorage.uroboros.controller;

import com.taskstorage.uroboros.model.Task;
import com.taskstorage.uroboros.model.User;
import com.taskstorage.uroboros.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@PropertySource("classpath:application.properties")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/tasks")
    public String listTasks(@RequestParam(required = false, defaultValue = "") String searchTag, Model model) {

        List<Task> tasks;

        if (searchTag != null && !searchTag.isEmpty()) {
            tasks = taskRepository.findByDescriptionContainingOrContentContaining(searchTag);
        } else {
            tasks = taskRepository.selectAll();
        }

        model.addAttribute("tasks", tasks);
		model.addAttribute("searchTag", searchTag);
        return "tasks";
    }

    @PostMapping("/createTask")
    public String create(@AuthenticationPrincipal User user,
                         @Valid Task task,
                         BindingResult bindingResult,
                         Model model,
                         @RequestParam("file") MultipartFile file) throws IOException {
        task.setAuthor(user);

        if (bindingResult.hasErrors()) {
            //Смотри ControllerUtils
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            //Добавляем ошибки в модель
            model.mergeAttributes(errorsMap);
            //Заполняем поля в форме добавления чтоб не вводить заново
            model.addAttribute("task", task);
            // Вытягиваем все объекты из репозитория и кладём в модель
            List<Task> tasks = taskRepository.selectAll();
            model.addAttribute("tasks", tasks);
            //Возвращаем модель
            return "tasks";

        } else {
            //Если ошибок валидации нет - редиректим чтоб сообщение не дублировалось при перезагрузке
            saveFile(task, file);
            taskRepository.createTask(task);
            return "redirect:/tasks";
        }
    }

    private void saveFile(@Valid Task task, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            task.setFilename(resultFilename);
        }
    }

    @PostMapping("/deleteTask/{id}")
    public String delete(@PathVariable Long id) {
        fileDelete(id);
        taskRepository.deleteTask(id);
        return "redirect:/tasks";
    }

    private void fileDelete(Long id) {
        Task parentTask = taskRepository.selectById(id);
        if (parentTask !=null){
            String fileToDelete = parentTask.getFilename();
            if (fileToDelete != null){
                File file = new File(uploadPath + "/" + fileToDelete);
                file.delete();}
        }
    }

    @GetMapping("/tasks/edit/{id}")
    public String getTask(Model model, @PathVariable Long id) {

        List<Task> tasks = taskRepository.selectAll();
        Task currentTask = taskRepository.selectById(id);

        model.addAttribute("tasks", tasks);
        model.addAttribute("task", currentTask);
        return "tasks";
    }

    @PostMapping("/tasks/edit/{id}")
    public String updateTask(
            @PathVariable Long id,
            @RequestParam("description") String description,
            @RequestParam("content") String content,
            @RequestParam("file") MultipartFile file) throws IOException {

        Task currentTask = taskRepository.selectById(id);
            if(!StringUtils.isEmpty(description)) {
                currentTask.setDescription(description);
            }
            if(!StringUtils.isEmpty(content)) {
                currentTask.setContent(content);
            }
            fileDelete(currentTask.getId());
            currentTask.setFilename(null);
            if (!file.getOriginalFilename().isEmpty())
            {
            saveFile(currentTask, file);
            }

            taskRepository.updateTask(currentTask);

        return "redirect:/tasks/";
    }
    //TODO Personal tasks
}



