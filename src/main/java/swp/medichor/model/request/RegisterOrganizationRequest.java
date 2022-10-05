package swp.medichor.model.request;

import lombok.Getter;
import lombok.Setter;
import swp.medichor.enums.Role;

@Getter
@Setter
public class RegisterOrganizationRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private String phone;
    private String email;
    private int districtId;
    private String addressDetails;
    private Role role;
    private String name;
    private String taxcode;
}
