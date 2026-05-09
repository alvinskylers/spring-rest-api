package com.alvinskylers.taskmanager.service;

import com.alvinskylers.taskmanager.dto.CategoryRequest;
import com.alvinskylers.taskmanager.dto.CategoryResponse;
import com.alvinskylers.taskmanager.entity.Category;
import com.alvinskylers.taskmanager.mapper.CategoryMapper;
import com.alvinskylers.taskmanager.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;

    }

    public List<CategoryResponse> grabAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::mapToResponse)
                .toList();
    }

    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElse(null);
        return categoryMapper.mapToResponse(category);
    }

    public CategoryResponse create(CategoryRequest request) {
         Category category = categoryMapper.toEntity(request);
         return categoryMapper.mapToResponse(categoryRepository.save(category));
    }

}
