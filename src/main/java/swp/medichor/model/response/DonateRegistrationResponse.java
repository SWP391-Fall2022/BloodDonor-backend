package swp.medichor.model.response;

import java.sql.Date;
import lombok.Getter;
import swp.medichor.enums.DonateRegistrationStatus;
import swp.medichor.enums.Period;
import swp.medichor.model.DonateRegistration;

@Getter
public class DonateRegistrationResponse {

    private Integer donorId;
    private Integer campaignId;
    private Date registeredDate;
    private DonateRegistrationStatus status;
    private Period period;
    private String code;

    public DonateRegistrationResponse(DonateRegistration registration) {
        donorId = registration.getId().getDonorId();
        campaignId = registration.getId().getCampaignId();
        registeredDate = registration.getId().getRegisteredDate();
        status = registration.getStatus();
        period = registration.getPeriod();
        code = registration.getCode();
    }
}
