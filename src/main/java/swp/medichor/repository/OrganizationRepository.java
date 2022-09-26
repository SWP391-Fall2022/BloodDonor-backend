package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Organization;
import swp.medichor.model.User;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
