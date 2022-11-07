package swp.medichor.model.request;

import lombok.Getter;

import java.sql.Date;

@Getter
public class GetDonateRecordRequest {
    private Integer donorId;
    private Integer campaignId;
    private Date registeredDate;
}
