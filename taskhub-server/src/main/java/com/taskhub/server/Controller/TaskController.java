package com.taskhub.server.controller;

import com.taskhub.server.entity.Task;
import com.taskhub.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks") // 加上 api 前綴是專業做法
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping // 當有人用 POST 方法發送資料到 /api/tasks 時
    public Task createTask(@RequestBody Task task) {
        // @RequestBody 會自動把瀏覽器傳來的 JSON 轉成 Task 物件
        return taskService.saveTask(task);
    }
}