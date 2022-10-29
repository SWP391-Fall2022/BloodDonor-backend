package swp.medichor.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import swp.medichor.model.Campaign;

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
    private boolean status;

    public CampaignResponse(Campaign campaign) {
        id = campaign.getId();
        name = campaign.getName();
        images = campaign.getImages();
        description = campaign.getDescription();
        startDate = campaign.getStartDate();
        endDate = campaign.getEndDate();
        emergency = campaign.getEmergency();
        bloodTypes = campaign.getBloodTypes();
        districtId = campaign.getDistrict().getId();
        addressDetails = campaign.getAddressDetails();
        organizationName = campaign.getOrganization().getName();
        status = campaign.getStatus();
    }
}
