package swp.medichor.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class OrganizationResponse {
    private Integer id;
    private String username;
    private String phone;
    private String email;
    private Integer districtId;
    private String AddressDetails;
    private String name;
    private String taxCode;
    private String avatar;
    private String website;
    private String introduction;
}
