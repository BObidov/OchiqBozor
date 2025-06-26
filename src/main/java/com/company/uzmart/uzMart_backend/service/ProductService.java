package com.company.uzmart.uzMart_backend.service;

import com.company.uzmart.uzMart_backend.dto.ProductDto;
import com.company.uzmart.uzMart_backend.entity.Product;
import com.company.uzmart.uzMart_backend.repository.ProductRepository;
import com.company.uzmart.uzMart_backend.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Product create(ProductDto dto) {
        if (dto.getBarcode() != null && !dto.getBarcode().trim().isEmpty()) {
            boolean exists = productRepository.findByBarcode(dto.getBarcode()).isPresent();
            if (exists) {
                throw new RuntimeException("Bu barcode bilan mahsulot allaqachon mavjud. Shuning uchun mahsulot saqlanmadi.");
            }
        }

        Product product = productMapper.toEntity(dto);
        return productRepository.save(product);
    }


    public List<ProductDto> getByBarcode(String barcode) {
        if (barcode != null && barcode.length() >= 2) {
            List<Product> products = productRepository.findByBarcodeStartingWith(barcode);
            return products.stream()
                    .map(productMapper::toDto)
                    .collect(Collectors.toList());
        }

        Product product = productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("Mahsulot topilmadi"));
        return Collections.singletonList(productMapper.toDto(product));
    }

    public List<ProductDto> getAllWithoutBarcode() {
        List<Product> products = productRepository.findAllByBarcodeIsNullOrBarcodeIs("");
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public Product update(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Agar barcode bo‘sh string bo‘lsa, uni null ga o‘zgartiramiz
        if (dto.getBarcode() != null && dto.getBarcode().trim().isEmpty()) {
            dto.setBarcode(null);
        }

        // Agar barcode mavjud bo‘lsa — unique bo‘lishini tekshiramiz
        if (dto.getBarcode() != null) {
            productRepository.findByBarcode(dto.getBarcode()).ifPresent(existingProduct -> {
                if (!existingProduct.getId().equals(id)) {
                    throw new RuntimeException("Bu barcode boshqa mahsulotga tegishli. Yangilash mumkin emas.");
                }
            });
        }

        // Boshqa maydonlarni yangilash
        productMapper.updateEntity(product, dto);

        // Mavjud amountga yangi amountni qo‘shish (agar mavjud bo‘lsa)
        if (dto.getAmount() != null) {
            int yangiAmount = product.getAmount() + dto.getAmount();
            product.setAmount(yangiAmount);
        }

        return productRepository.save(product);
    }


    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDto> getAllAdmin() {
        List<Product> productList = productRepository.findAllByOrderByAmountAsc();
        return productList.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getAllPOS() {
        List<Product> productList = productRepository.findAllByOrderByAmountAsc();

        return productList.stream()
                .filter(product -> product.getBarcode() != null && !product.getBarcode().isBlank()) // faqat barcode borlar
                .map(product -> {
                    ProductDto dto = new ProductDto();
                    dto.setId(product.getId());
                    dto.setName(product.getName());
                    dto.setAmount(product.getAmount());
                    dto.setPrice(product.getPrice());
                    return dto;
                })
                .collect(Collectors.toList());
    }



}

