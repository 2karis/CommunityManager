package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository

public interface LeaseRepository extends JpaRepository<Lease, Integer> {
    void deleteLeaseById(int id);

    Optional<Lease> findLeaseById(int id);

//    @Query("SELECT * FROM Lease l WHERE l.termFrom <= :today AND (l.termTo IS NULL OR s.termTo >= :today)")
//    List<Lease> findActiveLeases(LocalDate today);

//    @Query("SELECT COUNT(*) FROM Lease l WHERE l.user = :userId " +
//            "AND l.termFrom <= :newEndDate AND l.termTo >= :newStartDate ")
//    int overlappingLeases(int userId, LocalDate newStartDate, LocalDate newEndDate);

}
