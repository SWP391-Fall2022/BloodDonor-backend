package swp.medichor.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swp.medichor.enums.Approve;
import swp.medichor.enums.Role;
import swp.medichor.model.Organization;
import swp.medichor.model.VerificationCode;
import swp.medichor.model.response.Response;
import swp.medichor.repository.OrganizationRepository;
import swp.medichor.repository.UserRepository;
import swp.medichor.repository.VerificationCodeRepository;

@Service
public class AdminService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Autowired
    private CampaignService campaignService;

    @Transactional
    public Response verifyOrganizationAccount(Integer id) {
        Optional<Organization> existOrganization = organizationRepository.findById(id);
        if (existOrganization.isEmpty()) {
            return new Response(400, false, "ID not found");
        }
        Organization organization = existOrganization.get();
        VerificationCode code = verificationCodeRepository.findByUserId(organization.getUserId()).get();
        organization.setApprove(Approve.APPROVED);
        if (code.getConfirmed()) organization.getUser().setEnabled(true);
        return new Response(200, true, "Verify successfully");
    }

    @Transactional
    public Response rejectOrganizationAccount(Integer id) {
        Optional<Organization> existOrganization = organizationRepository.findById(id);
        if (existOrganization.isEmpty()) {
            return new Response(400, false, "ID not found");
        }
        Organization organization = existOrganization.get();
        organization.setApprove(Approve.REJECTED);
        organization.getUser().setEnabled(false);
        return new Response(200, true, "Reject successfully");
    }

    @Transactional
    public void lockUser(int userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(user -> {
                    if (user.getRole() != Role.ADMIN) {
                        user.setStatus(false);
                        if (user.getRole() == Role.ORGANIZATION) {
                            user.getOrganization().getCampaigns().forEach(c -> {
                                campaignService.closeCampaign(user, c.getId());
                            });
                        }
                    } else {
                        throw new RuntimeException("Cannot lock user with role ADMIN");
                    }
                }, () -> {
                    throw new RuntimeException("User ID not found");
                });
    }
}
