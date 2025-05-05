package com.polymath.notzerowalletservice.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletRequest(@NotNull @NotBlank(message = "First name is required") String firstName, @NotNull @NotBlank(message = "Last name is required") String lastName) {
}
