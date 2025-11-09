package com.kyra.TaskManager.service.impl;

import com.kyra.TaskManager.db.Task;
import com.kyra.TaskManager.db.enums.Priority;
import com.kyra.TaskManager.db.enums.TaskStatus;
import com.kyra.TaskManager.exceptions.ResourceNotFound;
import com.kyra.TaskManager.repository.TaskRepository;
import com.kyra.TaskManager.service.TaskService;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final int PAGE_SIZE = 5;
    private final String sortByParam = "title";

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Page<Task> getAllTasksByPage(@NonNull Long pageNumber,
                                        @Nullable Priority priority,
                                        @Nullable TaskStatus status) {
        Pageable p = PageRequest.of(pageNumber.intValue(), PAGE_SIZE, Sort.by(Sort.Direction.ASC, sortByParam));
        if (Objects.isNull(priority) && Objects.isNull(status)) {
            return repository.findAll(p);
        } else if (Objects.nonNull(priority) && Objects.isNull(status)) {
            return repository.findAllByPriority(priority, p);
        } else if (Objects.isNull(priority)) {
            return repository.findAllByStatus(status, p);
        } else {
            return repository.findAllByPriorityAndStatus(priority, status, p);
        }
    }

    @Override
    public Task getTaskById(@NonNull Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Task not found with id: " + id));
    }

    @Override
    public void deleteTaskById(@NonNull Long id) {
        repository.deleteById(id);
    }

    @Override
    public Task updateTask(@NonNull Long id, @NonNull Task updatedTask) {
        Task task = getTaskById(id);
        if (Objects.nonNull(task)) {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            task.setPriority(updatedTask.getPriority());
            task.setDueDate(updatedTask.getDueDate());
            return repository.save(task);
        } else {
            throw new ResourceNotFound("Task not found with id: " + id);
        }
    }

    @Override
    public Task createTask(@NonNull Task task) {
        return repository.save(task);
    }
}
