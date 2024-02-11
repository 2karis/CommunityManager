package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseRepo extends JpaRepository<Lease, Integer> {
}
