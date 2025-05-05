package com.polymath.notzerowalletservice.dto.requests;

import com.polymath.notzerowalletservice.model.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionRequest(
        @NotNull(message = "Transaction type cannot be null")TransactionType transactionType,
        @NotNull(message = "Amount is required") @DecimalMin(value = "0.01", message = "Amount must be greater than zero")BigDecimal amount,
        String description) {
}
