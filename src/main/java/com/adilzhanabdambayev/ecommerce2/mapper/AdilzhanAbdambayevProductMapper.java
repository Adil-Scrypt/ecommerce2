package com.adilzhanabdambayev.ecommerce2.mapper;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevProductDto;
import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevProduct;
import org.springframework.stereotype.Component;

@Component
public class AdilzhanAbdambayevProductMapper {

    public AdilzhanAbdambayevProductDto toDto(AdilzhanAbdambayevProduct product) {
        return new AdilzhanAbdambayevProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public AdilzhanAbdambayevProduct toEntity(AdilzhanAbdambayevProductDto dto) {
        return AdilzhanAbdambayevProduct.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .stock(dto.stock())
                .category(dto.category())
                .createdAt(dto.createdAt())
                .updatedAt(dto.updatedAt())
                .build();
    }

    public void updateEntity(AdilzhanAbdambayevProduct product, AdilzhanAbdambayevProductDto dto) {
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setCategory(dto.category());
    }
}
