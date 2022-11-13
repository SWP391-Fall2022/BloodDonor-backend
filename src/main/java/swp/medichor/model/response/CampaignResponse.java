package swp.medichor.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import swp.medichor.model.Campaign;
import swp.medichor.repository.LikeRecordRepository;

@Getter
@Setter
@AllArgsConstructor
public class CampaignResponse {

    @Autowired
    @JsonIgnore
    private LikeRecordRepository likeRecordRepository;

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
    private List<LocalDate> onSiteDates = new ArrayList<>();
    private boolean status;
    private int totalLike;
    private OrganizationResponse organization;

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
        organization = new OrganizationResponse(campaign.getOrganization());
        status = campaign.getStatus();
        if (campaign.getOnSiteDates() != null) {
            onSiteDates = Stream.of(campaign.getOnSiteDates().split(" ")).map(LocalDate::parse).collect(Collectors.toList());
            totalLike = campaign.getLikeRecord().size();
        }
    }

    @PostConstruct
    public void calTotalLike() {
        totalLike = likeRecordRepository.countTotalLikeByCampaignId(id);
    }
}
