package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaseRepo extends JpaRepository<Lease, Integer> {
    void deleteLeaseById(int id);

    Optional<Lease> findLeaseById(int id);
}
