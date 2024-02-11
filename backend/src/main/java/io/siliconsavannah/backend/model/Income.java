package io.siliconsavannah.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;


@Entity
public @Data class Income {
    @Id
    private int id;
    private Date dueOn;
    private Date paidOn;
    private String status;
    private BigDecimal amount;
    private BigDecimal lateFee;
    private BigDecimal paid;
    private BigDecimal balance;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name="id", table = "lease", nullable=false)
    private Lease lease;

}
