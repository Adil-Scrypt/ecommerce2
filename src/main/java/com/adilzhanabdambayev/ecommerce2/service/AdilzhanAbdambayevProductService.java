package com.adilzhanabdambayev.ecommerce2.service;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevProductDto;

import java.util.List;

public interface AdilzhanAbdambayevProductService {

    List<AdilzhanAbdambayevProductDto> getAllProducts();

    AdilzhanAbdambayevProductDto getProductById(Long id);

    AdilzhanAbdambayevProductDto createProduct(AdilzhanAbdambayevProductDto productDto);

    AdilzhanAbdambayevProductDto updateProduct(Long id, AdilzhanAbdambayevProductDto productDto);

    void deleteProduct(Long id);
}
