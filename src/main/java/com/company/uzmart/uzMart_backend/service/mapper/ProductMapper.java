package com.company.uzmart.uzMart_backend.service.mapper;

import com.company.uzmart.uzMart_backend.dto.ProductDto;
import com.company.uzmart.uzMart_backend.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setAmount(product.getAmount());
        dto.setPrice(product.getPrice());
        dto.setBarcode(product.getBarcode());
        dto.setImage(product.getImage());
        return dto;
    }

    public Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setAmount(dto.getAmount());
        product.setPrice(dto.getPrice());
        product.setBarcode(dto.getBarcode());
        product.setImage(dto.getImage());
        return product;
    }

    public void updateEntity(Product product, ProductDto dto) {
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setBarcode(dto.getBarcode());
        product.setImage(dto.getImage());
    }
}



