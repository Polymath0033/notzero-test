package com.polymath.notzerowalletservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String walletAccountNumber;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(nullable = false)
    private BigDecimal amount;
    private String description;
    @Column(nullable = false)
    private LocalDateTime timestamp;
    public Transaction(String walletAccountNumber, TransactionType type, BigDecimal amount, String description) {
        this.walletAccountNumber = walletAccountNumber;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }
}
