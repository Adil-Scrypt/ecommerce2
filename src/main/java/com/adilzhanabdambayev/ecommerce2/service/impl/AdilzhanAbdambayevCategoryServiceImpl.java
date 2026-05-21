package com.adilzhanabdambayev.ecommerce2.service.impl;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevCategoryDto;
import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevCategory;
import com.adilzhanabdambayev.ecommerce2.mapper.AdilzhanAbdambayevCategoryMapper;
import com.adilzhanabdambayev.ecommerce2.repository.AdilzhanAbdambayevCategoryRepository;
import com.adilzhanabdambayev.ecommerce2.service.AdilzhanAbdambayevCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdilzhanAbdambayevCategoryServiceImpl implements AdilzhanAbdambayevCategoryService {

    private final AdilzhanAbdambayevCategoryRepository categoryRepository;
    private final AdilzhanAbdambayevCategoryMapper categoryMapper;

    public AdilzhanAbdambayevCategoryServiceImpl(
            AdilzhanAbdambayevCategoryRepository categoryRepository,
            AdilzhanAbdambayevCategoryMapper categoryMapper
    ) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdilzhanAbdambayevCategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AdilzhanAbdambayevCategoryDto getCategoryById(Long id) {
        return categoryMapper.toDto(findCategoryById(id));
    }

    @Override
    @Transactional
    public AdilzhanAbdambayevCategoryDto createCategory(AdilzhanAbdambayevCategoryDto categoryDto) {
        if (categoryRepository.existsByNameIgnoreCase(categoryDto.name())) {
            throw new IllegalArgumentException("Category with this name already exists");
        }

        AdilzhanAbdambayevCategory category = categoryMapper.toEntity(categoryDto);
        category.setId(null);

        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public AdilzhanAbdambayevCategoryDto updateCategory(Long id, AdilzhanAbdambayevCategoryDto categoryDto) {
        AdilzhanAbdambayevCategory category = findCategoryById(id);
        categoryRepository.findByNameIgnoreCase(categoryDto.name())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Category with this name already exists");
                });

        categoryMapper.updateEntity(category, categoryDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        AdilzhanAbdambayevCategory category = findCategoryById(id);
        categoryRepository.delete(category);
    }

    private AdilzhanAbdambayevCategory findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
    }
}
