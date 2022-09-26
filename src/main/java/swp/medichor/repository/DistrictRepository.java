package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
}
