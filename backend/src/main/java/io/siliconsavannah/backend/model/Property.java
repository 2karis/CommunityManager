package io.siliconsavannah.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.util.HashSet;

@Entity
public @Data class Property {
    @Id
    private int id;
    private String address;
    private String unit;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @OneToMany(mappedBy="lease")
    private HashSet<Lease> lease;
    @OneToMany(mappedBy="expense")
    private HashSet<Expense> expense;

}
