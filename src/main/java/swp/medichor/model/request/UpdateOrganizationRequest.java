package swp.medichor.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateOrganizationRequest {
    private String phone;
    private Integer districtId;
    private String addressDetails;
    private String name;
    private String website;
    private String introduction;

}
