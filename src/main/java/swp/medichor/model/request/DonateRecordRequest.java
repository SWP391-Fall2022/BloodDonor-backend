package swp.medichor.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;


@Getter
@AllArgsConstructor
public class DonateRecordRequest {
    private Integer donorId;
    private Integer campaignId;
    private Date registeredDate;
    private String details;
    private Boolean status; //able to donate blood or not ?
    private String bloodType;
    private Integer amount;
    private Float weight; //weight of blood donation

}
