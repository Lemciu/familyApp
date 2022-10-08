package pl.ml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ml.model.family.Family;

public interface FamilyRepository extends JpaRepository<Family, Long> {
}
