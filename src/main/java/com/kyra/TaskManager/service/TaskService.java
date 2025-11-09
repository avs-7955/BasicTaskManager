package com.kyra.TaskManager.service;

import com.kyra.TaskManager.db.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskById(Long id);

    void deleteTaskById(Long id);

    Task updateTask(Long id, Task updatedTask);

    Task createTask(Task task);
}
