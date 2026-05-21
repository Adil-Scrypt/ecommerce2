package com.adilzhanabdambayev.ecommerce2.dto;

public record AdilzhanAbdambayevAuthResponse(
        String token,
        String tokenType,
        AdilzhanAbdambayevUserResponse user
) {
}
