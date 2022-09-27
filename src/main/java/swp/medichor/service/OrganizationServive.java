package swp.medichor.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swp.medichor.model.Organization;
import swp.medichor.repository.OrganizationRepository;
import swp.medichor.repository.UserRepository;

@Service
@AllArgsConstructor
public class OrganizationServive {
    private final OrganizationRepository organizationRepository;
    public boolean registerOrganization(Organization organization) {
        organizationRepository.save(organization);
        return true;
    }
}
