package com.polymath.notzerowalletservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {
    @Id
    @Column(length = 10)
    private String accountNumber;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private BigDecimal balance;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;
    public Wallet(String accountNumber,String firstName,String lastName){
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = BigDecimal.ZERO;
        this.createdAt = LocalDateTime.now();
        this.active = true;
    }
}
