package com.alvinskylers.taskmanager.mapper;

import com.alvinskylers.taskmanager.entity.Task;
import com.alvinskylers.taskmanager.dto.TaskRequest;
import com.alvinskylers.taskmanager.dto.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequest request) {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .completed(request.completed())
                .build();
    }

    public TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.getCompleted())
                .createdAt(task.getCreatedAt())
                .build();
    }

    public void updateEntityFromRequest(Task task, TaskRequest taskRequest) {
        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setCompleted(taskRequest.completed());
    }
}
