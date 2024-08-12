package io.siliconsavannah.backend.repo;

import io.siliconsavannah.backend.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface PropertyRepo extends JpaRepository<Property, Integer> {
    Optional<Property> findPropertyById(int id);

    void deletePropertyById(int id);
}
