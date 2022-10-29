package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.medichor.enums.DonateRegistrationStatus;
import swp.medichor.enums.Period;
import swp.medichor.model.DonateRegistration;
import swp.medichor.model.compositekey.DonateRegistrationKey;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface DonateRegistrationRepository extends JpaRepository<DonateRegistration, DonateRegistrationKey> {

    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and r.status <> ?2")
    List<DonateRegistration> findAllRegistration(Integer campaignId, DonateRegistrationStatus status);

    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and r.status <> ?2 "
            + "and r.id.registeredDate = ?3")
    List<DonateRegistration> findAllRegistrationAllDay(Integer campaignId,
            DonateRegistrationStatus status,
            LocalDate registeredDate);

    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and r.status <> ?2 and r.id.registeredDate "
            + "= ?3 and r.period = ?4")
    List<DonateRegistration> findAllRegistrationByPeriod(Integer campaignId,
            DonateRegistrationStatus status,
            LocalDate registeredDate,
            Period period);

//    @Query("SELECT count(d) FROM DonateRegistration d WHERE d.id.donorId = ?1")
    long countById_DonorId(int donorId);

    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and r.id.donorId = ?2 and r.status <> ?3 " +
            "and r.id.registeredDate = ?4")
    Optional<DonateRegistration> findByCampaignIdAndDonorId(Integer campaignId, Integer donorId,
            DonateRegistrationStatus status, LocalDate registeredDate);

    Optional<DonateRegistration> findById_DonorIdAndId_CampaignId(int donorId, int campaignId);

    @Modifying
    @Query("UPDATE DonateRegistration r SET r.id.registeredDate=?1, r.period=?2 WHERE r.id.donorId=?3 AND r.id.campaignId=?4 AND r.status='NOT_CHECKED_IN'")
    int updateDonateRegistration(LocalDate date, Period period, int donorId, int campaignId);

    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and (r.id.registeredDate < ?2 or r.id"
            + ".registeredDate > ?3) and r.status = ?4")
    List<DonateRegistration> findNormalByCampaignIdAndOutDate(Integer campaignId, LocalDate StartDate,
            LocalDate endDate, DonateRegistrationStatus status);

    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and r.id.registeredDate < ?2 and r.status ="
            + " ?3")
    List<DonateRegistration> findUrgentByCampaignIdAndOutDate(Integer campaignId, LocalDate StartDate,
            DonateRegistrationStatus status);

    @Query("select r from DonateRegistration r where r.id.campaignId = ?1 and r.id.registeredDate not in ?2 and r" +
            ".status = ?3")
    List<DonateRegistration> findNormalByCampaignIdAndOutOnsiteDate(Integer campaignId, List<LocalDate> onSiteDates,
                                                              DonateRegistrationStatus status);
}
