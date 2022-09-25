package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.EarnedReward;
import swp.medichor.model.compositekey.EarnedRewardKey;

@Repository
public interface EarnedRewardRepository extends JpaRepository<EarnedReward, EarnedRewardKey> {
    
}
