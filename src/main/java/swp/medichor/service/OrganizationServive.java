package swp.medichor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.model.Organization;
import swp.medichor.repository.OrganizationRepository;
import swp.medichor.repository.UserRepository;

@Service
public class OrganizationServive {
    @Autowired
    private OrganizationRepository organizationRepository;

    public boolean registerOrganization(Organization organization) {
        organizationRepository.save(organization);
        return true;
    }
}
