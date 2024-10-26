package io.siliconsavannah.backend.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "lease_id")
    private Lease lease;

    // Getters and Setters
}
