package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.medichor.model.DonateRecord;
import swp.medichor.model.compositekey.DonateRecordKey;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonateRecordRepository extends JpaRepository<DonateRecord, DonateRecordKey> {

    long countById_DonorIdAndStatusTrue(int donorId);

    @Query("SELECT SUM(d.amount) FROM DonateRecord d WHERE d.id.donorId=?1")
    Integer sumOfAmountByDonorId(int donorId);

    @Query("select sum(d.amount) from DonateRecord d where d.id.campaignId = ?1")
    Integer sumOfAmountByCampaignId(int campaignId);

    @Query("select d from DonateRecord d where d.id.campaignId = ?1 and d.id.donorId = ?2 and d.id.registeredDate = ?3")
    Optional<DonateRecord> findByCampaignIdAndDonorId(Integer campaignId, Integer donorId, Date registeredDate);

    @Query("select d from DonateRecord d where d.id.campaignId = ?1")
    List<DonateRecord> findByCampaignId(Integer campaignId);

    Optional<DonateRecord> findTopById_DonorIdOrderById_RegisteredDateDesc(int id);
}
