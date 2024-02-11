package io.siliconsavannah.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;

@Entity
public @Data class Lease {
    @Id
    private int id;
    private Date termFrom;
    private Date termTo;
    private BigDecimal rent;
    private BigDecimal deposit;
    private String status;
    private String file;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name="id", table = "property", nullable=false)
    private Property property;
    @OneToMany(mappedBy="income")
    private HashSet<Income> income;
    @OneToMany(mappedBy="user")
    private HashSet<User> users;
}
