package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.DonateRegistration;
import swp.medichor.model.compositekey.DonateRegistrationKey;

@Repository
public interface DonateRegistrationRepository extends JpaRepository<DonateRegistration, DonateRegistrationKey> {
//    @Query("SELECT count(d) FROM DonateRegistration d WHERE d.id.donorId = ?1")
    long countById_DonorId(int donorId);
}
