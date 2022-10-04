package swp.medichor.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateCampaignRequest {
    private String name;
    private String images;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean emergency;
    private Integer districtId;
    private String addressDetails;
}
