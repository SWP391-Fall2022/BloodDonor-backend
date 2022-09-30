package swp.medichor.model.request;

import lombok.Getter;

@Getter
public class UpdateUserRequest {

    private String phone;
    private Integer districtId;
    private String addressDetails;
}
