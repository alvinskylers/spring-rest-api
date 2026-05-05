package com.alvinskylers.taskmanager.service;

import com.alvinskylers.taskmanager.dto.TaskRequest;
import com.alvinskylers.taskmanager.dto.TaskResponse;
import com.alvinskylers.taskmanager.entity.Task;
import com.alvinskylers.taskmanager.exception.TaskNotFoundException;
import com.alvinskylers.taskmanager.mapper.TaskMapper;
import com.alvinskylers.taskmanager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasksRetrieved = taskRepository.findAll();
        return tasksRetrieved.stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toResponse(task);
    }

    public TaskResponse createTask(TaskRequest task) {
        Task entityTask = taskMapper.toEntity(task);
        Task savedTask = taskRepository.save(entityTask);
        return taskMapper.toResponse(savedTask);
    }

    public TaskResponse updateTask(Long id, TaskRequest updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskMapper.updateEntityFromRequest(task, updatedTask);
        return taskMapper.toResponse(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
            taskRepository.delete(task);
    }

    public List<TaskResponse> getCompletedTasks(boolean status) {
        List<Task> tasksRetrieved = taskRepository.findByCompleted(status);
        return tasksRetrieved.stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    public List<TaskResponse> searchTaskByTitle(String query) {
        List<Task> tasksRetrieved = taskRepository.findByTitleContaining(query);
        return tasksRetrieved.stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    public Page<TaskResponse> getCompletedTasks(boolean status, Pageable pageable) {
        Page<Task> tasksRetrieved = taskRepository.findByCompleted(status, pageable);
        return tasksRetrieved.map(taskMapper::toResponse);
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
}
