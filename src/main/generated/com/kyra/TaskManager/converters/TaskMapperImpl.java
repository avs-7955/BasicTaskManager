package com.kyra.TaskManager.converters;

import com.kyra.TaskManager.db.Task;
import com.kyra.TaskManager.dto.TaskDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-08T23:24:04+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Homebrew)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDTO getTaskDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO.TaskDTOBuilder taskDTO = TaskDTO.builder();

        taskDTO.id( task.getId() );
        taskDTO.title( task.getTitle() );
        taskDTO.description( task.getDescription() );
        taskDTO.status( task.getStatus() );
        taskDTO.priority( task.getPriority() );
        taskDTO.dueDate( task.getDueDate() );
        taskDTO.createdAt( task.getCreatedAt() );

        return taskDTO.build();
    }

    @Override
    public Task getTask(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task.TaskBuilder task = Task.builder();

        task.id( taskDTO.getId() );
        task.title( taskDTO.getTitle() );
        task.description( taskDTO.getDescription() );
        task.status( taskDTO.getStatus() );
        task.priority( taskDTO.getPriority() );
        task.dueDate( taskDTO.getDueDate() );
        task.createdAt( taskDTO.getCreatedAt() );

        return task.build();
    }

    @Override
    public List<TaskDTO> getTaskDTOs(List<Task> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<TaskDTO> list = new ArrayList<TaskDTO>( tasks.size() );
        for ( Task task : tasks ) {
            list.add( getTaskDTO( task ) );
        }

        return list;
    }
}
