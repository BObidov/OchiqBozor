package com.company.uzmart.uzMart_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sales_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String barcode;
    private Double price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "sales_report_id")
    private SalesReport salesReport;
}

