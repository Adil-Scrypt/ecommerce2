package com.adilzhanabdambayev.ecommerce2.dto;

import java.time.LocalDateTime;

public record AdilzhanAbdambayevUserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String role,
        LocalDateTime createdAt
) {
}
