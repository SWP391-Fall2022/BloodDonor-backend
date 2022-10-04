package swp.medichor.model.request;

import lombok.Getter;
import lombok.Setter;
import swp.medichor.enums.Role;
import swp.medichor.enums.Sex;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterDonorRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private String phone;
    private String email;
    private int districtId;
    private String addressDetails;
    private Role role;
    private String name;
    private LocalDate birthday;
    private Sex sex;
    private String identityNum;
}
