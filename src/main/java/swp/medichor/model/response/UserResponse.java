package swp.medichor.model.response;

import lombok.Getter;
import swp.medichor.enums.Role;
import swp.medichor.model.User;

@Getter
public class UserResponse {

    private Integer id;
    private String username;
    private String phone;
    private String email;
    private Integer districtId;
    private String addressDetails;
    private Role role;
    private Boolean status;
    private Boolean enabled;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.districtId = user.getDistrict().getId();
        this.addressDetails = user.getAddressDetails();
        this.role = user.getRole();
        this.status = user.getStatus();
        this.enabled = user.getEnabled();
    }
}
