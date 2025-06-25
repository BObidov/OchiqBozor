package com.company.uzmart.uzMart_backend.service.mapper;

import com.company.uzmart.uzMart_backend.dto.SalesItemDto;
import com.company.uzmart.uzMart_backend.dto.SalesReportDto;
import com.company.uzmart.uzMart_backend.entity.SalesItem;
import com.company.uzmart.uzMart_backend.entity.SalesReport;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SalesReportMapper {

    public SalesReportDto toDto(SalesReport report) {
        SalesReportDto dto = new SalesReportDto();
        dto.setId(report.getId());
        dto.setPaymentType(report.getPaymentType());
        dto.setSaleTime(report.getSaleTime());
        dto.setTotalSum(report.getTotalSum());

        List<SalesItemDto> itemDtos = report.getItems().stream()
                .map(this::toItemDto)
                .toList();
        dto.setItems(itemDtos);

        return dto;
    }

    public SalesItemDto toItemDto(SalesItem item) {
        SalesItemDto dto = new SalesItemDto();
        dto.setProductName(item.getProductName());
        dto.setBarcode(item.getBarcode());
        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());
        return dto;
    }
}

