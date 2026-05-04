package com.alvinskylers.taskmanager.service;

import com.alvinskylers.taskmanager.dto.TaskRequest;
import com.alvinskylers.taskmanager.dto.TaskResponse;
import com.alvinskylers.taskmanager.entity.Task;
import com.alvinskylers.taskmanager.exception.TaskNotFoundException;
import com.alvinskylers.taskmanager.mapper.TaskMapper;
import com.alvinskylers.taskmanager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public TaskResponse createTask(TaskRequest task) {
        Task entityTask = taskMapper.toEntity(task);
        Task savedTask = taskRepository.save(entityTask);
        return taskMapper.toResponse(savedTask);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.getCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
            taskRepository.delete(task);
    }

    public List<Task> getCompletedTasks(boolean status) {
        return taskRepository.findByCompleted(status);
    }

    public List<Task> searchTaskByTitle(String query) {
        return taskRepository.findByTitleContaining(query);
    }
}
