package swp.medichor.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import swp.medichor.enums.Approve;
import swp.medichor.enums.Role;
import swp.medichor.model.Organization;

@Getter
@AllArgsConstructor
@ToString
public class OrganizationResponse {

    private Integer id;
    private String username;
    private String phone;
    private String email;
    private Integer districtId;
    private String addressDetails;
    private Role role;
    private String name;
    private String taxCode;
    private Approve approve;
    private String avatar;
    private String website;
    private String introduction;

    public OrganizationResponse(Organization org) {
        id = org.getUserId();
        username = org.getUser().getUsername();
        phone = org.getUser().getPhone();
        email = org.getUser().getEmail();
        districtId = org.getUser().getDistrict().getId();
        addressDetails = org.getUser().getAddressDetails();
        role = org.getUser().getRole();
        name = org.getName();
        taxCode = org.getTaxCode();
        approve = org.getApprove();
        avatar = org.getAvatar();
        website = org.getWebsite();
        introduction = org.getIntroduction();
    }
}
