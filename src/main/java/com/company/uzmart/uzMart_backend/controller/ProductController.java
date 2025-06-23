package com.company.uzmart.uzMart_backend.controller;

import com.company.uzmart.uzMart_backend.dto.ProductDto;
import com.company.uzmart.uzMart_backend.entity.Product;
import com.company.uzmart.uzMart_backend.entity.User;
import com.company.uzmart.uzMart_backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.create(dto));
    }

    @GetMapping("/get")
    public ResponseEntity<Product> getByBarcode(@RequestParam String barcode) {
        return ResponseEntity.ok(productService.getByBarcode(barcode));
    }

    @PutMapping("/update")
    public ResponseEntity<Product> update(@RequestParam Long id, @RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        productService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @Operation(summary = "Get all products sorted by amount ascending")
    @GetMapping("/get-all-admin")
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.getAllAdmin());
    }

    @GetMapping("/get-all-POS")
    public ResponseEntity<List<ProductDto>> getAllPOSProducts() {
        return ResponseEntity.ok(productService.getAllPOS());
    }


}

