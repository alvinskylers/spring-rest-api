package com.alvinskylers.taskmanager.mapper;

import com.alvinskylers.taskmanager.dto.CategoryResponse;
import com.alvinskylers.taskmanager.entity.Category;
import com.alvinskylers.taskmanager.entity.Task;
import com.alvinskylers.taskmanager.dto.TaskRequest;
import com.alvinskylers.taskmanager.dto.TaskResponse;
import com.alvinskylers.taskmanager.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    public TaskMapper (CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    public Task toEntity(TaskRequest request) {
        Category category= null;

        if (request!= null && request.categoryId() != null ) {
            category = categoryMapper.toEntity(categoryService.findById(request.categoryId()));
        }

        return Task.builder()
                .title(request.title())
                .description(request.description())
                .completed(request.completed())
                .category(category)
                .build();
    }

    public TaskResponse toResponse(Task task) {
        CategoryResponse categoryResponse = null;

        if (task != null && task.getCategory() != null) {
            categoryResponse =  CategoryResponse.builder()
                    .categoryId(task.getCategory().getId())
                    .name(task.getCategory().getName())
                    .description(task.getCategory().getDescription())
                    .build();
        }

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.getCompleted())
                .createdAt(task.getCreatedAt())
                .category(categoryResponse)
                .build();
    }

    public void updateEntityFromRequest(Task task, TaskRequest taskRequest) {
        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setCompleted(taskRequest.completed());

        if (taskRequest.categoryId() != null) {
            task.setCategory(categoryMapper.toEntity(categoryService.findById(taskRequest.categoryId())));
        }
    }
}
