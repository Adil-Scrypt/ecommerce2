package com.adilzhanabdambayev.ecommerce2.mapper;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevCategoryDto;
import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevCategory;
import org.springframework.stereotype.Component;

@Component
public class AdilzhanAbdambayevCategoryMapper {

    public AdilzhanAbdambayevCategoryDto toDto(AdilzhanAbdambayevCategory category) {
        return new AdilzhanAbdambayevCategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    public AdilzhanAbdambayevCategory toEntity(AdilzhanAbdambayevCategoryDto dto) {
        return AdilzhanAbdambayevCategory.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .createdAt(dto.createdAt())
                .updatedAt(dto.updatedAt())
                .build();
    }

    public void updateEntity(AdilzhanAbdambayevCategory category, AdilzhanAbdambayevCategoryDto dto) {
        category.setName(dto.name());
        category.setDescription(dto.description());
    }
}
