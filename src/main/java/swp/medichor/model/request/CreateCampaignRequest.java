package swp.medichor.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateCampaignRequest {
    private String name;
    private String images;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean emergency;
    private String bloodTypes;
    private Integer districtId;
    private String addressDetails;
    private boolean sendMail;
}
