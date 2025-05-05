package com.polymath.notzerowalletservice.dto.responses;

import com.polymath.notzerowalletservice.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record WalletResponse(
        String accountNumber,
        String firstName,
        String lastName,
        BigDecimal balance,
        LocalDateTime createdAt
        ) {
}
