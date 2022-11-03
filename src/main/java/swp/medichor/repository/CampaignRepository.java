package swp.medichor.repository;

import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Campaign;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

    @Query("select c from Campaign c where c.organization.userId = ?1 and c.name = ?2 and c.status = true")
    List<Campaign> findByOrganizationIdAndCampaignName(Integer organizationId, String campaignName);

    @Query("SELECT c from Campaign c where c.startDate <= ?1 and c.endDate >= ?1 and c.status = true")
    List<Campaign> findAllActiveCampaigns(LocalDate now);

    @Query("SELECT c from Campaign c where c.organization.userId = ?1 and c.startDate <= ?2 and c.endDate >= ?2 and c"
            + ".status = true")
    List<Campaign> findAllActiveCampaignsByOrganizationId(Integer organizationId, LocalDate now);

    @Query("select c from Campaign c")
    List<Campaign> findAllCampaigns();

    @Query("select c from Campaign c where c.organization.userId = ?1")
    List<Campaign> findAllCampaignsByOrganizationId(Integer organizationId);

    @Query(value = "SELECT TOP 5 pro.Id, pro.Name, pro.Code, COUNT(*) AS Donations FROM Province pro\n"
            + "INNER JOIN District dis ON pro.Id = dis.ProvinceId\n"
            + "INNER JOIN Campaign cam ON dis.Id = cam.DistrictId\n"
            + "INNER JOIN DonateRecord dr ON cam.Id = dr.CampaignId\n"
            + "WHERE dr.[Status] = 1 AND dr.RegisteredDate BETWEEN ?1 AND ?2\n"
            + "GROUP BY pro.Id, pro.Name, pro.Code ORDER BY Donations DESC",
            nativeQuery = true)
    List<Map<String, Object>> findTop5Provinces(Date from, Date to);

    @Query(value = "SELECT TOP 5 org.UserId, org.Name, COUNT(*) AS Campaigns FROM Organization org\n"
            + "INNER JOIN Campaign cam ON org.UserId = cam.OrganizationId\n"
            + "WHERE cam.EndDate < GETDATE()\n"
            + "AND cam.[Status] = 1\n"
            + "AND (SELECT COUNT(*) FROM DonateRecord dr WHERE dr.CampaignId = cam.Id) >= 100\n"
            + "AND cam.EndDate BETWEEN ?1 AND ?2\n"
            + "GROUP BY org.UserId, org.Name ORDER BY Campaigns DESC",
            nativeQuery = true)
    List<Map<String, Object>> findTop5Orgs(Date from, Date to);
}
