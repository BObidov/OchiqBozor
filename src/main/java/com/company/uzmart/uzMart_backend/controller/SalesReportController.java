package com.company.uzmart.uzMart_backend.controller;

import com.company.uzmart.uzMart_backend.dto.SalesReportDto;
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
    public ResponseEntity<String> saveSale(@RequestBody List<SalesReportDto> saleList) {
        salesReportService.saveSale(saleList);
        return ResponseEntity.ok("Sotuv saqlandi");
    }

    @GetMapping("/get-all-sales-report")
    public ResponseEntity<List<SalesReport>> getAllReports() {
        return ResponseEntity.ok(salesReportService.getAllReports());
    }
}

