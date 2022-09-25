package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {
    
}