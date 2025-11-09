package com.kyra.TaskManager.converters;

import com.kyra.TaskManager.db.Task;
import com.kyra.TaskManager.dto.TaskDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends BaseMapper<Task, TaskDTO> {
}
