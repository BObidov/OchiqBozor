package com.company.uzmart.uzMart_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SalesItemDto {
    private String productName;
    private String barcode;
    private Double price;
    private Integer quantity;
}
