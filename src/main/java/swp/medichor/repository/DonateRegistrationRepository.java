package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.medichor.enums.DonateRegistrationStatus;
import swp.medichor.enums.Period;
import swp.medichor.model.DonateRegistration;
import swp.medichor.model.compositekey.DonateRegistrationKey;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonateRegistrationRepository extends JpaRepository<DonateRegistration, DonateRegistrationKey> {
    @Query("select r from DonateRegistration r where r.campaign.id = ?1 and r.status <> ?2")
    List<DonateRegistration> findAllRegistrationAllDay(Integer campaignId, DonateRegistrationStatus status);

    @Query("select r from DonateRegistration r where r.campaign.id = ?1 and r.status <> ?2 and r.period = ?3")
    List<DonateRegistration> findAllRegistrationByPeriod(Integer campaignId,
                                                       DonateRegistrationStatus status,
                                                       Period period);

}
