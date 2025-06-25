package com.company.uzmart.uzMart_backend.repository;

import com.company.uzmart.uzMart_backend.entity.Product;
import com.company.uzmart.uzMart_backend.entity.SalesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesReportRepository extends JpaRepository<SalesReport, Long> {
    List<SalesReport> findAllByOrderBySaleTimeDesc();
}