package io.siliconsavannah.backend.dto;

import lombok.Builder;

@Builder
public record SignUpDto(
        String firstName,
        String lastName,
        String email,
        String password) {

}