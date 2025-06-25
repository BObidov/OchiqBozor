package com.company.uzmart.uzMart_backend.service.mapper;

import com.company.uzmart.uzMart_backend.dto.SalesReportDto;
import com.company.uzmart.uzMart_backend.entity.SalesReport;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SalesReportMapper {

    public SalesReport toEntity(SalesReportDto dto) {
        SalesReport report = new SalesReport();
        report.setProductName(dto.getProductName());
        report.setBarcode(dto.getBarcode());
        report.setPrice(dto.getPrice());
        report.setQuantity(dto.getQuantity());
        report.setTotalSum(dto.getPrice() * dto.getQuantity());
        report.setPaymentType(dto.getPaymentType());
        report.setSaleTime(LocalDateTime.now());
        return report;
    }
}

