package com.kyra.TaskManager.converters;

import com.kyra.TaskManager.db.Task;
import com.kyra.TaskManager.dto.TaskDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO getTaskDTO(Task task);

    Task getTask(TaskDTO taskDTO);

    List<TaskDTO> getTaskDTOs(List<Task> tasks);
}
