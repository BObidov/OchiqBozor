package com.company.uzmart.uzMart_backend.entity;

import com.company.uzmart.uzMart_backend.model.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String barcode;
    private Double price;
    private Integer quantity;

    private Double totalSum;

    @Enumerated(EnumType.STRING) // bu muhim — enum nomini string sifatida saqlaydi
    private PaymentType paymentType;

    private LocalDateTime saleTime;
}
