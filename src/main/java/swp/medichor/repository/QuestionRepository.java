package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("select q from Question q where q.campaign.id = ?1")
    List<Question> findByCampaignId(Integer campaignId);

    @Query("select q from Question q where q.donor.userId = ?1")
    List<Question> findByDonorId(Integer donorId);
}
