package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepo extends JpaRepository<Property, Integer> {
    Optional<Property> findPropertyById(int id);

    void deletePropertyById(int id);
}
