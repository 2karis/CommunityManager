package io.siliconsavannah.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.repository.cdi.Eager;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Table(name="property")
@Entity
public @Data class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String unit;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
//    @OneToMany(mappedBy="id",fetch = FetchType.EAGER)
//    private Set<Lease> lease;
    @OneToMany(mappedBy="id" ,fetch = FetchType.EAGER)
    private Set<Expense> expense;


}
