package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp.medichor.model.Province;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
}
