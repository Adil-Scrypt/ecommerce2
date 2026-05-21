package com.adilzhanabdambayev.ecommerce2.service.impl;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevProductDto;
import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevProduct;
import com.adilzhanabdambayev.ecommerce2.mapper.AdilzhanAbdambayevProductMapper;
import com.adilzhanabdambayev.ecommerce2.repository.AdilzhanAbdambayevProductRepository;
import com.adilzhanabdambayev.ecommerce2.service.AdilzhanAbdambayevProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdilzhanAbdambayevProductServiceImpl implements AdilzhanAbdambayevProductService {

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
    public List<AdilzhanAbdambayevProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
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
}
