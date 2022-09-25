package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    
}
