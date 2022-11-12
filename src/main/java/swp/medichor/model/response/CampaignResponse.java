package swp.medichor.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import swp.medichor.model.Campaign;
import swp.medichor.service.CampaignService;

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
    private List<LocalDate> onSiteDates = new ArrayList<>();
    private boolean status;
    private int totalLike;


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
        if (campaign.getOnSiteDates() != null)
            onSiteDates = Stream.of(campaign.getOnSiteDates().split(" ")).map(LocalDate::parse).collect(Collectors.toList());
        totalLike = campaign.getLikeRecord().size();
    }
}
