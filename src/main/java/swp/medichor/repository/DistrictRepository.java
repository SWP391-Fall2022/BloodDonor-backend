package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp.medichor.model.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {
}
