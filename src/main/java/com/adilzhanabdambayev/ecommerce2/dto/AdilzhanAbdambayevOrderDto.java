package com.adilzhanabdambayev.ecommerce2.dto;

import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevOrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AdilzhanAbdambayevOrderDto(
        Long id,
        Long userId,
        String userEmail,

        @NotEmpty(message = "Order must contain at least one item")
        List<@Valid AdilzhanAbdambayevOrderItemRequest> items,

        List<AdilzhanAbdambayevOrderItemDto> orderItems,
        BigDecimal totalPrice,
        AdilzhanAbdambayevOrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
