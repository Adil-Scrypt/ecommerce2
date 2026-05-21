package com.adilzhanabdambayev.ecommerce2.service.impl;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevProductDto;
import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevProduct;
import com.adilzhanabdambayev.ecommerce2.mapper.AdilzhanAbdambayevProductMapper;
import com.adilzhanabdambayev.ecommerce2.repository.AdilzhanAbdambayevProductRepository;
import com.adilzhanabdambayev.ecommerce2.service.AdilzhanAbdambayevProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AdilzhanAbdambayevProductServiceImpl implements AdilzhanAbdambayevProductService {

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "name", "price", "stock", "category", "createdAt");

    private final AdilzhanAbdambayevProductRepository productRepository;
    private final AdilzhanAbdambayevProductMapper productMapper;

    public AdilzhanAbdambayevProductServiceImpl(
            AdilzhanAbdambayevProductRepository productRepository,
            AdilzhanAbdambayevProductMapper productMapper
    ) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdilzhanAbdambayevProductDto> getAllProducts(
            int page,
            int size,
            String sortBy,
            String direction,
            String search,
            String category
    ) {
        String validSortBy = ALLOWED_SORT_FIELDS.contains(sortBy) ? sortBy : "id";
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.max(size, 1), Sort.by(sortDirection, validSortBy));

        return productRepository.findProducts(normalize(search), normalize(category), pageable)
                .map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public AdilzhanAbdambayevProductDto getProductById(Long id) {
        return productMapper.toDto(findProductById(id));
    }

    @Override
    @Transactional
    public AdilzhanAbdambayevProductDto createProduct(AdilzhanAbdambayevProductDto productDto) {
        AdilzhanAbdambayevProduct product = productMapper.toEntity(productDto);
        product.setId(null);

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public AdilzhanAbdambayevProductDto updateProduct(Long id, AdilzhanAbdambayevProductDto productDto) {
        AdilzhanAbdambayevProduct product = findProductById(id);
        productMapper.updateEntity(product, productDto);

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        AdilzhanAbdambayevProduct product = findProductById(id);
        productRepository.delete(product);
    }

    private AdilzhanAbdambayevProduct findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        return value.trim();
    }
}
