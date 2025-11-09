package com.kyra.TaskManager.repository;

import com.kyra.TaskManager.db.Task;
import com.kyra.TaskManager.db.enums.Priority;
import com.kyra.TaskManager.db.enums.TaskStatus;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByPriority(@NonNull Priority priority, @NonNull Pageable p);

    Page<Task> findAllByStatus(@NonNull TaskStatus status, @NonNull Pageable p);

    Page<Task> findAllByPriorityAndStatus(@NonNull Priority priority, @NonNull TaskStatus status, @NonNull Pageable p);
}
