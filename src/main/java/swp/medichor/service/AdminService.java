package swp.medichor.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.enums.Approve;
import swp.medichor.model.Organization;
import swp.medichor.model.response.Response;
import swp.medichor.repository.OrganizationRepository;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Transactional
    public Response verifyOrganizationAccount(Integer id) {
        Optional<Organization> existOrganization = organizationRepository.findById(id);
        if (existOrganization.isEmpty()) return new Response(400, false, "ID not found");
        Organization organization = existOrganization.get();
        organization.setApprove(Approve.APPROVED);
        organization.getUser().setEnabled(true);
        return new Response(200, true, "Verify successfully");
    }

    @Transactional
    public Response rejectOrganizationAccount(Integer id) {
        Optional<Organization> existOrganization = organizationRepository.findById(id);
        if (existOrganization.isEmpty()) return new Response(400, false, "ID not found");
        Organization organization = existOrganization.get();
        organization.setApprove(Approve.REJECTED);
        return new Response(200, true, "Reject successfully");
    }
}
