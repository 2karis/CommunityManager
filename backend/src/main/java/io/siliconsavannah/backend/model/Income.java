package io.siliconsavannah.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Table(name="income")
@Entity
public @Data class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dueOn;
    private Date paidOn;
    private String status;
    private BigDecimal amount;
    private BigDecimal lateFee;
    private BigDecimal paid;
    private BigDecimal balance;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    @ManyToOne
    @JoinColumn(name="lease_id", nullable=false)
    private Lease lease;

}
