package com.alvinskylers.taskmanager.repository;

import com.alvinskylers.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
