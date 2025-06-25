package com.company.uzmart.uzMart_backend.dto;

import com.company.uzmart.uzMart_backend.model.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReportDto {

        private String productName;
        private String barcode;
        private Double price;
        private Integer quantity;
        private PaymentType paymentType;

}
