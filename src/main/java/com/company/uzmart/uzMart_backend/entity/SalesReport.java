package com.company.uzmart.uzMart_backend.entity;

import com.company.uzmart.uzMart_backend.model.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    private LocalDateTime saleTime;
    private Double totalSum;

    @OneToMany(mappedBy = "salesReport", cascade = CascadeType.ALL)
    private List<SalesItem> items;


}
