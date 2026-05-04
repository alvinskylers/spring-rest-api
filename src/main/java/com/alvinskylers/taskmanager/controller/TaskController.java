package com.alvinskylers.taskmanager.controller;

import com.alvinskylers.taskmanager.dto.TaskRequest;
import com.alvinskylers.taskmanager.dto.TaskResponse;
import com.alvinskylers.taskmanager.entity.Task;
import com.alvinskylers.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller and @ResponseBody
@RequestMapping("/api/v1/tasks") // Base URL for all endpoints in this controller
public class TaskController {

    private final TaskService taskService;

    // Constructor Injection
    // @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest task) {
       TaskResponse savedTask = taskService.createTask(task);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id,
                                           @RequestBody TaskRequest updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/completed/{status}")
    public List<TaskResponse> getTasksByCompletions(@PathVariable boolean status) {
        return taskService.getCompletedTasks(status);
    }

    @GetMapping("/search")
    public List<TaskResponse> getTasksByTitle(@RequestParam String title) {
        return  taskService.searchTaskByTitle(title);
    }


    /*

        HTTP STATUS CODES
        200 OK ~ Successful GET, PUT, DELETE request
        201 Created ~ Successful POST request (should return this)
        404 Not Found ~ Resource doesn't exist
        400 Bad Request ~ Invalid data

     */
}
