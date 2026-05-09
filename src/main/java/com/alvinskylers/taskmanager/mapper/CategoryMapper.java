package com.alvinskylers.taskmanager.mapper;

import com.alvinskylers.taskmanager.dto.CategoryRequest;
import com.alvinskylers.taskmanager.dto.CategoryResponse;
import com.alvinskylers.taskmanager.entity.Category;
import com.alvinskylers.taskmanager.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request) {
        return Category.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    public Category toEntity(CategoryResponse response) {
        return Category.builder()
                .name(response.name())
                .description(response.description())
                .build();
    }

    public CategoryResponse mapToResponse(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
