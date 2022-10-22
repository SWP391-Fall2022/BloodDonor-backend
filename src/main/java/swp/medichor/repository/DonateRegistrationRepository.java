package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.medichor.enums.DonateRegistrationStatus;
import swp.medichor.enums.Period;
import swp.medichor.model.DonateRegistration;
import swp.medichor.model.compositekey.DonateRegistrationKey;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonateRegistrationRepository extends JpaRepository<DonateRegistration, DonateRegistrationKey> {

    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and r.status <> ?2")
    List<DonateRegistration> findAllRegistration(Integer campaignId, DonateRegistrationStatus status);
    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and r.status <> ?2 " +
            "and r.id.registeredDate = ?3")
    List<DonateRegistration> findAllRegistrationAllDay(Integer campaignId,
                                                       DonateRegistrationStatus status,
                                                       Date registeredDate);

    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and r.status <> ?2 and r.id.registeredDate " +
            "= ?3 and r.period = ?4")
    List<DonateRegistration> findAllRegistrationByPeriod(Integer campaignId,
                                                       DonateRegistrationStatus status,
                                                       Date registeredDate,
                                                       Period period);

//    @Query("SELECT count(d) FROM DonateRegistration d WHERE d.id.donorId = ?1")
    long countById_DonorId(int donorId);

    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and r.id.donorId = ?2 and r.status <> ?3")
    Optional<DonateRegistration> findByCampaignIdAndDonorId(Integer campaignId, Integer donorId,
                                                            DonateRegistrationStatus status);

    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and (r.id.registeredDate < ?2 or r.id" +
            ".registeredDate > ?3) and r.status = ?4")
    List<DonateRegistration> findNormalByCampaignIdAndOutDate(Integer campaignId, Date StartDate,
                                                        Date endDate, DonateRegistrationStatus status);
    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and r.id.registeredDate < ?2 and r.status =" +
            " ?3")
    List<DonateRegistration> findUrgentByCampaignIdAndOutDate(Integer campaignId, Date StartDate,
                                                              DonateRegistrationStatus status);
}
