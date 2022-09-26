package swp.medichor.model.requests;

import lombok.Getter;
import lombok.Setter;
import swp.medichor.model.User;

@Getter
@Setter
public class RegisterOrganizationRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private String phone;
    private String email;
    private int districtId;
    private String AddressDetails;
    private User.Role role;
    private String name;
    private String taxcode;
}
