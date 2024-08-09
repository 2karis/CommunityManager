package io.siliconsavannah.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Table(name="lease")
@Entity
public @Data class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @DateTimeFormat(pattern = "MM/dd/yyyy")

    private SimpleDateFormat termFrom;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private SimpleDateFormat  termTo;
    private int rent;
    private int deposit;
    private String status;
    private String file;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    private Property property;
    @OneToMany(mappedBy="id")
    private Set<Income> income;
    @ManyToMany
    private Set<User> users;
}