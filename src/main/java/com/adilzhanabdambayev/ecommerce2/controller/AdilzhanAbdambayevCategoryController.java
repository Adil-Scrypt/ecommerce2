package com.adilzhanabdambayev.ecommerce2.controller;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevCategoryDto;
import com.adilzhanabdambayev.ecommerce2.service.AdilzhanAbdambayevCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class AdilzhanAbdambayevCategoryController {

    private final AdilzhanAbdambayevCategoryService categoryService;

    public AdilzhanAbdambayevCategoryController(AdilzhanAbdambayevCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<AdilzhanAbdambayevCategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdilzhanAbdambayevCategoryDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<AdilzhanAbdambayevCategoryDto> createCategory(
            @Valid @RequestBody AdilzhanAbdambayevCategoryDto categoryDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdilzhanAbdambayevCategoryDto> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody AdilzhanAbdambayevCategoryDto categoryDto
    ) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
