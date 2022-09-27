package swp.medichor.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swp.medichor.enums.Approve;
import swp.medichor.model.Organization;
import swp.medichor.repository.OrganizationRepository;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class AdminService {
    private final OrganizationRepository organizationRepository;
    @Transactional
    public String verifyOrganizationAccount(Integer id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> {
            return new IllegalStateException("ID not found");
        });
        organization.setApprove(Approve.APPROVED);
        organization.getUser().setEnabled(true);
        return "Approve successfully";
    }

    @Transactional
    public String rejectOrganizationAccount(Integer id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> {
            return new IllegalStateException("ID not found");
        });
        organization.setApprove(Approve.REJECTED);

        return "Reject successfully";
    }
}
