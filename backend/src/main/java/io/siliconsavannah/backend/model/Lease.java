package io.siliconsavannah.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;

@Table(name="lease")
@Entity
public @Data class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date termFrom;
    private Date termTo;
    private BigDecimal rent;
    private BigDecimal deposit;
    private String status;
    private String file;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    @ManyToOne
    @JoinColumn(name="property_id", nullable=false)
    private Property property;
    @OneToMany(mappedBy="id")
    private HashSet<Income> income;
    @OneToMany
    private HashSet<User> users;
}
