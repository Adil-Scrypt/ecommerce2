package com.adilzhanabdambayev.ecommerce2.service;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevCategoryDto;

import java.util.List;

public interface AdilzhanAbdambayevCategoryService {

    List<AdilzhanAbdambayevCategoryDto> getAllCategories();

    AdilzhanAbdambayevCategoryDto getCategoryById(Long id);

    AdilzhanAbdambayevCategoryDto createCategory(AdilzhanAbdambayevCategoryDto categoryDto);

    AdilzhanAbdambayevCategoryDto updateCategory(Long id, AdilzhanAbdambayevCategoryDto categoryDto);

    void deleteCategory(Long id);
}
