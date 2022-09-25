package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Integer> {
    
}
