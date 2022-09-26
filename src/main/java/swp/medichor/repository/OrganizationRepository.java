package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp.medichor.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

}
