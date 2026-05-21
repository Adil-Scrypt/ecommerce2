package com.adilzhanabdambayev.ecommerce2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AdilzhanAbdambayevCategoryDto(
        Long id,

        @NotBlank(message = "Category name is required")
        @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Category description is required")
        @Size(min = 5, max = 500, message = "Category description must be between 5 and 500 characters")
        String description,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
