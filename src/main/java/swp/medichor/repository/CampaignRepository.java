package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Campaign;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

    @Query("SELECT c from Campaign c where c.startDate <= ?1 and c.endDate >= ?1")
    List<Campaign> findAllActiveCampaigns(LocalDate now);

    @Query("SELECT c from Campaign c where c.organization.userId = ?1 and c.startDate <= ?2 and c.endDate >= ?2")
    List<Campaign> findAllActiveCampaignsByOrganizationId(Integer organizationId, LocalDate now);

    @Query("select c from Campaign c where c.status = true")
    List<Campaign> findAllCampaigns();

    @Query("select c from Campaign c where c.organization.userId = ?1 and c.status = true")
    List<Campaign> findAllCampaignsByOrganizationId(Integer organizationId);
}
