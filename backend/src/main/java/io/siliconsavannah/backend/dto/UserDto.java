package io.siliconsavannah.backend.dto;

import io.siliconsavannah.backend.enums.Role;
import io.siliconsavannah.backend.model.Authorities;
import io.siliconsavannah.backend.model.Task;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
@Builder
public record UserDto(
        int id,
        String firstName,
        String lastName,
        String email,
        String password,
        Role role,
        String phone,
        String imageUrl,
        Timestamp createdAt,
        Timestamp updatedAt,
        HashSet<Task>tasks
) {
}
