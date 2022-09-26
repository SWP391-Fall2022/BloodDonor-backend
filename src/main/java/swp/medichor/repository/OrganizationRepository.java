package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
