package swp.medichor.model.request;

import java.sql.Date;
import lombok.Getter;
import swp.medichor.enums.Period;

@Getter
public class DonateRegistrationRequest {

    private Date registerDate;
    private Period period;
}
