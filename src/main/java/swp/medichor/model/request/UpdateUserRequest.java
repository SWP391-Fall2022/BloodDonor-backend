package swp.medichor.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import swp.medichor.model.User;

@NoArgsConstructor
@Getter
public class UpdateUserRequest {

    private String phone;
    private Integer districtId;
    private String addressDetails;

    public UpdateUserRequest(User user) {
        this.phone = user.getPhone();
        this.districtId = user.getDistrict().getId();
        this.addressDetails = user.getAddressDetails();
    }
}
