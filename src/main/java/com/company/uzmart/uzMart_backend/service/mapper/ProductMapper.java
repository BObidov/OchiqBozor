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
        dto.setImage(product.getImage());
        dto.setPrice(product.getPrice());
        dto.setAmount(product.getAmount());
        dto.setBarcode(product.getBarcode());
        return dto;
    }

    public Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setImage(dto.getImage());
        product.setPrice(dto.getPrice());
        product.setAmount(dto.getAmount());
        product.setBarcode(dto.getBarcode());
        return product;
    }

    public void updateEntity(Product product, ProductDto dto) {
        product.setName(dto.getName());
        product.setImage(dto.getImage());
        product.setPrice(dto.getPrice());
        product.setAmount(dto.getAmount());
        product.setBarcode(dto.getBarcode());
    }
}


