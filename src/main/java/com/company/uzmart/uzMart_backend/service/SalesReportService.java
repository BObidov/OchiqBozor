package com.company.uzmart.uzMart_backend.service;

import com.company.uzmart.uzMart_backend.dto.SalesReportDto;
import com.company.uzmart.uzMart_backend.entity.Product;
import com.company.uzmart.uzMart_backend.entity.SalesReport;
import com.company.uzmart.uzMart_backend.repository.ProductRepository;
import com.company.uzmart.uzMart_backend.repository.SalesReportRepository;
import com.company.uzmart.uzMart_backend.service.mapper.SalesReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalesReportService {

    private final SalesReportRepository salesReportRepository;
    private final ProductRepository productRepository;
    private final SalesReportMapper salesReportMapper;

    public void saveSale(List<SalesReportDto> dtoList) {
        List<SalesReport> saleReports = new ArrayList<>();

        for (SalesReportDto dto : dtoList) {
            // Product mavjudligini tekshirmasdan faqat sotuv yozamiz
            SalesReport report = salesReportMapper.toEntity(dto);
            report.setTotalSum(dto.getPrice() * dto.getQuantity());
            report.setSaleTime(java.time.LocalDateTime.now());
            saleReports.add(report);
        }

        // Faqat sotuvni saqlaymiz, product bilan hech qanday aloqasi yo‘q
        salesReportRepository.saveAll(saleReports);
    }


    public List<SalesReport> getAllReports() {
        return salesReportRepository.findAllByOrderBySaleTimeDesc();
    }
}