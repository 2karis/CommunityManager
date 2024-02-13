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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTermFrom() {
        return termFrom;
    }

    public void setTermFrom(Date termFrom) {
        this.termFrom = termFrom;
    }

    public Date getTermTo() {
        return termTo;
    }

    public void setTermTo(Date termTo) {
        this.termTo = termTo;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public HashSet<Income> getIncome() {
        return income;
    }

    public void setIncome(HashSet<Income> income) {
        this.income = income;
    }

    public HashSet<User> getUsers() {
        return users;
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Lease{" +
                "id=" + id +
                ", termFrom=" + termFrom +
                ", termTo=" + termTo +
                ", rent=" + rent +
                ", deposit=" + deposit +
                ", status='" + status + '\'' +
                ", file='" + file + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", property=" + property +
                ", income=" + income +
                ", users=" + users +
                '}';
    }
}
