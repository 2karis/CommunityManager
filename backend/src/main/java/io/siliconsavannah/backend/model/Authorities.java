package io.siliconsavannah.backend.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;
import java.util.List;
@NoArgsConstructor
@Entity
public class Authorities implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;
    @Override
    public String getAuthority() {
        return role;
    }
}
