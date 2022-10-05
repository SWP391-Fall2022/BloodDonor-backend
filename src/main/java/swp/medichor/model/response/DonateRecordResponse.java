package swp.medichor.model.response;

import java.sql.Date;
import lombok.Getter;
import swp.medichor.model.DonateRecord;

@Getter
public class DonateRecordResponse {

    private Integer donorId;
    private Integer campaignId;
    private Date registeredDate;
    private String details;
    private Boolean status;
    private String bloodType;
    private Integer amount;
    private Float weight;

    public DonateRecordResponse(DonateRecord record) {
        donorId = record.getId().getDonorId();
        campaignId = record.getId().getCampaignId();
        registeredDate = record.getId().getRegisteredDate();
        details = record.getDetails();
        status = record.getStatus();
        bloodType = record.getBloodType();
        amount = record.getAmount();
        weight = record.getWeight();
    }
}
