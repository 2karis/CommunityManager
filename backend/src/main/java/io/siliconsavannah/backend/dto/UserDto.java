package io.siliconsavannah.backend.dto;

import io.siliconsavannah.backend.model.Task;

import java.sql.Timestamp;
import java.util.HashSet;

public record UserDto(
        int id,
        String firstName,
        String lastName,
        String email,
        String password,
        String type,
        String phone,
        Timestamp createdAt,
        Timestamp updatedAt,
        HashSet<Task>tasks
) {
}
