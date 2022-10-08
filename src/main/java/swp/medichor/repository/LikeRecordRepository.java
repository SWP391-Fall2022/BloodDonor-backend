package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.medichor.model.LikeRecord;
import swp.medichor.model.compositekey.LikeRecordKey;

import java.util.Optional;

@Repository
public interface LikeRecordRepository extends JpaRepository<LikeRecord, LikeRecordKey> {
    @Query("select l from LikeRecord l where l.id.campaignId = ?1 and l.id.donorId = ?2")
    Optional<LikeRecord> findByCampaignIdAndDonorId(Integer campaignId, Integer donorId);

    @Query("select count(l) from LikeRecord l where l.id.campaignId = ?1")
    Integer countTotalLikeByCampaignId(Integer campaignId);
}
