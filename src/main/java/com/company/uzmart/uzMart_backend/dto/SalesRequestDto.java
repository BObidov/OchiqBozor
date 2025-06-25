package com.company.uzmart.uzMart_backend.dto;

import com.company.uzmart.uzMart_backend.model.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SalesRequestDto {
    private List<SalesItemDto> items;
    private PaymentType paymentType;
}

