package com.taskstorage.uroboros.controller;

import com.taskstorage.uroboros.model.Task;
import com.taskstorage.uroboros.model.User;
import com.taskstorage.uroboros.repository.TaskRepository;
import com.taskstorage.uroboros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@PreAuthorize("hasAuthority('ADMIN')")
@PropertySource("classpath:application.properties")
public class ManageController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    //Получаем все
    @GetMapping("/manage")
    public String allTasks(@RequestParam(required = false, defaultValue = "") String searchTag, Model model) {

        Iterable<Task> tasks;

        List<User> users = userService.selectAll();

        if (searchTag != null && !searchTag.isEmpty()) {
            tasks = taskRepository.findByDescriptionContainingOrContentContaining(searchTag);
        } else {
            tasks = taskRepository.selectAll();
        }
        model.addAttribute("users", users);
        model.addAttribute("tasks", tasks);
        model.addAttribute("searchTag", searchTag);

        return "manageTasks";
    }

    //Создаём новые
    @PostMapping("/manage/create")
    public String create(@RequestParam("username") String username,
                         @Valid Task task,
                         BindingResult bindingResult,
                         Model model,
                         @RequestParam("file") MultipartFile file) throws IOException {

        User user = userService.selectByUsername(username);

        task.setAuthor(user);

        if (bindingResult.hasErrors()) {
            //Смотри ControllerUtils
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            //Добавляем ошибки в модель
            model.mergeAttributes(errorsMap);
            //Заполняем поля в форме добавления чтоб не вводить заново
            model.addAttribute("task", task);
            // Вытягиваем все объекты из репозитория и кладём в модель
            List<Task> tasks = tasks = taskRepository.selectAll();
            model.addAttribute("tasks", tasks);
            //Возвращаем модель
            return "manageTasks";

        } else {
            //Если ошибок валидации нет - редиректим чтоб сообщение не дублировалось при перезагрузке
            saveFile(task, file);
            taskRepository.createTask(task);
            return "redirect:/manage";
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

    @PostMapping("/manage/delete/{id}")
    public String delete(@PathVariable Long id) {

            fileDelete(id);
            taskRepository.deleteTask(id);

        return "redirect:/manage";
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
//
//    @GetMapping("/tasks/edit/{id}")
//    public String getTask(@AuthenticationPrincipal User user, Model model, @PathVariable Long id) {
//
//        List<Task> tasks = taskRepository.selectByUser(user);
//        Task currentTask = taskRepository.selectById(id);
//        model.addAttribute("tasks", tasks);
//
//        if (tasks.contains(currentTask) || user.isAdmin()) {
//            model.addAttribute("task", currentTask);
//            return "tasks";
//        }
//        return "tasks";
//    }
//
//    @PostMapping("/tasks/edit/{id}")
//    public String updateTask(
//            @AuthenticationPrincipal User user,
//            @PathVariable Long id,
//            @RequestParam("description") String description,
//            @RequestParam("content") String content,
//            @RequestParam("file") MultipartFile file) throws IOException {
//
//        List<Task> tasks = taskRepository.selectByUser(user);
//        Task currentTask = taskRepository.selectById(id);
//
//        if (tasks.contains(currentTask) || user.isAdmin()) {
//            if(!StringUtils.isEmpty(description)) {
//                currentTask.setDescription(description);
//            }
//            if(!StringUtils.isEmpty(content)) {
//                currentTask.setContent(content);
//            }
//            fileDelete(currentTask.getId());
//            currentTask.setFilename(null);
//            if (!file.getOriginalFilename().isEmpty()) {
//                saveFile(currentTask, file);
//            }
//            taskRepository.updateTask(currentTask);
//        }
//        return "redirect:/tasks/";
//    }
//
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/manage")
//    public String allTasks(@RequestParam(required = false, defaultValue = "") String searchTag, Model model) {
//
//        Iterable<Task> tasks;
//
//        if (searchTag != null && !searchTag.isEmpty()) {
//            tasks = taskRepository.findByDescriptionContainingOrContentContaining(searchTag);
//        } else {
//            tasks = taskRepository.selectAll();
//        }
//
//        model.addAttribute("tasks", tasks);
//        model.addAttribute("searchTag", searchTag);
//        return "tasks";
//    }
//
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/manage/{userId}")
//    public String personalTasks(
//            @PathVariable Long userId,
//            @RequestParam(required = false, defaultValue = "") String searchTag,
//            Model model,
//            @RequestParam(required = false) Task task
//
//    ) {
//        Iterable<Task> tasks;
//        User user = userRepository.selectById(userId);
//
//        if (searchTag != null && !searchTag.isEmpty()) {
//            tasks = taskRepository.findByDescriptionContainingAndAuthorOrContentContainingAndAuthor(searchTag, user);
//        } else {
//            tasks = user.getTasks();
//        }
//
//        model.addAttribute("tasks", tasks);
//        model.addAttribute("task", task);
//
//        return "userTasks";
//    }
}



