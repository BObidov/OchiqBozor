package com.company.uzmart.uzMart_backend.controller;

import com.company.uzmart.uzMart_backend.dto.SalesItemDto;
import com.company.uzmart.uzMart_backend.dto.SalesReportDto;
import com.company.uzmart.uzMart_backend.dto.SalesRequestDto;
import com.company.uzmart.uzMart_backend.entity.SalesReport;
import com.company.uzmart.uzMart_backend.service.SalesReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesReportController {

    private final SalesReportService salesReportService;

    @PostMapping("/create")
    public ResponseEntity<String> saveSale(@RequestBody SalesRequestDto requestDto) {
        salesReportService.saveSale(requestDto);
        return ResponseEntity.ok("Sotuv muvaffaqiyatli saqlandi");
    }

    @GetMapping("/get-all-reports")
    public ResponseEntity<List<SalesReportDto>> getAllReports() {
        return ResponseEntity.ok(salesReportService.getAllReports());
    }

    @GetMapping("/get-report-id")
    public ResponseEntity<SalesReportDto> getReportById(@RequestParam Long id) {
        return ResponseEntity.ok(salesReportService.getReportById(id));
    }

    @GetMapping("/get/items-report")
    public ResponseEntity<List<SalesItemDto>> getItemsByReport(@RequestParam Long id) {
        return ResponseEntity.ok(salesReportService.getItemsByReportId(id));
    }
}
