package com.company.uzmart.uzMart_backend.service;

import com.company.uzmart.uzMart_backend.dto.ProductDto;
import com.company.uzmart.uzMart_backend.entity.Product;
import com.company.uzmart.uzMart_backend.entity.User;
import com.company.uzmart.uzMart_backend.repository.ProductRepository;
import com.company.uzmart.uzMart_backend.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Product create(ProductDto dto) {
        Product product = productMapper.toEntity(dto);
        return productRepository.save(product);
    }

    public Product getByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product update(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productMapper.updateEntity(product, dto);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDto> getAll() {
        List<Product> productList = productRepository.findAllByOrderByAmountAsc();
        return productList.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

}

