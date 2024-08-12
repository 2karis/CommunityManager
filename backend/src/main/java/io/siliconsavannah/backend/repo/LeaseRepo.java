package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface LeaseRepo extends JpaRepository<Lease, Integer> {
    void deleteLeaseById(int id);

    Optional<Lease> findLeaseById(int id);
}
