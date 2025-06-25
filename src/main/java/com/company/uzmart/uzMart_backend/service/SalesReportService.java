package com.company.uzmart.uzMart_backend.service;

import com.company.uzmart.uzMart_backend.dto.SalesItemDto;
import com.company.uzmart.uzMart_backend.dto.SalesReportDto;
import com.company.uzmart.uzMart_backend.dto.SalesRequestDto;
import com.company.uzmart.uzMart_backend.entity.Product;
import com.company.uzmart.uzMart_backend.entity.SalesItem;
import com.company.uzmart.uzMart_backend.entity.SalesReport;
import com.company.uzmart.uzMart_backend.repository.ProductRepository;
import com.company.uzmart.uzMart_backend.repository.SalesItemRepository;
import com.company.uzmart.uzMart_backend.repository.SalesReportRepository;
import com.company.uzmart.uzMart_backend.service.mapper.SalesReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalesReportService {

    private final SalesReportRepository salesReportRepository;
    private final SalesItemRepository salesItemRepository;
    private final ProductRepository productRepository;
    private final SalesReportMapper salesReportMapper;

    public void saveSale(SalesRequestDto requestDto) {
        double totalSum = 0;
        List<SalesItem> items = new ArrayList<>();
        List<Product> updatedProducts = new ArrayList<>();

        for (SalesItemDto dto : requestDto.getItems()) {
            Optional<Product> optionalProduct =
                    (dto.getBarcode() != null && !dto.getBarcode().isBlank()) ?
                            productRepository.findByBarcode(dto.getBarcode()) :
                            productRepository.findByName(dto.getProductName());

            Product product = optionalProduct.orElseThrow(() ->
                    new RuntimeException("Mahsulot topilmadi: " + dto.getProductName()));

            if (product.getAmount() < dto.getQuantity()) {
                throw new RuntimeException("Yetarli mahsulot yo‘q: " + product.getName());
            }

            // Mahsulot miqdorini kamaytiramiz
            product.setAmount(product.getAmount() - dto.getQuantity());
            updatedProducts.add(product); // keyinchalik saveAll qilinadi

            // Sotuv elementini yaratamiz
            SalesItem item = new SalesItem();
            item.setProductName(dto.getProductName());
            item.setBarcode(dto.getBarcode());
            item.setPrice(dto.getPrice());
            item.setQuantity(dto.getQuantity());

            items.add(item);
            totalSum += dto.getPrice() * dto.getQuantity();
        }

        // Productlar bazada yangilanadi
        productRepository.saveAll(updatedProducts);

        // Umumiy SalesReport ni yaratamiz
        SalesReport report = new SalesReport();
        report.setPaymentType(requestDto.getPaymentType());
        report.setSaleTime(LocalDateTime.now());
        report.setTotalSum(totalSum);

        // Bog‘lash: har bir itemni report bilan bog‘laymiz
        for (SalesItem item : items) {
            item.setSalesReport(report);
        }

        report.setItems(items);

        // SalesReport va barcha items saqlanadi (cascade orqali)
        salesReportRepository.save(report);
    }


    public List<SalesReportDto> getAllReports() {
        return salesReportRepository.findAllByOrderBySaleTimeDesc().stream()
                .map(salesReportMapper::toDto)
                .toList();
    }

    public SalesReportDto getReportById(Long id) {
        SalesReport report = salesReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sotuv topilmadi: ID=" + id));
        return salesReportMapper.toDto(report);
    }

    public List<SalesItemDto> getItemsByReportId(Long reportId) {
        return salesItemRepository.findBySalesReportId(reportId).stream()
                .map(salesReportMapper::toItemDto)
                .toList();
    }
}

