package com.alvinskylers.taskmanager.controller;

import com.alvinskylers.taskmanager.dto.CategoryRequest;
import com.alvinskylers.taskmanager.dto.CategoryResponse;
import com.alvinskylers.taskmanager.entity.Category;
import com.alvinskylers.taskmanager.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponse> getAll() {
        return this.categoryService.grabAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable  Long id) {
        return this.categoryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create( @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse response = categoryService.create(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
