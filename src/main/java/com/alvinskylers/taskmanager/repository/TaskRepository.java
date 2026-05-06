package com.alvinskylers.taskmanager.repository;

import com.alvinskylers.taskmanager.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    //Pagination Implementations
    Page<Task> findByCompleted(boolean completed, Pageable pageable);
    Page<Task> findByTitleContaining(String query, Pageable pageable);

    @Query("SELECT t FROM Task t where t.completed = :completed")
    Page<Task> findTasksByCompletionStatus(@Param("completed") boolean completed, Pageable pageable);

    @Query("SELECT t " +
            "FROM Task t " +
            "WHERE  LOWER(t.title) LIKE " +
            "LOWER(CONCAT('%', :title, '%')) AND t.completed =:completed")
    Page<Task> findByTitleContainingAndCompleted(String title,
                                                 boolean completed,
                                                 Pageable pageable);
}
