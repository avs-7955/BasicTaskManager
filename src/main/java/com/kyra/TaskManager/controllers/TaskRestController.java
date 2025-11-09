package com.kyra.TaskManager.controllers;

import com.kyra.TaskManager.converters.TaskMapper;
import com.kyra.TaskManager.db.Task;
import com.kyra.TaskManager.dto.TaskDTO;
import com.kyra.TaskManager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@ResponseBody
@RequestMapping("/api/tasks")
public class TaskRestController {

    private final TaskService service;
    private final TaskMapper mapper;

    public TaskRestController(TaskService service, TaskMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(mapper.getTaskDTOs(service.getAllTasks()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        TaskDTO task = mapper.getTaskDTO(service.getTaskById(id));
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO reqTask) {
        Task task = mapper.getTask(reqTask);
        return new ResponseEntity<>(mapper.getTaskDTO(service.createTask(task)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id,
                                              @Valid @RequestBody TaskDTO reqTask) {
        Task task = mapper.getTask(reqTask);
        return ResponseEntity.ok(mapper.getTaskDTO(service.updateTask(id, task)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id) {
        Task task = service.getTaskById(id);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteTaskById(id);
        return ResponseEntity.ok(true);
    }


    // EXCEPTION HANDLER FOR ONLY THIS REST CONTROLLER
//    @ExceptionHandler(ResourceNotFound.class)
//    public ResponseEntity<String> handleTaskNotFound(ResourceNotFound ex) {
//        return new ResponseEntity<>("Task not found.", HttpStatus.NOT_FOUND);
//    }
}
