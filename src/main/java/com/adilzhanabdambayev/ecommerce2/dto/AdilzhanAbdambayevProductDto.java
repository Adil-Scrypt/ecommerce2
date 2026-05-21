package com.adilzhanabdambayev.ecommerce2.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AdilzhanAbdambayevProductDto(
        Long id,

        @NotBlank(message = "Product name is required")
        @Size(min = 2, max = 150, message = "Product name must be between 2 and 150 characters")
        String name,

        @NotBlank(message = "Product description is required")
        @Size(min = 5, max = 1000, message = "Product description must be between 5 and 1000 characters")
        String description,

        @NotNull(message = "Product price is required")
        @DecimalMin(value = "0.01", message = "Product price must be greater than zero")
        BigDecimal price,

        @NotNull(message = "Product stock is required")
        @Min(value = 0, message = "Product stock cannot be negative")
        Integer stock,

        @NotBlank(message = "Product category is required")
        @Size(min = 2, max = 100, message = "Product category must be between 2 and 100 characters")
        String category,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
