package com.alvinskylers.taskmanager.repository;

import com.alvinskylers.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// @Repository isn't always necessary because JpaRepository already uses it
public interface TaskRepository extends JpaRepository<Task, Long> {

    //Custom Methods
     List<Task> findByCompleted(boolean completed);
    List<Task> findByTitleContaining(String query);

    //Custom Query
    @Query("SELECT t FROM Task t where t.completed = :completed")
    List<Task> findTasksByCompletionStatus(@Param("completed") boolean completed);
}
