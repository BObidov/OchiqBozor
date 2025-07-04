package com.company.uzmart.uzMart_backend.dto;

import com.company.uzmart.uzMart_backend.model.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReportDto {

    private Long id;
    private PaymentType paymentType;
    private LocalDateTime saleTime;
    private Double totalSum;
    private List<SalesItemDto> items;


}
