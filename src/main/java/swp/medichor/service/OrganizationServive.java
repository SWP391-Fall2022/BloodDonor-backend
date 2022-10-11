package swp.medichor.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.enums.Approve;
import swp.medichor.model.Organization;
import swp.medichor.model.request.ChangePasswordRequest;
import swp.medichor.model.request.UpdateAvatarRequest;
import swp.medichor.model.request.UpdateOrganizationRequest;
import swp.medichor.model.response.OrganizationResponse;
import swp.medichor.model.response.Response;
import swp.medichor.repository.OrganizationRepository;
import swp.medichor.utils.Validator;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizationServive {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private AddressService addressService;

    public boolean registerOrganization(Organization organization) {
        organizationRepository.save(organization);
        return true;
    }

    public List<OrganizationResponse> getAllOrganizations() {
        return organizationRepository.findAll()
                .stream()
                .map(org -> new OrganizationResponse(org))
                .collect(Collectors.toList());
    }

    public Response getInfoOfOne(Organization organization) {
        if (!organization.getUser().getStatus() || !organization.getUser().getEnabled()
                || organization.getApprove().equals(Approve.PENDING) || organization.getApprove().equals(Approve.REJECTED)) {
            return new Response(403, false, "The account is disabled or unverified");
        }
        OrganizationResponse organizationInfo = new OrganizationResponse(
                organization.getUserId(),
                organization.getUser().getUsername(),
                organization.getUser().getPhone(),
                organization.getUser().getEmail(),
                organization.getUser().getDistrict().getId(),
                organization.getUser().getAddressDetails(),
                organization.getName(),
                organization.getTaxCode(),
                organization.getAvatar(),
                organization.getWebsite(),
                organization.getIntroduction()
        );
        return new Response(200, true, organizationInfo);
    }

    @Transactional
    public Response updateInfoOfOne(Integer organizationId, UpdateOrganizationRequest request) {
        Optional<Organization> isExistOrganization = organizationRepository.findById(organizationId);
        if (isExistOrganization.isEmpty()) {
            return new Response(400, false, "ID not found");
        }
        Organization organization = isExistOrganization.get();
        if (!organization.getUser().getStatus() || !organization.getUser().getEnabled()
                || organization.getApprove().equals(Approve.PENDING) || organization.getApprove().equals(Approve.REJECTED)) {
            return new Response(403, false, "The account is disabled or unverified");
        }
        if (!Validator.testPhoneNumber(request.getPhone())) {
            return new Response(400, false, "Invalid phone number");
        }

        organization.getUser().setPhone(request.getPhone());
        organization.getUser().setDistrict(addressService.getDistrictById(request.getDistrictId()));
        organization.getUser().setAddressDetails(request.getAddressDetails());
        organization.setName(request.getName());
        organization.setWebsite(request.getWebsite());
        organization.setIntroduction(request.getIntroduction());
        return new Response(200, true, "Update info successfully");
    }

//    @Transactional
//    public Response updateAvatar(Integer organizationId, String avatar) {
//        Optional<Organization> isExistOrganization = organizationRepository.findById(organizationId);
//        if (isExistOrganization.isEmpty())
//            return new Response(400, false, "ID not found");
//        Organization organization = isExistOrganization.get();
//        if (!organization.getUser().getStatus() || !organization.getUser().getEnabled()
//                || organization.getApprove().equals(Approve.PENDING) || organization.getApprove().equals(Approve.REJECTED)) {
//            return new Response(403, false, "The account is disabled or unverified");
//        }
//        organization.setAvatar(avatar);
//        return new Response(200, true, "Update avatar successfully");
//    }

}
