package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    
}
