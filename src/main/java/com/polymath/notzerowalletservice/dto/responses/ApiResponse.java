package com.polymath.notzerowalletservice.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Standard API response wrapper")
public class ApiResponse<T> {
    @Schema(description = "HTTP status code", example = "200")
    private int status;
    @Schema(description = "Status message", example = "Success")
    private String message;
    @Schema(description = "Response payload")
    private T data;
}