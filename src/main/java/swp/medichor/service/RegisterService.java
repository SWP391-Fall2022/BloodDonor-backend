package swp.medichor.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.model.Organization;
import swp.medichor.model.User;
import swp.medichor.model.requests.RegisterOrganizationRequest;
import swp.medichor.utils.Validator;

@Service
@AllArgsConstructor
public class RegisterService {
    private final UserService userService;
    private final OrganizationServive organizationServive;
    private final AddressService addressService;
    public String registerOrganization(RegisterOrganizationRequest request) {
        if (!request.getRole().equals(User.Role.ROLE_ORGANIZATION))
            throw new IllegalStateException("Invalid role");
        if (!Validator.testEmail(request.getEmail())) {
            throw new IllegalStateException("Email not valid");
        }
        if (!Validator.testPhoneNumber(request.getPhone())) {
            throw new IllegalStateException("Phone number not valid");
        }
        if (!request.getConfirmPassword().equals(request.getPassword())) {
            throw new IllegalStateException("Confirm password not match");
        }
        User user = userService.registerUser(new User(
                0,
                request.getUsername(),
                request.getPassword(),
                request.getPhone(),
                request.getEmail(),
                addressService.getDistrictById(request.getDistrictId()),
                request.getAddressDetails(),
                request.getRole(),
                true,
                false

        ));
        Boolean saveOrganization = organizationServive.registerOrganization(new Organization(
                user,
                request.getName(),
                request.getTaxcode(),
                Organization.Approve.APPROVE_PENDING,
                null,
                null,
                null
        ));
        if (saveOrganization) return "Register successfully";
        else return "Register failed";
    }
}
