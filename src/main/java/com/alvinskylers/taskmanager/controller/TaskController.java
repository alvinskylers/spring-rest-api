package com.alvinskylers.taskmanager.controller;

import com.alvinskylers.taskmanager.entity.Task;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController // @Controller and @ResponseBody
@RequestMapping("/api/v1/tasks") // Base URL for all endpoints in this controller
public class TaskController {

    List<Task> tasks = new ArrayList<>();
    private long taskId = 1L;

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(taskId++);
        task.setCompleted(false);
        task.setCreatedAt(LocalDateTime.now());
        tasks.add(task);

        return task;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        for (int i = 0;i < tasks.size(); i++ ) {
            Task task = tasks.get(i);
            if (task.getId().equals(id)) {
                updatedTask.setId(id);
                updatedTask.setCreatedAt(task.getCreatedAt());
                tasks.set(i, updatedTask);
                return updatedTask;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }



    /*

        HTTP STATUS CODES
        200 OK ~ Successful GET, PUT, DELETE request
        201 Created ~ Successful POST request (should return this)
        404 Not Found ~ Resource doesn't exist
        400 Bad Request ~ Invalid data

     */
}
