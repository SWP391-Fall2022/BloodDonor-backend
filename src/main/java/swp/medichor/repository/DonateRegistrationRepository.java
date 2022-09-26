package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.DonateRegistration;
import swp.medichor.model.compositekey.DonateRegistrationKey;

@Repository
public interface DonateRegistrationRepository extends JpaRepository<DonateRegistration, DonateRegistrationKey> {
    
}
