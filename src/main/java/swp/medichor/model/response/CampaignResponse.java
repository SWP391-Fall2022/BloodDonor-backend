package swp.medichor.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CampaignResponse {
    private Integer id;
    private String name;
    private String images;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean emergency;
    private String bloodTypes;
    private Integer districtId;
    private String addressDetails;
    private String organizationName;
}
