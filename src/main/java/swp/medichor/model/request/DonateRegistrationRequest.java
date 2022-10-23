package swp.medichor.model.request;

import java.time.LocalDate;
import lombok.Getter;
import swp.medichor.enums.Period;

@Getter
public class DonateRegistrationRequest {

    private int campaignId;
    private LocalDate registerDate;
    private Period period;
}
