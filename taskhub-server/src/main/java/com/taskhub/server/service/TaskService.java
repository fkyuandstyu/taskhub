package com.taskhub.server.service;

import com.taskhub.server.entity.Task;
import com.taskhub.server.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // 取得所有任務
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        // 呼叫倉庫的 save 方法，它會自動判斷要執行 INSERT 還是 UPDATE
        return taskRepository.save(task);
    }
}