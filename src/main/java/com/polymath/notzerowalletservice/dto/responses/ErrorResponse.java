package com.polymath.notzerowalletservice.dto.responses;

import java.time.LocalDateTime;

public record ErrorResponse(int status, String message, long timestamp) {
}
