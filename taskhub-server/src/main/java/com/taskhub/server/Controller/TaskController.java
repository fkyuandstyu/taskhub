package com.taskhub.server.controller;

import com.taskhub.server.entity.Task;
import com.taskhub.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task add(@RequestBody Task task) {
        return taskService.createTask(task);
    }
}