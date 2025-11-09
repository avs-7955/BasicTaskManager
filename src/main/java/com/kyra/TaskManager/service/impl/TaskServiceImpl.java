package com.kyra.TaskManager.service.impl;

import com.kyra.TaskManager.db.Task;
import com.kyra.TaskManager.exceptions.ResourceNotFound;
import com.kyra.TaskManager.repository.TaskRepository;
import com.kyra.TaskManager.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Task not found with id: " + id));
    }

    @Override
    public void deleteTaskById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {
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
    public Task createTask(Task task) {
        return repository.save(task);
    }
}
