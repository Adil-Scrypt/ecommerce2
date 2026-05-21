package com.adilzhanabdambayev.ecommerce2.service;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevProductDto;
import org.springframework.data.domain.Page;

public interface AdilzhanAbdambayevProductService {

    Page<AdilzhanAbdambayevProductDto> getAllProducts(
            int page,
            int size,
            String sortBy,
            String direction,
            String search,
            String category
    );

    AdilzhanAbdambayevProductDto getProductById(Long id);

    AdilzhanAbdambayevProductDto createProduct(AdilzhanAbdambayevProductDto productDto);

    AdilzhanAbdambayevProductDto updateProduct(Long id, AdilzhanAbdambayevProductDto productDto);

    void deleteProduct(Long id);
}
