package io.siliconsavannah.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Entity
public @Data class User {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String type;
    private String phone;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

}
