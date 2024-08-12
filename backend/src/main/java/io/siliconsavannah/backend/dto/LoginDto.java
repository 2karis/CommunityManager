package io.siliconsavannah.backend.dto;

import lombok.Builder;

@Builder
public record LoginDto(
        String email,
        String password) {

}