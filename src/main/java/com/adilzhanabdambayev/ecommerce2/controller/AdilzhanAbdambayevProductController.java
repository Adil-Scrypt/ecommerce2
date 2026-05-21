package com.adilzhanabdambayev.ecommerce2.controller;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevProductDto;
import com.adilzhanabdambayev.ecommerce2.service.AdilzhanAbdambayevProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class AdilzhanAbdambayevProductController {

    private final AdilzhanAbdambayevProductService productService;

    public AdilzhanAbdambayevProductController(AdilzhanAbdambayevProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<AdilzhanAbdambayevProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdilzhanAbdambayevProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<AdilzhanAbdambayevProductDto> createProduct(
            @Valid @RequestBody AdilzhanAbdambayevProductDto productDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdilzhanAbdambayevProductDto> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody AdilzhanAbdambayevProductDto productDto
    ) {
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
