package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepo extends JpaRepository<Property, Integer> {
}
