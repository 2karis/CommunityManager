package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.BillingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingItemRepository extends JpaRepository<BillingItem, Long> {
}