package com.kyra.TaskManager.service;

import com.kyra.TaskManager.db.Task;
import com.kyra.TaskManager.db.enums.Priority;
import com.kyra.TaskManager.db.enums.TaskStatus;
import lombok.NonNull;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Page<Task> getAllTasksByPage(Long pageNumber, Priority priority, TaskStatus status);

    Task getTaskById(@NonNull Long id);

    void deleteTaskById(@NonNull Long id);

    Task updateTask(@NonNull Long id, @NonNull Task updatedTask);

    Task createTask(@NonNull Task task);
}
